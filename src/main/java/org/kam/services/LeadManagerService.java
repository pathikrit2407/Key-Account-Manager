package org.kam.services;

import com.mongodb.client.model.Filters;
import org.bson.conversions.Bson;
import org.kam.db.MongoWrapperService;
import org.kam.dtos.PointOfContactDto;
import org.kam.dtos.domain.RestaurantDto;
import org.kam.dtos.lead.LeadDto;
import org.kam.dtos.lead.RestaurantLeadDto;
import org.kam.enums.Domain;
import org.kam.enums.Status;
import org.kam.mapper.RestaurantMapper;
import org.kam.req.RestaurantReqBody;
import org.kam.res.CommonResponse;
import org.kam.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LeadManagerService {

    private final Logger logger = LoggerFactory.getLogger(LeadManagerService.class);

    private final RestaurantMapper mapper;
    private final MongoWrapperService<RestaurantDto> restaurantMongoWrapperService;
    private final MongoWrapperService<PointOfContactDto> pocMongoWrapperService;
    private final MongoWrapperService<RestaurantLeadDto> leadMongoWrapperService;

    public LeadManagerService(RestaurantMapper mapper, MongoWrapperService<RestaurantDto> restaurantMongoWrapperService,
                              MongoWrapperService<PointOfContactDto> pocMongoWrapperService, MongoWrapperService<RestaurantLeadDto> leadMongoWrapperService) {
        this.mapper = mapper;
        this.restaurantMongoWrapperService = restaurantMongoWrapperService;
        this.pocMongoWrapperService = pocMongoWrapperService;
        this.leadMongoWrapperService = leadMongoWrapperService;
    }

    public CommonResponse postRestaurantLead(RestaurantReqBody restaurantReqBody) {
        CommonResponse commonResponse = new CommonResponse();
        try {
            RestaurantDto restaurantDto = mapper.toRestaurantDto(restaurantReqBody);
            RestaurantLeadDto leadDto = getLeadForRestaurant(restaurantDto);
            List<PointOfContactDto> pocDto = mapper.toPointOfContactDtos(restaurantReqBody, leadDto);

            //TODO: Push data into restaurant_db, poc_db, lead_db
            restaurantMongoWrapperService.insertOne(Constants.RESTAURANTS, restaurantDto, RestaurantDto.class);
            pocMongoWrapperService.insertMany(Constants.POCS, pocDto, PointOfContactDto.class);
            leadMongoWrapperService.insertOne(Constants.LEADS, leadDto, RestaurantLeadDto.class);

            logger.info("Lead Id : {}, Restaurant id: {}", leadDto.getId(), restaurantDto.getId());
        } catch (Exception e) {
            commonResponse.setSuccessfulEvent(false);
            logger.error("Error occurred : ", e);
        }

        return commonResponse;
    }

    public CommonResponse<RestaurantLeadDto> getLeadStatus(String leadId) {

        CommonResponse<RestaurantLeadDto> commonResponse = new CommonResponse<>();

        try {
            Optional<RestaurantLeadDto> restaurantLeadDto = leadMongoWrapperService.find(Constants.LEADS, filterForFetchingLead(leadId),
                    RestaurantLeadDto.class);

            if (restaurantLeadDto.isPresent()) {
                commonResponse.setData(List.of(restaurantLeadDto.get()));
            } else {
                commonResponse.setSuccessfulEvent(false);
            }

            logger.info("Info fetched with lead id {} : {}", leadId, commonResponse.getData());
        } catch (Exception e) {
            commonResponse.setSuccessfulEvent(false);
            logger.error("Error occurred : ", e);
        }

        return commonResponse;
    }

    private Bson filterForFetchingLead(String leadId) {
        return Filters.and(
                Filters.eq("_id", leadId)
        );
    }

    private RestaurantLeadDto getLeadForRestaurant(RestaurantDto restaurantDto) {
        RestaurantLeadDto lead = new RestaurantLeadDto();
        lead.setId(UUID.randomUUID().toString());
        lead.setRestaurantId(restaurantDto.getId());
        lead.setCalls(List.of());
        lead.setDomain(Domain.FOOD);
        lead.setStatus(Status.TODO);

        return lead;
    }

}

package org.kam.services;

import org.kam.db.MongoWrapperService;
import org.kam.dtos.PointOfContactDto;
import org.kam.dtos.domain.RestaurantDto;
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

@Service
public class LeadManagerService {

    //TODO: Create Next Value Service for MongoDB

    private Logger logger = LoggerFactory.getLogger(LeadManagerService.class);
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

    private RestaurantLeadDto getLeadForRestaurant(RestaurantDto restaurantDto) {
        RestaurantLeadDto lead = new RestaurantLeadDto();
        lead.setId(11);//TODO: Add via next val service
        lead.setRestaurantId(restaurantDto.getId());
        lead.setCalls(List.of());
        lead.setDomain(Domain.FOOD);
        lead.setStatus(Status.TODO);

        return lead;
    }

}

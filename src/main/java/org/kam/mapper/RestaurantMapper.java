package org.kam.mapper;

import org.kam.dtos.PointOfContactDto;
import org.kam.dtos.domain.RestaurantDto;
import org.kam.dtos.lead.LeadDto;
import org.kam.req.RestaurantReqBody;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class RestaurantMapper {

    public RestaurantDto toRestaurantDto(RestaurantReqBody reqBody) {
        RestaurantDto restaurantDto = new RestaurantDto();
        restaurantDto.setId(UUID.randomUUID().toString());
        restaurantDto.setName(reqBody.getName());
        restaurantDto.setAddress(reqBody.getAddress());
        restaurantDto.setEmail(reqBody.getEmail());
        restaurantDto.setRating(reqBody.getRating());
        restaurantDto.setPhoneNumber(reqBody.getPhoneNumber());
        return restaurantDto;
    }

    public List<PointOfContactDto> toPointOfContactDtos(RestaurantReqBody reqBody, LeadDto leadDto) {
        return reqBody.getPointOfContactList().stream()
                .map(req -> toPointOfContactDto(req, leadDto))
                .toList();
    }

    private PointOfContactDto toPointOfContactDto(RestaurantReqBody.PointOfContactReqBody reqBody, LeadDto leadDto) {
        PointOfContactDto dto = new PointOfContactDto();
        dto.setId(UUID.randomUUID().toString());
        dto.setName(reqBody.getName());
        dto.setEmail(reqBody.getEmail());
        dto.setPhoneNumber(reqBody.getPhoneNumber());
        dto.setDesignation(reqBody.getDesignation());
        dto.setLeadId(leadDto.getId());
        return dto;
    }
}

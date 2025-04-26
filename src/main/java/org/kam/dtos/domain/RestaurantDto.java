package org.kam.dtos.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.kam.dtos.PointOfContactDto;

import java.util.List;

@Data
public class RestaurantDto {

    private Integer id;
    private String name;
    private String address;
    @JsonProperty("phone_number")
    private String phoneNumber;
    private String email;
    private Integer rating;
    //Lead can have multiple POCs
    @JsonProperty("point_of_contacts")
    private List<PointOfContactDto> pocList;
}

package org.kam.dtos.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RestaurantDto {

    private String id;
    private String name;
    private String address;
    @JsonProperty("phone_number")
    private String phoneNumber;
    private String email;
    private Integer rating;
}

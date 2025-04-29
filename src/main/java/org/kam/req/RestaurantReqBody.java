package org.kam.req;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

import java.util.List;

@Data
public class RestaurantReqBody {

    @JsonProperty("restaurant_name")
    private String name;

    @JsonProperty("restaurant_address")
    private String address;

    @JsonProperty("restaurant_phone_number")
    private String phoneNumber;

    @JsonProperty("restaurant_email")
    private String email;

    @JsonProperty("restaurant_rating")
    private Integer rating;

    @JsonProperty("point_of_contacts")
    private List<PointOfContactReqBody> pointOfContactList;

    @Data
    public static class PointOfContactReqBody {

        @JsonProperty("poc_name")
        private String name;

        @JsonProperty("poc_designation")
        private String designation;

        @JsonProperty("poc_email")
        private String email;

        @JsonProperty("poc_phone_number")
        private String phoneNumber;
    }
}

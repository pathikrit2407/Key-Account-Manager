package org.kam.dtos.lead;

import lombok.Data;

@Data
public class RestaurantLeadDto extends LeadDto {
    private Integer restaurantId;
}

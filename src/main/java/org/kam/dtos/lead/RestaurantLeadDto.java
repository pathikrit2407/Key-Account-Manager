package org.kam.dtos.lead;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class RestaurantLeadDto extends LeadDto {
    private String restaurantId;
}

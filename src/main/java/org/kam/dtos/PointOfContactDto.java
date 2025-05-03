package org.kam.dtos;

import lombok.Data;

@Data
public class PointOfContactDto {
    private String id;
    private String name;
    private String designation;
    private String email;
    private String phoneNumber;
    private String leadId;
}

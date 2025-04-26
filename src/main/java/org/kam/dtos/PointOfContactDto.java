package org.kam.dtos;

import lombok.Data;

@Data
public class PointOfContactDto {
    private Integer id;
    private String name;
    private String designation;
    private String email;
    private String phoneNumber;
}

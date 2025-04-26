package org.kam.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CallDto {
    private Integer id;
    private Integer contactDetailsId;
    private Integer kamId;
    private LocalDate callDate;
}

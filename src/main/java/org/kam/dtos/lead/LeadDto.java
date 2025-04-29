package org.kam.dtos.lead;

import lombok.Data;
import org.kam.dtos.CallDto;
import org.kam.enums.Domain;
import org.kam.enums.Status;

import java.util.List;

@Data
public class LeadDto {
    private Integer id;
    private Domain domain;
    private Status status;
    private List<CallDto> calls;
}

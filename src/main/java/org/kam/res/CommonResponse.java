package org.kam.res;

import lombok.Data;

import java.util.List;

@Data
public class CommonResponse<T> {
    private Boolean successfulEvent = true;
    private List<T> data;
}

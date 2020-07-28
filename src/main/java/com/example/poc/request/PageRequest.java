package com.example.poc.request;

import java.util.*;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class PageRequest {
    @ApiModelProperty(name = "pageNumber", dataType = "Integer", example = "0")
    private Integer pageNumber;
    @ApiModelProperty(name = "pageSize", dataType = "Integer", example = "10")
    private Integer pageSize;
    @ApiModelProperty(name = "sortColumns", dataType = "List", example = "[\"ticketNumber\", \"flightNo\", \"aircraftCode\"]")
    private List<String> sortColumns;
    private List<Filter> filters;
}
package com.example.poc.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Filter {
    @ApiModelProperty(name = "column", dataType = "String", example = "aircraftCode")
    private String column;
    @ApiModelProperty(name = "column", dataType = "String", example = "SU9")
    private String filtervalue;
    @ApiModelProperty(name = "column", dataType = "String", example = "and")
    private String operation;
    
}
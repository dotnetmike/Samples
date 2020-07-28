package com.example.poc.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketResultGrouping {
    private String groupBy;
    private List<TicketGroupItem> result;
}
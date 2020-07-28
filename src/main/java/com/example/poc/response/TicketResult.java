package com.example.poc.response;

import java.util.List;

import com.example.poc.entity.Ticket;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketResult {
    private Long totalElements;
    private Integer totalPages;
    List<Ticket> result;
}
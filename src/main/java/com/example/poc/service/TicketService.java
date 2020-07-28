package com.example.poc.service;

import java.util.List;
import java.util.Map;

import com.example.poc.entity.Ticket;
import com.example.poc.request.Filter;
import com.example.poc.response.TicketResult;

import org.springframework.data.domain.Page;



public interface TicketService {
    Page<Ticket> findAll(Integer pageNumber, Integer pageSize, List<String> sortOrder);
    TicketResult findAllAdvanced(Integer pageNumber, Integer pageSize, List<Filter> filters, List<String> sortOrder);
	Map<String, Long> findAllAdvancedGrouping(String groupBy, Integer pageNumber, Integer pageSize, List<Filter> filters,
			List<String> sortColumns);
}
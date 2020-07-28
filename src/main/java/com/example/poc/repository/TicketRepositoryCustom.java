package com.example.poc.repository;

import java.util.List;
import java.util.Map;

import com.example.poc.request.Filter;
import com.example.poc.response.TicketResult;

import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepositoryCustom {
    TicketResult findAllAdvance(Integer pageNumber, Integer pageSize, List<Filter> filters, List<String> sortOrder);
    Map<String, Long> findAllAdvancedGrouping(String groupBy, Integer pageNumber, Integer pageSize, List<Filter> filters, List<String> sortColumns);
}
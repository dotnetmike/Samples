package com.example.poc.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.example.poc.entity.Ticket;
import com.example.poc.repository.TicketRepository;
import com.example.poc.repository.TicketRepositoryCustom;
import com.example.poc.request.Filter;
import com.example.poc.response.TicketResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketRepository repository;

    @Autowired
    private TicketRepositoryCustom repositoryCustom;

    @Override
    public Page<Ticket> findAll(Integer pageNumber, Integer pageSize, List<String> sortOrder) {
        TicketResult result = new TicketResult();
        Page<Ticket> pagedResult;
        Sort sorting = Sort.unsorted();
        if (sortOrder != null && sortOrder.size() > 0) {
            List<Order> orders = sortOrder.stream().map(e -> Order.asc(e)).collect(Collectors.toList());

            sorting = Sort.by(orders);
        }

        pagedResult = repository.findAll(PageRequest.of(pageNumber, pageSize, sorting));
        result.setResult(pagedResult.toList());
        result.setTotalElements(pagedResult.getTotalElements());
        result.setTotalPages(pagedResult.getTotalPages());

        return pagedResult;
    }

    @Override
    public TicketResult findAllAdvanced(Integer pageNumber, Integer pageSize, List<Filter> filters,
            List<String> sortOrder) {
        return repositoryCustom.findAllAdvance(pageNumber, pageSize, filters, sortOrder);
    }

    @Override
    public Map<String, Long> findAllAdvancedGrouping(String groupBy, Integer pageNumber, Integer pageSize,
            List<Filter> filters, List<String> sortColumns) {
        
        return repositoryCustom.findAllAdvancedGrouping(groupBy, pageNumber, pageSize, filters, sortColumns);
    }
}
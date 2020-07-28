package com.example.graphql;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.poc.entity.Ticket;
import com.example.poc.request.PageRequest;
import com.example.poc.response.TicketGroupItem;
import com.example.poc.response.TicketResult;
import com.example.poc.response.TicketResultGrouping;
import com.example.poc.service.TicketService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TicketQuery implements GraphQLQueryResolver {
    
    @Autowired
    private TicketService ticketService;

    public TicketResult getTickets(PageRequest request) {
        return ticketService.findAllAdvanced(request.getPageNumber(), request.getPageSize(), 
        request.getFilters(), request.getSortColumns());
    }

    public Ticket getTicket(final String ticketNumber ) {
        TicketResult result = ticketService.findAllAdvanced(0, 10, null, null);
        return result.getResult().get(0);
    }

    public TicketResultGrouping getTicketGrouping(String groupBy, PageRequest request ) {
        TicketResultGrouping result = new TicketResultGrouping();
        List<TicketGroupItem> items = new ArrayList<>();
        Map<String, Long> queryResult = ticketService.findAllAdvancedGrouping(groupBy, request.getPageNumber(), request.getPageSize(), 
            request.getFilters(), request.getSortColumns());
        
        result.setGroupBy(groupBy);
        result.setResult(items);
        queryResult.forEach((k,v) -> items.add(new TicketGroupItem(k, v)));
        

        return result;
    }
}
package com.example.poc.controller;

import com.example.poc.service.TicketService;

import java.util.Map;

import com.example.poc.request.PageRequest;
import com.example.poc.response.TicketResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TicketController {
    
    @Autowired
    private TicketService service;

    @PostMapping(path = "all")
    public ResponseEntity<TicketResult> findAllAdvanced(@RequestBody PageRequest page) {
        return ResponseEntity.ok(service.findAllAdvanced(page.getPageNumber(), page.getPageSize(), page.getFilters(), page.getSortColumns()));
    }
    
    @PostMapping(path = "all-group")
    public ResponseEntity<Map<String, Long>> findAllAdvancedGrouping(@RequestBody PageRequest page, @RequestParam String groupBy) {
        return ResponseEntity.ok(service.findAllAdvancedGrouping(groupBy, page.getPageNumber(), page.getPageSize(), page.getFilters(), page.getSortColumns()));
    }
}
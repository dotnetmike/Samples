package com.example.poc.repository;

import com.example.poc.entity.Ticket;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends PagingAndSortingRepository<Ticket, Integer> {
    Page<Ticket> findByFlightId(TicketSpecification spec, Integer flightId, Pageable pageable);
}
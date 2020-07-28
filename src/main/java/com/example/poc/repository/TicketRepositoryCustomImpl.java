package com.example.poc.repository;

import java.util.*;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.example.poc.entity.Ticket;
import com.example.poc.response.TicketResult;
import com.example.poc.request.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.Order;


@Repository
public class TicketRepositoryCustomImpl implements TicketRepositoryCustom {

    @Autowired
    EntityManager em;

    public TicketResult findAllAdvance(Integer pageNumber, Integer pageSize, List<Filter> filters, List<String> sortOrder) {
        TicketResult result = new TicketResult();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Ticket> cq = cb.createQuery(Ticket.class);
        Root<Ticket> ticketRoot = cq.from(Ticket.class);
        
        result.setTotalElements( getResultCount(filters) );

        if (sortOrder != null && sortOrder.size() > 0) {
            List<Order> orders = sortOrder.stream().map(e -> cb.asc(ticketRoot.get(e))).collect(Collectors.toList()); //Can be clever here by making custom SortOrder class and specify sort direction (ASC/DESC)
            cq.orderBy(orders);
        }

        cq.select(ticketRoot);
        Predicate filter = toPredicate(ticketRoot, cq, cb, filters);
        if (filter != null) {
            cq.where(filter);
        }

        List<Ticket> queryResult = em.createQuery(cq)
            .setFirstResult(pageNumber * pageSize)
            .setMaxResults(pageSize)
            .getResultList();
        result.setResult(queryResult);
        result.setTotalPages((int) (result.getTotalElements() / pageSize + 1));
        return result;
    }

    private Long getResultCount(List<Filter> filters) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaCount = cb.createQuery(Long.class);
        Root<Ticket> ticketRootCount = criteriaCount.from(Ticket.class);
        criteriaCount.select(cb.count(ticketRootCount));
        Predicate filter = toPredicate(ticketRootCount, criteriaCount, cb, filters);
        if (filter != null) {
            criteriaCount.where(filter);
        }
        return em.createQuery(criteriaCount).getSingleResult();
    }

    private Predicate toPredicate(Root<Ticket> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder, List<Filter> filters) {
        Predicate effectivePredicate = null;

        if (filters != null && filters.size() > 0) {
            for (Filter item : filters) {
                if (item.getOperation().equalsIgnoreCase("and")) {
                    if (effectivePredicate == null) {
                        effectivePredicate = criteriaBuilder.and(
                            criteriaBuilder.equal(root.get(item.getColumn()), item.getFiltervalue()));
                    } else {
                        effectivePredicate = criteriaBuilder.and(effectivePredicate, 
                            criteriaBuilder.equal(root.get(item.getColumn()), item.getFiltervalue()));
                    }
                } else {
                    if (effectivePredicate == null) {
                        effectivePredicate = criteriaBuilder.or(
                            criteriaBuilder.equal(root.get(item.getColumn()), item.getFiltervalue()));
                    } else {
                        effectivePredicate = criteriaBuilder.or(effectivePredicate, 
                            criteriaBuilder.equal(root.get(item.getColumn()), item.getFiltervalue()));
                    }
                }
            }
        } 
        return effectivePredicate;
    }

    
	public Map<String, Long> findAllAdvancedGrouping(String groupBy, Integer pageNumber, Integer pageSize, List<Filter> filters, List<String> sortColumns) {
        Map<String, Long> result = new HashMap<>();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Object[]> cq = cb.createQuery(Object[].class);
        Root<Ticket> ticketRoot = cq.from(Ticket.class);
        
        cq.multiselect(ticketRoot.get(groupBy), cb.count(ticketRoot));
        cq.groupBy(ticketRoot.get(groupBy));
        Predicate filter = toPredicate(ticketRoot, cq, cb, filters);
        if (filter != null) {
            cq.where(filter);
        }

        //Can implement paging here if required
        em.createQuery(cq).getResultList().forEach((item) -> {
            result.put((String)item[0], (Long)item[1]);
        } );

        return result;
	}
}
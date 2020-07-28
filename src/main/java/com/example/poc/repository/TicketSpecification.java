package com.example.poc.repository;


import java.util.List;
import java.util.ArrayList;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.example.poc.entity.Ticket;
import com.example.poc.request.Filter;

import org.springframework.data.jpa.domain.Specification;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketSpecification implements Specification<Ticket> {
    private static final long serialVersionUID = -7564896112256770126L;
    private List<Filter> filters;

    @Override
    public Predicate toPredicate(Root<Ticket> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicateList = new ArrayList<>();

        if (filters != null && filters.size() > 0) {
            for (Filter item : filters) {
                if (item.getOperation() == "and") {
                    predicateList.add(criteriaBuilder.and(
                        criteriaBuilder.equal(root.get(item.getColumn()), item.getFiltervalue())
                    ));
                } else {
                    predicateList.add(criteriaBuilder.or(
                        criteriaBuilder.equal(root.get(item.getColumn()), item.getFiltervalue())
                    ));
                }
            }
        }

        return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
    }
    
}
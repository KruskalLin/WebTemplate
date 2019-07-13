package stg.template.template.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;
import stg.template.template.entity.User;
import stg.template.template.payloads.FilterRequest;

import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class UserSpecification implements Specification<User> {

    private FilterRequest filter;

    public UserSpecification(FilterRequest filter) {
        this.filter = filter;
    }

    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.like(root.get("username"), "%" + filter.getTitle() + "%"));
        return builder.and(toPredicateArray(predicates));
    }

    private Predicate[] toPredicateArray(List<Predicate> predicates) {
        Predicate[] p = new Predicate[predicates.size()];
        return predicates.toArray(p);
    }
}

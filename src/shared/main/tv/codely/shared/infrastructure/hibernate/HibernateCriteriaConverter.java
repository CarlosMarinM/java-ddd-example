package tv.codely.shared.infrastructure.hibernate;

import tv.codely.shared.domain.criteria.Criteria;
import tv.codely.shared.domain.criteria.Filter;
import tv.codely.shared.domain.criteria.FilterOperator;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.HashMap;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public final class HibernateCriteriaConverter<T> {

    private final CriteriaBuilder builder;
    private final HashMap<FilterOperator, BiFunction<Filter, Root<T>, Predicate>> predicateTransformers = new HashMap<FilterOperator, BiFunction<Filter, Root<T>, Predicate>>() {{
        this.put(FilterOperator.EQUAL, HibernateCriteriaConverter.this::equalsPredicateTransformer);
        this.put(FilterOperator.NOT_EQUAL, HibernateCriteriaConverter.this::notEqualsPredicateTransformer);
        this.put(FilterOperator.GT, HibernateCriteriaConverter.this::greaterThanPredicateTransformer);
        this.put(FilterOperator.LT, HibernateCriteriaConverter.this::lowerThanPredicateTransformer);
        this.put(FilterOperator.CONTAINS, HibernateCriteriaConverter.this::containsPredicateTransformer);
        this.put(FilterOperator.NOT_CONTAINS, HibernateCriteriaConverter.this::notContainsPredicateTransformer);
    }};

    public HibernateCriteriaConverter(CriteriaBuilder builder) {
        this.builder = builder;
    }

    public CriteriaQuery<T> convert(Criteria criteria, Class<T> aggregateClass) {
        final CriteriaQuery<T> hibernateCriteria = this.builder.createQuery(aggregateClass);
        final Root<T> root = hibernateCriteria.from(aggregateClass);

        hibernateCriteria.where(this.formatPredicates(criteria.getFilters().getFilters(), root));

        if (criteria.getOrder().hasOrder()) {
            final Path<Object> orderBy = root.get(criteria.getOrder().orderBy().value());
            final Order order = criteria.getOrder().orderType().isAsc() ? this.builder.asc(orderBy) : this.builder.desc(orderBy);

            hibernateCriteria.orderBy(order);
        }

        return hibernateCriteria;
    }

    private Predicate[] formatPredicates(List<Filter> filters, Root<T> root) {
        final List<Predicate> predicates = filters.stream()
            .map(filter -> this.formatPredicate(filter, root))
            .collect(Collectors.toList());

        Predicate[] predicatesArray = new Predicate[predicates.size()];
        predicatesArray = predicates.toArray(predicatesArray);

        return predicatesArray;
    }

    private Predicate formatPredicate(Filter filter, Root<T> root) {
        final BiFunction<Filter, Root<T>, Predicate> transformer = this.predicateTransformers.get(filter.getOperator());

        return transformer.apply(filter, root);
    }

    private Predicate equalsPredicateTransformer(Filter filter, Root<T> root) {
        return this.builder.equal(root.get(filter.getField().value()), filter.getValue().value());
    }

    private Predicate notEqualsPredicateTransformer(Filter filter, Root<T> root) {
        return this.builder.notEqual(root.get(filter.getField().value()), filter.getValue().value());
    }

    private Predicate greaterThanPredicateTransformer(Filter filter, Root<T> root) {
        return this.builder.greaterThan(root.get(filter.getField().value()), filter.getValue().value());
    }

    private Predicate lowerThanPredicateTransformer(Filter filter, Root<T> root) {
        return this.builder.lessThan(root.get(filter.getField().value()), filter.getValue().value());
    }

    private Predicate containsPredicateTransformer(Filter filter, Root<T> root) {
        return this.builder.like(root.get(filter.getField().value()), String.format("%%%s%%", filter.getValue().value()));
    }

    private Predicate notContainsPredicateTransformer(Filter filter, Root<T> root) {
        return this.builder.notLike(root.get(filter.getField().value()), String.format("%%%s%%", filter.getValue().value()));
    }
}

package tv.codely.backoffice.courses.application.search_by_criteria;

import lombok.RequiredArgsConstructor;
import tv.codely.backoffice.courses.application.BackofficeCoursesResponse;
import tv.codely.shared.domain.Service;
import tv.codely.shared.domain.bus.query.QueryHandler;
import tv.codely.shared.domain.criteria.Filters;
import tv.codely.shared.domain.criteria.Order;

@Service
@RequiredArgsConstructor
public final class SearchBackofficeCoursesByCriteriaQueryHandler implements QueryHandler<SearchBackofficeCoursesByCriteriaQuery, BackofficeCoursesResponse> {

    private final BackofficeCoursesByCriteriaSearcher searcher;

    @Override
    public BackofficeCoursesResponse ask(SearchBackofficeCoursesByCriteriaQuery query) {
        final Filters filters = Filters.fromValues(query.getFilters());
        final Order order = Order.fromValues(query.getOrderBy(), query.getOrderType());

        return this.searcher.search(filters, order, query.getLimit(), query.getOffset());
    }

}

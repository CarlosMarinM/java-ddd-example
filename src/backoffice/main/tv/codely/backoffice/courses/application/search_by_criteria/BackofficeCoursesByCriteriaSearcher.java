package tv.codely.backoffice.courses.application.search_by_criteria;

import lombok.RequiredArgsConstructor;
import tv.codely.backoffice.courses.application.BackofficeCoursesResponse;
import tv.codely.backoffice.courses.domain.BackofficeCourseRepository;
import tv.codely.shared.domain.Service;
import tv.codely.shared.domain.criteria.Criteria;
import tv.codely.shared.domain.criteria.Filters;
import tv.codely.shared.domain.criteria.Order;

@Service
@RequiredArgsConstructor
public class BackofficeCoursesByCriteriaSearcher {

    private final BackofficeCourseRepository repository;

    public BackofficeCoursesResponse search(Filters filters, Order order, Integer limit, Integer offset) {
        final Criteria criteria = new Criteria(filters, order, limit, offset);

        return new BackofficeCoursesResponse(this.repository.matching(criteria));
    }
}

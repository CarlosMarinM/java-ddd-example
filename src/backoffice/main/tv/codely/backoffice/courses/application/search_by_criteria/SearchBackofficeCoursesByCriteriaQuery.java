package tv.codely.backoffice.courses.application.search_by_criteria;

import lombok.Value;
import tv.codely.backoffice.courses.application.BackofficeCoursesResponse;
import tv.codely.shared.domain.bus.query.Query;

import java.util.List;
import java.util.Map;

@Value
public class SearchBackofficeCoursesByCriteriaQuery implements Query<BackofficeCoursesResponse> {

    List<Map<String, String>> filters;
    String orderBy;
    String orderType;
    Integer limit;
    Integer offset;
}

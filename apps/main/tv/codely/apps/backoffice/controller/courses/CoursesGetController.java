package tv.codely.apps.backoffice.controller.courses;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tv.codely.apps.backoffice.controller.courses.dto.BackofficeCoursesDto;
import tv.codely.apps.backoffice.controller.courses.mapper.BackofficeCourseMapper;
import tv.codely.backoffice.courses.application.search_all.SearchAllBackofficeCoursesQuery;
import tv.codely.backoffice.courses.application.search_by_criteria.SearchBackofficeCoursesByCriteriaQuery;
import tv.codely.shared.domain.bus.query.QueryBus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequiredArgsConstructor
public class CoursesGetController {

    private final QueryBus queryBus;
    private final BackofficeCourseMapper responseMapper;

    @GetMapping("/courses")
    public ResponseEntity<BackofficeCoursesDto> searchAllCourses() {
        final var response = this.queryBus.ask(new SearchAllBackofficeCoursesQuery());
        return ResponseEntity.ok(this.responseMapper.toBackofficeCoursesDto(response));
    }

    @GetMapping("/coursesByFilter")
    public ResponseEntity<BackofficeCoursesDto> searchCoursesByFilter(@RequestParam String search, String orderBy, String order, Integer limit, Integer offset) {
        final var response = this.queryBus.ask(new SearchBackofficeCoursesByCriteriaQuery(
            this.parseFilters(search),
            orderBy,
            order,
            limit,
            offset)
        );
        return ResponseEntity.ok(this.responseMapper.toBackofficeCoursesDto(response));
    }

    private List<Map<String, String>> parseFilters(String search) {
        final List<Map<String, String>> filters = new ArrayList<>();
        final Pattern pattern = Pattern.compile("(\\w+?)( NOT_IN | IN | !: | : | < | > )(\\w+?),");
        final Matcher matcher = pattern.matcher(search + ",");

        while (matcher.find()) {
            filters.add(Map.of(
                "field", matcher.group(1),
                "operator", matcher.group(2).trim(),
                "value", matcher.group(3)
            ));
        }

        return filters;
    }
}

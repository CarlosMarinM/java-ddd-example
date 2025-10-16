package tv.codely.backoffice.courses.application;

import lombok.Value;
import tv.codely.backoffice.courses.domain.BackofficeCourse;
import tv.codely.shared.domain.bus.query.Response;

import java.util.List;

@Value
public class BackofficeCoursesResponse implements Response {
    List<BackofficeCourse> courses;
}

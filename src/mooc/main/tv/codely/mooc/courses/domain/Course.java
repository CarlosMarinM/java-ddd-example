package tv.codely.mooc.courses.domain;

import lombok.EqualsAndHashCode;
import lombok.Value;
import tv.codely.shared.domain.AggregateRoot;
import tv.codely.shared.domain.course.CourseCreatedDomainEvent;

@EqualsAndHashCode(callSuper = false)
@Value
public class Course extends AggregateRoot {
    CourseId id;
    CourseName name;
    CourseDuration duration;

    public static Course create(final CourseId id, final CourseName name, final CourseDuration duration) {
        final var course = new Course(id, name, duration);

        course.register(new CourseCreatedDomainEvent(id.value(), name.value(), duration.value()));

        return course;
    }
}

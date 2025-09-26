package tv.codely.mooc.courses.application.create;

import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import tv.codely.mooc.courses.application.CoursesModuleUnitTestCase;
import tv.codely.mooc.courses.domain.Course;
import tv.codely.mooc.courses.domain.CourseCreatedDomainEvent;
import tv.codely.mooc.courses.domain.CourseDuration;
import tv.codely.mooc.courses.domain.CourseId;
import tv.codely.mooc.courses.domain.CourseName;

import java.util.List;

import static org.instancio.Select.field;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

final class CourseCreatorTest extends CoursesModuleUnitTestCase {

    @InjectMocks
    private CourseCreator target;

    @Test
    void create_a_valid_course() {
        final var createCourseRequest = Instancio.of(CreateCourseRequest.class)
            .generate(field(CreateCourseRequest::getId), gen -> gen.text().uuid())
            .create();
        final var course = new Course(
            CourseId.of(createCourseRequest.getId()),
            new CourseName(createCourseRequest.getName()),
            new CourseDuration(createCourseRequest.getDuration())
        );
        final var expectedEvent = new CourseCreatedDomainEvent(
            course.getId().value(),
            course.getName().value(),
            course.getDuration().value()
        );

        this.target.create(createCourseRequest);

        verify(this.repository, atLeastOnce()).save(course);
        verify(this.eventBus, atLeastOnce()).publish(List.of(expectedEvent));
    }
}

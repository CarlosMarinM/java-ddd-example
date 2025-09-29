package tv.codely.mooc.courses.application.create;

import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
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

final class CreateCourseCommandHandlerTest extends CoursesModuleUnitTestCase {

    @InjectMocks
    private CreateCourseCommandHandler target;

    @BeforeEach
    void setUp() {
        this.target = new CreateCourseCommandHandler(new CourseCreator(this.repository, this.eventBus));
    }

    @Test
    void create_a_valid_course() {
        final var command = Instancio.of(CreateCourseCommand.class)
            .generate(field(CreateCourseCommand::getId), gen -> gen.text().uuid())
            .create();
        final var course = new Course(
            CourseId.of(command.getId()),
            new CourseName(command.getName()),
            new CourseDuration(command.getDuration())
        );
        final var expectedEvent = new CourseCreatedDomainEvent(
            course.getId().value(),
            course.getName().value(),
            course.getDuration().value()
        );

        this.target.handle(command);

        verify(this.repository, atLeastOnce()).save(course);
        verify(this.eventBus, atLeastOnce()).publish(List.of(expectedEvent));
    }
}

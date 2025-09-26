package tv.codely.mooc.courses.infrastructure.persistence.adapter;

import org.junit.jupiter.api.Test;
import tv.codely.mooc.courses.domain.Course;
import tv.codely.mooc.courses.domain.CourseId;
import tv.codely.mooc.courses.infrastructure.CoursesModuleInfrastructureTestCase;

import java.util.Optional;

import static org.instancio.Instancio.create;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

final class CourseAdapterRepositoryTest extends CoursesModuleInfrastructureTestCase {

    @Test
    void givenACourse_whenItIsSaved_thenItDoesNotThrowAnException() {
        final var course = create(Course.class);

        assertDoesNotThrow(() -> this.target.save(course));
    }

    @Test
    void givenASavedCourse_whenSearchIt_thenItReturnsTheCourse() {
        final var course = create(Course.class);

        this.target.save(course);

        assertEquals(Optional.of(course), this.target.search(course.getId()));
    }

    @Test
    void givenANonExistingCourse_whenSearchIt_thenItDoesNotReturnAnyCourse() {
        assertFalse(this.target.search(create(CourseId.class)).isPresent());
    }
}

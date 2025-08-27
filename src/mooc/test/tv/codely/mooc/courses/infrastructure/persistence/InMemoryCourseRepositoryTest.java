package tv.codely.mooc.courses.infrastructure.persistence;

import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import tv.codely.mooc.courses.CoursesModuleInfrastructureTestCase;
import tv.codely.mooc.courses.domain.Course;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

final class InMemoryCourseRepositoryTest extends CoursesModuleInfrastructureTestCase {

    @Test
    void save_a_course() {
        final var course = Instancio.create(Course.class);

        assertDoesNotThrow(() -> this.target.save(course));
    }

    @Test
    void return_an_existing_course() {
        final var course = Instancio.create(Course.class);

        this.target.save(course);

        assertEquals(Optional.of(course), this.target.search(course.getId().value()));
    }

    @Test
    void not_return_a_non_existing_course() {

        assertFalse(this.target.search(Instancio.gen().uuid().get()).isPresent());
    }
}

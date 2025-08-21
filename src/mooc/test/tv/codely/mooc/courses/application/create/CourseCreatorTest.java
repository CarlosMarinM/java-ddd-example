package tv.codely.mooc.courses.application.create;

import org.junit.jupiter.api.Test;
import tv.codely.mooc.courses.domain.Course;
import tv.codely.mooc.courses.domain.CourseRepository;

import static org.mockito.Mockito.*;

final class CourseCreatorTest {

	@Test
	void create_a_valid_course() {
		final var repository = mock(CourseRepository.class);
		final var creator = new CourseCreator(repository);
		final var id = "some-id";
		final var name = "name";
		final var duration = "duration";

		final var course = new Course(id, name, duration);

		creator.create(course.id(), course.name(), course.duration());

		verify(repository, atLeastOnce()).save(course);
	}
}

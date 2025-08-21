package tv.codely.mooc.courses.infrastructure.persistence;

import org.junit.jupiter.api.Test;
import tv.codely.mooc.courses.domain.Course;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

final class InMemoryCourseRepositoryTest {

	@Test
	void save_a_course() {
		final var repository = new InMemoryCourseRepository();
		final var course = new Course("id", "name", "duration");

		repository.save(course);
	}

	@Test
	void return_an_existing_course() {
		final var repository = new InMemoryCourseRepository();
		final var course = new Course("id", "name", "duration");

		repository.save(course);

		assertEquals(Optional.of(course), repository.search(course.id()));
	}

	@Test
	void not_return_a_non_existing_course() {
		final var repository = new InMemoryCourseRepository();

		assertFalse(repository.search("randomId").isPresent());
	}
}

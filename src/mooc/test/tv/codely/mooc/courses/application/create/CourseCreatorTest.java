package tv.codely.mooc.courses.application.create;

import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tv.codely.mooc.courses.domain.Course;
import tv.codely.mooc.courses.domain.CourseRepository;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
final class CourseCreatorTest {

	@Mock
	private CourseRepository repository;

	@InjectMocks
	private CourseCreator target;

	@Test
	void create_a_valid_course() {
		final var createCourseRequest = Instancio.create(CreateCourseRequest.class);
		final var course = new Course(createCourseRequest.getId(), createCourseRequest.getName(),
				createCourseRequest.getDuration());

		this.target.create(createCourseRequest);

		verify(this.repository, atLeastOnce()).save(course);
	}
}

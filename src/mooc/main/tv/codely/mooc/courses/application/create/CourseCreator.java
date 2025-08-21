package tv.codely.mooc.courses.application.create;

import tv.codely.mooc.courses.domain.Course;
import tv.codely.mooc.courses.domain.CourseRepository;
import tv.codely.shared.domain.Service;

@Service
public final class CourseCreator {

	private final CourseRepository repository;

	public CourseCreator(final CourseRepository repository) {
		this.repository = repository;
	}

	public void create(final String id, final String name, final String duration) {
		final var course = new Course(id, name, duration);

		this.repository.save(course);
	}
}

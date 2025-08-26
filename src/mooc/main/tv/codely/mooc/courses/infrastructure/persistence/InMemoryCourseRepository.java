package tv.codely.mooc.courses.infrastructure.persistence;

import tv.codely.mooc.courses.domain.Course;
import tv.codely.mooc.courses.domain.CourseRepository;
import tv.codely.shared.domain.Service;

import java.util.HashMap;
import java.util.Optional;

@Service
public final class InMemoryCourseRepository implements CourseRepository {
	private final HashMap<String, Course> courses = new HashMap<>();

	@Override
	public void save(final Course course) {
		this.courses.put(course.getId(), course);
	}

	public Optional<Course> search(final String id) {
		return Optional.ofNullable(this.courses.get(id));
	}
}

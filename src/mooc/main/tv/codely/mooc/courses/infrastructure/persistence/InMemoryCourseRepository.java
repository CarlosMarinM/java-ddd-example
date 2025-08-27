package tv.codely.mooc.courses.infrastructure.persistence;

import tv.codely.mooc.courses.domain.Course;
import tv.codely.mooc.courses.domain.CourseRepository;
import tv.codely.shared.domain.Service;

import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

@Service
public final class InMemoryCourseRepository implements CourseRepository {
    private final HashMap<UUID, Course> courses = new HashMap<>();

    @Override
    public void save(final Course course) {
        this.courses.put(course.getId().value(), course);
    }

    public Optional<Course> search(final UUID id) {
        return Optional.ofNullable(this.courses.get(id));
    }
}

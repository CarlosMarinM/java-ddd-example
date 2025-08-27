package tv.codely.mooc.courses.domain;

import java.util.Optional;
import java.util.UUID;

public interface CourseRepository {
    void save(Course course);

    Optional<Course> search(UUID id);
}

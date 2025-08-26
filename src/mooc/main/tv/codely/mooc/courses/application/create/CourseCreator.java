package tv.codely.mooc.courses.application.create;

import lombok.RequiredArgsConstructor;
import tv.codely.mooc.courses.domain.Course;
import tv.codely.mooc.courses.domain.CourseRepository;
import tv.codely.shared.domain.Service;

@Service
@RequiredArgsConstructor
public final class CourseCreator {

    private final CourseRepository repository;

    public void create(final CreateCourseRequest createCourseRequest) {
        final var course = new Course(createCourseRequest.getId(), createCourseRequest.getName(),
            createCourseRequest.getDuration());

        this.repository.save(course);
    }
}

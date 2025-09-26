package tv.codely.mooc.courses.application.create;

import lombok.RequiredArgsConstructor;
import tv.codely.mooc.courses.domain.Course;
import tv.codely.mooc.courses.domain.CourseDuration;
import tv.codely.mooc.courses.domain.CourseId;
import tv.codely.mooc.courses.domain.CourseName;
import tv.codely.mooc.courses.domain.CourseRepository;
import tv.codely.shared.domain.Service;
import tv.codely.shared.domain.bus.event.EventBus;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CourseCreator {

    private final CourseRepository repository;
    private final EventBus eventBus;

    @Transactional
    public void create(final CreateCourseRequest createCourseRequest) {
        final var id = CourseId.of(createCourseRequest.getId());
        final var name = new CourseName(createCourseRequest.getName());
        final var duration = new CourseDuration(createCourseRequest.getDuration());

        final var course = Course.create(id, name, duration);

        this.repository.save(course);
        this.eventBus.publish(course.pullDomainEvents());
    }
}

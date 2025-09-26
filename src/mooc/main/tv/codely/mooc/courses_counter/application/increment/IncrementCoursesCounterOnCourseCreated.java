package tv.codely.mooc.courses_counter.application.increment;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import tv.codely.mooc.courses.domain.CourseCreatedDomainEvent;
import tv.codely.mooc.courses.domain.CourseId;
import tv.codely.shared.domain.Service;

@Service
@RequiredArgsConstructor
public final class IncrementCoursesCounterOnCourseCreated {
    private final CoursesCounterIncrementer incrementer;

    @EventListener
    public void on(final CourseCreatedDomainEvent event) {
        final CourseId courseId = new CourseId(event.getAggregateId());

        this.incrementer.increment(courseId);
    }
}

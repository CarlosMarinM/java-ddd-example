package tv.codely.mooc.courses_counter.application.increment;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import tv.codely.mooc.courses.domain.CourseCreatedDomainEvent;
import tv.codely.mooc.courses.domain.CourseId;
import tv.codely.shared.domain.Service;
import tv.codely.shared.domain.bus.event.DomainEventSubscriber;

@Service
@DomainEventSubscriber({CourseCreatedDomainEvent.class})
@RequiredArgsConstructor
public final class IncrementCoursesCounterOnCourseCreated {

    private final CoursesCounterIncrementer incrementer;

    @EventListener
    public void on(final CourseCreatedDomainEvent event) {
        final var courseId = new CourseId(event.getAggregateId());

        this.incrementer.increment(courseId);
    }
}

package tv.codely.shared.domain.course;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import tv.codely.shared.domain.bus.event.DomainEvent;

import java.util.UUID;

@Getter
@EqualsAndHashCode(callSuper = false)
public class CourseCreatedDomainEvent extends DomainEvent<UUID> {

    private final String name;
    private final String duration;

    public CourseCreatedDomainEvent() {
        super(null);

        this.name = null;
        this.duration = null;
    }

    public CourseCreatedDomainEvent(final UUID aggregateId, final String name, final String duration) {
        super(aggregateId);
        this.name = name;
        this.duration = duration;
    }

    @Override
    public String eventName() {
        return "course.created";
    }
}

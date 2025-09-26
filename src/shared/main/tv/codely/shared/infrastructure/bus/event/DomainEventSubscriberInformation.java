package tv.codely.shared.infrastructure.bus.event;

import tv.codely.shared.domain.Utils;
import tv.codely.shared.domain.bus.event.DomainEvent;

import java.util.List;

public final class DomainEventSubscriberInformation {
    private final Class<?> subscriberClass;
    private final List<Class<? extends DomainEvent>> subscribedEvents;

    public DomainEventSubscriberInformation(
        Class<?> subscriberClass,
        List<Class<? extends DomainEvent>> subscribedEvents
    ) {
        this.subscriberClass = subscriberClass;
        this.subscribedEvents = subscribedEvents;
    }

    public Class<?> subscriberClass() {
        return this.subscriberClass;
    }

    public String contextName() {
        final String[] nameParts = this.subscriberClass.getName().split("\\.");

        return nameParts[2];
    }

    public String moduleName() {
        final String[] nameParts = this.subscriberClass.getName().split("\\.");

        return nameParts[3];
    }

    public String className() {
        final String[] nameParts = this.subscriberClass.getName().split("\\.");

        return nameParts[nameParts.length - 1];
    }

    public List<Class<? extends DomainEvent>> subscribedEvents() {
        return this.subscribedEvents;
    }

    public String formatRabbitMqQueueName() {
        return String.format("codely.%s.%s.%s", this.contextName(), this.moduleName(), Utils.toSnake(this.className()));
    }
}

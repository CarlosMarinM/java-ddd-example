package tv.codely.shared.domain;

import tv.codely.shared.domain.bus.event.DomainEvent;

import java.util.ArrayList;
import java.util.List;

public abstract class AggregateRoot {

    private final List<DomainEvent<?>> domainEvents = new ArrayList<>();

    protected void register(final DomainEvent<?> event) {
        this.domainEvents.add(event);
    }

    public List<DomainEvent<?>> pullDomainEvents() {
        final var registeredDomainEvents = List.copyOf(this.domainEvents);
        this.domainEvents.clear();
        return registeredDomainEvents;
    }

}

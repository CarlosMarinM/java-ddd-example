package tv.codely.shared.infrastructure.bus.event.mysql;

import lombok.RequiredArgsConstructor;
import tv.codely.shared.domain.Service;
import tv.codely.shared.domain.bus.event.DomainEvent;
import tv.codely.shared.domain.bus.event.EventBus;
import tv.codely.shared.infrastructure.persistence.entity.DomainEventEntity;
import tv.codely.shared.infrastructure.persistence.mysql.DomainEventMySqlRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MySqlEventBus implements EventBus {

    private final DomainEventMySqlRepository repository;

    @Override
    public void publish(final List<DomainEvent<?>> events) {
        events.forEach(this::publish);
    }

    private void publish(final DomainEvent<?> domainEvent) {
        this.repository.save(this.getEntity(domainEvent));
    }

    private DomainEventEntity getEntity(final DomainEvent<?> domainEvent) {
        return new DomainEventEntity(
            domainEvent.getEventId(),
            domainEvent.getAggregateId().toString(),
            domainEvent.eventName(),
            domainEvent.toJson(),
            domainEvent.getOccurredOn()
        );
    }
}

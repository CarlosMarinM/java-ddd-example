package tv.codely.shared.infrastructure.bus.event.rabbitmq;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.AmqpException;
import org.springframework.context.annotation.Primary;
import tv.codely.shared.domain.Service;
import tv.codely.shared.domain.bus.event.DomainEvent;
import tv.codely.shared.domain.bus.event.EventBus;
import tv.codely.shared.infrastructure.bus.event.mysql.MySqlEventBus;

import java.util.Collections;
import java.util.List;

@Primary
@Service
@RequiredArgsConstructor
public class RabbitMqEventBus implements EventBus {

    private static final String EXCHANGE_NAME = "domain_events";
    
    private final RabbitMqPublisher publisher;
    private final MySqlEventBus failoverPublisher;

    @Override
    public void publish(List<DomainEvent<?>> events) {
        events.forEach(this::publish);
    }

    private void publish(DomainEvent<?> domainEvent) {
        try {
            this.publisher.publish(domainEvent, EXCHANGE_NAME);
        } catch (final AmqpException error) {
            this.failoverPublisher.publish(Collections.singletonList(domainEvent));
        }
    }
}

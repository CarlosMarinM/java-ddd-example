package tv.codely.shared.infrastructure.bus.event.rabbitmq;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import tv.codely.shared.domain.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class RabbitMqConsumerAutoStarter implements ApplicationListener<ApplicationEvent> {

    private final RabbitMqDomainEventsConsumer rabbitMqDomainEventsConsumer;

    @Override
    public void onApplicationEvent(final ApplicationEvent event) {
        if (event instanceof ApplicationReadyEvent) {
            this.rabbitMqDomainEventsConsumer.consume();
            log.debug("RabbitMQ consumer has been started.");
        }
    }
}

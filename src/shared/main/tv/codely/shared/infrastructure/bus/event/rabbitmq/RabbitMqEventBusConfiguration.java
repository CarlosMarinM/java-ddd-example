package tv.codely.shared.infrastructure.bus.event.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Declarable;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tv.codely.shared.infrastructure.bus.event.DomainEventSubscribersInformation;
import tv.codely.shared.infrastructure.bus.event.DomainEventsInformation;
import tv.codely.shared.infrastructure.config.Parameter;
import tv.codely.shared.infrastructure.config.ParameterNotExist;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class RabbitMqEventBusConfiguration {
    private final DomainEventSubscribersInformation domainEventSubscribersInformation;
    private final DomainEventsInformation domainEventsInformation;
    private final Parameter config;
    private final String exchangeName;

    public RabbitMqEventBusConfiguration(
        DomainEventSubscribersInformation domainEventSubscribersInformation,
        DomainEventsInformation domainEventsInformation,
        Parameter config
    ) throws ParameterNotExist {
        this.domainEventSubscribersInformation = domainEventSubscribersInformation;
        this.domainEventsInformation = domainEventsInformation;
        this.config = config;
        this.exchangeName = config.get("RABBITMQ_EXCHANGE");
    }

    @Bean
    public CachingConnectionFactory connection() throws ParameterNotExist {
        final CachingConnectionFactory factory = new CachingConnectionFactory();

        factory.setHost(this.config.get("RABBITMQ_HOST"));
        factory.setPort(this.config.getInt("RABBITMQ_PORT"));
        factory.setUsername(this.config.get("RABBITMQ_LOGIN"));
        factory.setPassword(this.config.get("RABBITMQ_PASSWORD"));

        return factory;
    }

    @Bean
    public Declarables declaration() {
        final String retryExchangeName = RabbitMqExchangeNameFormatter.retry(this.exchangeName);
        final String deadLetterExchangeName = RabbitMqExchangeNameFormatter.deadLetter(this.exchangeName);

        final TopicExchange domainEventsExchange = new TopicExchange(this.exchangeName, true, false);
        final TopicExchange retryDomainEventsExchange = new TopicExchange(retryExchangeName, true, false);
        final TopicExchange deadLetterDomainEventsExchange = new TopicExchange(deadLetterExchangeName, true, false);
        final List<Declarable> declarables = new ArrayList<>();
        declarables.add(domainEventsExchange);
        declarables.add(retryDomainEventsExchange);
        declarables.add(deadLetterDomainEventsExchange);

        final Collection<Declarable> queuesAndBindings = this.declareQueuesAndBindings(
            domainEventsExchange,
            retryDomainEventsExchange,
            deadLetterDomainEventsExchange
        ).stream().flatMap(Collection::stream).collect(Collectors.toList());

        declarables.addAll(queuesAndBindings);

        return new Declarables(declarables);
    }

    private Collection<Collection<Declarable>> declareQueuesAndBindings(
        TopicExchange domainEventsExchange,
        TopicExchange retryDomainEventsExchange,
        TopicExchange deadLetterDomainEventsExchange
    ) {
        return this.domainEventSubscribersInformation.all().stream().map(information -> {
            final String queueName = RabbitMqQueueNameFormatter.format(information);
            final String retryQueueName = RabbitMqQueueNameFormatter.formatRetry(information);
            final String deadLetterQueueName = RabbitMqQueueNameFormatter.formatDeadLetter(information);

            final Queue queue = QueueBuilder.durable(queueName).build();
            final Queue retryQueue = QueueBuilder.durable(retryQueueName).withArguments(
                this.retryQueueArguments(domainEventsExchange, queueName)
            ).build();
            final Queue deadLetterQueue = QueueBuilder.durable(deadLetterQueueName).build();

            final Binding fromExchangeSameQueueBinding = BindingBuilder
                .bind(queue)
                .to(domainEventsExchange)
                .with(queueName);

            final Binding fromRetryExchangeSameQueueBinding = BindingBuilder
                .bind(retryQueue)
                .to(retryDomainEventsExchange)
                .with(queueName);

            final Binding fromDeadLetterExchangeSameQueueBinding = BindingBuilder
                .bind(deadLetterQueue)
                .to(deadLetterDomainEventsExchange)
                .with(queueName);

            final List<Binding> fromExchangeDomainEventsBindings = information.subscribedEvents().stream().map(
                domainEventClass -> {
                    final String eventName = this.domainEventsInformation.forClass(domainEventClass);
                    return BindingBuilder
                        .bind(queue)
                        .to(domainEventsExchange)
                        .with(eventName);
                }).collect(Collectors.toList());

            final List<Declarable> queuesAndBindings = new ArrayList<>();
            queuesAndBindings.add(queue);
            queuesAndBindings.add(fromExchangeSameQueueBinding);
            queuesAndBindings.addAll(fromExchangeDomainEventsBindings);

            queuesAndBindings.add(retryQueue);
            queuesAndBindings.add(fromRetryExchangeSameQueueBinding);

            queuesAndBindings.add(deadLetterQueue);
            queuesAndBindings.add(fromDeadLetterExchangeSameQueueBinding);

            return queuesAndBindings;
        }).collect(Collectors.toList());
    }

    private HashMap<String, Object> retryQueueArguments(TopicExchange exchange, String routingKey) {
        return new HashMap<String, Object>() {{
            this.put("x-dead-letter-exchange", exchange.getName());
            this.put("x-dead-letter-routing-key", routingKey);
            this.put("x-message-ttl", 1000);
        }};
    }
}

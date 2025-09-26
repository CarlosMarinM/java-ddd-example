package tv.codely.shared.domain.bus.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@RequiredArgsConstructor
@Getter
public abstract class DomainEvent<T> {

    private final T aggregateId;
    private final String eventId;
    private final String occurredOn;
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    protected DomainEvent(final T aggregateId) {
        this.aggregateId = aggregateId;
        this.eventId = UUID.randomUUID().toString();
        this.occurredOn = LocalDateTime.now().toString();
    }

    public abstract String eventName();

    public String asBody() {
        try {
            return OBJECT_MAPPER.writeValueAsString(this);
        } catch (final JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}

package tv.codely.shared.domain.bus.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;
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

    @SuppressWarnings("unchecked")
    public Map<String, Object> toMap() {
        return OBJECT_MAPPER.convertValue(this, Map.class);
    }

    public String toJson() {
        try {
            return OBJECT_MAPPER.writeValueAsString(this);
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static DomainEvent fromJson(final String body, Class<? extends DomainEvent> clazz) {
        try {
            return OBJECT_MAPPER.readValue(body, clazz);
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }
}

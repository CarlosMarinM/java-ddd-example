package tv.codely.shared.infrastructure.bus.event;

import tv.codely.shared.domain.Utils;
import tv.codely.shared.domain.bus.event.DomainEvent;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public final class DomainEventJsonSerializer {
    public static String serialize(DomainEvent domainEvent) {
        final Map<String, Object> attributes = domainEvent.toMap();

        return Utils.jsonEncode(new HashMap<String, Serializable>() {{
            this.put("data", new HashMap<String, Object>() {{
                this.put("id", domainEvent.getEventId());
                this.put("type", domainEvent.eventName());
                this.put("occurred_on", domainEvent.getOccurredOn());
                this.put("attributes", attributes);
            }});
            this.put("meta", new HashMap<String, Serializable>());
        }});
    }
}

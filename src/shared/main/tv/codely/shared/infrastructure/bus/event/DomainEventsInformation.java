package tv.codely.shared.infrastructure.bus.event;

import org.reflections.Reflections;
import tv.codely.shared.domain.Service;
import tv.codely.shared.domain.bus.event.DomainEvent;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@Service
public final class DomainEventsInformation {
    HashMap<String, Class<? extends DomainEvent>> indexedDomainEvents;

    public DomainEventsInformation() {
        final Reflections reflections = new Reflections("tv.codely");
        final Set<Class<? extends DomainEvent>> classes = reflections.getSubTypesOf(DomainEvent.class);

        try {
            this.indexedDomainEvents = this.formatEvents(classes);
        } catch (final NoSuchMethodException | IllegalAccessException | InstantiationException |
                       InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public Class<? extends DomainEvent> forName(String name) {
        return this.indexedDomainEvents.get(name);
    }

    public String forClass(Class<? extends DomainEvent> domainEventClass) {
        return this.indexedDomainEvents.entrySet()
            .stream()
            .filter(entry -> Objects.equals(entry.getValue(), domainEventClass))
            .map(Map.Entry::getKey)
            .findFirst().orElse("");
    }

    private HashMap<String, Class<? extends DomainEvent>> formatEvents(
        Set<Class<? extends DomainEvent>> domainEvents
    ) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        final HashMap<String, Class<? extends DomainEvent>> events = new HashMap<>();

        for (final Class<? extends DomainEvent> domainEvent : domainEvents) {
            final DomainEvent nullInstance = domainEvent.getConstructor().newInstance();

            events.put((String) domainEvent.getMethod("eventName").invoke(nullInstance), domainEvent);
        }

        return events;
    }
}

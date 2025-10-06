package tv.codely.shared.infrastructure.bus.query.reflection;

import tv.codely.shared.domain.Service;
import tv.codely.shared.domain.bus.Listener;
import tv.codely.shared.domain.bus.query.Query;
import tv.codely.shared.domain.bus.query.QueryBus;
import tv.codely.shared.domain.bus.query.QueryHandler;
import tv.codely.shared.domain.bus.query.Response;
import tv.codely.shared.infrastructure.bus.command.reflection.NotAvailableHandlerException;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ReflectionQueryBus implements QueryBus {

    private final Map<Class<?>, QueryHandler> handlers;

    public ReflectionQueryBus(final Set<QueryHandler> queryHandlers) {
        this.handlers = queryHandlers.stream().collect(Collectors.toMap(Listener::listenTo, handler -> handler));
    }

    @Override
    public <R extends Response> R ask(final Query<R> query) {
        return (R) this.handlers.computeIfAbsent(query.getClass(), (s) -> {
            throw NotAvailableHandlerException.forMessage(query);
        }).ask(query);
    }
}

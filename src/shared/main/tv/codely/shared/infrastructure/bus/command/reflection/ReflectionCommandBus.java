package tv.codely.shared.infrastructure.bus.command.reflection;

import org.springframework.context.annotation.Primary;
import tv.codely.shared.domain.Service;
import tv.codely.shared.domain.bus.Listener;
import tv.codely.shared.domain.bus.command.Command;
import tv.codely.shared.domain.bus.command.CommandBus;
import tv.codely.shared.domain.bus.command.CommandHandler;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Primary
@Service
public class ReflectionCommandBus implements CommandBus {

    private final Map<Class<?>, CommandHandler> handlers;

    public ReflectionCommandBus(final Set<CommandHandler> commandHandlers) {
        this.handlers = commandHandlers.stream().collect(Collectors.toMap(Listener::listenTo, handler -> handler));
    }

    @Override
    public <C extends Command> void dispatch(C command) {
        this.handlers.computeIfAbsent(command.getClass(), (s) -> {
            throw NotAvailableHandlerException.forMessage(command);
        }).handle(command);
    }
}

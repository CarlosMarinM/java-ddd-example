package tv.codely.shared.infrastructure.bus.command.spring;

import lombok.RequiredArgsConstructor;
import tv.codely.shared.domain.Service;
import tv.codely.shared.domain.bus.command.Command;
import tv.codely.shared.domain.bus.command.CommandBus;
import tv.codely.shared.domain.bus.command.CommandHandler;
import tv.codely.shared.domain.bus.command.CommandHandlerExecutionError;

@Service
@RequiredArgsConstructor
public class SpringCommandBus implements CommandBus {

    private final Registry registry;

    @Override
    public <C extends Command> void dispatch(C command) throws CommandHandlerExecutionError {
        final CommandHandler<C> commandHandler = (CommandHandler<C>) this.registry.get(command.getClass());
        commandHandler.handle(command);
    }
}

package tv.codely.shared.infrastructure.bus.command.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.core.GenericTypeResolver;
import tv.codely.shared.domain.Service;
import tv.codely.shared.domain.bus.command.Command;
import tv.codely.shared.domain.bus.command.CommandHandler;

import java.util.HashMap;
import java.util.Map;

@Service
public class Registry {

	private final Map<Class<? extends Command>, CommandProvider> providerMap = new HashMap<>();

	public Registry(final ApplicationContext applicationContext) {
		final String[] names = applicationContext.getBeanNamesForType(CommandHandler.class);
		for (final String name : names) {
			this.register(applicationContext, name);
		}
	}

	private void register(final ApplicationContext applicationContext, final String name) {
		final Class<CommandHandler<?>> handlerClass = (Class<CommandHandler<?>>) applicationContext.getType(name);
		final Class<?>[] generics = GenericTypeResolver.resolveTypeArguments(handlerClass, CommandHandler.class);
		final Class<? extends Command> commandType = (Class<? extends Command>) generics[0];
		this.providerMap.put(commandType, new CommandProvider(applicationContext, handlerClass));
	}

	@SuppressWarnings("unchecked")
	<C extends Command> CommandHandler<C> get(final Class<C> commandClass) {
		return this.providerMap.get(commandClass).get();
	}
}

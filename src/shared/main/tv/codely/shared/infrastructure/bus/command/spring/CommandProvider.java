package tv.codely.shared.infrastructure.bus.command.spring;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import tv.codely.shared.domain.bus.command.CommandHandler;

@RequiredArgsConstructor
public class CommandProvider<H extends CommandHandler<?>> {

    private final ApplicationContext applicationContext;
    private final Class<H> type;

    public H get() {
        return this.applicationContext.getBean(this.type);
    }
}

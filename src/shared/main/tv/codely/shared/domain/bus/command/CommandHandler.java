package tv.codely.shared.domain.bus.command;

import tv.codely.shared.domain.bus.Listener;

public interface CommandHandler<C extends Command> extends Listener<C> {
    void handle(C command);
}

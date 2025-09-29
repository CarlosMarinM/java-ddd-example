package tv.codely.shared.domain.bus.command;

public interface CommandBus {

    /**
     * Searches the handler and passes the command to it.
     *
     * @param command command object
     * @param <C>     type of command
     */
    <C extends Command> void dispatch(C command);
}

package tv.codely.shared.infrastructure.bus.command.reflection;

import tv.codely.shared.domain.bus.Message;

public class NotAvailableHandlerException extends RuntimeException {

    private static final long serialVersionUID = -8119937941218818630L;

    public NotAvailableHandlerException(final String message) {
        super(message);
    }

    public static NotAvailableHandlerException forMessage(final Message message) {
        return new NotAvailableHandlerException(String.format("No handler for %s", message.getClass().getName()));
    }
}

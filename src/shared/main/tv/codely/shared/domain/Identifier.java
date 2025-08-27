package tv.codely.shared.domain;

import java.util.UUID;

public abstract class Identifier extends ValueObject<UUID> {

    protected Identifier(final UUID value) {
        super(value);
    }

}

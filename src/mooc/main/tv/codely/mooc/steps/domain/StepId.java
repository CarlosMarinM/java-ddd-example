package tv.codely.mooc.steps.domain;

import tv.codely.shared.domain.Identifier;

import java.util.UUID;

public final class StepId extends Identifier {

    public StepId(final UUID value) {
        super(value);
    }

    public static StepId of(final String value) {
        return new StepId(UUID.fromString(value));
    }
}

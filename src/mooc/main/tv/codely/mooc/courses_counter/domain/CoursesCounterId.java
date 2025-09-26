package tv.codely.mooc.courses_counter.domain;

import tv.codely.shared.domain.Identifier;

import java.util.UUID;

public final class CoursesCounterId extends Identifier {

    public CoursesCounterId(final UUID value) {
        super(value);
    }

}

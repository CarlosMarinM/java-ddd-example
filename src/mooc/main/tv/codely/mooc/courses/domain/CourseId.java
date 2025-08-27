package tv.codely.mooc.courses.domain;

import tv.codely.shared.domain.Identifier;

import java.util.UUID;

public final class CourseId extends Identifier {

    public CourseId(final UUID value) {
        super(value);
    }

    public static CourseId of(final String value) {
        return new CourseId(UUID.fromString(value));
    }
}

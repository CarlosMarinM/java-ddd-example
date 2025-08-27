package tv.codely.mooc.courses.domain;

import tv.codely.shared.domain.ValueObject;

public final class CourseName extends ValueObject<String> {

    public CourseName(final String value) {
        super(value);
    }
}

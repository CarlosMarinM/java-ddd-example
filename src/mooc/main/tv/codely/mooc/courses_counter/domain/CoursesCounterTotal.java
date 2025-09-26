package tv.codely.mooc.courses_counter.domain;

import tv.codely.shared.domain.ValueObject;

public final class CoursesCounterTotal extends ValueObject<Integer> {
    public CoursesCounterTotal(final Integer value) {
        super(value);
    }

    public static CoursesCounterTotal initialize() {
        return new CoursesCounterTotal(0);
    }

    public CoursesCounterTotal increment() {
        return new CoursesCounterTotal(this.value() + 1);
    }
}

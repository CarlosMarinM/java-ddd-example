package tv.codely.mooc.courses_counter.application.find;

import java.util.Objects;

public final class CoursesCounterResponse {
    private final Integer total;

    public CoursesCounterResponse(Integer total) {
        this.total = total;
    }

    public Integer total() {
        return this.total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        final CoursesCounterResponse that = (CoursesCounterResponse) o;
        return this.total.equals(that.total);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.total);
    }
}

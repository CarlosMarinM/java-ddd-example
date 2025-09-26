package tv.codely.mooc.courses_counter.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import tv.codely.mooc.courses.domain.CourseId;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class CoursesCounter {
    private final CoursesCounterId id;
    private CoursesCounterTotal total;
    private final List<CourseId> existingCourses;

    public static CoursesCounter initialize(final UUID id) {
        return new CoursesCounter(new CoursesCounterId(id), CoursesCounterTotal.initialize(), new ArrayList<>());
    }

    public boolean hasIncremented(final CourseId id) {
        return this.existingCourses.contains(id);
    }

    public void increment(final CourseId id) {
        this.total = this.total.increment();
        this.existingCourses.add(id);
    }

}

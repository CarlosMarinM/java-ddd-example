package tv.codely.mooc.courses_counter.application.increment;

import lombok.RequiredArgsConstructor;
import tv.codely.mooc.courses.domain.CourseId;
import tv.codely.mooc.courses_counter.domain.CoursesCounter;
import tv.codely.mooc.courses_counter.domain.CoursesCounterRepository;
import tv.codely.shared.domain.Service;
import tv.codely.shared.domain.UuidGenerator;

@Service
@RequiredArgsConstructor
public final class CoursesCounterIncrementer {
    private final CoursesCounterRepository repository;
    private final UuidGenerator uuidGenerator;

    public void increment(final CourseId id) {
        final CoursesCounter counter = this.repository.search()
            .orElseGet(() -> CoursesCounter.initialize(this.uuidGenerator.generate()));

        if (!counter.hasIncremented(id)) {
            counter.increment(id);

            this.repository.save(counter);
        }
    }
}

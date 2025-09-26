package tv.codely.mooc.courses_counter.application.find;

import tv.codely.mooc.courses_counter.domain.CoursesCounter;
import tv.codely.mooc.courses_counter.domain.CoursesCounterNotInitialized;
import tv.codely.mooc.courses_counter.domain.CoursesCounterRepository;
import tv.codely.shared.domain.Service;

@Service
public final class CoursesCounterFinder {
    private final CoursesCounterRepository repository;

    public CoursesCounterFinder(CoursesCounterRepository repository) {
        this.repository = repository;
    }

    public CoursesCounterResponse find() {
        final CoursesCounter coursesCounter = this.repository.search().orElseThrow(CoursesCounterNotInitialized::new);

        return new CoursesCounterResponse(coursesCounter.getTotal().value());
    }
}

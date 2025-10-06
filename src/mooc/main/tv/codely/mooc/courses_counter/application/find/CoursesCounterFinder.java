package tv.codely.mooc.courses_counter.application.find;

import lombok.RequiredArgsConstructor;
import tv.codely.mooc.courses_counter.domain.CoursesCounterNotInitialized;
import tv.codely.mooc.courses_counter.domain.CoursesCounterRepository;
import tv.codely.shared.domain.Service;

@Service
@RequiredArgsConstructor
public final class CoursesCounterFinder {

    private final CoursesCounterRepository repository;

    public CoursesCounterResponse find() {
        final var coursesCounter = this.repository.search().orElseThrow(CoursesCounterNotInitialized::new);

        return new CoursesCounterResponse(coursesCounter.getTotal().value());
    }
}

package tv.codely.mooc.courses_counter.application.find;

import lombok.RequiredArgsConstructor;
import tv.codely.shared.domain.Service;
import tv.codely.shared.domain.bus.query.QueryHandler;

@Service
@RequiredArgsConstructor
public class FindCoursesCounterQueryHandler implements QueryHandler<FindCoursesCounterQuery, CoursesCounterResponse> {

    private final CoursesCounterFinder coursesCounterFinder;

    @Override
    public CoursesCounterResponse ask(final FindCoursesCounterQuery query) {
        return this.coursesCounterFinder.find();
    }
}

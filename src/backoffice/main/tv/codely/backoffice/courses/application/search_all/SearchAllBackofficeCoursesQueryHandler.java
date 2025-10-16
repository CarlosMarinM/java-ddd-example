package tv.codely.backoffice.courses.application.search_all;

import lombok.RequiredArgsConstructor;
import tv.codely.backoffice.courses.application.BackofficeCoursesResponse;
import tv.codely.shared.domain.Service;
import tv.codely.shared.domain.bus.query.QueryHandler;

@Service
@RequiredArgsConstructor
public class SearchAllBackofficeCoursesQueryHandler implements QueryHandler<SearchAllBackofficeCoursesQuery, BackofficeCoursesResponse> {

    private final AllBackofficeCoursesSearcher searcher;

    @Override
    public BackofficeCoursesResponse ask(SearchAllBackofficeCoursesQuery query) {
        return this.searcher.search();
    }
}

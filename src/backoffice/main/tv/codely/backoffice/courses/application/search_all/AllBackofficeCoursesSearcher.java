package tv.codely.backoffice.courses.application.search_all;

import lombok.RequiredArgsConstructor;
import tv.codely.backoffice.courses.application.BackofficeCoursesResponse;
import tv.codely.backoffice.courses.domain.BackofficeCourseRepository;
import tv.codely.shared.domain.Service;

@Service
@RequiredArgsConstructor
public class AllBackofficeCoursesSearcher {

    private final BackofficeCourseRepository repository;

    public BackofficeCoursesResponse search() {
        return new BackofficeCoursesResponse(this.repository.searchAll());
    }
}

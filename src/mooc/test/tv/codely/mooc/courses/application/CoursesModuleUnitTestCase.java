package tv.codely.mooc.courses.application;

import org.mockito.Mock;
import tv.codely.mooc.courses.domain.Course;
import tv.codely.mooc.courses.domain.CourseRepository;
import tv.codely.shared.application.UnitTestCase;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

public class CoursesModuleUnitTestCase extends UnitTestCase {

    @Mock
    protected CourseRepository repository;

    protected void shouldHaveSaved(final Course course) {
        verify(this.repository, atLeastOnce()).save(course);
    }
}

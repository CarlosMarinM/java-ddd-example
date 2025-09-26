package tv.codely.mooc.courses.application;

import org.mockito.Mock;
import tv.codely.mooc.courses.domain.CourseRepository;
import tv.codely.shared.application.UnitTestCase;
import tv.codely.shared.domain.bus.event.EventBus;

public class CoursesModuleUnitTestCase extends UnitTestCase {

    @Mock
    protected CourseRepository repository;

    @Mock
    protected EventBus eventBus;
}

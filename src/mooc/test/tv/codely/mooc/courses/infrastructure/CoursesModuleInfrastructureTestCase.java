package tv.codely.mooc.courses.infrastructure;

import org.springframework.beans.factory.annotation.Autowired;
import tv.codely.mooc.courses.infrastructure.persistence.adapter.CourseAdapterRepository;
import tv.codely.shared.infrastructure.InfrastructureTestCase;

public abstract class CoursesModuleInfrastructureTestCase extends InfrastructureTestCase {

    @Autowired
    protected CourseAdapterRepository target;
}

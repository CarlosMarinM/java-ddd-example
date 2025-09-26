package tv.codely.mooc.steps.infrastructure;

import org.springframework.beans.factory.annotation.Autowired;
import tv.codely.mooc.steps.infrastructure.persistence.adapter.StepAdapterRepository;
import tv.codely.shared.infrastructure.InfrastructureTestCase;

public class StepsModuleInfrastructureTestCase extends InfrastructureTestCase {

    @Autowired
    protected StepAdapterRepository target;
}

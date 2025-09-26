package tv.codely.apps.mooc.controller.health_check;

import org.junit.jupiter.api.Test;
import tv.codely.apps.mooc.controller.ApplicationTestCase;

final class HealthCheckGetControllerShould extends ApplicationTestCase {
    @Test
    public void check_the_app_is_working_ok() throws Exception {
        this.assertResponse("/health-check", 200, "{'status': 'ok'}");
    }
}

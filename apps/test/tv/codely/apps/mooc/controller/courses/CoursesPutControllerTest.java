package tv.codely.apps.mooc.controller.courses;

import org.junit.jupiter.api.Test;
import tv.codely.apps.mooc.controller.ApplicationTestCase;

final class CoursesPutControllerTest extends ApplicationTestCase {

    @Test
    void create_a_valid_non_existing_course() throws Exception {
        this.assertRequestWithBody("PUT",
            "/courses/1aab45ba-3c7a-4344-8936-78466eca77fb",
            "{\"name\": \"The best course\", \"duration\": \"5 hours\"}",
            201);
    }
}

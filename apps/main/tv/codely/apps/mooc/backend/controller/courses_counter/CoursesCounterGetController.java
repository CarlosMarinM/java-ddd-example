package tv.codely.apps.mooc.backend.controller.courses_counter;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import tv.codely.mooc.courses_counter.application.find.FindCoursesCounterQuery;
import tv.codely.shared.domain.bus.query.QueryBus;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public final class CoursesCounterGetController {

    private final QueryBus queryBus;

    @GetMapping("/courses-counter")
    public Map<String, Integer> index() {
        final var response = this.queryBus.ask(new FindCoursesCounterQuery());

        return new HashMap<>(Map.of("total", response.getTotal()));
    }
}

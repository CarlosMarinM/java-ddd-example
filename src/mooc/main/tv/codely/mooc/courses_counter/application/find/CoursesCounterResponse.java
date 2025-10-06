package tv.codely.mooc.courses_counter.application.find;

import lombok.Value;
import tv.codely.shared.domain.bus.query.Response;

@Value
public class CoursesCounterResponse implements Response {

    Integer total;

}

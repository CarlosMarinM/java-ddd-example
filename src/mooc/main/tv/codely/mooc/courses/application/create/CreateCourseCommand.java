package tv.codely.mooc.courses.application.create;

import lombok.Value;
import tv.codely.shared.domain.bus.command.Command;

@Value
public class CreateCourseCommand implements Command {
    String id;
    String name;
    String duration;
}

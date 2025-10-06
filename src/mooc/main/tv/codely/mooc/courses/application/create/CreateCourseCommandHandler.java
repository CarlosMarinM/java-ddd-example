package tv.codely.mooc.courses.application.create;

import lombok.RequiredArgsConstructor;
import tv.codely.mooc.courses.domain.CourseDuration;
import tv.codely.mooc.courses.domain.CourseId;
import tv.codely.mooc.courses.domain.CourseName;
import tv.codely.shared.domain.Service;
import tv.codely.shared.domain.bus.command.CommandHandler;

@Service
@RequiredArgsConstructor
public final class CreateCourseCommandHandler implements CommandHandler<CreateCourseCommand> {

    private final CourseCreator creator;

    @Override
    public void handle(final CreateCourseCommand command) {
        final var id = CourseId.of(command.getId());
        final var name = new CourseName(command.getName());
        final var duration = new CourseDuration(command.getDuration());

        this.creator.create(id, name, duration);
    }
}

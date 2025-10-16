package tv.codely.apps.mooc.controller.courses;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tv.codely.apps.mooc.controller.courses.dto.CreateCourseDto;
import tv.codely.apps.mooc.controller.courses.mapper.CourseMapper;
import tv.codely.shared.domain.bus.command.CommandBus;

@RestController
@RequiredArgsConstructor
public class CoursesPutController {

    private final CourseMapper mapper;

    private final CommandBus commandBus;

    @PutMapping(value = "/courses/{id}")
    public ResponseEntity<Void> index(@PathVariable final String id, @RequestBody final CreateCourseDto request) {
        this.commandBus.dispatch(this.mapper.toCommand(id, request));

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}

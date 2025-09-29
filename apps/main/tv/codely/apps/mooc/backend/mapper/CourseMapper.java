package tv.codely.apps.mooc.backend.mapper;

import org.mapstruct.Mapper;
import tv.codely.apps.mooc.backend.dto.CreateCourseDto;
import tv.codely.mooc.courses.application.create.CreateCourseCommand;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    CreateCourseCommand toCommand(String id, CreateCourseDto dto);
}

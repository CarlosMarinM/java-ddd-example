package tv.codely.apps.mooc.controller.courses.mapper;

import org.mapstruct.Mapper;
import tv.codely.apps.mooc.controller.courses.dto.CreateCourseDto;
import tv.codely.mooc.courses.application.create.CreateCourseCommand;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    CreateCourseCommand toCommand(String id, CreateCourseDto dto);
}

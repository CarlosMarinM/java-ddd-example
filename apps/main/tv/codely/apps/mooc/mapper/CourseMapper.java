package tv.codely.apps.mooc.mapper;

import org.mapstruct.Mapper;
import tv.codely.apps.mooc.dto.CreateCourseDto;
import tv.codely.mooc.courses.application.create.CreateCourseRequest;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    CreateCourseRequest toDomain(String id, CreateCourseDto dto);
}

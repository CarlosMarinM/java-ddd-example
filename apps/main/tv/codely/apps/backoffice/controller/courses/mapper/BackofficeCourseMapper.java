package tv.codely.apps.backoffice.controller.courses.mapper;

import org.mapstruct.Mapper;
import tv.codely.apps.backoffice.controller.courses.dto.BackofficeCoursesDto;
import tv.codely.backoffice.courses.application.BackofficeCoursesResponse;

@Mapper(componentModel = "spring")
public interface BackofficeCourseMapper {

    BackofficeCoursesDto toBackofficeCoursesDto(BackofficeCoursesResponse response);
}

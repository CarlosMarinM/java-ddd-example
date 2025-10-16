package tv.codely.backoffice.courses.infrastructure.persistence.mapper;

import org.mapstruct.Mapper;
import tv.codely.backoffice.courses.domain.BackofficeCourse;
import tv.codely.backoffice.courses.infrastructure.persistence.entity.BackofficeCourseEntity;

@Mapper(componentModel = "spring")
public interface BackofficeCourseEntityMapper {
    BackofficeCourseEntity toEntity(BackofficeCourse course);

    BackofficeCourse toDomain(BackofficeCourseEntity backofficeCourseEntity);
}

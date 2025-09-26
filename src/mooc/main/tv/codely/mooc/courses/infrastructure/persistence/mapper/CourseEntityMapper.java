package tv.codely.mooc.courses.infrastructure.persistence.mapper;

import org.mapstruct.Mapper;
import tv.codely.mooc.courses.domain.Course;
import tv.codely.mooc.courses.infrastructure.persistence.entity.CourseEntity;

@Mapper(componentModel = "spring", uses = {ValueObjectMapper.class})
public interface CourseEntityMapper {

    CourseEntity toEntity(Course course);

    Course toDomain(CourseEntity courseEntity);
}

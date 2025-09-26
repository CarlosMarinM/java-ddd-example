package tv.codely.mooc.courses_counter.infrastructure.persistence.mapper;

import org.mapstruct.Mapper;
import tv.codely.mooc.courses.infrastructure.persistence.mapper.ValueObjectMapper;
import tv.codely.mooc.courses_counter.domain.CoursesCounter;
import tv.codely.mooc.courses_counter.infrastructure.persistence.entity.CoursesCounterEntity;

@Mapper(componentModel = "spring", uses = {ValueObjectMapper.class})
public interface CoursesCounterEntityMapper {
    CoursesCounterEntity toEntity(CoursesCounter counter);

    CoursesCounter toDomain(CoursesCounterEntity coursesCounterEntity);
}

package tv.codely.mooc.courses.infrastructure.persistence.adapter;

import lombok.RequiredArgsConstructor;
import tv.codely.mooc.courses.domain.Course;
import tv.codely.mooc.courses.domain.CourseId;
import tv.codely.mooc.courses.domain.CourseRepository;
import tv.codely.mooc.courses.infrastructure.persistence.mapper.CourseEntityMapper;
import tv.codely.mooc.courses.infrastructure.persistence.mysql.CourseMySqlRepository;
import tv.codely.shared.domain.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public final class CourseAdapterRepository implements CourseRepository {

    private final CourseMySqlRepository repository;

    private final CourseEntityMapper mapper;

    @Override
    public void save(final Course course) {
        this.repository.save(this.mapper.toEntity(course));
    }

    @Override
    public Optional<Course> search(final CourseId id) {
        return this.repository.findById(id.value().toString())
            .map(this.mapper::toDomain);
    }
}

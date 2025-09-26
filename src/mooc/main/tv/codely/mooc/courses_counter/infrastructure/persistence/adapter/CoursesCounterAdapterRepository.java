package tv.codely.mooc.courses_counter.infrastructure.persistence.adapter;

import lombok.RequiredArgsConstructor;
import tv.codely.mooc.courses_counter.domain.CoursesCounter;
import tv.codely.mooc.courses_counter.domain.CoursesCounterRepository;
import tv.codely.mooc.courses_counter.infrastructure.persistence.entity.CoursesCounterEntity;
import tv.codely.mooc.courses_counter.infrastructure.persistence.mapper.CoursesCounterEntityMapper;
import tv.codely.mooc.courses_counter.infrastructure.persistence.mysql.CoursesCounterMySqlRepository;
import tv.codely.shared.domain.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CoursesCounterAdapterRepository implements CoursesCounterRepository {

    private final CoursesCounterMySqlRepository repository;

    private final CoursesCounterEntityMapper mapper;

    @Override
    public void save(final CoursesCounter counter) {
        this.repository.save(this.mapper.toEntity(counter));
    }

    @Override
    public Optional<CoursesCounter> search() {
        final List<CoursesCounterEntity> coursesCounter = this.repository.findAll();

        return coursesCounter.isEmpty() ? Optional.empty() :
            Optional.ofNullable(this.mapper.toDomain(coursesCounter.get(0)));
    }
}

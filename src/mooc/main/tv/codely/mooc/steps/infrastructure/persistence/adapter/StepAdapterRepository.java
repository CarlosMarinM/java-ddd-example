package tv.codely.mooc.steps.infrastructure.persistence.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import tv.codely.mooc.steps.domain.Step;
import tv.codely.mooc.steps.domain.StepId;
import tv.codely.mooc.steps.domain.StepRepository;
import tv.codely.mooc.steps.infrastructure.persistence.mapper.StepEntityMapper;
import tv.codely.mooc.steps.infrastructure.persistence.mysql.StepMySqlRepository;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class StepAdapterRepository implements StepRepository {

    private final StepMySqlRepository repository;

    private final StepEntityMapper mapper;

    @Override
    public void save(final Step step) {
        this.repository.save(this.mapper.toEntity(step));
    }

    @Override
    public Optional<Step> search(final StepId id) {
        return this.repository.findById(id.value().toString())
            .map(this.mapper::toDomain);
    }
}

package tv.codely.mooc.steps.domain;

import java.util.Optional;

public interface StepRepository {
    void save(Step step);

    Optional<Step> search(StepId id);
}

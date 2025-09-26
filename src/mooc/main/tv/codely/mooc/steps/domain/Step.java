package tv.codely.mooc.steps.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EqualsAndHashCode
@Getter
public abstract class Step {

    private final StepId id;
    private final StepTitle title;
}

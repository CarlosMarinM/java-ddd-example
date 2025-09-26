package tv.codely.mooc.steps.domain.challenge;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import tv.codely.mooc.steps.domain.Step;
import tv.codely.mooc.steps.domain.StepId;
import tv.codely.mooc.steps.domain.StepTitle;

@EqualsAndHashCode(callSuper = true)
@Getter
public final class ChallengeStep extends Step {

    private final ChallengeStepStatement statement;

    public ChallengeStep(final StepId id, final StepTitle title, final ChallengeStepStatement statement) {
        super(id, title);
        this.statement = statement;
    }
}

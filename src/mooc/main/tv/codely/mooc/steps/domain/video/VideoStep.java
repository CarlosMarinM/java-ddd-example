package tv.codely.mooc.steps.domain.video;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import tv.codely.mooc.steps.domain.Step;
import tv.codely.mooc.steps.domain.StepId;
import tv.codely.mooc.steps.domain.StepTitle;

@EqualsAndHashCode(callSuper = true)
@Getter
public final class VideoStep extends Step {

    private final VideoUrl url;
    private final VideoStepText text;

    public VideoStep(final StepId id, final StepTitle title, final VideoUrl url, final VideoStepText text) {
        super(id, title);
        this.url = url;
        this.text = text;
    }
}

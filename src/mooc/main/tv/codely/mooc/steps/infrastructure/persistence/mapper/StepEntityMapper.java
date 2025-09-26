package tv.codely.mooc.steps.infrastructure.persistence.mapper;

import org.mapstruct.Mapper;
import tv.codely.mooc.courses.infrastructure.persistence.mapper.ValueObjectMapper;
import tv.codely.mooc.steps.domain.Step;
import tv.codely.mooc.steps.domain.challenge.ChallengeStep;
import tv.codely.mooc.steps.domain.video.VideoStep;
import tv.codely.mooc.steps.infrastructure.persistence.entity.ChallengeStepEntity;
import tv.codely.mooc.steps.infrastructure.persistence.entity.StepEntity;
import tv.codely.mooc.steps.infrastructure.persistence.entity.VideoStepEntity;

@Mapper(componentModel = "spring", uses = {ValueObjectMapper.class})
public interface StepEntityMapper {

    VideoStepEntity toEntity(VideoStep step);

    VideoStep toDomain(VideoStepEntity stepEntity);

    ChallengeStepEntity toEntity(ChallengeStep step);

    ChallengeStep toDomain(ChallengeStepEntity stepEntity);

    default StepEntity toEntity(final Step step) {
        if (step instanceof VideoStep) {
            return this.toEntity((VideoStep) step);
        }
        if (step instanceof ChallengeStep) {
            return this.toEntity((ChallengeStep) step);
        }
        throw new IllegalArgumentException("Unknown Step type: " + step.getClass().getName());
    }

    default Step toDomain(final StepEntity stepEntity) {
        if (stepEntity instanceof VideoStepEntity) {
            return this.toDomain((VideoStepEntity) stepEntity);
        }
        if (stepEntity instanceof ChallengeStepEntity) {
            return this.toDomain((ChallengeStepEntity) stepEntity);
        }
        throw new IllegalArgumentException("Unknown StepEntity type: " + stepEntity.getClass().getName());
    }

}

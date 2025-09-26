package tv.codely.mooc.courses.infrastructure.persistence.mapper;

import org.mapstruct.Mapper;
import tv.codely.mooc.courses.domain.CourseDuration;
import tv.codely.mooc.courses.domain.CourseId;
import tv.codely.mooc.courses.domain.CourseName;
import tv.codely.mooc.courses_counter.domain.CoursesCounterId;
import tv.codely.mooc.courses_counter.domain.CoursesCounterTotal;
import tv.codely.mooc.steps.domain.StepId;
import tv.codely.mooc.steps.domain.StepTitle;
import tv.codely.mooc.steps.domain.challenge.ChallengeStepStatement;
import tv.codely.mooc.steps.domain.video.VideoStepText;
import tv.codely.mooc.steps.domain.video.VideoUrl;
import tv.codely.shared.domain.ValueObject;

@Mapper(componentModel = "spring")
public interface ValueObjectMapper {

    default <T> T toValue(final ValueObject<T> vo) {
        return vo == null ? null : vo.value();
    }

    CourseId toCourseId(final String value);

    CourseName toCourseName(final String value);

    CourseDuration toCourseDuration(final String value);

    StepId toStepId(final String value);

    StepTitle toStepTitle(final String value);

    VideoUrl toVideoUrl(final String value);

    VideoStepText toVideoStepText(final String value);

    ChallengeStepStatement toChallengeStepStatement(final String value);

    CoursesCounterId toCoursesCounterId(final String value);

    CoursesCounterTotal toCoursesCounterTotal(final Integer value);

}

package tv.codely.mooc.steps.infrastructure.persistence.adapter;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import tv.codely.mooc.steps.domain.Step;
import tv.codely.mooc.steps.domain.StepId;
import tv.codely.mooc.steps.domain.challenge.ChallengeStep;
import tv.codely.mooc.steps.domain.video.VideoStep;
import tv.codely.mooc.steps.infrastructure.StepsModuleInfrastructureTestCase;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.instancio.Instancio.create;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;

class StepAdapterRepositoryTest extends StepsModuleInfrastructureTestCase {

    private static Stream<Step> steps() {
        return Stream.of(
            create(VideoStep.class),
            create(ChallengeStep.class)
        );
    }

    @Nested
    class Save {

        @ParameterizedTest
        @MethodSource("tv.codely.mooc.steps.infrastructure.persistence.adapter.StepAdapterRepositoryTest#steps")
        void givenStep_whenSave_thenRepositorySaveIsCalled(final Step step) {
            assertDoesNotThrow(() -> StepAdapterRepositoryTest.this.target.save(step));
        }
    }

    @Nested
    class Search {

        @ParameterizedTest
        @MethodSource("tv.codely.mooc.steps.infrastructure.persistence.adapter.StepAdapterRepositoryTest#steps")
        void givenExistingStep_whenSearch_thenReturnsOptionalOfStep(final Step step) {
            StepAdapterRepositoryTest.this.target.save(step);

            final var actual = StepAdapterRepositoryTest.this.target.search(step.getId());

            assertThat(actual).isPresent().contains(step);
        }

        @Test
        void givenNonExistingStep_whenSearch_thenReturnsEmptyOptional() {
            assertFalse(StepAdapterRepositoryTest.this.target.search(create(StepId.class)).isPresent());
        }
    }
}

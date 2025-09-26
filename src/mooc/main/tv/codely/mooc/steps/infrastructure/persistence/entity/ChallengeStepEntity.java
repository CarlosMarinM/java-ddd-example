package tv.codely.mooc.steps.infrastructure.persistence.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "challenge_steps")
@Data
public class ChallengeStepEntity extends StepEntity {

    private String statement;

}

package tv.codely.mooc.steps.infrastructure.persistence.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "steps")
@Data
public abstract class StepEntity {

    @Id
    private String id;
    private String title;
}

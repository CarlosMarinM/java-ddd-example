package tv.codely.mooc.steps.infrastructure.persistence.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "video_steps")
@Data
public class VideoStepEntity extends StepEntity {

    private String url;
    private String text;

}

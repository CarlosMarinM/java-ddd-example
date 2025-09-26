package tv.codely.mooc.courses.infrastructure.persistence.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "courses")
@Data
public class CourseEntity {

    @Id
    private String id;
    private String name;
    private String duration;
}

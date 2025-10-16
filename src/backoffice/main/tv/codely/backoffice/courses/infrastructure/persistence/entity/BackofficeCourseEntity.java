package tv.codely.backoffice.courses.infrastructure.persistence.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "courses")
@Data
public class BackofficeCourseEntity {

    @Id
    String id;
    String name;
    String duration;
}

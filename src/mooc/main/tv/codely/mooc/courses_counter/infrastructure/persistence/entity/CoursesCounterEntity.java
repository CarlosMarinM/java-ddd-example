package tv.codely.mooc.courses_counter.infrastructure.persistence.entity;

import lombok.Data;
import tv.codely.mooc.shared.infrastructure.persistence.StringListConverter;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "courses_counter")
@Data
public class CoursesCounterEntity {

    @Id
    private String id;
    private Integer total;
    @Convert(converter = StringListConverter.class)
    private List<String> existingCourses;

}

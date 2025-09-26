package tv.codely.mooc.courses.infrastructure.persistence.mysql;

import org.springframework.data.jpa.repository.JpaRepository;
import tv.codely.mooc.courses.infrastructure.persistence.entity.CourseEntity;

public interface CourseMySqlRepository extends JpaRepository<CourseEntity, String> {
}

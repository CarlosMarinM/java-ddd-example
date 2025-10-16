package tv.codely.backoffice.courses.infrastructure.persistence.mysql;

import org.springframework.data.jpa.repository.JpaRepository;
import tv.codely.backoffice.courses.infrastructure.persistence.entity.BackofficeCourseEntity;

public interface BackofficeCourseMySqlRepository extends JpaRepository<BackofficeCourseEntity, String> {
}

package tv.codely.mooc.courses_counter.infrastructure.persistence.mysql;

import org.springframework.data.jpa.repository.JpaRepository;
import tv.codely.mooc.courses_counter.infrastructure.persistence.entity.CoursesCounterEntity;

public interface CoursesCounterMySqlRepository extends JpaRepository<CoursesCounterEntity, String> {
}

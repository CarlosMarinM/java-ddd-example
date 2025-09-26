package tv.codely.mooc.steps.infrastructure.persistence.mysql;

import org.springframework.data.jpa.repository.JpaRepository;
import tv.codely.mooc.steps.infrastructure.persistence.entity.StepEntity;

public interface StepMySqlRepository extends JpaRepository<StepEntity, String> {
}

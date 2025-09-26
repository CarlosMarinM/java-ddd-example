package tv.codely.shared.infrastructure.persistence.mysql;

import org.springframework.data.jpa.repository.JpaRepository;
import tv.codely.shared.infrastructure.persistence.entity.DomainEventEntity;

public interface DomainEventMySqlRepository extends JpaRepository<DomainEventEntity, String> {
}

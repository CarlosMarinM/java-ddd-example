package tv.codely.shared.infrastructure.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "domain_event")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DomainEventEntity {

    @Id
    private String id;
    private String aggregateId;
    private String name;
    private String body;
    private String occurredOn;

}

package tv.codely.backoffice.courses.infrastructure.persistence.adapter;

import org.springframework.beans.factory.annotation.Qualifier;
import tv.codely.backoffice.courses.domain.BackofficeCourse;
import tv.codely.backoffice.courses.domain.BackofficeCourseRepository;
import tv.codely.backoffice.courses.infrastructure.persistence.entity.BackofficeCourseEntity;
import tv.codely.backoffice.courses.infrastructure.persistence.mapper.BackofficeCourseEntityMapper;
import tv.codely.backoffice.courses.infrastructure.persistence.mysql.BackofficeCourseMySqlRepository;
import tv.codely.shared.domain.Service;
import tv.codely.shared.domain.criteria.Criteria;
import tv.codely.shared.infrastructure.hibernate.HibernateCriteriaConverter;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BackofficeCourseAdapterRepository implements BackofficeCourseRepository {

    private final BackofficeCourseMySqlRepository repository;

    private final BackofficeCourseEntityMapper mapper;

    private final EntityManager em;

    public BackofficeCourseAdapterRepository(
        BackofficeCourseMySqlRepository repository,
        BackofficeCourseEntityMapper mapper,
        @Qualifier("backofficeEntityManagerFactory") EntityManager em) {
        this.repository = repository;
        this.mapper = mapper;
        this.em = em;
    }

    @Override
    public void save(BackofficeCourse course) {
        this.repository.save(this.mapper.toEntity(course));
    }

    @Override
    public List<BackofficeCourse> searchAll() {
        return this.repository.findAll().stream().map(this.mapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<BackofficeCourse> matching(Criteria criteria) {
        return this.byCriteria(criteria);
    }

    private List<BackofficeCourse> byCriteria(Criteria criteria) {
        final HibernateCriteriaConverter<BackofficeCourseEntity> criteriaConverter = new HibernateCriteriaConverter<>(this.em.getCriteriaBuilder());
        final CriteriaQuery<BackofficeCourseEntity> hibernateCriteria = criteriaConverter.convert(criteria, BackofficeCourseEntity.class);
        final List<BackofficeCourseEntity> resultList = this.em.createQuery(hibernateCriteria).getResultList();

        return resultList.stream().map(this.mapper::toDomain).collect(Collectors.toList());
    }
}

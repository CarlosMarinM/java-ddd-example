package tv.codely.retention.shared.infrastructure.persistence;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Objects;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    basePackages = {"tv.codely.retention.*.infrastructure.persistence"},
    entityManagerFactoryRef = "retentionEntityManagerFactory",
    transactionManagerRef = "retentionTransactionManager"
)
public class RetentionDataSourceConfiguration {

    @Bean(name = "retentionDataSource")
    public DataSource retentionDataSource() {
        return DataSourceBuilder.create()
            .url("jdbc:mysql://localhost:3306/retention")
            .username("user")
            .password("pass")
            .driverClassName("com.mysql.cj.jdbc.Driver")
            .build();
    }

    @Bean(name = "retentionEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean retentionEntityManagerFactory(@Qualifier("retentionDataSource") DataSource retentionDataSource) {
        final var em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(retentionDataSource);
        em.setPackagesToScan("tv.codely.retention.*.infrastructure.persistence.entity");
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        final var jpaProperties = new HashMap<String, Object>();
        jpaProperties.put("hibernate.hbm2ddl.auto", "update"); // Example if you need to override
        jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        jpaProperties.put("hibernate.show_sql", "true");

        em.setJpaPropertyMap(jpaProperties);

        return em;
    }

    @Bean(name = "retentionTransactionManager")
    @Primary
    public PlatformTransactionManager retentionTransactionManager(@Qualifier("retentionEntityManagerFactory") LocalContainerEntityManagerFactoryBean retentionEntityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(retentionEntityManagerFactory.getObject()));
    }
}

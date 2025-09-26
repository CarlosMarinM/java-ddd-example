package tv.codely.shared.infrastructure.persistence;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
    basePackages = {"tv.codely.shared.infrastructure.persistence"},
    entityManagerFactoryRef = "sharedEntityManagerFactory",
    transactionManagerRef = "sharedTransactionManager"
)
public class SharedDataSourceConfiguration {

    @Bean(name = "sharedDataSource")
    public DataSource sharedDataSource() {
        return DataSourceBuilder.create()
            .url("jdbc:mysql://localhost:3306/shared")
            .username("user")
            .password("pass")
            .driverClassName("com.mysql.cj.jdbc.Driver")
            .build();
    }

    @Bean(name = "sharedEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean sharedEntityManagerFactory(@Qualifier("sharedDataSource") DataSource sharedDataSource) {
        final var em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(sharedDataSource);
        em.setPackagesToScan("tv.codely.shared.infrastructure.persistence.entity");
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        final var jpaProperties = new HashMap<String, Object>();
        jpaProperties.put("hibernate.hbm2ddl.auto", "update"); // Example if you need to override
        jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        jpaProperties.put("hibernate.show_sql", "true");

        em.setJpaPropertyMap(jpaProperties);

        return em;
    }

    @Bean(name = "sharedTransactionManager")
    public PlatformTransactionManager sharedTransactionManager(@Qualifier("sharedEntityManagerFactory") LocalContainerEntityManagerFactoryBean sharedEntityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(sharedEntityManagerFactory.getObject()));
    }
}

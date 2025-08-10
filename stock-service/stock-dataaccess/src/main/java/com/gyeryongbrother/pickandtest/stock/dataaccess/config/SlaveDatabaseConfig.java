package com.gyeryongbrother.pickandtest.stock.dataaccess.config;

import jakarta.persistence.EntityManagerFactory;
import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class SlaveDatabaseConfig {

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean slaveEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("slaveDataSource") DataSource dataSource) {
        Map<String, Object> props = new HashMap<>();
        props.put("hibernate.implicit_naming_strategy",
                "org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl");
        props.put("hibernate.physical_naming_strategy",
                "org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy");
        return builder
                .dataSource(dataSource)
                .packages("com.gyeryongbrother.pickandtest.stock.dataaccess.entity")
                .persistenceUnit("slave")
                .properties(props)
                .build();
    }

    @Bean
    @Primary
    public PlatformTransactionManager slaveTransactionManager(
            @Qualifier("slaveEntityManagerFactory") EntityManagerFactory slaveEntityManagerFactory) {
        return new JpaTransactionManager(slaveEntityManagerFactory);
    }
}

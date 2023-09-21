package com.vitech.pensionCalc.config;

import java.util.Objects;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.vitech.pensionCalc.PensionCalcApplication;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
entityManagerFactoryRef = "todosEntityManagerFactory",
transactionManagerRef = "todosTransactionManager"
)

public class JpaConfiguration {
	@Bean
    public LocalContainerEntityManagerFactoryBean todosEntityManagerFactory(
      @Qualifier("getDataSource") DataSource dataSource,
      EntityManagerFactoryBuilder builder) {
        return builder
          .dataSource(dataSource)
          .build();
    }

    @Bean
    public PlatformTransactionManager todosTransactionManager(
      @Qualifier("getDataSource") 
      LocalContainerEntityManagerFactoryBean todosEntityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(todosEntityManagerFactory.getObject()));
    }
}

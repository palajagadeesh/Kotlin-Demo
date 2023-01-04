package com.project.training.assignment.employeedetails.config

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.transaction.annotation.EnableTransactionManagement
import java.util.HashMap
import javax.persistence.EntityManagerFactory
import javax.sql.DataSource
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    entityManagerFactoryRef = "db1EntityManagerFactory",
    transactionManagerRef = "db1TransactionManager",
    basePackages = ["com.project.training.assignment.employeedetails.repository.employee"]
)
class DbOneConfig {
    @Bean
    @Primary
    @ConfigurationProperties(prefix = "db1.datasource")
    fun db1DataSource(): DataSource {
        return DataSourceBuilder.create().build()
    }

    @Primary
    @Bean
    fun db1EntityManagerFactory(emfb: EntityManagerFactoryBuilder): LocalContainerEntityManagerFactoryBean {
        val properties = HashMap<String, Any?>()
        properties["hibernate.hbm2ddl.auto"] = "create"
        properties["hibernate.dialect"] = "org.hibernate.dialect.MySQL8Dialect"
        return emfb.dataSource(db1DataSource())
            .packages("com.project.training.assignment.employeedetails.model.employee")
            .properties(properties)
            .build()
    }

    @Primary
    @Bean
    fun db1TransactionManager(@Qualifier("db1EntityManagerFactory") entityManagerFactory: EntityManagerFactory?): JpaTransactionManager? {
        return entityManagerFactory?.let { JpaTransactionManager(it) }
    }
}
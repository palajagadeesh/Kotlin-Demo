package com.project.training.assignment.employeedetails.config

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
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
    entityManagerFactoryRef = "db2EntityManagerFactory",
    transactionManagerRef = "db2TransactionManager",
    basePackages = ["com.project.training.assignment.employeedetails.repository.owner"]
)
class DbTwoConfig {
    @Bean
    @ConfigurationProperties(prefix = "db2.datasource")
    fun db2DataSource(): DataSource {
        return DataSourceBuilder.create().build()
    }

    @Bean
    fun db2EntityManagerFactory(emfb: EntityManagerFactoryBuilder): LocalContainerEntityManagerFactoryBean {
        val properties = HashMap<String, Any?>()
        properties["hibernate.hbm2ddl.auto"] = "create"
        properties["hibernate.dialect"] = "org.hibernate.dialect.MySQL8Dialect"
        return emfb.dataSource(db2DataSource())
            .packages("com.project.training.assignment.employeedetails.model.owner")
            .properties(properties)
            .build()
    }

    @Bean
    fun db2TransactionManager(@Qualifier("db2EntityManagerFactory") entityManagerFactory: EntityManagerFactory?): JpaTransactionManager? {
        return entityManagerFactory?.let { JpaTransactionManager(it) }
    }
}
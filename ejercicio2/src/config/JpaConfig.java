package config;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

// Una clase de configuraciones tiene beans!
@Configuration // Es hija de @Component
@EnableTransactionManagement
public class JpaConfig {
	// El nombre del método es el id del bean
	@Bean // Define un bean
	public EntityManagerFactory entityManagerFactory() {
		return Persistence.createEntityManagerFactory("persona-jpa");
	}
	
	@Bean
	public EntityManager entityManager() {
		return entityManagerFactory().createEntityManager();
	}
	
	@Bean
	public PlatformTransactionManager transactionManager() {
		return new JpaTransactionManager(entityManagerFactory());
	}
}

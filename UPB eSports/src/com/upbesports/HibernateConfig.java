package com.upbesports;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class HibernateConfig 
{
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() 
	{
		LocalContainerEntityManagerFactoryBean em  = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource());
		em.setPackagesToScan("com.upbesports");
 
		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaProperties(additionalProperties());
 
		return em;
	}
 
	@Bean
   	public DataSource dataSource()
	{
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://upbesports.cyyybemy0h6v.us-east-2.rds.amazonaws.com:3306/upb_esports");
		dataSource.setUsername( "administrator" );
		dataSource.setPassword( "L0r3m1psumD0l0rS1tAm3t" );
		return dataSource;
	}
 
	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf)
	{
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(emf);
 
		return transactionManager;
	}
 
	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation()
	{
		return new PersistenceExceptionTranslationPostProcessor();
	}
 
   private Properties additionalProperties() 
   {
       Properties properties = new Properties();
       properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
       properties.setProperty("hibernate.show_sql", "true");
       properties.setProperty("hibernate.format_sql", "true");
        
       return properties;
   }
}
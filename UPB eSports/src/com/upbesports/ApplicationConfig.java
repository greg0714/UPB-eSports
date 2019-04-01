package com.upbesports;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@PropertySource(value={"classpath:/main/resources/application.properties"})
public class ApplicationConfig 
{
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() 
	{
		LocalContainerEntityManagerFactoryBean em  = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource());
		em.setPackagesToScan("com.upbesports");
 
		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
 
		return em;
	}
 
	@Bean
   	public DataSource dataSource()
	{
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://upbesports.cyyybemy0h6v.us-east-2.rds.amazonaws.com:3306/upb_esports");
		dataSource.setUsername( "administrator" );
		dataSource.setPassword( "L0r3m1psumD0l0rS1tAm3t" );
		return dataSource;
	}
}
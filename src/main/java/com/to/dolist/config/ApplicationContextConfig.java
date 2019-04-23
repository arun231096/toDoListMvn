package com.to.dolist.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
 
@Configuration
@ComponentScan("com.to.dolist.*")
 
@EnableTransactionManagement
 
public class ApplicationContextConfig {
 
 
   @Bean(name = "viewResolver")
   public InternalResourceViewResolver getViewResolver() {
       InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
 
       viewResolver.setPrefix("/WEB-INF/jsp/");
       viewResolver.setSuffix(".jsp");
 
       return viewResolver;
   }
 
   @Bean(name = "dataSource")
   public DataSource getDataSource() {

       DriverManagerDataSource dataSource = new DriverManagerDataSource();
       dataSource.setDriverClassName("com.mysql.jdbc.Driver");
       dataSource.setUrl("jdbc:mysql://rpc71:3306/arunkumar");
       dataSource.setUsername("root");
       dataSource.setPassword("admin"); 
       System.out.println("## getDataSource: " + dataSource);
       return dataSource;
   }
 
   @Bean(name = "transactionManager")
   public DataSourceTransactionManager getTransactionManager() {
       DataSourceTransactionManager txManager = new DataSourceTransactionManager();
 
       DataSource dataSource = this.getDataSource();
       txManager.setDataSource(dataSource);
 
       return txManager;
   }
 
}

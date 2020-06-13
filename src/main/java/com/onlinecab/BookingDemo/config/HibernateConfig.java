package com.onlinecab.BookingDemo.config;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class HibernateConfig {

   /* @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    @Bean
    public SessionFactory getSessionFactory(){
        if(entityManagerFactory.unwrap(SessionFactory.class) == null){
            throw new NullPointerException("SessionFactory object not present");
        }
        return entityManagerFactory.unwrap(SessionFactory.class);
    }*/

    @Value("${spring.datasource.driver-class-name}")
    private String driverClass;
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;
    @Value("${spring.jpa.database-platform}")
    private String dialect;

    @Bean
    public DataSource getDataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource(url, username, password);
        dataSource.setDriverClassName(driverClass);
        return dataSource;
    }

    public Properties hibernateProperties(){
        Properties properties = new Properties();
        properties.putIfAbsent("hibernate.dialect", dialect);
        properties.putIfAbsent("hibernate.hbm2ddl.auto", "update");
        properties.putIfAbsent("hibernate.show_sql","true");
        return properties;
    }

    @Bean
    public LocalSessionFactoryBean getSessionFactory(){
        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
        localSessionFactoryBean.setDataSource(getDataSource());
        localSessionFactoryBean.setHibernateProperties(hibernateProperties());
        localSessionFactoryBean.setPackagesToScan(new String[] {"com.onlinecab.BookingDemo"});
        return localSessionFactoryBean;
    }

    @Bean
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory){
        HibernateTransactionManager manager =  new HibernateTransactionManager();
        manager.setSessionFactory(sessionFactory);
        return manager;
    }
}

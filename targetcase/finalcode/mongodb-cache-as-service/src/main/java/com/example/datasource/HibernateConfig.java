package com.example.datasource;

/**
 * Created by summit on 9/7/17.
 */
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableConfigurationProperties(DataSourceProperties.class)
@ComponentScan(basePackages = {"com.example.entity"})
public class HibernateConfig {
    @Autowired
    private Environment env;


    @Autowired
    private DataSourceProperties dataSourceProperties;

    public HibernateConfig() {
        super();
    }
    @Qualifier("sessionFactory")
    @Bean
    public LocalSessionFactoryBean sessionFactory() throws NamingException {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(restDataSource());
        sessionFactory.setPackagesToScan(new String[] { "com.example.entity" });
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

    @Bean
    @Qualifier("mysqlDataSource")
    public DataSource restDataSource() throws NamingException {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(dataSourceProperties.getDriver().trim());
        dataSource.setUrl(dataSourceProperties.getUrl().trim());
        dataSource.setUsername(dataSourceProperties.getUsername().trim());
        dataSource.setPassword(dataSourceProperties.getPassword());
        return dataSource;
    }

    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory);

        return txManager;
    }

    Properties hibernateProperties() {
        return new Properties() {
            private static final long serialVersionUID = 1L;
            {
                System.out.println("Faco : "+dataSourceProperties.getFactoryClass().trim());
                setProperty("hibernate.show_sql", String.valueOf(dataSourceProperties.isShowSql()));
                setProperty("hibernate.dialect",dataSourceProperties.getDialect().trim());
                setProperty("hibernate.enable_lazy_load_no_trans", String.valueOf(dataSourceProperties.getEnableLazyLoadNoTrans()));
//                setProperty("hibernate.cache.use_second_level_cache",dataSourceProperties.getUseSecondLevelCache());
//                setProperty("hibernate.cache.use_query_cache",dataSourceProperties.getUseQueryCache());
//                setProperty("hibernate.cache.region.factory_class",dataSourceProperties.getFactoryClass().trim());
                //setProperty("net.sf.ehcache.configurationResourceName","cache.xml");
                //setProperty("hibernate.cache.provider_class",dataSourceProperties.getProviderClass().trim());
            }
        };
    }
}

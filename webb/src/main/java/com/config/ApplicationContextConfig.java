package com.config;


import com.services.AccountService;
import com.services.OrderService;
import com.services.ProductService;
import com.services.impl.AccountServiceImpl;
import com.services.impl.OrderServiceImpl;
import com.services.impl.ProductServiceImpl;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = "com")
@EnableTransactionManagement
@PropertySource("classpath:ds-hibernate-cfg.properties")
//@EnableCaching
public class ApplicationContextConfig {

    private static final Logger log = Logger.getLogger(ApplicationContextConfig.class);

    /**
     * The Environment class serves as the property holder
     * and stores all the properties loaded by the @PropertySource
     */
    @Autowired
    private Environment env;

    /**
     * Load property in message/validator.properties
     *
     * @return ResourceBundleMessageSource
     */
    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource rb = new ResourceBundleMessageSource();
        rb.setBasenames(new String[]{"messages/validator", "i18n/messages"});
        return rb;
    }

    /**
     * Config property view resolver
     *
     * @return Internal Resource View Resolver
     */
    @Bean(name = "viewResolver")
    public InternalResourceViewResolver getViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/pages/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    /**
     * Config for Upload.
     *
     * @return commonsMultipartResolver
     */
    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
        return commonsMultipartResolver;
    }

    /**
     * Config property for DataBase.
     *
     * @return DataSource
     */
    @Bean(name = "dataSource")
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("ds.database-driver"));
        dataSource.setUrl(env.getProperty("ds.url"));
        dataSource.setUsername(env.getProperty("ds.username"));
        dataSource.setPassword(env.getProperty("ds.password"));
        log.info("## getDataSource: " + dataSource);
        return dataSource;
    }

    @Autowired
    @Bean(name = "sessionFactory")
    public SessionFactory getSessionFactory(DataSource dataSource) throws Exception {
        log.info("getSessionFactory :");
        Properties properties = new Properties();
        properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
        properties.put("current_session_context_class", env.getProperty("current_session_context_class"));
        properties.put("hibernate.connection.isolation", env.getProperty("hibernate.connection.isolation"));

//        properties.put("hibernate.cache.use_query_cache", env.getProperty("hibernate.cache.use_query_cache"));
//        properties.put("hibernate.cache.use_second_level_cache", env.getProperty("hibernate.cache.use_second_level_cache"));
//        properties.put("hibernate.cache.region.factory_class", env.getProperty("hibernate.cache.region.factory_class"));
//        properties.put("net.sf.ehcache.configurationResourceName", env.getProperty("net.sf.ehcache.configurationResourceName"));

        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        factoryBean.setPackagesToScan(new String[]{"com.entity"});
        factoryBean.setDataSource(dataSource);
        factoryBean.setHibernateProperties(properties);
        factoryBean.afterPropertiesSet();
        SessionFactory sf = factoryBean.getObject();
        log.info("## getSessionFactory: " + sf);
        return sf;
    }

    @Autowired
    @Bean(name = "transactionManager")
    public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
        return transactionManager;
    }


    @Bean(name = "accountService")
    public AccountService getApplicantService() {
        return new AccountServiceImpl();
    }

    @Bean(name = "productService")
    public ProductService getProductService() {
        return new ProductServiceImpl();
    }

    @Bean(name = "orderService")
    public OrderService getOrderService() {
        return new OrderServiceImpl();
    }

    @Bean(name = "accountService")
    public AccountService getAccountDAO() {
        return new AccountServiceImpl();
    }

}

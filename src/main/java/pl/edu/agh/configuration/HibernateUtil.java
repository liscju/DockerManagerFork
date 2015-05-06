package pl.edu.agh.configuration;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    private static final String hibernateDialect = "org.hibernate.dialect.H2Dialect";
    private static final String hibernateShowSql = "true";
    private static final String hibernateHbm2ddlAuto = "create";

    private static DataSource getDataSource() {
        DataSource dataSource = new EmbeddedDatabaseBuilder()
                .setName("DockerManagerDB")
                .setType(EmbeddedDatabaseType.H2)
                .build();
        return dataSource;
    }


    private static Properties getHibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect",hibernateDialect);
        properties.put("hibernate.show_sql",hibernateShowSql);
        properties.put("hibernate.hbm2ddl.auto",hibernateHbm2ddlAuto);
        return properties;
    }

    private static SessionFactory initializeSessionFactory() {
        try {
            LocalSessionFactoryBean asfb = new LocalSessionFactoryBean();
            asfb.setDataSource(getDataSource());
            asfb.setHibernateProperties(getHibernateProperties());
            asfb.setPackagesToScan(new String[]{"pl.edu.agh"});
            asfb.afterPropertiesSet();
            return asfb.getObject();
        }catch (HibernateException he) {
            System.err.println("Error creating Session: " + he);
            throw new ExceptionInInitializerError(he);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = initializeSessionFactory();
        }
        return sessionFactory;
    }
}






















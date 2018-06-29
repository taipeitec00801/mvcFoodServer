package config;

import java.beans.PropertyVetoException;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@PropertySource(value= {"classpath:/config/db.properties"},
				ignoreResourceNotFound = true)
@EnableTransactionManagement
public class RootAppConfig {
	@Value("${jdbc.user}")
	String user;
	
	@Value("${jdbc.password}")
	String password;
	
	@Value("${jdbc.driverClass}")
	String driverClass;
	
	@Value("${jdbc.jdbcUrl}")
	String jdbcUrl;
	
	@Value("${jdbc.initPoolSize}")
	Integer initPoolSize;
	
	@Value("${jdbc.maxPoolSize}")
	Integer maxPoolSize;

	@Bean
	public DataSource dataSource() {
		ComboPooledDataSource cpdSource = new ComboPooledDataSource();
		cpdSource.setUser(user);
		cpdSource.setPassword(password);
		try {
			cpdSource.setDriverClass(driverClass);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
		cpdSource.setJdbcUrl(jdbcUrl);
		cpdSource.setInitialPoolSize(initPoolSize);
		cpdSource.setMaxPoolSize(maxPoolSize);
		
		return cpdSource;
	}
	
	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean factory = new LocalSessionFactoryBean();
		factory.setDataSource(dataSource());
		factory.setPackagesToScan(new String[] {
				"member.Model",
				"other.Model"
				});
		factory.setHibernateProperties(additionalProperties());
		
		return factory;
	}
	
	@Bean
	public Properties additionalProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", org.hibernate.dialect.MySQL5InnoDBDialect.class);
		properties.put("hibernate.show_sql", Boolean.TRUE);
		properties.put("hibernate.format_sql", Boolean.TRUE);
		properties.put("default_batch_fetch_size", 10);
		properties.put("hibernate.hbm2ddl.auto", "update");
		
		return properties;		
	}
	
	@Bean
	public HibernateTransactionManager transactionManager() {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory().getObject());
		
		return txManager;
	}	
}

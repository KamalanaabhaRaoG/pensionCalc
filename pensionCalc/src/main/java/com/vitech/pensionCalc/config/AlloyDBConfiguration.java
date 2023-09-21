package com.vitech.pensionCalc.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class AlloyDBConfiguration {
	@Bean
	@ConfigurationProperties("spring.datasource")
	public DataSourceProperties alloyDBDataSourceProperties() {
		return new DataSourceProperties();
	}
	@Bean
	public static HikariDataSource getDataSource() {
	    HikariConfig config = new HikariConfig();

	    // There is no need to set a host on the JDBC URL
	    // since the Connector will resolve the correct IP address.
	    config.setJdbcUrl("jdbc:postgresql:///postgres");
	    config.setUsername("postgres");
	    config.setPassword("Temp1234");

	    // Tell the driver to use the AlloyDB Java Connector's SocketFactory
	    // when connecting to an instance/
	    config.addDataSourceProperty("socketFactory",
	        "com.google.cloud.alloydb.SocketFactory");
	    // Tell the Java Connector which instance to connect to.
	    config.addDataSourceProperty("alloydbInstanceName",
	       "projects/testalloydb-399019/locations/us-central1-b/clusters/my-cluster/instances/my-primary");

	   return new HikariDataSource(config);
	  }
	
	@Bean
	public JdbcTemplate alloyDBJdcbcTemplate(@Qualifier("getDataSource") DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

}

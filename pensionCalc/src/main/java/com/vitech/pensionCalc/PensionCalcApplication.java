package com.vitech.pensionCalc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@SpringBootApplication
public class PensionCalcApplication {

	public static void main(String[] args) {
		SpringApplication.run(PensionCalcApplication.class, args);
	}
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
	       "projects/TestAloyDB/locations/us-central1/clusters/my-cluster/instances/my-primary");

	   return new HikariDataSource(config);
	  }

}

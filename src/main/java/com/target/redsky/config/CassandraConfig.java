package com.target.redsky.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.cassandra.CassandraProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;


@Configuration
@EnableConfigurationProperties(CassandraProperties.class)
@EnableCassandraRepositories("com.target.redsky.repository")
public class CassandraConfig extends AbstractCassandraConfiguration {
	

	@Value("${spring.data.cassandra.keyspace-name}")
	private String keySpace;

	@Value("${spring.data.cassandra.contact-points}")
	private String contactPoints;

	@Value("${spring.data.cassandra.port}")
	private int port;

	@Value("${spring.data.cassandra.username}")
	private String userName;

	@Value("${spring.data.cassandra.password}")
	private String password;
	
	 @Value("${cassandra.basePackages}")
	  private String basePackages;
	 
	 @Value("${cassandra.hosts}")
		private String cassandraHosts;

	@Override
	  protected String getKeyspaceName() {
	    return keySpace;
	  }

	  @Override
	  protected String getContactPoints() {
	    return contactPoints;
	  }

	

	  @Override
	  public SchemaAction getSchemaAction() {
	    return SchemaAction.CREATE_IF_NOT_EXISTS;
	  }

	  @Override
	  public String[] getEntityBasePackages() {
	    return new String[] {"com.target.redsky.repository"};
	  }
	  
				  
	  @Override
	  protected boolean getMetricsEnabled() { return false; }
	 
	 
	  
	  
	  
	  
		  
}

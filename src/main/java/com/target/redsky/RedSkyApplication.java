package com.target.redsky;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@ComponentScan(basePackages = { "com.target.redsky" })
@EntityScan(basePackages = { "com.target.redsky" })
@EnableCassandraRepositories(basePackages = { "com.target.redsky" })
@SpringBootApplication
public class RedSkyApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedSkyApplication.class, args);

	}
}
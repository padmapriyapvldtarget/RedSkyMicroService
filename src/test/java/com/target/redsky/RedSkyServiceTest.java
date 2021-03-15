package com.target.redsky;

import org.cassandraunit.spring.CassandraDataSet;




import org.cassandraunit.spring.CassandraUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.target.redsky.constants.JsonConstants;
import com.target.redsky.controller.RedSkyController;
import com.target.redsky.entity.RedSkyEntity;
import com.target.redsky.model.RedSky;
import com.target.redsky.service.RedSkyService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.lang.reflect.Type;
import java.util.ArrayList;

import org.springframework.test.context.ContextConfiguration;

@RunWith(JUnitPlatform.class)
@SpringBootTest({ "spring.data.cassandra.port=9042", "spring.data.cassandra.keyspace-name=redsky1" })
@EnableAutoConfiguration
@ComponentScan
@ContextConfiguration
@CassandraDataSet(value = { "cassandra-init.sh" }, keyspace = "redsky1")
@CassandraUnit
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RedSkyServiceTest {

	private MockMvc mockMvc;

	@Autowired
	private RedSkyService redSkyService;

	@Autowired
	private RedSkyController redSkyController;
	
	



	@BeforeEach
	public void init() {
		redSkyController.setRedSkyService(redSkyService);

		this.mockMvc = MockMvcBuilders.standaloneSetup(redSkyController).build();
		
	}


	@Test
	@Order(1)  
	public void saveRedSky() throws Exception {

		String mockRedSkyJson = JsonConstants.mockRedSkyJson;
		Gson gson = new Gson();

		Type redSkyListType = new TypeToken<ArrayList<RedSky>>() {
		}.getType();

		ArrayList<RedSky> redSkyList = gson.fromJson(mockRedSkyJson, redSkyListType);

		for (int i = 0; i < redSkyList.size(); i++) {

			RedSky redSky = redSkyList.get(i);

	
		mockMvc.perform(MockMvcRequestBuilders.post("/redsky/save").content(asJsonString(redSky))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
		}
				
	}
	
	@Test
	@Order(2)  
	public void getProductById() throws Exception {

		mockMvc.perform(get("/redsky/getRedSky/{id}", "1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

	}

	@Test
	@Order(3)  
	public void getAllProducts() throws Exception {

		mockMvc.perform(get("/redsky/getAllProducts").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}


	@Test
	@Order(4)  
	public void updateRedSkyAPI() throws Exception {
		String mockRedSkyJson = JsonConstants.mockRedSkyJson;
		
		Gson gson = new Gson();

		Type redSkyListType = new TypeToken<ArrayList<RedSkyEntity>>() {
		}.getType();

		ArrayList<RedSkyEntity> redSkyList = gson.fromJson(mockRedSkyJson, redSkyListType);

				mockMvc.perform(MockMvcRequestBuilders.put("/redsky/updateRedSky/{id}", "1").content(asJsonString(redSkyList.get(0)))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.name").value(redSkyList.get(0).getName()));


		
	}
	
	@Test
	@Order(5)  
	public void deleteRedSkyAPI() throws Exception 
	{
		mockMvc.perform( MockMvcRequestBuilders.delete("/redsky/deleteRedSky/{id}", "1") )
	        .andExpect(status().isNoContent());
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
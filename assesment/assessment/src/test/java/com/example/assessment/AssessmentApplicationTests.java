package com.example.assessment;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//testing for the application
@SpringBootTest
@AutoConfigureMockMvc
class AssessmentApplicationTests {
	@Autowired
	private MockMvc mockMvc;


	@Test
	void contextLoads() {
	}
	@Test
	void ValidNameForFirstHalf() throws Exception {
		mockMvc.perform(get("/hello-world").param("name", "Alice"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.message").value("Hello Alice"));
	}

	@Test
	void IsMessageReturnsForInvalidName() throws Exception {
		mockMvc.perform(get("/hello-world").param("name", "Zara"))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.error").value("Invalid Input"));
	}

	@Test
	void IsMessageReturnsForMissingValue() throws Exception {
		mockMvc.perform(get("/hello-world"))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.error").value("Invalid Input"));
	}

	@Test
	void IsMessageReturnForEmpty() throws Exception {
		mockMvc.perform(get("/hello-world").param("name", " "))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.error").value("Invalid Input"));
	}
}

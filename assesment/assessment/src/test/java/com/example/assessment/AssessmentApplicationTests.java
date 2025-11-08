package com.example.assessment;

import com.example.assessment.Service.GetNameService;
import com.example.assessment.controller.GetNameController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(GetNameController.class)
class AssessmentApplicationTests {
	@Autowired
	private MockMvc mockMvc;

	@MockitoBean
	private GetNameService helloService;

	@Test
	void GetNameStartsWithAtoM() throws Exception {
		when(helloService.GetName("Alice")).thenReturn("Hello Alice");

		mockMvc.perform(get("/hello-world")
						.param("name", "Alice")
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.message").value("Hello Alice"))
				.andExpect(jsonPath("$.error").doesNotExist());
	}

	@Test
	void GetNameStartsWithNtoZ() throws Exception {
		when(helloService.GetName("Zara")).thenReturn(null);

		mockMvc.perform(get("/hello-world")
						.param("name", "Zara")
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.error").value("Invalid Input"))
				.andExpect(jsonPath("$.message").doesNotExist());
	}

	@Test
	void GetNameIsEmpty() throws Exception {
		when(helloService.GetName("")).thenReturn(null);

		mockMvc.perform(get("/hello-world")
						.param("name", "")
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.error").value("Invalid Input"));
	}

	@Test
	void GetParameterIsMissing() throws Exception {
		mockMvc.perform(get("/hello-world")
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.error").value("Invalid Input"));
	}
}

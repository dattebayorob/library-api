package com.dtb.librayapi.resource;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.dtb.librayapi.model.constants.Endpoints;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class BookControllerTest {
	MockMvc mockMvc;
	
	@BeforeEach
	public void beforeAll() {
		BookController bookController = new BookController();
		mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
	}
	
	@Test
	@DisplayName("Should create a book successfully")
	public void createBookTest() throws Exception {
		
		String json = new ObjectMapper().writeValueAsString(null);
		
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders
				.post(Endpoints.BOOK_API)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(json);
		
		mockMvc
			.perform(request)
			.andExpect( status().isCreated() )
			.andExpect( jsonPath("id").isNotEmpty() )
			.andExpect( jsonPath("title").value("My Book") )
			.andExpect( jsonPath("author").value("Author") )
			.andExpect( jsonPath("isbn").value("12345") );
	}
}

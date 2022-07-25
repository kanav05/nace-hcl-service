package com.hcl.dbclm.controller;


import com.hcl.dbclm.entity.Nace;
import com.hcl.dbclm.exceptions.NaceResourceNotFoundException;
import com.hcl.dbclm.service.NaceService;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(NaceController.class)
public class NaceControllerTests {
	
	 @MockBean
	  NaceService naceService;
	 
	  @Autowired
	  MockMvc mockMvc;

	@Test
	void getNaceDetails_shouldReturnNaceDetails() throws Exception {

		given(naceService.findByNaceCode(anyString())).willReturn(Nace.builder().level("1").description("AGRICULTURE, FORESTRY AND FISHING").build());

		mockMvc.perform(MockMvcRequestBuilders.get("/api/nace/A"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("level").value("1"))
				.andExpect(jsonPath("description").value("AGRICULTURE, FORESTRY AND FISHING"));
	}

	@Test
	void getNaceDetails_notFound()throws Exception{
		given(naceService.findByNaceCode(anyString())).willThrow(new NaceResourceNotFoundException());

		mockMvc.perform(MockMvcRequestBuilders.get("/api/nace/A"))
				.andExpect(status().isNotFound());
	}

	@Test
	void givenNaceDetails_create_shouldSaveNaceDetailsToDb() throws Exception {
		JSONObject naceJsonObject = new JSONObject();
		naceJsonObject.put("level","1");
		naceJsonObject.put("naceCode","A");
		naceJsonObject.put("description","AGRICULTURE, FORESTRY AND FISHING");

		given(naceService.create(any())).willReturn(Nace.builder().level("1").naceCode("A").description("AGRICULTURE, FORESTRY AND FISHING").build());
		mockMvc.perform(MockMvcRequestBuilders.post("/api/nace/createNace").contentType(MediaType.APPLICATION_JSON).content(naceJsonObject.toString()))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("level").value("1"))
				.andExpect(jsonPath("naceCode").value("A"))
				.andExpect(jsonPath("description").value("AGRICULTURE, FORESTRY AND FISHING"));
	}

}

package com.hcl.dbclm.integration;


import com.hcl.dbclm.entity.Nace;
import com.hcl.dbclm.repository.NaceRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
public class IntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private NaceRepository naceRepository;

    @Test
    void givenNaceCode_getNaceDetails_shouldReturnNaceDetailsForGivenNaceCode() {
        naceRepository.deleteAll();
        naceRepository.save(Nace.builder().level("1").description("AGRICULTURE, FORESTRY AND FISHING").naceCode("A").build());

        ResponseEntity<Nace> response = restTemplate.getForEntity("/api/nace/A", Nace.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getLevel()).isEqualTo("1");
        assertThat(response.getBody().getDescription()).isEqualTo("AGRICULTURE, FORESTRY AND FISHING");
    }

    @Test
    void givenNaceDetails_putNaceDetails_shouldSaveNaceDetailsToDb() throws JSONException {
        JSONObject naceJsonObject = new JSONObject();
        naceJsonObject.put("level","1");
        naceJsonObject.put("naceCode","A");
        naceJsonObject.put("description","AGRICULTURE, FORESTRY AND FISHING");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<>(naceJsonObject.toString(), headers);

        ResponseEntity<Nace> response = restTemplate.postForEntity("/api/nace/createNace", request, Nace.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody().getNaceOrder()).isNotNull();
        assertThat(response.getBody().getLevel()).isEqualTo("1");
        assertThat(response.getBody().getNaceCode()).isEqualTo("A");
        assertThat(response.getBody().getDescription()).isEqualTo("AGRICULTURE, FORESTRY AND FISHING");
    }

    @Test
    @Disabled
    void givenInvalidNaceCode_getNaceDetails_shouldReturn404(){
        ResponseEntity<Nace> response = restTemplate.getForEntity("/api/nace/AN", Nace.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}

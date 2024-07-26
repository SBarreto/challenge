package com.directa24.main.challenge.integration;

import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc(addFilters = false)
@AutoConfigureWireMock(port = 0)
@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:application-test.properties")
class DirectorsIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    void setup() {
        WireMock.reset();
    }

    @Test
    @DisplayName("Consume movies service and return 200 OK with directors that made more than 4 movies")
    void get_directors_returns200_for_4_movies_threshold() throws Exception {
        WireMock.stubFor(WireMock.get(urlPathMatching("/api/movies/search"))
                .withQueryParam("page", WireMock.matching("(10|[0-9])"))
                .willReturn(aResponse()
                        .withBodyFile("movies_ok_response.json")
                        .withStatus(200)));
        mockMvc.perform(MockMvcRequestBuilders.get("/directa24/challenge/api/directors")
                        .param("threshold", "4"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.directors[:1]").value("John Doe"))
                .andReturn();

    }

    @Test
    @DisplayName("Consume movies service and return 500 Server error when exception is arisen")
    void get_directors_returns500_for_service_error() throws Exception {
        WireMock.stubFor(WireMock.get(urlPathMatching("/api/movies/search"))
                .withQueryParam("page", WireMock.matching("(10|[0-9])"))
                .willReturn(aResponse()
                        .withBody("There was an error trying to fetch movies from api")
                        .withStatus(500)));
        mockMvc.perform(MockMvcRequestBuilders.get("/directa24/challenge/api/directors")
                        .param("threshold", "4"))
                .andExpect(status().isInternalServerError());
    }

    @Test
    @DisplayName("Consume movies service and return 400 bad request when a bad parameter is received in request")
    void get_directors_returns400_for_bad_request() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/directa24/challenge/api/directors")
                        .param("threshold", "hey"))
                .andExpect(status().isBadRequest());
    }
}

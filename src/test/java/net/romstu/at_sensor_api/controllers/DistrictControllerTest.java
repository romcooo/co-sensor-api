package net.romstu.at_sensor_api.controllers;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.RequestPostProcessor;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class DistrictControllerTest {
    @Autowired
    private MockMvc mockMvc;

    // should be taken from TestDataLoader
    private final RequestPostProcessor wienAuth = httpBasic("Wien", "testpass");

    @Test
    public void validGet_returns200() throws Exception {
        mockMvc.perform(get("/districts")
                .contentType(MediaType.APPLICATION_JSON)
                .with(wienAuth))
                .andExpect(status().isOk())
                // This should also be taken from the mock data source
                .andExpect(jsonPath("$[0].sensors", Matchers.hasSize(2)))
                .andExpect(jsonPath("$[0].name", Matchers.is("Wahring")));
    }
}

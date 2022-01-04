package net.romstu.at_sensor_api.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.romstu.at_sensor_api.domain.City;
import net.romstu.at_sensor_api.test_dto.TestRequestCity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CityControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    private static final String ENDPOINT = "/cities";
    private final TestRequestCity paris = new TestRequestCity(new City("Paris", "hunter1"));
    private final RequestPostProcessor parisAuth = httpBasic(paris.getName(), paris.getPassword());
    private final RequestPostProcessor parisBadAuth = httpBasic(paris.getName(), "wrongPassword");
    private final TestRequestCity parisAgain = new TestRequestCity(new City(paris.getName(), "hunter2"));
    private final TestRequestCity brno = new TestRequestCity(new City("Brno", "hunter1"));


    @Test
    @Transactional
    public void validPost_returns204() throws Exception {
        mockMvc.perform(post(ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(paris)))
                .andExpect(status().isNoContent());
        mockMvc.perform(post(ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(brno)))
                .andExpect(status().isNoContent());
    }

    @Test
    @Transactional
    public void invalidPost_existingName_returns403() throws Exception {
        mockMvc.perform(post(ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(paris)))
                .andExpect(status().isNoContent());
        mockMvc.perform(post(ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(parisAgain)))
                .andExpect(status().isForbidden());
    }

    @Test
    @Transactional
    public void validPatch_returns204() throws Exception {
        mockMvc.perform(post(ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(paris)))
                .andExpect(status().isNoContent());
        var oldPass = paris.getPassword();
        var newPass = "newPass";
        var authWithNewPass = httpBasic(paris.getName(), newPass);
        mockMvc.perform(patch(ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new TestRequestCity(newPass)))
                .with(parisAuth))
                .andExpect(status().isNoContent());
        mockMvc.perform(patch(ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new TestRequestCity(oldPass)))
                .with(authWithNewPass))
                .andExpect(status().isNoContent());
    }

    @Test
    @Transactional
    public void invalidPatch_returns401() throws Exception {
        mockMvc.perform(post(ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(paris)))
                .andExpect(status().isNoContent());
        var patchPassword = new TestRequestCity("newPass");
        mockMvc.perform(patch(ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(patchPassword))
                .with(parisBadAuth))
                .andExpect(status().isUnauthorized());
    }
}

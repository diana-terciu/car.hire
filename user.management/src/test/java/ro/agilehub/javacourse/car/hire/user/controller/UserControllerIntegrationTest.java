package ro.agilehub.javacourse.car.hire.user.controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import ro.agilehub.javacourse.car.hire.api.model.CreatedDTO;
import ro.agilehub.javacourse.car.hire.api.model.UserDTO;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("integrationtest")
public class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser("jack")
    public void whenAddUserOk_thenFindById() throws Exception {
        final String email = "diana.terciu@email.com";
        var input = new UserDTO().email(email);

        var postResult = mvc.perform(post("/users")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isCreated())
                .andReturn();

        var createdDTO = objectMapper.readValue(postResult.getResponse().getContentAsString(), CreatedDTO.class);

        var getResult = mvc.perform(get("/users/" + createdDTO.getId()))
                .andExpect(status().isOk())
                .andReturn();

        var userDTO = objectMapper.readValue(getResult.getResponse().getContentAsString(), UserDTO.class);

        assertEquals(email, userDTO.getEmail());
    }

}

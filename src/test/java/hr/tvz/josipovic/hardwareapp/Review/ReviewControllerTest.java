package hr.tvz.josipovic.hardwareapp.Review;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import hr.tvz.josipovic.hardwareapp.Hardware.Hardware;
import hr.tvz.josipovic.hardwareapp.Hardware.HardwareDTO;
import hr.tvz.josipovic.hardwareapp.Hardware.HardwareService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ReviewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ReviewService reviewService;


    @Test
    //@WithMockUser(username = "usr" , password = "user" , roles = "USER")
    void getAllReviews() throws Exception {
        List <ReviewDTO> hwList = reviewService.getAllReviews();
        String jwt = loginAsAdmin();
        String responseBody = mockMvc.perform(get("/review").header("Authorization", "Bearer " + jwt)
                .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].title").value(hwList.get(0).getTitle()))
                .andExpect(jsonPath(("$[1].hwCode")).value(hwList.get(1).getHwCode()))
                .andExpect(jsonPath(("$[2].rating")).value(hwList.get(2).getRating()))
                .andExpect(jsonPath(("$[3].text")).value(hwList.get(3).getText()))
                .andReturn().getResponse().getContentAsString();

    }

    @Test
    void getAllReviewsByHardwareCode() throws Exception {
        String hwCode = "I57300HQ";
        List <ReviewDTO> hwList = reviewService.getAllbyHardwareCode(hwCode);
        String jwt = loginAsAdmin();
        mockMvc.perform(get("/review/?code="+hwCode).header("Authorization", "Bearer " + jwt)
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(hwList.size())))
                .andExpect(jsonPath("$[0].hwCode").value("I57300HQ"))
                .andExpect(jsonPath("$[1].hwCode").value("I57300HQ"))
                .andExpect(jsonPath("$[2]").doesNotExist());
    }
    @Test
    void getAllReviewsByHardwareCodeNone() throws Exception {
        String hwCode = "I67300HQ";
        //List <ReviewDTO> hwList = reviewService.getAllbyHardwareCode(hwCode);
        String jwt = loginAsAdmin();
        mockMvc.perform(get("/review/?code="+ hwCode).header("Authorization", "Bearer " + jwt)
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isEmpty());

    }

    @Test
    void getAllbyRating() throws Exception {

        List <ReviewDTO> hwList = reviewService.getByRating(2,3);
        Integer fRating = hwList.get(0).getRating();
        String jwt = loginAsAdmin();
        mockMvc.perform(get("/review/?fromto=2-3").header("Authorization", "Bearer " + jwt)
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(hwList.size())))
                .andExpect(jsonPath("$[0].rating").value(fRating));

    }


    private String loginAsUser() throws Exception {

        Map<String, Object> body = new HashMap<>();
        body.put("username", "usr");
        body.put("password", "user");
        String resultJWT = mockMvc.perform(
                        post("/authentication/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(body))

                ).andReturn()
                .getResponse()
                .getContentAsString();
        String jwt = String.valueOf(jsonPath("jwt", resultJWT));
        System.out.println(jwt);
        return jwt;

    }

    private String loginAsAdmin() throws Exception {

        Map<String, Object> body = new HashMap<>();
        body.put("username", "admin");
        body.put("password", "admin");
        String resultJWT = mockMvc.perform(
                        post("/authentication/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(body))

                ).andReturn()
                .getResponse()
                .getContentAsString();
        String jwt = JsonPath.read(resultJWT, "jwt");
        System.out.println(jwt);
        return jwt;
    }



}
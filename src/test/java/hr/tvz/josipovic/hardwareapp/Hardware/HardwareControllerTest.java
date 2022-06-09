package hr.tvz.josipovic.hardwareapp.Hardware;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import hr.tvz.josipovic.hardwareapp.Review.ReviewDTO;
import hr.tvz.josipovic.hardwareapp.Review.ReviewService;
import hr.tvz.josipovic.hardwareapp.Type;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@SpringBootTest
@AutoConfigureMockMvc
@SuppressWarnings("deprecation")
class HardwareControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private HardwareService hardwareService;

    @Test
    void getAlHardware() throws Exception {

        List<HardwareDTO> hwList = hardwareService.findAll();
        String jwt = loginAsUser();
        String responseBody = mockMvc.perform(get("/hardware").header("Authorization", "Bearer " + jwt)
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value(hwList.get(0).getName()))
                .andExpect(jsonPath(("$[1].price")).value(hwList.get(1).getPrice()))
                .andExpect(jsonPath(("$[2].code")).value(hwList.get(2).getCode()))
                .andReturn().getResponse().getContentAsString();
    }

    @Test
    void getHardwareByCode() throws Exception {
        String code = "NRTX2070";
        Optional<HardwareDTO> hw = hardwareService.findbyCode(code);
        String jwt = loginAsAdmin();
        mockMvc.perform(get("/hardware/"+code).header("Authorization", "Bearer " + jwt)
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.name").value(hw.get().getName()))
                .andExpect(jsonPath("$.code").value(hw.get().getCode()));
    }

    @Test
    void getHardwareByCodeNotFound() throws Exception {
        String code = "NSPX5010";
        String jwt = loginAsAdmin();
        mockMvc.perform(get("/hardware/"+code).header("Authorization", "Bearer " + jwt)
                        .with(csrf()))
                .andExpect(status().isNotFound());
    }

    @Transactional
    @Test
    void insertHardware() throws Exception {
        String jwt = loginAsAdmin();
        HardwareCommand hw = new HardwareCommand();
        hw.setCode("NRST4444");
        hw.setName("new hw");
        hw.setPrice(4444.);
        hw.setStock(4);
        hw.setType(Type.MBO);

        this.mockMvc.perform(
                        post("/hardware")
                                .header("Authorization", "Bearer " + jwt)
                                .with(csrf())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(hw))
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.name").value(hw.getName()))
                .andExpect(jsonPath("$.code").value(hw.getCode()))
                .andExpect(jsonPath("$.price").value(hw.getPrice()));
    }

    @Test
    void insertInvalidHardware() throws Exception {
        String jwt = loginAsAdmin();
        HardwareCommand hw = new HardwareCommand();
        hw.setCode("44444444");
        hw.setName("new hw");
        hw.setPrice(4444.);
        hw.setStock(4);
        hw.setType(Type.MBO);


        mockMvc.perform(
                        post("/hardware")
                                .header("Authorization", "Bearer " + jwt)
                                .with(csrf())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(hw))
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest());
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
        String jwt = JsonPath.read(resultJWT, "jwt");
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
package com.cailam.springdatajpaangularpoc.controller;

import com.cailam.springdatajpaangularpoc.model.Cloth;
import com.cailam.springdatajpaangularpoc.service.ClothService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(RestApiController.class)
public class RestApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClothService clothService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void testFindByNameSuccessfully() throws Exception {

        Cloth cloth = new Cloth("Foo","cotton",10.0);
        given(this.clothService.saveCloth(cloth)).willReturn(cloth);

        this.mockMvc.perform(post("/api/cloth/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(cloth)))
                .andExpect(status().isCreated())
                ;

    }
}

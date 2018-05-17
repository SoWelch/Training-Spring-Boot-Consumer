package com.sofi.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import com.sofi.services.HomeService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest
public class HomeControllerTest {
    @MockBean
    private HomeService service;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnHelloMessageFromService() throws Exception {
        //Using Mockito, when the service is called return Hello Mock
        when(service.getStatus()).thenReturn("Hello Mock");

        // Now call the service, without a server, and make sure we get the mock response
//        this.mockMvc.perform(get("/"))
//                    .andDo(print())
//                    .andExpect(status().isOk())
//                    .andExpect(content()
//                    .string(containsString("Hello Mock")));
    }
}
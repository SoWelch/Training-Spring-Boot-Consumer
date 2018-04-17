package sofi;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import sofi.controllers.MainController;

@RunWith(SpringRunner.class)
@WebMvcTest(MainController.class)
public class ApplicationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MainController controller;

    @Test
    public void contextLoads() {
        // Check that the controller is being created and injected
        assertThat(controller).isNotNull();
    }

    @Test
    public void shouldReturnIndexMessage() throws Exception {
        // Don't actually start a server, instead pass the information to the controller and mock an http request
        this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk()).andExpect(content()
                .string(containsString("Greetings from Spring Boot!")));
    }
}
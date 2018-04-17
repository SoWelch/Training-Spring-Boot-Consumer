package sofi.controllers;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MainControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Test
    public void greetingShouldReturnDefaultMessage() throws Exception {
        // Start the server on a random port to avoid conflicts, use Spring's test rest template, and make sure the endpoint works
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/", String.class))
                .contains("Greetings from Spring Boot!");
    }
}

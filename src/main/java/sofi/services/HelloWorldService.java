package sofi.services;

import org.springframework.stereotype.Service;

@Service
public class HelloWorldService {
    // While this only returns a string here, in a real project this is where your logic goes.
    public String hello() {
        return "Hello World";
    }
}

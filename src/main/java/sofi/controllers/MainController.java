package sofi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import sofi.services.MainService;

@Controller
public class MainController {
    private final MainService service;

    public MainController(MainService service) {
        this.service = service;
    }

    // Setup the endpoint for returning the service message
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public @ResponseBody String message() {
        return service.hello();
    }
}

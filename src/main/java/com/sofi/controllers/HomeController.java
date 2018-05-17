package com.sofi.controllers;

import com.sofi.services.DockerService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
    private final DockerService service;

    public HomeController(DockerService service) {
        this.service = service;
    }

    // Setup the endpoint for returning the service message
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model, @RequestParam(value="name", required = false, defaultValue = "Seth") String name) {
        model.addAttribute("name", name);
        model.addAttribute("title", "Training Consumer");

        service.getMachine();

        return "home";
    }
}

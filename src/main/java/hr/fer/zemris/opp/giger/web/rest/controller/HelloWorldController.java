package hr.fer.zemris.opp.giger.web.rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class HelloWorldController {    //todo remove at some point

    @GetMapping({"/hello"})
    public String firstPage() {
        return "Hello World";
    }
}

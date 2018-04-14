package com.example.restfulwebservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
public class HelloWorldController {
    @Autowired
    private MessageSource messageSource;

    @RequestMapping(method = RequestMethod.GET, path = "/helloworld")
    public String helloWorld() {
        return "Hello world!";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/helloworld-bean")
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean("Hello world!");
    }

    @GetMapping("/helloworld-international")
    public String getHelloWorldInternationalized(@RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        String greetings = messageSource.getMessage("greetings", null, locale);
        String from = messageSource.getMessage("from", null, locale);
        String owner = messageSource.getMessage("owner", null, locale);

        return String.format("%s %s %s", greetings, from, owner);
    }
}




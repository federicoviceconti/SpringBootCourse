package com.example.restfulwebservices.helloworld;

import com.example.restfulwebservices.model.HelloWorldBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
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
    public String getHelloWorldInternationalized() {
        Locale locale = LocaleContextHolder.getLocale();
        String greetings = messageSource.getMessage("greetings", null, locale);
        String from = messageSource.getMessage("from", null, locale);
        String owner = messageSource.getMessage("owner", null, locale);

        return String.format("%s %s %s", greetings, from, owner);
    }
}




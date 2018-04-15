package com.example.restfulwebservices.versioning;

import com.example.restfulwebservices.model.Name;
import com.example.restfulwebservices.model.PersonV1;
import com.example.restfulwebservices.model.PersonV2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionController {
    @GetMapping("v1/some-person")
    public PersonV1 v1() {
        return new PersonV1("First Last");
    }

    @GetMapping("v2/some-person")
    public PersonV2 v2() {
        return new PersonV2(new Name("First", "Last"));
    }

    @GetMapping(value = "/some-person/param", params = "version=1")
    public PersonV1 param1() {
        return new PersonV1("First Last");
    }

    @GetMapping(value = "/some-person/param", params = "version=2")
    public PersonV2 param2() {
        return new PersonV2(new Name("First", "Last"));
    }

    @GetMapping(value = "/some-person/header", headers = "X-API-VERSION=1")
    public PersonV1 header1() {
        return new PersonV1("First Last");
    }

    @GetMapping(value = "/some-person/header", headers = "X-API-VERSION=2")
    public PersonV2 header2() {
        return new PersonV2(new Name("First", "Last"));
    }
}

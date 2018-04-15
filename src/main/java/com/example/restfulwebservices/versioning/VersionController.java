package com.example.restfulwebservices.versioning;

import com.example.restfulwebservices.model.Name;
import com.example.restfulwebservices.model.PersonV1;
import com.example.restfulwebservices.model.PersonV2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller which contains different kind of versioning method.
 * - The first one is the Uri versioning, which cause URI pollution,
 * but is the simplest one. The caching is easier, because URI changes
 * - The header versioning (accept and custom) make caching more difficult,
 * because they use the same URI for every request and this cause to check
 * every time the header
 *
 * If users not use client to make request? It's impossible to change header, if you
 * don't use a specific plugin
 *
 * Which one is best for documentation? URI and request parameters
 * Which one should I choose? The solution which fit with your problem
 *
 */
@RestController
public class VersionController {
    //Uri versioning
    @GetMapping("v1/some-person")
    public PersonV1 v1() {
        return new PersonV1("First Last");
    }

    @GetMapping("v2/some-person")
    public PersonV2 v2() {
        return new PersonV2(new Name("First", "Last"));
    }

    //Request parameter versioning
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

    @GetMapping(value = "/some-person/produces", produces = "application/my.company.app+json")
    public PersonV1 produces1() {
        return new PersonV1("First Last");
    }

    @GetMapping(value = "/some-person/produces", produces = "application/my.company.app+json")
    public PersonV2 produces2() {
        return new PersonV2(new Name("First", "Last"));
    }
}

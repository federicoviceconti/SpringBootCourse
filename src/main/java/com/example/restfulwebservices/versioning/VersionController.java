package com.example.restfulwebservices.versioning;

import com.example.restfulwebservices.model.Name;
import com.example.restfulwebservices.model.PersonV1;
import com.example.restfulwebservices.model.PersonV2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionController {
    @GetMapping("v1/some-person")
    public PersonV1 PersonV1() {
        return new PersonV1("First Last");
    }

    @GetMapping("v2/some-person")
    public PersonV2 PersonV2() {
        return new PersonV2(new Name("First", "Last"));
    }
}

package com.example.restfulwebservices;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = {"f1", "f2"})
public class SomeBean {
    private String f1;
    private String f2;
    private String f3;

    public SomeBean(String v1, String v2, String v3) {
        this.f1 = v1;
        this.f2 = v2;
        this.f3 = v3;
    }

    public String getF1() {
        return f1;
    }

    public String getF2() {
        return f2;
    }

    public String getF3() {
        return f3;
    }
}

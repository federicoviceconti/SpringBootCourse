package com.example.restfulwebservices.model;

import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("SomeBeanFilter")
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

    public SomeBean setF1(String f1) {
        this.f1 = f1;
        return this;
    }

    public SomeBean setF2(String f2) {
        this.f2 = f2;
        return this;
    }

    public SomeBean setF3(String f3) {
        this.f3 = f3;
        return this;
    }
}

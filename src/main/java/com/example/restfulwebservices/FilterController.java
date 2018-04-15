package com.example.restfulwebservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FilterController {
    private List<SomeBean> someBeanList;

    @Autowired
    public void setSomeBeanList(List<SomeBean> someBeanList) {
        this.someBeanList = someBeanList;
        someBeanList.add(new SomeBean("v1", "v2", "v3"));
        someBeanList.add(new SomeBean("v11", "v12", "v13"));
        someBeanList.add(new SomeBean("v21", "v22", "v23"));
        someBeanList.add(new SomeBean("v31", "v32", "v33"));
    }

    @GetMapping("/filter")
    public SomeBean getSomeBean() {
        return someBeanList.get((int) (Math.random() * someBeanList.size()));
    }

    @GetMapping("/all-filter")
    public List<SomeBean> getSomeBeanList() {
        return someBeanList;
    }
}

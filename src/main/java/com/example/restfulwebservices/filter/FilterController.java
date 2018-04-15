package com.example.restfulwebservices.filter;

import com.example.restfulwebservices.common.Field;
import com.example.restfulwebservices.model.SomeBean;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
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

    private <T> MappingJacksonValue returnFilteredBean(T bean, String... args) {
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("SomeBeanFilter",
                SimpleBeanPropertyFilter.filterOutAllExcept(args)
        );

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(bean);
        mappingJacksonValue.setFilters(filterProvider);
        return mappingJacksonValue;
    }

    @GetMapping("/filter")
    public MappingJacksonValue getSomeBean() {
        SomeBean someBean = someBeanList.get((int) (Math.random() * someBeanList.size()));
        return returnFilteredBean(someBean, Field.F1, Field.F3);
    }

    @GetMapping("/all-filter")
    public MappingJacksonValue getSomeBeanList() {
        return returnFilteredBean(this.someBeanList, Field.F1);
    }
}

package com.rest.restfulwebservices.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.rest.restfulwebservices.beans.FilteringBean;

@RestController
public class FilteringBeanController {

	@GetMapping("/bean-staticfiltering")
	public FilteringBean getBean() {
		return new FilteringBean("value1","value2","value3");
	}
	
	@GetMapping("/beans-staticfiltering")
	public List<FilteringBean> getBeans() {
		return Arrays.asList(
				new FilteringBean("value1","value2","value3"),
				new FilteringBean("value12","value22","value32")
			);
	}
	
	//field1,field4
	@GetMapping("/bean-dynamicfiltering")
	public MappingJacksonValue getDynamicBean() {
		
		//define bean
		FilteringBean bean=new FilteringBean("value1","value2","value3","value4","value5");
		
		//define Bean Filter
		SimpleBeanPropertyFilter filter=SimpleBeanPropertyFilter
				.filterOutAllExcept(Set.of("field1","field4"));
		
		//define FilterProvider
		FilterProvider filterProvider=new SimpleFilterProvider().addFilter("filter-one", filter);
		
		//Define JacksonValue mapping
		MappingJacksonValue mapping=new MappingJacksonValue(bean);
		
		//add filters to mapping
		mapping.setFilters(filterProvider);
		
		//return mapping
		return mapping;
	}
	//field1,field4
	@GetMapping("/beans-dynamicfiltering")
	public MappingJacksonValue getDynamicBeans() {
		
		//define bean
		List<FilteringBean> beans=Arrays.asList(
				new FilteringBean("value1","value2","value3","value4","value5"),
				new FilteringBean("value15","value25","value35","value45","value55"));
		
		//define Bean Filter
		SimpleBeanPropertyFilter filter=SimpleBeanPropertyFilter
				.filterOutAllExcept(Set.of("field4","field5"));
		
		//define FilterProvider
		FilterProvider filterProvider=new SimpleFilterProvider().addFilter("filter-one", filter);
		
		//Define JacksonValue mapping
		MappingJacksonValue mapping=new MappingJacksonValue(beans);
		
		//add filters to mapping
		mapping.setFilters(filterProvider);
		
		//return mapping
		return mapping;
	}
}

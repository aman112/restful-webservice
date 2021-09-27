package com.rest.restfulwebservices.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.rest.restfulwebservices.beans.HelloWorldBean;

@RestController
public class HelloWorldController {

	@Autowired
	MessageSource messageSource;
	
	@GetMapping(path="/hello-world")
	public String helloWorld() {
		return "Hello World";
	}
	@GetMapping(path="/hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Hello World");
	}
	@GetMapping(path="/hello-world/path-var/{name}")
	public HelloWorldBean helloWorldBean(@PathVariable String name) {
		return new HelloWorldBean(String.format("Hello World, %s !!",name));
	}
	
	/*@GetMapping(path="/hello-world-internalized")
	public HelloWorldBean helloWorldBeanInternalized(
					@RequestHeader(name="Accept-Language",required=false) Locale locale) {
		
		return new HelloWorldBean(messageSource.getMessage("hello.world", null, locale));
	}*/
	@GetMapping(path="/hello-world-internalized")
	public HelloWorldBean helloWorldBeanInternalized() {
		return new HelloWorldBean(messageSource.getMessage("hello.world", null, LocaleContextHolder.getLocale()));
	}
}

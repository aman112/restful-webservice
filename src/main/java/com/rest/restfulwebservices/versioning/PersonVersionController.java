package com.rest.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersionController {

	//URI Versioning
	@GetMapping("V1/person")
	public PersonV1 getPersonV1_uri() {
		return new PersonV1("Aman Aggarwal");
	}
	@GetMapping("V2/person")
	public PersonV2 getPersonV2_uri() {
		return new PersonV2(new Name("Aman","Aggarwal"));
	}
	
	//Parameter Versioning
	@GetMapping(path="/person",params="version=V1")
	public PersonV1 getPersonV1_param() {
		return new PersonV1("Aman Aggarwal");
	}
	@GetMapping(path="/person",params="version=V2")
	public PersonV2 getPersonV2_param() {
		return new PersonV2(new Name("Aman","Aggarwal"));
	}
	
	//Header Versioning
	@GetMapping(path="/person",headers="X-API-VERSION=V1")
	public PersonV1 getPersonV1_headers() {
		return new PersonV1("Aman Aggarwal");
	}
	@GetMapping(path="/person",headers="X-API-VERSION=V2")
	public PersonV2 getPersonV2_header() {
		return new PersonV2(new Name("Aman","Aggarwal"));
	}
	
	//Content Negotiation Versioning
	@GetMapping(path="/person",produces="application/vnd.company.app-v1+json")
	public PersonV1 getPersonV1_contentNeg() {
		return new PersonV1("Aman Aggarwal");
	}
	@GetMapping(path="/person",produces="application/vnd.company.app-v2+json")
	public PersonV2 getPersonV2_contentNeg() {
		return new PersonV2(new Name("Aman","Aggarwal"));
	}
}

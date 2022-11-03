package com.example.integrationtest;

import java.util.List;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockClassRule;
import io.restassured.RestAssured;
import org.assertj.core.api.BDDAssertions;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.hamcrest.Matchers.equalTo;

/**
 * @author Marcin Grzejszczak
 * @since
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class PersonServiceTests {

	@ClassRule
	public static WireMockClassRule rule = new WireMockClassRule(8081);

//	@Rule public WireMockRule rule = new WireMockRule(8081);

	@Autowired PersonService personService;

	@Before
	public void setup(){
		rule.stubFor(get("/person/1")
				.willReturn(aResponse()
						.withHeader("Content-Type","application/json")
						.withBody("{\"name\" : \"m1\"}")));
		rule.stubFor(get("/person/2")
				.willReturn(aResponse()
						.withHeader("Content-Type","application/json")
						.withBody("{\"name\" : \"m2\"}")));

	}

	@Test
	public void should_get_two_people() {
		List<Person> people = this.personService.people();

		BDDAssertions.then(people).hasSize(2);
	}

	@Test
	public void should_get_two_people_using_rest_assured() {
		RestAssured.given()
				.baseUri("http://localhost:8081")
				.when()
				.get("/person/{id}", 1)
				.then()
				.statusCode(200)
				.contentType("application/json")
				.body("name", Matchers.equalTo("m1"));

	}
}

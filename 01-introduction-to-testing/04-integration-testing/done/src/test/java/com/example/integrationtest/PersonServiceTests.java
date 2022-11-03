package com.example.integrationtest;

import java.util.List;

import org.assertj.core.api.BDDAssertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Marcin Grzejszczak
 * @since
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class PersonServiceTests {

	@Autowired PersonService personService;

	@Test
	public void should_get_two_people() {
		List<Person> people = this.personService.people();

		BDDAssertions.then(people).hasSize(2);
	}

	@Configuration
	static class PersonTestConfiguration extends PersonConfiguration {

		@Override
		PersonClient personClient() {
			return id -> new Person("m", "[" + id + "]");
		}
	}
}

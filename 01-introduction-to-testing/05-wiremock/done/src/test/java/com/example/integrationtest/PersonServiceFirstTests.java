package com.example.integrationtest;

import java.util.List;

import org.assertj.core.api.BDDAssertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.ResourceAccessException;

/**
 * @author Marcin Grzejszczak
 * @since
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class PersonServiceFirstTests {

	@Autowired PersonService personService;

	// ConnectionRefused
	@Test(expected = ResourceAccessException.class)
	public void should_get_two_people() {
		List<Person> people = this.personService.people();

		BDDAssertions.then(people).hasSize(2);
	}
}

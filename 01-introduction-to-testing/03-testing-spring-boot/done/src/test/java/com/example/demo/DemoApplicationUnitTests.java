package com.example.demo;

import org.assertj.core.api.BDDAssertions;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

public class DemoApplicationUnitTests {

	@Test
	public void should_print_people() {
		PersonFetcher personFetcher = new PersonFetcher();
		PersonPrinter printer = new PersonPrinter(personFetcher);

		String print = printer.print();

		BDDAssertions.then(print).isEqualTo("marcin\njohn");
	}

}

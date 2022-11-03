package com.example.demo;

import org.assertj.core.api.BDDAssertions;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Autowired PersonPrinter personPrinter;

	@Test
	public void contextLoads() {
		String print = this.personPrinter.print();

		BDDAssertions.then(print).isEqualTo("marcin\njohn");
	}

}

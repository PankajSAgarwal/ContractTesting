package com.example.demo;

import java.io.File;

import org.assertj.core.api.BDDAssertions;
import org.junit.ClassRule;
import org.junit.Test;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.containers.wait.strategy.Wait;

import org.springframework.web.client.RestTemplate;

public class DemoApplicationTests {

	@ClassRule
	public static DockerComposeContainer environment =
			new DockerComposeContainer(new File("docker-compose.yml"))
					.withExposedService("client_1", 9876, Wait.forListeningPort());

	@Test
	public void contextLoads() {
		RestTemplate restTemplate = new RestTemplate();

		String response =
				restTemplate.getForObject("http://localhost:9876/person", String.class);

		System.out.println(response);
		BDDAssertions.then(response).isNotBlank();
	}

}

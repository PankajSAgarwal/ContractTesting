package com.example.carrental;

import com.github.tomakehurst.wiremock.client.WireMock;
import org.assertj.core.api.BDDAssertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.junit.StubRunnerRule;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarRentalApplicationTests {

	@Rule
	public StubRunnerRule rule = new StubRunnerRule()
			.downloadStub("com.example:02-06-fraud-detection:0.0.1-SNAPSHOT:stubs")
			.withPort(9876)
			.stubsMode(StubRunnerProperties.StubsMode.LOCAL);


	@Test
	public void should_return_a_list_of_frauds() {
		String response = "[\"pankaj\",\"josh\"]";
		String object = new RestTemplate().getForObject("http://localhost:9876/fraudweiei", String.class);
		BDDAssertions.then(object).isEqualTo("[\"pankaj\",\"josh\"]");
	}



}

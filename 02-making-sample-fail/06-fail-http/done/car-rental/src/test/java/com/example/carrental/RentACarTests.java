package com.example.carrental;

import java.net.URI;

import org.assertj.core.api.BDDAssertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.junit.StubRunnerRule;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.github.tomakehurst.wiremock.client.WireMock;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
// 2 - added WireMock stub
@AutoConfigureWireMock(port = 6543)
@DirtiesContext
public class RentACarTests {

	@Test
	public void should_retrieve_list_of_frauds() {
		// 2 - added WireMock stub - the test is passing
		String body = "[\"marcin\", \"josh\"]";
		WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/fraud"))
				.willReturn(WireMock.aResponse().withBody(body)));

		// 1 - not passing test
		ResponseEntity<String> entity = new RestTemplate().exchange(RequestEntity
		.get(URI.create("http://localhost:6543/fraud")).build(), String.class);

		BDDAssertions.then(entity.getStatusCode().value()).isEqualTo(200);
		BDDAssertions.then(entity.getBody()).isEqualTo(body);
	}

	@Rule public StubRunnerRule stubRunner = new StubRunnerRule()
			.downloadStub("com.example:02-06-fraud-detection-done:+:stubs:6544");

	@Test
	public void should_fetch_stubs_from_stub_runner() {
		String body = "[\"marcin\", \"josh\"]";
		WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/frauds"))
				.willReturn(WireMock.aResponse().withBody(body)));

		// 1 - not passing test
		ResponseEntity<String> entity = new RestTemplate().exchange(RequestEntity
				.get(URI.create("http://localhost:6543/frauds")).build(), String.class);

		BDDAssertions.then(entity.getStatusCode().value()).isEqualTo(200);
		BDDAssertions.then(entity.getBody()).isEqualTo(body);
	}

}
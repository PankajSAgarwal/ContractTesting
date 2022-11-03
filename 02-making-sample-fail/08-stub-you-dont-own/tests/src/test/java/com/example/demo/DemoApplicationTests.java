package com.example.demo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stripe.Stripe;
import com.stripe.model.Charge;
import org.assertj.core.api.BDDAssertions;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureStubRunner(
		ids = "com.example:02-08-stub-you-dont-own:+:stubs:9876",
		stubsMode = StubRunnerProperties.StubsMode.LOCAL
)
public class DemoApplicationTests {

	@Test
	public void contextLoads() {
		StripeClient stripeClient = new StripeClient("http://localhost:9876");

		List<Charge> charges = stripeClient.listAllCharges();

		BDDAssertions.then(charges).hasSize(25);
	}

	@SpringBootConfiguration
	static class Configuration {

	}
}

class StripeClient {

	private final String url;

	StripeClient(String url) {
		this.url = url;
	}

	List<Charge> listAllCharges() {
		Stripe.apiKey = "sk_test_BQokikJOvBiI2HlWgH4olfQ2";
		Map<String, Object> chargeParams = new HashMap<>();
		Stripe.overrideApiBase(this.url);
		chargeParams.put("limit", 25);
		try {
			return Charge.list(chargeParams).getData();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}

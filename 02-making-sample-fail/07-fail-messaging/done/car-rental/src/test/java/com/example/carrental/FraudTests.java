package com.example.carrental;

import org.assertj.core.api.BDDAssertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.StubTrigger;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CarRentalApplication.class,
		webEnvironment = SpringBootTest.WebEnvironment.NONE)
// 2 stub runner for messaging
@AutoConfigureStubRunner(stubsMode = StubRunnerProperties.StubsMode.LOCAL,
		ids = "com.example:02-07-fraud-detection-done")
public class FraudTests {

	@Autowired FraudListener fraudListener;
	@Autowired StubTrigger stubTrigger;

	@Test
	public void should_store_info_about_fraud_via_stub_runner() {
		this.stubTrigger.trigger("trigger_a_fraud");

		BDDAssertions.then(this.fraudListener.name).isEqualTo("m");
	}

}

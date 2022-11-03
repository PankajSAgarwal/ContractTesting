package contracts.foo

import org.springframework.cloud.contract.spec.Contract

Contract.make {
	request {
		method GET()
		url "/foo"
	}
	response {
		status OK()
	}
}
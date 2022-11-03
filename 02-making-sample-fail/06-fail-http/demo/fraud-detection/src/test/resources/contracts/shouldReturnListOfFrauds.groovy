import org.springframework.cloud.contract.spec.Contract

Contract.make {
	request {
		method(GET())
		url("/fraud")
	}
	response {
		status(OK())
		headers {
			contentType(applicationJson())
		}
		body(["pankaj","josh"])
	}

}
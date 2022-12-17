package br.com.seplag.updateservice.test;

import org.junit.Test;
import org.springframework.web.client.HttpClientErrorException;

import br.com.seplag.services.rest.MicrosoftUpdateServiceRest;
import br.com.seplag.services.rest.impl.MicrosoftUpdateServiceRestImpl;

public class UpdateMicrosoftRestTest {

	@Test(expected = HttpClientErrorException.class)
	public void endpointInvalidoTest() {

		String endpoint = "https://api.msrc.microsoft.com/cvrf/v2.0/teste";

		MicrosoftUpdateServiceRest rest = new MicrosoftUpdateServiceRestImpl();
		rest.getMicrosoftVO(endpoint);

	}

}

#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/**
 * 
 */
package ${package}.war.integrationtest;

import java.net.URISyntaxException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import ${package}.rest.model.*;


public class ExampleIntegrationTest {
	
	private static String baseUrl = "http://127.0.0.1:${tomcatPort}/${artifactId}/rest";
	
	@Test
	public final void testInvoke() throws RestClientException, URISyntaxException {
		final String url = String.format("%s/exampleService/requestOutput", baseUrl);
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		ExampleInput input = new ExampleInput();
		HttpEntity<ExampleInput> requestEntity = new HttpEntity<ExampleInput>(input, headers);
		HttpEntity<ExampleOutput> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, ExampleOutput.class);
		ExampleOutput dto = response.getBody();
		Assert.assertTrue(dto!=null);
	}
}
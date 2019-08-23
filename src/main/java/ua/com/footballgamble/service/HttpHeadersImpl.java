package ua.com.footballgamble.service;

import java.util.Arrays;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component(value = "httpHeadersImpl")
public class HttpHeadersImpl {
	public HttpHeaders getHeaders(String userName, String password) {
		String plainCredentials = userName + ":" + password;
		String base64Credentials = new String(Base64.encodeBase64(plainCredentials.getBytes()));

		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Basic " + base64Credentials);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		return headers;
	}

	public HttpHeaders getHeaders() {

		return getHeaders("admin", "123");
	}

	public HttpEntity<String> getHttpAuthEntity() {
		HttpEntity<String> request = new HttpEntity<String>(getHeaders("admin", "123"));

		return request;
	}

}

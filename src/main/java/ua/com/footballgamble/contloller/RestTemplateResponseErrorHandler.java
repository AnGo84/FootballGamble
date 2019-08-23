package ua.com.footballgamble.contloller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import ua.com.footballgamble.exception.AuthorisationException;
import ua.com.footballgamble.exception.DataConflictException;
import ua.com.footballgamble.exception.NoContentException;
import ua.com.footballgamble.exception.NotFoundException;
import ua.com.footballgamble.exception.RestAPIServerException;

@Component
public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {
	public static final Logger logger = LoggerFactory.getLogger(RestTemplateResponseErrorHandler.class);

	@Override
	public boolean hasError(ClientHttpResponse httpResponse) throws IOException {
		logger.info("Get " + httpResponse.getStatusCode().series() + " type error");

		return (httpResponse.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR
				|| httpResponse.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR);
	}

	@Override
	public void handleError(ClientHttpResponse httpResponse) throws IOException {
		String responceBodyText = getResponceBodyText(httpResponse);

		if (httpResponse.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR) {
			logger.info("Get " + httpResponse.getStatusCode().series() + " type error: " + responceBodyText);
			throw new RestAPIServerException(responceBodyText);

			// handle SERVER_ERROR
		} else if (httpResponse.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR) {
			logger.info("Get " + httpResponse.getStatusCode().series() + " with statys " + httpResponse.getStatusCode()
					+ " type error: " + responceBodyText);
			// handle CLIENT_ERROR

			if (httpResponse.getStatusCode() == HttpStatus.NO_CONTENT) {

				throw new NoContentException("No content. " + responceBodyText);
			}
			if (httpResponse.getStatusCode() == HttpStatus.UNAUTHORIZED) {

				throw new AuthorisationException("Request not AUTHORIZED. " + responceBodyText);
			}
			if (httpResponse.getStatusCode() == HttpStatus.NOT_FOUND) {
				throw new NotFoundException(responceBodyText);
			}
			if (httpResponse.getStatusCode() == HttpStatus.CONFLICT) {
				throw new DataConflictException(responceBodyText);
			}
		}
	}

	private String getResponceBodyText(ClientHttpResponse httpResponse)
			throws UnsupportedEncodingException, IOException {
		StringBuilder stringBuilder = new StringBuilder();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpResponse.getBody(), "UTF-8"));

		String line = null;
		while ((line = bufferedReader.readLine()) != null) {
			stringBuilder.append(line);
			stringBuilder.append('\n');
		}
		return stringBuilder.toString();
	}
}

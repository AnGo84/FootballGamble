package ua.com.footballgamble.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import ua.com.footballgamble.contloller.RestTemplateResponseErrorHandler;
import ua.com.footballgamble.model.user.UserRole;

@Service("userRoleService")
//@PropertySource(ignoreResourceNotFound = true, value = "classpath:footballgamble.properties")
public class UserRoleServiceImpl {

	private static final String ENTITY_PATH = "/userroles";
	public static final Logger logger = LoggerFactory.getLogger(UserRoleServiceImpl.class);
	@Value("${football.api.path}")
	private String apiPath;

	@Autowired
	private HttpHeadersImpl httpHeaders;

	@SuppressWarnings("unchecked")
	public List<UserRole> findAllUserRoles() {
		logger.info("Get All User Roles List");
		List<UserRole> userRolesList = new ArrayList<>();

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());
		ResponseEntity<List<UserRole>> response = restTemplate.exchange(apiPath + ENTITY_PATH, HttpMethod.GET,
				httpHeaders.getHttpAuthEntity(), new ParameterizedTypeReference<List<UserRole>>() {
				});
		userRolesList = response.getBody();
		return userRolesList;
	}

}

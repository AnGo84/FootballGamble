package ua.com.footballgamble.service;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import ua.com.footballgamble.contloller.RestTemplateResponseErrorHandler;
import ua.com.footballgamble.exception.AuthorisationException;
import ua.com.footballgamble.exception.DataConflictException;
import ua.com.footballgamble.exception.NoContentException;
import ua.com.footballgamble.exception.NotFoundException;
import ua.com.footballgamble.exception.RestAPIServerException;
import ua.com.footballgamble.model.user.User;

@Service("usersService")
//@PropertySource(ignoreResourceNotFound = true, value = "classpath:footballgamble.properties")
public class UserServiceImpl implements UserService {

	private static final String ENTITY_PATH = "/users";

	public static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	@Value("${football.api.path}")
	private String apiPath;

	@Autowired
	private HttpHeadersImpl httpHeaders;

	@SuppressWarnings("unchecked")
	public List<User> findAllUsers()
			throws AuthorisationException, NotFoundException, DataConflictException, RestAPIServerException {
		logger.info("Get All Users List");
		List<User> usersList = new ArrayList<>();

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());
		// Example: https://www.baeldung.com/spring-rest-template-list
		ResponseEntity<List<User>> response = restTemplate.exchange(apiPath + ENTITY_PATH, HttpMethod.GET,
				httpHeaders.getHttpAuthEntity(), new ParameterizedTypeReference<List<User>>() {
				});
		usersList = response.getBody();
		return usersList;
		/*
		 * List<LinkedHashMap<String, Object>> usersMap =
		 * restTemplate.getForObject(apiPath + "/users", List.class); if (usersMap !=
		 * null) { for (LinkedHashMap<String, Object> map : usersMap) {
		 * System.out.println("User : id=" + map.get("id") + ", Name=" + map.get("name")
		 * + ", Age=" + map.get("age") + ", Salary=" + map.get("salary")); ; } } else {
		 * System.out.println("No user exist----------"); }
		 */
	}

	public User findById(long id)
			throws AuthorisationException, NotFoundException, DataConflictException, RestAPIServerException {
		logger.info("Get User by id: " + id);
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());
		ResponseEntity<User> response = restTemplate.exchange(apiPath + ENTITY_PATH + "/" + id, HttpMethod.GET,
				httpHeaders.getHttpAuthEntity(), User.class);
		User user = response.getBody();
		logger.info("Getted User from API: " + user);
		return user;
	}

	public User findByLogin(String login)
			throws AuthorisationException, NotFoundException, DataConflictException, RestAPIServerException {
		logger.info("Get User by LOGIN: " + login);
		User user = null;
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());

		ResponseEntity<User> response = restTemplate.exchange(apiPath + ENTITY_PATH + "/login/" + login, HttpMethod.GET,
				httpHeaders.getHttpAuthEntity(), User.class);

		user = response.getBody();

		logger.info("Getted User from API: " + user);
		return user;
	}

	public void saveUser(User user)
			throws AuthorisationException, NotFoundException, DataConflictException, RestAPIServerException {
		logger.info("Create User : " + user);

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());

		HttpEntity<User> request = new HttpEntity<>(user, httpHeaders.getHeaders());

		URI uri = restTemplate.postForLocation(apiPath + ENTITY_PATH + "/add", request, User.class);

		logger.info("Location : " + uri.toASCIIString());
	}

	public void updateUser(User user)
			throws AuthorisationException, NotFoundException, DataConflictException, RestAPIServerException {
		logger.info("Update User API: " + user);
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());
		HttpEntity<User> request = new HttpEntity<>(user, httpHeaders.getHeaders());
		// restTemplate.put(apiPath + ENTITY_PATH + "/update", user);
		ResponseEntity<User> updatedUser = restTemplate.exchange(apiPath + ENTITY_PATH + "/update", HttpMethod.PUT,
				request, User.class);

		logger.info("Updated USER: " + updatedUser);
	}

	public void deleteUserById(long id) throws RuntimeException {
		logger.info("Delete User by ID: " + id);

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());

		HttpEntity<User> request = new HttpEntity<>(httpHeaders.getHeaders());
		// restTemplate.delete(apiPath + ENTITY_PATH + "/delete-" + id,
		// httpHeaders.getHttpAuthEntity());
		try {
			restTemplate.exchange(apiPath + ENTITY_PATH + "/delete-" + id, HttpMethod.DELETE, request, String.class);
		} catch (NoContentException e) {
			logger.info("Deletion return HttpStatus.NO_CONTENT");
		}

	}

	public boolean isUserExist(User user)
			throws AuthorisationException, NotFoundException, DataConflictException, RestAPIServerException {
		logger.info("is user Exist: " + user);
		return (findByLogin(user.getLogin()) != null) || (findById(user.getId()) != null);
	}

	public boolean isLoginExist(String login)
			throws AuthorisationException, NotFoundException, DataConflictException, RestAPIServerException {
		logger.info("is Login Exist: " + login);
		return (findByLogin(login) != null);
	}

}

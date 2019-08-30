package ua.com.footballgamble.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
import ua.com.footballgamble.exception.AuthorisationException;
import ua.com.footballgamble.exception.DataConflictException;
import ua.com.footballgamble.exception.NotFoundException;
import ua.com.footballgamble.exception.RestAPIServerException;
import ua.com.footballgamble.model.entity.GambleRuleEntity;

@Service("gambleRulesService")
public class GambleRuleServiceImpl implements CommonService<GambleRuleEntity> {

	private static final String ENTITY_PATH = "/gamblerules";

	public static final Logger logger = LoggerFactory.getLogger(GambleRuleServiceImpl.class);
	@Value("${football.api.path}")
	private String apiPath;

	@Autowired
	private HttpHeadersImpl httpHeaders;

	@Override
	public List<GambleRuleEntity> findAll()
			throws AuthorisationException, NotFoundException, DataConflictException, RestAPIServerException {
		logger.info("Get All GambleRules List");
		List<GambleRuleEntity> list = new ArrayList<>();

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());
		// Example: https://www.baeldung.com/spring-rest-template-list
		ResponseEntity<List<GambleRuleEntity>> response = restTemplate.exchange(apiPath + ENTITY_PATH, HttpMethod.GET,
				httpHeaders.getHttpAuthEntity(), new ParameterizedTypeReference<List<GambleRuleEntity>>() {
				});
		list = response.getBody();

		list = list.stream().sorted(Comparator.comparing(GambleRuleEntity::getId)).collect(Collectors.toList());

		return list;
	}

	@Override
	public GambleRuleEntity findById(long id)
			throws AuthorisationException, NotFoundException, DataConflictException, RestAPIServerException {
		logger.info("Get GambleRule by id: " + id);
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());
		ResponseEntity<GambleRuleEntity> response = restTemplate.exchange(apiPath + ENTITY_PATH + "/" + id,
				HttpMethod.GET, httpHeaders.getHttpAuthEntity(), GambleRuleEntity.class);
		GambleRuleEntity entity = response.getBody();
		logger.info("Getted GambleRuleEntity from API: " + entity);
		return entity;
	}

	@Override
	public GambleRuleEntity findByName(String name) {
		/*
		 * logger.info("Get User by LOGIN: " + login); User user = null; RestTemplate
		 * restTemplate = new RestTemplate(); restTemplate.setErrorHandler(new
		 * RestTemplateResponseErrorHandler());
		 * 
		 * ResponseEntity<User> response = restTemplate.exchange(apiPath + ENTITY_PATH +
		 * "/login/" + login, HttpMethod.GET, httpHeaders.getHttpAuthEntity(),
		 * User.class);
		 * 
		 * user = response.getBody();
		 * 
		 * logger.info("Getted User from API: " + user); return user;
		 */
		return null;
	}

	@Override
	public void save(GambleRuleEntity object)
			throws AuthorisationException, NotFoundException, DataConflictException, RestAPIServerException {
		/*
		 * logger.info("Create User : " + user);
		 * 
		 * RestTemplate restTemplate = new RestTemplate();
		 * restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());
		 * 
		 * HttpEntity<User> request = new HttpEntity<>(user, httpHeaders.getHeaders());
		 * 
		 * URI uri = restTemplate.postForLocation(apiPath + ENTITY_PATH + "/add",
		 * request, User.class); logger.info("Location : " + uri.toASCIIString());
		 */

	}

	@Override
	public void update(GambleRuleEntity object)
			throws AuthorisationException, NotFoundException, DataConflictException, RestAPIServerException {
		/*
		 * logger.info("Update User API: " + user); RestTemplate restTemplate = new
		 * RestTemplate(); restTemplate.setErrorHandler(new
		 * RestTemplateResponseErrorHandler()); HttpEntity<User> request = new
		 * HttpEntity<>(user, httpHeaders.getHeaders()); // restTemplate.put(apiPath +
		 * ENTITY_PATH + "/update", user); ResponseEntity<User> updatedUser =
		 * restTemplate.exchange(apiPath + ENTITY_PATH + "/update", HttpMethod.PUT,
		 * request, User.class);
		 * 
		 * logger.info("Updated USER: " + updatedUser);
		 */

	}

	/*
	 * public void updateFromAPI(GambleRuleEntity object) throws
	 * AuthorisationException, NotFoundException, DataConflictException,
	 * RestAPIServerException { logger.info("Update Team from API: " + object);
	 * RestTemplate restTemplate = new RestTemplate();
	 * restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());
	 * ResponseEntity<TeamEntity> response = restTemplate.exchange( apiPath +
	 * ENTITY_PATH + "/updatefromapi/" + object.getId(), HttpMethod.GET,
	 * httpHeaders.getHttpAuthEntity(), TeamEntity.class); TeamEntity competition =
	 * response.getBody(); logger.info("Updated CompetitionEntity from API: " +
	 * competition); }
	 */

	@Override
	public void deleteById(long id) {
		/*
		 * logger.info("Delete User by ID: " + id);
		 * 
		 * RestTemplate restTemplate = new RestTemplate();
		 * restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());
		 * 
		 * HttpEntity<User> request = new HttpEntity<>(httpHeaders.getHeaders()); //
		 * restTemplate.delete(apiPath + ENTITY_PATH + "/delete-" + id, //
		 * httpHeaders.getHttpAuthEntity()); try { restTemplate.exchange(apiPath +
		 * ENTITY_PATH + "/delete-" + id, HttpMethod.DELETE, request, String.class); }
		 * catch (NoContentException e) {
		 * logger.info("Deletion return HttpStatus.NO_CONTENT"); }
		 */

	}

	@Override
	public boolean isExist(GambleRuleEntity entity)
			throws AuthorisationException, NotFoundException, DataConflictException, RestAPIServerException {
		logger.info("is GambleRule Exist: " + entity);
		return (findByName(entity.getFullName()) != null) || (findById(entity.getId()) != null);
	}

}

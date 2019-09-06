package ua.com.footballgamble.service;

import java.net.URI;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
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
import ua.com.footballgamble.exception.NotFoundException;
import ua.com.footballgamble.exception.RestAPIServerException;
import ua.com.footballgamble.model.entity.GambleEntity;
import ua.com.footballgamble.model.entity.GambleUserScore;

@Service("gamblesService")
public class GambleServiceImpl implements CommonService<GambleEntity> {

	private static final String ENTITY_PATH = "/gambles";

	public static final Logger logger = LoggerFactory.getLogger(GambleServiceImpl.class);
	@Value("${football.api.path}")
	private String apiPath;

	@Autowired
	private HttpHeadersImpl httpHeaders;

	@Override
	public List<GambleEntity> findAll()
			throws AuthorisationException, NotFoundException, DataConflictException, RestAPIServerException {
		logger.info("Get All Gambles List");
		List<GambleEntity> list = new ArrayList<>();

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());
		// Example: https://www.baeldung.com/spring-rest-template-list
		ResponseEntity<List<GambleEntity>> response = restTemplate.exchange(apiPath + ENTITY_PATH, HttpMethod.GET,
				httpHeaders.getHttpAuthEntity(), new ParameterizedTypeReference<List<GambleEntity>>() {
				});
		list = response.getBody();

		// list =
		// list.stream().sorted(Comparator.comparingLong(GambleEntity::getId)).collect(Collectors.toList());

		return list;
	}

	@Override
	public GambleEntity findById(long id)
			throws AuthorisationException, NotFoundException, DataConflictException, RestAPIServerException {
		logger.info("Get Gamble by id: " + id);
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());
		ResponseEntity<GambleEntity> response = restTemplate.exchange(apiPath + ENTITY_PATH + "/" + id, HttpMethod.GET,
				httpHeaders.getHttpAuthEntity(), GambleEntity.class);
		GambleEntity entity = response.getBody();
		logger.info("Getted GambleEntity from API: " + entity);
		return entity;
	}

	@Override
	public GambleEntity findByName(String name) {
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
	public void save(GambleEntity object)
			throws AuthorisationException, NotFoundException, DataConflictException, RestAPIServerException {

		logger.info("Create Gamble: " + object);

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());

		HttpEntity<GambleEntity> request = new HttpEntity<>(object, httpHeaders.getHeaders());

		URI uri = restTemplate.postForLocation(apiPath + ENTITY_PATH + "/add", request, GambleEntity.class);
		logger.info("Location : " + uri.toASCIIString());

	}

	@Override
	public void update(GambleEntity object)
			throws AuthorisationException, NotFoundException, DataConflictException, RestAPIServerException {

		logger.info("Update Gamble: " + object);
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());
		HttpEntity<GambleEntity> request = new HttpEntity<>(object, httpHeaders.getHeaders());
		restTemplate.put(apiPath + ENTITY_PATH + "/update", object);
		ResponseEntity<GambleEntity> updatedObject = restTemplate.exchange(apiPath + ENTITY_PATH + "/update",
				HttpMethod.PUT, request, GambleEntity.class);

		logger.info("Updated GambleEntity: " + updatedObject);

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
	public boolean isExist(GambleEntity entity)
			throws AuthorisationException, NotFoundException, DataConflictException, RestAPIServerException {
		logger.info("is Gamble Exist: " + entity);
		return (findById(entity.getId()) != null);
	}

	public List<GambleEntity> findAllActiveForUser(String login)
			throws AuthorisationException, NotFoundException, DataConflictException, RestAPIServerException {
		logger.info("Get All Active Gambles for user {}", login);
		if (StringUtils.isBlank(login)) {
			throw new AuthorisationException("You must be logged!");
		}
		List<GambleEntity> list = new ArrayList<>();

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());
		// Example: https://www.baeldung.com/spring-rest-template-list
		ResponseEntity<List<GambleEntity>> response = restTemplate.exchange(
				apiPath + ENTITY_PATH + "/active/user/" + login, HttpMethod.GET, httpHeaders.getHttpAuthEntity(),
				new ParameterizedTypeReference<List<GambleEntity>>() {
				});
		list = response.getBody();

		// list =
		// list.stream().sorted(Comparator.comparingLong(GambleEntity::getId)).collect(Collectors.toList());

		return list;
	}

	public List<GambleUserScore> getGambleUsersScores(Long gambleId)
			throws AuthorisationException, NotFoundException, DataConflictException, RestAPIServerException {
		logger.info("Get Gamble Users' Scores for id: {}", gambleId);

		List<GambleUserScore> list = new ArrayList<>();

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());
		// Example: https://www.baeldung.com/spring-rest-template-list
		ResponseEntity<List<GambleUserScore>> response = restTemplate.exchange(
				apiPath + ENTITY_PATH + "/" + gambleId + "/users/scores", HttpMethod.GET,
				httpHeaders.getHttpAuthEntity(), new ParameterizedTypeReference<List<GambleUserScore>>() {
				});
		list = response.getBody();

		list = list.stream().sorted(Comparator.comparingLong(GambleUserScore::getScore).reversed()
				.thenComparing(GambleUserScore::getUserLogin)).collect(Collectors.toList());

		return list;
	}
}

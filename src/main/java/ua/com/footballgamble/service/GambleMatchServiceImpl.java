package ua.com.footballgamble.service;

import java.net.URI;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
import ua.com.footballgamble.model.entity.GambleCompetition;
import ua.com.footballgamble.model.entity.GambleEntity;
import ua.com.footballgamble.model.entity.GambleMatchEntity;
import ua.com.footballgamble.model.entity.GambleRuleEntity;
import ua.com.footballgamble.model.entity.GambleStage;
import ua.com.footballgamble.model.entity.GambleUser;
import ua.com.footballgamble.model.entity.MatchEntity;
import ua.com.footballgamble.model.entity.SeasonEntity;

@Service("gambleMatchesService")
public class GambleMatchServiceImpl {

	private static final String ENTITY_PATH = "/gamblematches";

	public static final Logger logger = LoggerFactory.getLogger(GambleMatchServiceImpl.class);
	@Value("${football.api.path}")
	private String apiPath;

	@Autowired
	private HttpHeadersImpl httpHeaders;
	@Autowired
	private SeasonServiceImpl seasonService;

	public List<GambleMatchEntity> findAll()
			throws AuthorisationException, NotFoundException, DataConflictException, RestAPIServerException {
		logger.info("Get All GambleMatches List");
		List<GambleMatchEntity> list = new ArrayList<>();

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());
		// Example: https://www.baeldung.com/spring-rest-template-list
		ResponseEntity<List<GambleMatchEntity>> response = restTemplate.exchange(apiPath + ENTITY_PATH, HttpMethod.GET,
				httpHeaders.getHttpAuthEntity(), new ParameterizedTypeReference<List<GambleMatchEntity>>() {
				});
		list = response.getBody();

		list = list.stream().sorted(Comparator.comparing(GambleMatchEntity::getCompetitionId))
				.collect(Collectors.toList());

		return list;
	}

	public List<GambleMatchEntity> findAllByGambleId(Long gambleId)
			throws AuthorisationException, NotFoundException, DataConflictException, RestAPIServerException {
		logger.info("Get All GambleMatches List by GambleId= " + gambleId);
		List<GambleMatchEntity> list = new ArrayList<>();

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());
		// Example: https://www.baeldung.com/spring-rest-template-list
		ResponseEntity<List<GambleMatchEntity>> response = restTemplate.exchange(
				apiPath + ENTITY_PATH + "/gamble=" + gambleId, HttpMethod.GET, httpHeaders.getHttpAuthEntity(),
				new ParameterizedTypeReference<List<GambleMatchEntity>>() {
				});
		list = response.getBody();

		// list =
		// list.stream().sorted(Comparator.comparing(GambleMatchEntity::getId)).collect(Collectors.toList());

		return list;
	}

	public GambleMatchEntity findById(String id)
			throws AuthorisationException, NotFoundException, DataConflictException, RestAPIServerException {
		logger.info("Get GambleMatch by id: " + id);
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());
		ResponseEntity<GambleMatchEntity> response = restTemplate.exchange(apiPath + ENTITY_PATH + "/" + id,
				HttpMethod.GET, httpHeaders.getHttpAuthEntity(), GambleMatchEntity.class);
		GambleMatchEntity entity = response.getBody();
		logger.info("Getted GambleRuleEntity from API: " + entity);
		return entity;
	}

	public void save(GambleMatchEntity object)
			throws AuthorisationException, NotFoundException, DataConflictException, RestAPIServerException {

		logger.info("Create GambleMatch : " + object);

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());

		HttpEntity<GambleMatchEntity> request = new HttpEntity<>(object, httpHeaders.getHeaders());

		URI uri = restTemplate.postForLocation(apiPath + ENTITY_PATH + "/add", request, GambleMatchEntity.class);
		logger.info("Location : " + uri.toASCIIString());

	}

	public void saveAll(List<GambleMatchEntity> saveList)
			throws AuthorisationException, NotFoundException, DataConflictException, RestAPIServerException {

		logger.info("Save GambleMatches");
		if (saveList == null || saveList.isEmpty()) {
			logger.info("List for update is empty!");
			throw new DataConflictException("List for update is empty!");
		}
		logger.info("Save GambleMatches List size: " + saveList.size());
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());

		HttpEntity<List<GambleMatchEntity>> request = new HttpEntity<>(saveList, httpHeaders.getHeaders());
		// restTemplate.put(apiPath + ENTITY_PATH + "/update", object);
		String apiUrl = apiPath + ENTITY_PATH + "/save/all/list";

		try {
			restTemplate.exchange(apiUrl, HttpMethod.POST, request,
					new ParameterizedTypeReference<List<GambleMatchEntity>>() {
					});
		} catch (NoContentException e) {
			logger.info("Saving return HttpStatus.NO_CONTENT");
		}
	}

	public void update(GambleMatchEntity object)
			throws AuthorisationException, NotFoundException, DataConflictException, RestAPIServerException {

		logger.info("Update GambleMatch: " + object);
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());
		HttpEntity<GambleMatchEntity> request = new HttpEntity<>(object, httpHeaders.getHeaders());
		// restTemplate.put(apiPath + ENTITY_PATH + "/update", object);
		ResponseEntity<GambleMatchEntity> updatedGambleMatch = restTemplate.exchange(apiPath + ENTITY_PATH + "/update",
				HttpMethod.PUT, request, GambleMatchEntity.class);

		logger.info("Updated GambleMatch: " + updatedGambleMatch);

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

	public void deleteById(String id)
			throws AuthorisationException, NotFoundException, DataConflictException, RestAPIServerException {

		logger.info("Delete GambleMatch by ID: " + id);

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());

		HttpEntity<GambleMatchEntity> request = new HttpEntity<>(httpHeaders.getHeaders());
		// restTemplate.delete(apiPath + ENTITY_PATH + "/delete-" + id,
		// httpHeaders.getHttpAuthEntity());
		try {
			restTemplate.exchange(apiPath + ENTITY_PATH + "/delete/" + id, HttpMethod.DELETE, request, String.class);
		} catch (NoContentException e) {
			logger.info("Deletion return HttpStatus.NO_CONTENT");
		}

	}

	public void deleteByGambleId(Long gambleId)
			throws AuthorisationException, NotFoundException, DataConflictException, RestAPIServerException {

		logger.info("Delete GambleMatchs by gamble ID: " + gambleId);

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());

		HttpEntity<GambleMatchEntity> request = new HttpEntity<>(httpHeaders.getHeaders());
		try {
			restTemplate.exchange(apiPath + ENTITY_PATH + "/delete/gambleid=" + gambleId, HttpMethod.DELETE, request,
					String.class);
		} catch (NoContentException e) {
			logger.info("Deletion return HttpStatus.NO_CONTENT");
		}

	}

	public void deleteAllGambleMatchesByGambleIdAndCompetitionIdAndStage(long gambleId, long competitionId,
			String stage)
			throws AuthorisationException, NotFoundException, DataConflictException, RestAPIServerException {

		logger.info("Delete GambleMatchs by gamble ID: " + gambleId);

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());

		HttpEntity<GambleMatchEntity> request = new HttpEntity<>(httpHeaders.getHeaders());
		String theUrl = apiPath + ENTITY_PATH + "/delete/all/gamble=" + gambleId + "/competiton=" + competitionId
				+ "/stage=" + stage;
		try {
			restTemplate.exchange(theUrl, HttpMethod.DELETE, request, String.class);
		} catch (NoContentException e) {
			logger.info("Deletion return HttpStatus.NO_CONTENT");
		}
	}

	public void deleteAll(List<GambleMatchEntity> deleteList)
			throws AuthorisationException, NotFoundException, DataConflictException, RestAPIServerException {

		logger.info("Delete GambleMatches");

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());

		HttpEntity<List<GambleMatchEntity>> request = new HttpEntity<>(deleteList, httpHeaders.getHeaders());
		// restTemplate.put(apiPath + ENTITY_PATH + "/update", object);
		String apiUrl = apiPath + ENTITY_PATH + "/delete/all/list";

		try {
			restTemplate.exchange(apiUrl, HttpMethod.DELETE, request,
					new ParameterizedTypeReference<List<GambleMatchEntity>>() {
					});
		} catch (NoContentException e) {
			logger.info("Deletion return HttpStatus.NO_CONTENT");
		}
	}

	/*
	 * public boolean isExist(GambleMatchEntity entity) throws
	 * AuthorisationException, NotFoundException, DataConflictException,
	 * RestAPIServerException { logger.info("is GambleMatch Exist: " + entity);
	 * 
	 * 
	 * return (findByName(entity.getFullName()) != null) ||
	 * (findById(entity.getId()) != null); }
	 */

	/*
	 * private void updateGambleMatches(GambleEntity gambleEntity, List<MatchEntity>
	 * matches, List<GambleMatchEntity> gambleMatches) {
	 */
	public void updateGambleMatches(GambleEntity gambleEntity, List<GambleMatchEntity> gambleMatches)
			throws AuthorisationException, NotFoundException, DataConflictException, RestAPIServerException {
		logger.info("Start updateGambleMatches");
		GambleUpdateData gambleData = new GambleUpdateData(gambleEntity, gambleMatches);
		gambleData = clearGambleMatches(gambleData);
		logger.info("Data cleaned");
		List<GambleMatchEntity> updatedGambleMatchList = getGambleMatchesForUpdate(gambleData);
		// TODO remove
		for (GambleMatchEntity gambleMatchEntity : updatedGambleMatchList) {
			logger.info("	GambleMatchEntity: " + gambleMatchEntity);
		}
		logger.info("Start saveAll");
		saveAll(updatedGambleMatchList);

	}

	public GambleUpdateData clearGambleMatches(GambleUpdateData gambleData) {
		logger.info("Clear Gamble Matches: ");
		if (gambleData.checkMissDataForMatches()) {
			deleteByGambleId(gambleData.getGambleEntity().getId());
		} else {

			boolean hasDeletions = deleteByGambleDataStage(gambleData);
			if (hasDeletions) {
				gambleData.setGambleMatches(findAllByGambleId(gambleData.getGambleEntity().getId()));
			}

			List<GambleMatchEntity> deleteList = gambleData.gambleMatchesForDelete();
			if (deleteList != null && !deleteList.isEmpty()) {
				deleteAll(deleteList);
				gambleData.setGambleMatches(findAllByGambleId(gambleData.getGambleEntity().getId()));
			}
		}
		return gambleData;
	}

	public boolean deleteByGambleDataStage(GambleUpdateData gambleData) {
		logger.info("delete Gamble Matches By GambleData Stage");
		boolean hasDeletions = false;
		for (GambleCompetition gambleCompetition : gambleData.getGambleEntity().getCompetitions()) {
			if (gambleCompetition.getStages() != null && !gambleCompetition.getStages().isEmpty()) {
				for (GambleStage gambleStage : gambleCompetition.getStages()) {
					if (!gambleStage.isActive()
							&& gambleData.isExistMatchesByStage(gambleCompetition.getId(), gambleStage.getName())) {
						hasDeletions = true;
						logger.info("	Delete Stage= {} for GambleId= {} and CompetitionId={}", gambleStage.getName(),
								gambleData.getGambleEntity().getId(), gambleCompetition.getId());
						deleteAllGambleMatchesByGambleIdAndCompetitionIdAndStage(gambleData.getGambleEntity().getId(),
								gambleCompetition.getId(), gambleStage.getName());
					}
				}
			}
		}
		return hasDeletions;
	}

	public List<GambleMatchEntity> getGambleMatchesForUpdate(GambleUpdateData gambleData) {
		logger.info("Get Gamble Matches for update");
		if (gambleData != null && gambleData.getGambleEntity() != null && !gambleData.isGambleCompetitionsBlank()
				&& !gambleData.isGambleParticipantsBlank()) {
			logger.info("Gamble has data for update");

			Long gambleId = gambleData.getGambleEntity().getId();

			List<GambleCompetition> competitions = gambleData.getGambleEntity().getCompetitions();
			List<GambleUser> participants = gambleData.getGambleEntity().getParticipants();
			GambleRuleEntity gambleRule = gambleData.getGambleEntity().getRule();

			List<GambleMatchEntity> updatedGambleMatchList = new ArrayList<>();
			for (GambleCompetition competition : competitions) {
				SeasonEntity seasonEntity = seasonService.findById(competition.getSeasonId());
				List<MatchEntity> matches = seasonEntity.getMatches();
				for (GambleUser user : participants) {
					for (MatchEntity match : matches) {
						GambleMatchEntity matchEntity = null;
						if ((matchEntity = gambleData.isExist(gambleId, competition.getId(), match.getId(),
								user.getId())) == null) {
							matchEntity = mapGambleMatch(gambleId, competition, user, match, gambleRule);
							updatedGambleMatchList.add(matchEntity);

						} else {
							if (!matchEntity.getMatch().getLastUpdated().equals(match.getLastUpdated())) {
								matchEntity.setMatch(match);
								updatedGambleMatchList.add(matchEntity);
							}
						}
					}
				}
			}
			logger.info("Get {} new records", updatedGambleMatchList.size());
			return updatedGambleMatchList;
		}
		logger.info("Gamble hasn't data for update");
		return null;
	}

	private GambleMatchEntity mapGambleMatch(Long gambleId, GambleCompetition competition, GambleUser user,
			MatchEntity match, GambleRuleEntity gambleRule) {
		GambleMatchEntity matchEntity = new GambleMatchEntity();
		matchEntity.setGambleId(gambleId);
		matchEntity.setCompetitionId(competition.getId());
		matchEntity.setSeasonId(competition.getSeasonId());
		matchEntity.setMatchId(match.getId());
		matchEntity.setMatch(match);
		matchEntity.setStage(match.getStage());
		matchEntity.setUserId(user.getId());
		matchEntity.setUser(user);
		matchEntity.setRule(gambleRule);
		// private String id;
		// private Long gambleId;
		// private Long competitionId;
		// private Long seasonId;
		// private Long matchId;
		// private MatchEntity match;
		// private String state;
		// private Long userId;
		// private GambleUser user;
		// private GambleRuleEntity rule;
		// private Integer scoreFullTimeHomeTeam;
		// private Integer scoreFullTimeAwayTeam;
		// private Integer scoreExtraTimeHomeTeam;
		// private Integer scoreExtraTimeAwayTeam;
		// private Integer scorePenaltiesHomeTeam;
		// private Integer scorePenaltiesAwayTeam;
		// private Integer total;
		return matchEntity;
	}
}

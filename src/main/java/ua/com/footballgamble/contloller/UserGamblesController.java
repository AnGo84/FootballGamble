package ua.com.footballgamble.contloller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import ua.com.footballgamble.model.entity.GambleCompetition;
import ua.com.footballgamble.model.entity.GambleEntity;
import ua.com.footballgamble.model.entity.GambleMatchEntity;
import ua.com.footballgamble.model.entity.GambleUserScore;
import ua.com.footballgamble.primefaces.PrimeFacesMessageUtils;
import ua.com.footballgamble.service.GambleMatchServiceImpl;
import ua.com.footballgamble.service.GambleServiceImpl;

@Named(value = "userGamblesController")
@ViewScoped
public class UserGamblesController implements Serializable {
	private static final long serialVersionUID = -1;
	public static final Logger logger = LoggerFactory.getLogger(UserGamblesController.class);

	@Autowired
	private GambleServiceImpl gambleService;

	private List<GambleEntity> userGambles;
	private GambleEntity selectedGamble;

	@Autowired
	private GambleMatchServiceImpl gambleMatchService;

	private List<GambleMatchEntity> gambleMatches;
	private List<GambleMatchEntity> filteredGambleMatches;
	private GambleMatchEntity selectedMatch;

	private List<GambleUserScore> scoreTable;

	@PostConstruct
	public void loadData() {
		logger.info("PostConstruct loadData");
		try {
			userGambles = gambleService.findAllActiveForUser(getPrincipal());
			if (userGambles != null && !userGambles.isEmpty()) {
				selectedGamble = userGambles.get(0);
				updateGambleData(selectedGamble);
			}

		} catch (Exception e) {
			logger.error("Error on init bean", e);
			PrimeFacesMessageUtils.addGlobalErrorMessage("Error on get data: " + e.getMessage());
		}
	}

	public void updateGambleData(GambleEntity gamble) {
		if (gamble != null) {
			scoreTable = gambleService.getGambleUsersScores(gamble.getId());
			gambleMatches = gambleMatchService.findAllUserMatchesByGambleId(gamble.getId(), getPrincipal());
		} else {
			scoreTable = null;
			gambleMatches = null;
		}
	}

	public void onChangeGamble() {
		logger.info("onChangeGamble ");
		updateGambleData(selectedGamble);
		// logger.info("Selected Season: " + selectedSeasonItem);
		/*
		 * matches = loadSeasonMatches(Long.valueOf(selectedSeasonItem));
		 * 
		 * PrimeFacesMessageUtils.addGlobalInfoMessage("Selected Season: " +
		 * selectedSeasonItem);
		 */

		// logger.info("Matches: " + matches);
	}

	private String getPrincipal() {
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}

	public void onCellEdit(GambleMatchEntity editedGambleMatch) {
		logger.info("onCellEdit: " + editedGambleMatch);

		GambleMatchEntity gambleMatch = gambleMatches.stream()
				.filter(match -> editedGambleMatch.getId().equals(match.getId())).findAny().orElse(null);
		if (gambleMatch != null) {
			gambleMatch.setEdited(true);
			gambleMatch.setScoreFullTimeHomeTeam(editedGambleMatch.getScoreFullTimeHomeTeam());
			gambleMatch.setScoreFullTimeAwayTeam(editedGambleMatch.getScoreFullTimeAwayTeam());
			/*
			 * GambleMatchScore matchScore = new GambleMatchScore(gambleMatch);
			 * matchScore.calcMatchScore();
			 * gambleMatch.setTotal(matchScore.getGambleMatch().getTotal());
			 */
		}
	}

	public void onGambleMatchesUpdate() {
		logger.info("onGambleMatchesUpdate");

		List<GambleMatchEntity> editedMatches = gambleMatches.stream().filter(match -> match.isEdited())
				.collect(Collectors.toList());
		if (editedMatches != null && !editedMatches.isEmpty()) {
			logger.info("GambleMatches for Update: " + editedMatches.size());
			try {
				gambleMatchService.saveAll(editedMatches);
				PrimeFacesMessageUtils.addGlobalInfoMessage("Updated '" + editedMatches.size() + "' matches");

				gambleMatches.stream().filter(match -> match.isEdited()).forEach(match -> match.setEdited(false));

			} catch (Exception ex) {
				logger.error("Error on Update matches: " + ex.getMessage());
				PrimeFacesMessageUtils.addGlobalErrorMessage("Error on Update matches: " + ex.getMessage());
			}
		}
	}

	// Getters-Setters
	public List<GambleEntity> getUserGambles() {
		return userGambles;
	}

	//
	public List<String> getUserGamblesCompetitionsList() {
		if (selectedGamble == null || selectedGamble.getCompetitions() == null
				|| selectedGamble.getCompetitions().isEmpty()) {
			return null;
		}
		List<String> list = new ArrayList<>();
		for (GambleCompetition competition : selectedGamble.getCompetitions()) {
			list.add(competition.getName());
		}
		return list;
	}

	public void setUserGambles(List<GambleEntity> userGambles) {
		this.userGambles = userGambles;
	}

	public GambleEntity getSelectedGamble() {
		return selectedGamble;
	}

	public void setSelectedGamble(GambleEntity selectedGamble) {
		this.selectedGamble = selectedGamble;
	}

	public List<GambleMatchEntity> getGambleMatches() {
		return gambleMatches;
	}

	public void setGambleMatches(List<GambleMatchEntity> gambleMatches) {
		this.gambleMatches = gambleMatches;
	}

	public List<GambleMatchEntity> getFilteredGambleMatches() {
		return filteredGambleMatches;
	}

	public void setFilteredGambleMatches(List<GambleMatchEntity> filteredGambleMatches) {
		this.filteredGambleMatches = filteredGambleMatches;
	}

	public GambleMatchEntity getSelectedMatch() {
		return selectedMatch;
	}

	public void setSelectedMatch(GambleMatchEntity selectedMatch) {
		this.selectedMatch = selectedMatch;
	}

	public List<GambleUserScore> getScoreTable() {
		return scoreTable;
	}

	public void setScoreTable(List<GambleUserScore> scoreTable) {
		this.scoreTable = scoreTable;
	}

}

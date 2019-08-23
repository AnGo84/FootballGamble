package ua.com.footballgamble.contloller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.FacesException;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import ua.com.footballgamble.model.EventType;
import ua.com.footballgamble.model.entity.CompetitionEntity;
import ua.com.footballgamble.model.entity.MatchEntity;
import ua.com.footballgamble.model.entity.SeasonEntity;
import ua.com.footballgamble.primefaces.FacesContextUtils;
import ua.com.footballgamble.primefaces.PrimeFacesMessageUtils;
import ua.com.footballgamble.service.CompetitionServiceImpl;
import ua.com.footballgamble.service.SeasonServiceImpl;

@Named(value = "competitionController")
@ViewScoped
public class CompetitionController implements Serializable {
	private static final long serialVersionUID = -1;

	public static final Logger logger = LoggerFactory.getLogger(CompetitionController.class);
	@Autowired
	private CompetitionServiceImpl competitionService;
	@Autowired
	private SeasonServiceImpl seasonService;

	private List<MatchEntity> matches;

	private CompetitionEntity selectedCompetition;
	private SeasonEntity selectedSeason;
	private MatchEntity selectedMatch;

	private EventType eventType;

	@PostConstruct
	public void loadData() throws FacesException {
		logger.info("PostConstruct loadData");

		eventType = (EventType) FacesContextUtils.getSessionMapObject("eventType");

		if (eventType == null) {
			eventType = EventType.VIEW;
		}
		selectedCompetition = (CompetitionEntity) FacesContextUtils.getSessionMapObject("selectedCompetition");
		logger.info("Get from params Competition: " + selectedCompetition);
		if (selectedCompetition == null) {
			selectedCompetition = new CompetitionEntity();
			selectedSeason = null;
			matches = null;
		} else {
			// selectedSeason = selectedCompetition.getCurrentSeason();
			if (selectedCompetition.getSeasons() != null && !selectedCompetition.getSeasons().isEmpty()) {
				selectedSeason = selectedCompetition.getSeasons().get(0);
				matches = loadSeasonMatches(selectedSeason);
			} else {
				selectedSeason = null;
				matches = null;
			}
		}

		List<String> list = Arrays.asList("selectedCompetition", "eventType");
		String[] array = list.stream().toArray(String[]::new);
		FacesContextUtils.clearMaps(array);

	}

	private List<MatchEntity> loadSeasonMatches(SeasonEntity season) {
		logger.info("Load matches for season: " + season);
		List<MatchEntity> matches = null;
		if (season != null) {
			SeasonEntity loadedSeason;
			if ((loadedSeason = seasonService.findById(season.getId())) != null) {
				matches = loadedSeason.getMatches();
			}
		}
		logger.info("Loaded matches: " + matches);
		return matches;
	}

	public void onUpdateCompetitionFromAPI() {
		logger.info("On Update Selected Competition From API: " + selectedCompetition);
		if (selectedCompetition == null) {
			PrimeFacesMessageUtils.addGlobalErrorMessage("Competition not selected!");

		} else {
			try {
				competitionService.updateFromAPI(selectedCompetition);
				selectedCompetition = competitionService.findById(selectedCompetition.getId());
				PrimeFacesMessageUtils
						.addGlobalInfoMessage("Competition '" + selectedCompetition.getName() + "' was updated");
			} catch (Exception ex) {
				logger.error("Error on Update for " + selectedCompetition.getName() + ": " + ex.getMessage());
				PrimeFacesMessageUtils.addGlobalErrorMessage(
						"Error on Update for " + selectedCompetition.getName() + ": " + ex.getMessage());
			}
		}
	}

	/*
	 * public String onUserEditSubmit() throws FacesException {
	 * logger.info("Submit action '" + eventType + "' for: " + selectedUser); if
	 * (showPassPanel) { selectedUser.setPassword(encoder.encode(pass)); } try { if
	 * (eventType.equals(EventType.ADD)) { userService.saveUser(selectedUser);
	 * PrimeFacesMessageUtils.addGlobalInfoMessage("User '" +
	 * selectedUser.getLogin() + "' was added"); } else if
	 * (eventType.equals(EventType.EDIT)) { userService.updateUser(selectedUser);
	 * PrimeFacesMessageUtils.addGlobalInfoMessage("User '" +
	 * selectedUser.getLogin() + "' was edited"); } } catch (Exception ex) {
	 * logger.error("Error on " + eventType + " for " + selectedUser + ": " +
	 * ex.getMessage()); PrimeFacesMessageUtils.addGlobalErrorMessage("Error on " +
	 * eventType + ": " + ex.getMessage()); return ""; }
	 * 
	 * return "users.xhmtl?faces-redirect=true"; return
	 * "users.xhmtl?faces-redirect=true"; }
	 */

	/*
	 * public String onUserEditCancel() { logger.info("Cancel action " + eventType +
	 * " for: " + selectedUser); return "users.xhmtl?faces-redirect=true"; return
	 * "users.xhmtl?faces-redirect=true"; }
	 */

	public void onChangeSeason() {
		logger.info("onChangeSeason ");
		logger.info("Selected Season: " + selectedSeason);
		matches = loadSeasonMatches(selectedSeason);
		logger.info("Matches: " + matches);
	}

	public String onBackToCompetitions() {
		logger.info("onBackToCompetitions ");
		/* return "users.xhmtl?faces-redirect=true"; */
		return "competitions.xhmtl?faces-redirect=true";
	}

	// Getters-Setters

	public EventType getEventType() {
		return eventType;
	}

	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}

	public CompetitionEntity getSelectedCompetition() {
		return selectedCompetition;
	}

	public void setSelectedCompetition(CompetitionEntity selectedCompetition) {
		this.selectedCompetition = selectedCompetition;
	}

	public SeasonEntity getSelectedSeason() {
		return selectedSeason;
	}

	public void setSelectedSeason(SeasonEntity selectedSeason) {
		this.selectedSeason = selectedSeason;
	}

	public List<MatchEntity> getMatches() {
		return matches;
	}

	public void setMatches(List<MatchEntity> matches) {
		this.matches = matches;
	}

	public MatchEntity getSelectedMatch() {
		return selectedMatch;
	}

	public void setSelectedMatch(MatchEntity selectedMatch) {
		this.selectedMatch = selectedMatch;
	}

}

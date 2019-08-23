package ua.com.footballgamble.contloller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import ua.com.footballgamble.model.EventType;
import ua.com.footballgamble.model.entity.CompetitionEntity;
import ua.com.footballgamble.model.entity.SeasonEntity;
import ua.com.footballgamble.primefaces.FacesContextUtils;
import ua.com.footballgamble.primefaces.PrimeFacesMessageUtils;
import ua.com.footballgamble.service.CompetitionServiceImpl;

@Named(value = "competitionsController")
@ViewScoped
public class ConpetitionsController implements Serializable {
	private static final long serialVersionUID = -1;
	public static final Logger logger = LoggerFactory.getLogger(ConpetitionsController.class);
	@Autowired
	private CompetitionServiceImpl competitionService;

	private List<CompetitionEntity> competitions;

	private CompetitionEntity selectedCompetition;
	private SeasonEntity selectedSeason;

	@PostConstruct
	public void loadData() {
		logger.info("PostConstruct loadData");
		competitions = competitionService.findAll();
	}

	/*
	 * public String onAddNewUser() { logger.info("On Add new User");
	 * FacesContextUtils.putSessionMapObject("selectedUser", new User());
	 * FacesContextUtils.putSessionMapObject("eventType", EventType.ADD);
	 * 
	 * FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put(
	 * "selectedUser", new User());
	 * FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put(
	 * "eventType", EventType.ADD);
	 * 
	 * return "user.html?faces-redirect=true"; }
	 */

	public void onUpdateFromAPI() {
		logger.info("On Update Selected Competition From API: " + selectedCompetition);
		if (selectedCompetition == null) {
			PrimeFacesMessageUtils.addGlobalErrorMessage("Competition not selected!");

		} else {
			onUpdateFromAPI(selectedCompetition);
		}
	}

	public void onUpdateFromAPI(CompetitionEntity competition) {
		logger.info("On Update Competition From API: " + competition);
		if (competition == null) {
			PrimeFacesMessageUtils.addGlobalErrorMessage("Competition not selected!");

		} else {
			try {
				competitionService.updateFromAPI(competition);
				competitions = competitionService.findAll();
				PrimeFacesMessageUtils.addGlobalInfoMessage("Competition '" + competition.getName() + "' was updated");
			} catch (Exception ex) {
				logger.error("Error on Update for " + competition.getName() + ": " + ex.getMessage());
				PrimeFacesMessageUtils
						.addGlobalErrorMessage("Error on Update for " + competition.getName() + ": " + ex.getMessage());
			}
		}
	}

	public String onView(CompetitionEntity competition) {
		logger.info("Get Competition for View: " + competition);
		FacesContextUtils.putSessionMapObject("selectedCompetition", competition);
		FacesContextUtils.putSessionMapObject("eventType", EventType.VIEW);
		return "competition.html?faces-redirect=true";
	}

	/*
	 * public String onEditUser(User user) { logger.info("Get user for Edit: " +
	 * user);
	 * 
	 * FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put(
	 * "selectedUser", selectedUser);
	 * FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put(
	 * "eventType", EventType.EDIT);
	 * 
	 * FacesContextUtils.putSessionMapObject("selectedUser", user);
	 * FacesContextUtils.putSessionMapObject("eventType", EventType.EDIT); return
	 * "user.html?faces-redirect=true"; }
	 */

	/*
	 * public void onDeleteUser(User user) { logger.info("onDeleteUser: " + user);
	 * 
	 * try { userService.deleteUserById(user.getId()); competitions.remove(user);
	 * PrimeFacesMessageUtils.addGlobalInfoMessage("User '" + user.getLogin() +
	 * "' was deleted"); } catch (Exception ex) {
	 * logger.error("Error on DELETE for " + user + ": " + ex.getMessage());
	 * PrimeFacesMessageUtils .addGlobalErrorMessage("Error on DELETE for id" +
	 * user.getId() + ": " + ex.getMessage()); } }
	 */

	// Getters-Setters

	public List<CompetitionEntity> getCompetitions() {
		return competitions;
	}

	public void setCompetitions(List<CompetitionEntity> competitions) {
		this.competitions = competitions;
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
}

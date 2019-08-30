package ua.com.footballgamble.contloller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import ua.com.footballgamble.model.entity.TeamEntity;
import ua.com.footballgamble.primefaces.PrimeFacesMessageUtils;
import ua.com.footballgamble.service.TeamServiceImpl;

@Named(value = "teamsController")
@ViewScoped
public class TeamsController implements Serializable {
	private static final long serialVersionUID = -1;
	public static final Logger logger = LoggerFactory.getLogger(TeamsController.class);
	@Autowired
	private TeamServiceImpl teamService;

	private List<TeamEntity> teams;
	private List<TeamEntity> filteredTeams;
	private TeamEntity selectedTeam;

	@PostConstruct
	public void loadData() {
		logger.info("PostConstruct loadData");
		teams = teamService.findAll();
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
		logger.info("On Update Selected Team From API: " + selectedTeam);
		if (selectedTeam == null) {
			PrimeFacesMessageUtils.addGlobalErrorMessage("Team not selected!");

		} else {
			onUpdateFromAPI(selectedTeam);
		}
	}

	public void onUpdateFromAPI(TeamEntity team) {
		logger.info("On Update Team From API: " + team);
		if (team == null) {
			PrimeFacesMessageUtils.addGlobalErrorMessage("Team not selected!");

		} else {
			try {
				teamService.updateFromAPI(team);
				teams = teamService.findAll();

				PrimeFacesMessageUtils.addGlobalInfoMessage("Team '" + team.getName() + "' was updated");
			} catch (Exception ex) {
				logger.error("Error on Update for " + team.getName() + ": " + ex.getMessage());
				PrimeFacesMessageUtils
						.addGlobalErrorMessage("Error on Update for " + team.getName() + ": " + ex.getMessage());
			}
		}
	}

	/*
	 * public String onView(CompetitionEntity competition) {
	 * logger.info("Get Competition for View: " + competition);
	 * FacesContextUtils.putSessionMapObject("selectedCompetition", competition);
	 * FacesContextUtils.putSessionMapObject("eventType", EventType.VIEW); return
	 * "competition.html?faces-redirect=true"; }
	 */

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

	public List<TeamEntity> getTeams() {
		return teams;
	}

	public void setTeams(List<TeamEntity> teams) {
		this.teams = teams;
	}

	public List<TeamEntity> getFilteredTeams() {
		return filteredTeams;
	}

	public void setFilteredTeams(List<TeamEntity> filteredTeams) {
		this.filteredTeams = filteredTeams;
	}

	public TeamEntity getSelectedTeam() {
		return selectedTeam;
	}

	public void setSelectedTeam(TeamEntity selectedTeam) {
		this.selectedTeam = selectedTeam;
	}

}

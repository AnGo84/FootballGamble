package ua.com.footballgamble.contloller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import ua.com.footballgamble.model.entity.GambleEntity;
import ua.com.footballgamble.model.entity.GambleMatchEntity;
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

	@PostConstruct
	public void loadData() {
		logger.info("PostConstruct loadData");
		try {
			userGambles = gambleService.findAllActiveForUser(getPrincipal());

			// gambleMatches = gambleMatchService.findAll();
		} catch (Exception e) {
			logger.error("Error on init bean", e);
			PrimeFacesMessageUtils.addGlobalErrorMessage("Error on get data: " + e.getMessage());
		}
	}

	public void onChangeGamble() {
		logger.info("onChangeSeason ");
		// logger.info("Selected Season: " + selectedSeasonItem);
		/*
		 * matches = loadSeasonMatches(Long.valueOf(selectedSeasonItem));
		 * 
		 * PrimeFacesMessageUtils.addGlobalInfoMessage("Selected Season: " +
		 * selectedSeasonItem);
		 */

		// logger.info("Matches: " + matches);
	}

	/**
	 * This method returns the principal[user-name] of logged-in user.
	 */
	@Bean
	public String getPrincipal() {
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}

	//

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

	public List<GambleEntity> getUserGambles() {
		return userGambles;
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
}

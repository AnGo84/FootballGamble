package ua.com.footballgamble.contloller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import ua.com.footballgamble.model.entity.GambleRuleEntity;
import ua.com.footballgamble.service.GambleRuleServiceImpl;

@Named(value = "gambleRulesController")
@ViewScoped
public class GambleRulesController implements Serializable {
	private static final long serialVersionUID = -1;
	public static final Logger logger = LoggerFactory.getLogger(GambleRulesController.class);
	@Autowired
	private GambleRuleServiceImpl gambleRuleService;

	private List<GambleRuleEntity> gambleRules;
	private List<GambleRuleEntity> filteredGambleRules;
	private GambleRuleEntity selectedRule;

	@PostConstruct
	public void loadData() {
		logger.info("PostConstruct loadData");
		gambleRules = gambleRuleService.findAll();
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

	public List<GambleRuleEntity> getGambleRules() {
		return gambleRules;
	}

	public void setGambleRules(List<GambleRuleEntity> gambleRules) {
		this.gambleRules = gambleRules;
	}

	public List<GambleRuleEntity> getFilteredGambleRules() {
		return filteredGambleRules;
	}

	public void setFilteredGambleRules(List<GambleRuleEntity> filteredGambleRules) {
		this.filteredGambleRules = filteredGambleRules;
	}

	public GambleRuleEntity getSelectedRule() {
		return selectedRule;
	}

	public void setSelectedRule(GambleRuleEntity selectedRule) {
		this.selectedRule = selectedRule;
	}

}

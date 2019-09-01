package ua.com.footballgamble.contloller;

import java.io.Serializable;
import java.util.ArrayList;
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
import ua.com.footballgamble.model.entity.GambleCompetition;
import ua.com.footballgamble.model.entity.GambleEntity;
import ua.com.footballgamble.model.entity.GambleRuleEntity;
import ua.com.footballgamble.model.entity.GambleUser;
import ua.com.footballgamble.primefaces.FacesContextUtils;
import ua.com.footballgamble.primefaces.PrimeFacesMessageUtils;
import ua.com.footballgamble.service.GambleCompetitionServiceImpl;
import ua.com.footballgamble.service.GambleRuleServiceImpl;
import ua.com.footballgamble.service.GambleServiceImpl;
import ua.com.footballgamble.service.GambleUserServiceImpl;
import ua.com.footballgamble.utils.GambleCompetitionUtils;
import ua.com.footballgamble.utils.GambleUserUtils;

@Named(value = "gambleController")
@ViewScoped
public class GambleController implements Serializable {
	private static final long serialVersionUID = -1;

	public static final Logger logger = LoggerFactory.getLogger(GambleController.class);

	/*@Autowired
	private AppEntitiesLists appEntitiesLists;*/

	@Autowired
	private GambleServiceImpl gambleService;

	@Autowired
	private GambleRuleServiceImpl gambleRuleService;

	private List<GambleRuleEntity> gambleRules;

	@Autowired
	private GambleUserServiceImpl gambleUserService;

	private List<GambleUser> allGambleUsersList;
	private List<GambleUser> newGambleUsers;
	private GambleUser selectedGambleUser;

	@Autowired
	private GambleCompetitionServiceImpl gambleCompetitionService;
	private List<GambleCompetition> allGambleCompetitionsList;
	private List<GambleCompetition> newGambleCompetitions;
	private GambleCompetition selectedGambleCompetition;

	// private int gambleId;

	private GambleEntity selectedGamble;

	private EventType eventType;

	@PostConstruct
	public void loadData() throws FacesException {
		logger.info("PostConstruct loadData");

		eventType = (EventType) FacesContextUtils.getSessionMapObject("eventType");

		if (eventType == null) {
			eventType = EventType.VIEW;
		}
		selectedGamble = (GambleEntity) FacesContextUtils.getSessionMapObject("selectedGamble");
		logger.info("Get from params SelectedGamble: " + selectedGamble);
		if (selectedGamble == null) {
			selectedGamble = new GambleEntity();
		}

		if (selectedGamble.getParticipants() == null) {
			List<GambleUser> newList = new ArrayList<>();
			selectedGamble.setParticipants(newList);
		}

		if (selectedGamble.getCompetitons() == null) {
			List<GambleCompetition> newList = new ArrayList<>();
			selectedGamble.setCompetitons(newList);
		}

		List<String> list = Arrays.asList("selectedGamble", "eventType");
		String[] array = list.stream().toArray(String[]::new);
		FacesContextUtils.clearMaps(array);

		gambleRules = gambleRuleService.findAll();
		allGambleUsersList = gambleUserService.findAll();
		allGambleCompetitionsList = gambleCompetitionService.findAll();
	}

	public String onGambleEditSubmit() throws FacesException {
		logger.info("Submit action '" + eventType + "' for: " + selectedGamble);

		try {
			if (eventType.equals(EventType.ADD)) {
				gambleService.save(selectedGamble);
				PrimeFacesMessageUtils.addGlobalInfoMessage("Gamble '" + selectedGamble.getId() + "' was added");
			} else if (eventType.equals(EventType.EDIT)) {
				gambleService.update(selectedGamble);
				PrimeFacesMessageUtils.addGlobalInfoMessage("Gamble '" + selectedGamble.getId() + "' was edited");
			}
		} catch (Exception ex) {
			logger.error("Error on " + eventType + " for " + selectedGamble + ": " + ex.getMessage());
			PrimeFacesMessageUtils.addGlobalErrorMessage("Error on " + eventType + ": " + ex.getMessage());
			return "";
		}

		return "gambles.xhmtl?faces-redirect=true";
	}

	public String onGambleEditCancel() {
		logger.info("Cancel for action '" + eventType + "' for: " + selectedGamble);
		return "gambles.xhmtl?faces-redirect=true";
	}

	public void onAddNewParticipants() {
		logger.info("onAddNewUsers. List size: ");
		if (newGambleUsers == null || newGambleUsers.isEmpty()) {
			PrimeFacesMessageUtils.addGlobalErrorMessage("New user's list is empty!");
		} else {
			logger.info("New user's list size: " + newGambleUsers.size());

			for (GambleUser newUser : newGambleUsers) {
				selectedGamble.getParticipants().add(newUser);
			}
			PrimeFacesMessageUtils.addGlobalInfoMessage("Added '" + newGambleUsers.size() + "' users");
			newGambleUsers.clear();
		}
	}

	public void onAddNewCompetitions() {
		logger.info("onAddNewCompetitions");
		if (newGambleCompetitions == null || newGambleCompetitions.isEmpty()) {
			PrimeFacesMessageUtils.addGlobalErrorMessage("New competition's list is empty!");
		} else {
			logger.info("New competition's list size: " + newGambleCompetitions.size());

			for (GambleCompetition newCompetition : newGambleCompetitions) {
				selectedGamble.getCompetitons().add(newCompetition);
				logger.info("Added new competition: " + newCompetition);
			}
			PrimeFacesMessageUtils.addGlobalInfoMessage("Added '" + newGambleCompetitions.size() + "' competitions");
			newGambleCompetitions.clear();
		}
	}

	public void onDeleteParticipant(GambleUser user) {
		logger.info("onDeleteParticipant: " + user);

		try {

			selectedGamble.getParticipants().remove(user);
			PrimeFacesMessageUtils.addGlobalInfoMessage("User '" + user.getLogin() + "' was deleted");
		} catch (Exception ex) {
			logger.error("Error on DELETE for " + user + ": " + ex.getMessage());
			PrimeFacesMessageUtils
					.addGlobalErrorMessage("Error on DELETE for id" + user.getId() + ": " + ex.getMessage());
		}

	}

	public void onDeleteCompetition(GambleCompetition competition) {
		logger.info("onDeleteCompetition: " + competition);
		try {
			selectedGamble.getCompetitons().remove(competition);
			PrimeFacesMessageUtils.addGlobalInfoMessage("Competition '" + competition.getName() + "' was deleted");
		} catch (Exception ex) {
			logger.error("Error on DELETE for " + competition + ": " + ex.getMessage());
			PrimeFacesMessageUtils
					.addGlobalErrorMessage("Error on DELETE for id" + competition.getName() + ": " + ex.getMessage());
		}

	}

	// Getters-Setters

	public EventType getEventType() {
		return eventType;
	}

	public GambleEntity getSelectedGamble() {
		return selectedGamble;
	}

	public void setSelectedGamble(GambleEntity selectedGamble) {
		this.selectedGamble = selectedGamble;
	}

	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}

	public List<GambleRuleEntity> getGambleRules() {
		return gambleRules;
	}

	public void setGambleRules(List<GambleRuleEntity> gambleRules) {
		this.gambleRules = gambleRules;
	}

	public List<GambleCompetition> getGambleCompetitions() {
		return GambleCompetitionUtils.filterByGamble(allGambleCompetitionsList, selectedGamble);
		// return gambleUsers;
	}

	public List<GambleUser> getGambleUsers() {
		return GambleUserUtils.filterByGamble(allGambleUsersList, selectedGamble);
		// return gambleUsers;
	}

	public GambleUser getSelectedGambleUser() {
		return selectedGambleUser;
	}

	public void setSelectedGambleUser(GambleUser selectedGambleUser) {
		this.selectedGambleUser = selectedGambleUser;
	}

	public List<GambleCompetition> getNewGambleCompetitions() {
		return newGambleCompetitions;
	}

	public void setNewGambleCompetitions(List<GambleCompetition> newGambleCompetitions) {
		this.newGambleCompetitions = newGambleCompetitions;
	}

	public GambleCompetition getSelectedGambleCompetition() {
		return selectedGambleCompetition;
	}

	public void setSelectedGambleCompetition(GambleCompetition selectedGambleCompetition) {
		this.selectedGambleCompetition = selectedGambleCompetition;
	}

	public List<GambleUser> getNewGambleUsers() {
		return newGambleUsers;
	}

	public void setNewGambleUsers(List<GambleUser> newGambleUsers) {
		this.newGambleUsers = newGambleUsers;
	}

}

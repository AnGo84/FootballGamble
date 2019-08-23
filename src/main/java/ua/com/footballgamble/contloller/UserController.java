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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import ua.com.footballgamble.model.EventType;
import ua.com.footballgamble.model.user.User;
import ua.com.footballgamble.model.user.UserRole;
import ua.com.footballgamble.primefaces.FacesContextUtils;
import ua.com.footballgamble.primefaces.PrimeFacesMessageUtils;
import ua.com.footballgamble.service.UserRoleServiceImpl;
import ua.com.footballgamble.service.UserServiceImpl;

@Named(value = "userController")
@ViewScoped
public class UserController implements Serializable {
	private static final long serialVersionUID = -1;

	public static final Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private UserServiceImpl userService;
	@Autowired
	private UserRoleServiceImpl userRoleService;

	@Autowired
	private BCryptPasswordEncoder encoder;

	private List<UserRole> userRoles;

	private int userId;

	private User selectedUser;

	private boolean showPassPanel;
	private EventType eventType;

	private String pass;
	private String passRepeat;

	@PostConstruct
	public void loadData() throws FacesException {
		// products = productRepository.findAll();
		logger.info("PostConstruct loadData");

		// eventType = (EventType)
		// FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get("eventType");
		eventType = (EventType) FacesContextUtils.getSessionMapObject("eventType");

		if (eventType == null) {
			eventType = EventType.VIEW;
		}
		showPassPanel = (eventType.equals(EventType.ADD) ? true : false);

		/*
		 * selectedUser = (User)
		 * FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
		 * .get("selectedUser");
		 */
		selectedUser = (User) FacesContextUtils.getSessionMapObject("selectedUser");
		logger.info("Get from params SelectedUser: " + selectedUser);
		if (selectedUser == null) {
			selectedUser = new User();
		}

		// FacesContext.getCurrentInstance().getExternalContext().getRequestMap().remove("selectedUser");
		// FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("selectedUser");

		List<String> list = Arrays.asList("selectedUser", "eventType");
		String[] array = list.stream().toArray(String[]::new);
		FacesContextUtils.clearMaps(array);

		// selectedUser = userService.findById(userId);
		userRoles = userRoleService.findAllUserRoles();
	}

	public String onUserEditSubmit() throws FacesException {
		logger.info("Submit action '" + eventType + "' for: " + selectedUser);
		if (showPassPanel) {
			selectedUser.setPassword(encoder.encode(pass));
		}
		try {
			if (eventType.equals(EventType.ADD)) {
				userService.saveUser(selectedUser);
				PrimeFacesMessageUtils.addGlobalInfoMessage("User '" + selectedUser.getLogin() + "' was added");
			} else if (eventType.equals(EventType.EDIT)) {
				userService.updateUser(selectedUser);
				PrimeFacesMessageUtils.addGlobalInfoMessage("User '" + selectedUser.getLogin() + "' was edited");
			}
		} catch (Exception ex) {
			logger.error("Error on " + eventType + " for " + selectedUser + ": " + ex.getMessage());
			PrimeFacesMessageUtils.addGlobalErrorMessage("Error on " + eventType + ": " + ex.getMessage());
			return "";
		}

		/* return "users.xhmtl?faces-redirect=true"; */
		return "users.xhmtl?faces-redirect=true";
	}

	public String onUserEditCancel() {
		logger.info("Cancel action " + eventType + " for: " + selectedUser);
		/* return "users.xhmtl?faces-redirect=true"; */
		return "users.xhmtl?faces-redirect=true";
	}

	// Getters-Setters

	public List<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	public User getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(User selectedUser) {
		this.selectedUser = selectedUser;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public EventType getEventType() {
		return eventType;
	}

	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getPassRepeat() {
		return passRepeat;
	}

	public void setPassRepeat(String passRepeat) {
		this.passRepeat = passRepeat;
	}

	public boolean isShowPassPanel() {
		return showPassPanel;
	}

	public void setShowPassPanel(boolean showPassPanel) {
		this.showPassPanel = showPassPanel;
	}

}

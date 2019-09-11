package ua.com.footballgamble.contloller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import ua.com.footballgamble.model.EventType;
import ua.com.footballgamble.model.user.User;
import ua.com.footballgamble.model.user.UserRole;
import ua.com.footballgamble.primefaces.FacesContextUtils;
import ua.com.footballgamble.primefaces.PrimeFacesMessageUtils;
import ua.com.footballgamble.service.UserRoleServiceImpl;
import ua.com.footballgamble.service.UserServiceImpl;

@Named(value = "usersController")
@ViewScoped
public class UsersController implements Serializable {
	private static final long serialVersionUID = -1;
	public static final Logger logger = LoggerFactory.getLogger(UsersController.class);
	@Autowired
	private UserServiceImpl userService;
	@Autowired
	private UserRoleServiceImpl userRoleService;

	private List<User> users;

	private List<UserRole> userRoles;

	private User selectedUser;

	@PostConstruct
	public void loadData() {
		// products = productRepository.findAll();
		logger.info("PostConstruct loadData");
		users = userService.findAllUsers();
		userRoles = userRoleService.findAllUserRoles();
	}

	public String onAddNewUser() {
		logger.info("On Add new User");
		User newUser = new User();
		newUser.setId(getNextId());

		FacesContextUtils.putSessionMapObject("selectedUser", newUser);
		FacesContextUtils.putSessionMapObject("eventType", EventType.ADD);
		/*
		 * FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put(
		 * "selectedUser", new User());
		 * FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put(
		 * "eventType", EventType.ADD);
		 */
		return "user.html?faces-redirect=true";
	}

	public Long getNextId() {
		Long maxId = 0l;

		for (User user : users) {
			if (user.getId() > maxId) {
				maxId = user.getId();
			}
		}
		return ++maxId;
	}

	public String onEditUser(User user) {
		logger.info("Get user for Edit: " + user);
		/*
		 * FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put(
		 * "selectedUser", selectedUser);
		 * FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put(
		 * "eventType", EventType.EDIT);
		 */
		FacesContextUtils.putSessionMapObject("selectedUser", user);
		FacesContextUtils.putSessionMapObject("eventType", EventType.EDIT);
		return "user.html?faces-redirect=true";
	}

	public void onDeleteUser(User user) {
		logger.info("onDeleteUser: " + user);

		try {
			userService.deleteUserById(user.getId());
			users.remove(user);
			PrimeFacesMessageUtils.addGlobalInfoMessage("User '" + user.getLogin() + "' was deleted");
		} catch (Exception ex) {
			logger.error("Error on DELETE for " + user + ": " + ex.getMessage());
			PrimeFacesMessageUtils
					.addGlobalErrorMessage("Error on DELETE for id" + user.getId() + ": " + ex.getMessage());
		}

		/*
		 * FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put(
		 * "selectedUser", selectedUser);
		 * FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put(
		 * "eventType", EventType.EDIT); return "user.html";
		 */
	}

	// template
	public String onViewLoginedUserData() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		/*
		 * org.springframework.security.core.userdetails.User loginedUser =
		 * (org.springframework.security.core.userdetails.User) ((Authentication)
		 * principal) .getPrincipal();
		 */
		String username = "";
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}

		/*
		 * logger.info("Get logined user for VIEW|EDIT: " + loginedUser.getUsername() +
		 * "(" + loginedUser + ")"); User selectedUser =
		 * userService.findByLogin(loginedUser.getUsername());
		 */
		logger.info("Get logined user for VIEW|EDIT: " + username);
		User selectedUser = userService.findByLogin(username);

		/*
		 * FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put(
		 * "selectedUser", selectedUser);
		 * FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put(
		 * "eventType", EventType.EDIT);
		 */

		FacesContextUtils.putSessionMapObject("selectedUser", selectedUser);
		FacesContextUtils.putSessionMapObject("eventType", EventType.EDIT);

		return "user_info.html?faces-redirect=true";
	}

	// Getters-Setters
	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

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

}

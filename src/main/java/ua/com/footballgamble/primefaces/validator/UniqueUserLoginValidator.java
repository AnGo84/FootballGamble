package ua.com.footballgamble.primefaces.validator;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ua.com.footballgamble.exception.NotFoundException;
import ua.com.footballgamble.model.user.User;
import ua.com.footballgamble.service.UserServiceImpl;

/*Example:
 * 1: https://stackoverflow.com/questions/14750895/how-to-validate-uniqueness-in-jsf-and-get-a-message-to-the-user
 * 2: https://www.springfuse.com/2012/09/27/jsf2-jpa2-unique-validator.html
 */

//@RequestScoped
//@FacesValidator("uniqueUserLoginValidator")
@Component("uniqueUserLoginValidator")
//@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class UniqueUserLoginValidator implements Validator, Serializable {
	private static final long serialVersionUID = 1L;

	private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserServiceImpl userService;

	@Override
	public void validate(final FacesContext context, final UIComponent comp, final Object newValue)
			throws ValidatorException {
		logger.info("Validate for Login: " + newValue);
		if (newValue == null) {
			return;
		}
		User currentUser = (User) comp.getAttributes().get("currentUser");
		logger.info("For User: " + currentUser);
		String currentLogin = (String) newValue;

		// boolean isLoginExist = false;
		User findUser = null;
		try {
			logger.debug("Is userService null: " + ((userService == null ? "yes" : "no")));
			findUser = userService.findByLogin(currentLogin);
			// isLoginExist = userService.isLoginExist(currentLogin);
		} catch (NotFoundException ex) {

			findUser = null;
		} /*
			 * catch (DataConflictException ex) { // isLoginExist = false; throw new
			 * IllegalStateException("Cannot check Login! Msg: " + ex.getMessage()); } catch
			 * (RestAPIServerException ex) { // isLoginExist = false; throw new
			 * IllegalStateException("Cannot check Login! Msg: " + ex.getMessage()); }
			 */
		catch (Exception ex) {
			// isLoginExist = false;
			// throw new IllegalStateException("Cannot check Login! Msg: " +
			// ex.getMessage());
			FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Service Error", ex.getMessage());
			throw new ValidatorException(fm);
		}

		// if (isLoginExist) {

		if (findUser != null && (currentUser.getId() == null || !currentUser.getId().equals(findUser.getId()))) {

			// FacesMessage fm = new FacesMessage("Login " + currentLogin + " already
			// exists");
			// fm.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Validation Error",
					"Login " + currentLogin + " already exists");
			throw new ValidatorException(fm);
		}
		// }

	}
}

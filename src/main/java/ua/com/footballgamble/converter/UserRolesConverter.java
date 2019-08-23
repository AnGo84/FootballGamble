package ua.com.footballgamble.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;

import org.springframework.beans.factory.annotation.Autowired;

import ua.com.footballgamble.service.UserRoleServiceImpl;

@FacesConverter(value = "userRoleConverter")
public class UserRolesConverter {
	@Autowired
	private UserRoleServiceImpl userRoleService;

	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
		/*
		 * UserRole role = userRoleService.fi return new Characteristic("testKey",
		 * value, "testType");
		 */
		return null;
	}

	public String getAsString(FacesContext fc, UIComponent uic, Object object) {
		if (object == null) {
			return "";
		}
		return "";
		/*
		 * if (object instanceof UserRole) { UserRole userRole = (UserRole) object;
		 * String name = userRole.getValue();
		 * System.out.println("In the converter. Returning " + name); return name; }
		 * else { throw new ConverterException(new FacesMessage(object +
		 * " is not a Characteristic")); }
		 */
	}

}

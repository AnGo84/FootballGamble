package ua.com.footballgamble.converter.faces;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import ua.com.footballgamble.model.entity.GambleUser;

@FacesConverter(value = "gambleUserConverter")
public class GambleUserConverter implements Converter<GambleUser> {
	public static final Logger logger = LoggerFactory.getLogger(GambleUserConverter.class);

	@Autowired
	private AppEntitiesLists appEntitiesLists;

	@Override
	public GambleUser getAsObject(FacesContext ctx, UIComponent uiComponent, String objectName) {
		logger.info("Get object by string: " + objectName);
		return appEntitiesLists.getGambleUserEntityByLogin(objectName);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, GambleUser value) {
		logger.info("Get String grom object: " + value);
		if (value == null) {
			return null;
		}
		return String.valueOf(value.getLogin());
	}

}

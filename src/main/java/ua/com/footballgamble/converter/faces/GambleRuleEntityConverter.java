package ua.com.footballgamble.converter.faces;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import ua.com.footballgamble.model.entity.GambleRuleEntity;

@FacesConverter(value = "gambleRuleEntityConverter")
public class GambleRuleEntityConverter implements Converter<GambleRuleEntity> {
	public static final Logger logger = LoggerFactory.getLogger(GambleRuleEntityConverter.class);

	@Autowired
	private AppEntitiesLists appEntitiesLists;

	@Override
	public GambleRuleEntity getAsObject(FacesContext ctx, UIComponent uiComponent, String objectName) {
		logger.info("Get object by string: " + objectName);
		/*
		 * long id; try { id = Long.valueOf(objectName); } catch (Exception e) { return
		 * null;}
		 */

		return appEntitiesLists.getGambleRuleEntityByFullName(objectName);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, GambleRuleEntity value) {
		logger.info("Get String grom object: " + value);
		if (value == null) {
			return null;
		}
		return String.valueOf(value.getFullName());
	}

}

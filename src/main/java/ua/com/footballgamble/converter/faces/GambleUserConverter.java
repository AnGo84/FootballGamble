package ua.com.footballgamble.converter.faces;

import javax.faces.component.UIComponent;
import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import ua.com.footballgamble.model.entity.GambleUser;

import java.util.List;
import java.util.function.Predicate;

@FacesConverter(value = "gambleUserConverter")
public class GambleUserConverter implements Converter<GambleUser> {
	public static final Logger logger = LoggerFactory.getLogger(GambleUserConverter.class);

	/*@Autowired
	private AppEntitiesLists appEntitiesLists;*/

	@Override
	public GambleUser getAsObject(FacesContext ctx, UIComponent uiComponent, String objectName) {
		logger.info("Get object by string: " + objectName);
		//return appEntitiesLists.getGambleUserEntityByLogin(objectName);
		return getSelectedItemAsEntity(uiComponent, objectName);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, GambleUser value) {
		logger.info("Get String from object: " + value);
		if (value == null) {
			return null;
		}
		return String.valueOf(value.getLogin());
	}

	private GambleUser getSelectedItemAsEntity(UIComponent comp, String value) {
		GambleUser item = null;

		if (StringUtils.isBlank(value)) {
			return null;
		}

		List<GambleUser> selectItems = null;
		for (UIComponent uic : comp.getChildren()) {
			if (uic instanceof UISelectItems) {

				selectItems = (List<GambleUser>) ((UISelectItems) uic).getValue();

				if (selectItems != null && !selectItems.isEmpty()) {
					selectItems.forEach(selectItem->logger.info("SelectItem: " + selectItem));
					Predicate<GambleUser> predicate = i -> i.getLogin().equals(value);
					item = selectItems.stream().filter(predicate).findFirst().orElse(null);
				}
				/*for (GambleRuleEntity gambleRuleEntity : gambleRules) {
					logger.info("		Compare " + gambleRuleEntity.getFullName()+ " with " + fullName);
					if (gambleRuleEntity.getFullName().equals(fullName)) {
						return gambleRuleEntity;
					}
				}*/
			}
		}

		return item;
	}
}

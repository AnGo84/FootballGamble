package ua.com.footballgamble.converter.faces;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.footballgamble.model.entity.GambleUser;

import javax.faces.component.UIComponent;
import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.util.List;
import java.util.function.Predicate;

@FacesConverter(value = "triStateToBooleanConverter")
public class TriStateToBooleanConverter implements Converter<Boolean> {
	public static final Logger logger = LoggerFactory.getLogger(TriStateToBooleanConverter.class);

	@Override
	public Boolean getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
		logger.info("String value: " + s);
		if (s.equals("2")) {
			return false;
		}else if (s.equals("1")) {
			return true;
		}else{
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent uiComponent, Boolean o) {
		logger.info("Object value: " + o);
		if (!o) {
			return "2";
		}else if (o) {
			return "1";
		}else{
			return "0";
		}
	}



/*	private GambleUser getSelectedItemAsEntity(UIComponent comp, String value) {
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
				*//*for (GambleRuleEntity gambleRuleEntity : gambleRules) {
					logger.info("		Compare " + gambleRuleEntity.getFullName()+ " with " + fullName);
					if (gambleRuleEntity.getFullName().equals(fullName)) {
						return gambleRuleEntity;
					}
				}*//*
			}
		}

		return item;
	}*/
}

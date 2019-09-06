package ua.com.footballgamble.converter.faces;

import java.util.List;
import java.util.function.Predicate;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ua.com.footballgamble.model.entity.GambleEntity;

@FacesConverter(value = "gambleEntityConverter")
public class GambleEntityConverter implements Converter<GambleEntity> {
	public static final Logger logger = LoggerFactory.getLogger(GambleEntityConverter.class);

	@Override
	public GambleEntity getAsObject(FacesContext ctx, UIComponent uiComponent, String objectName) {

		try {
			// return appEntitiesLists.getGambleRuleEntityByFullName(objectName);
			return getSelectedItemAsEntity(uiComponent, objectName);
		} catch (NumberFormatException e) {
			throw new ConverterException(new FacesMessage(objectName + " is not a valid Gamble name"), e);
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, GambleEntity value) {
		// logger.info("Get String from object: " + value);
		if (value == null) {
			return "";
		}

		return String.valueOf(value.getName());
	}

	private GambleEntity getSelectedItemAsEntity(UIComponent comp, String value) {
		GambleEntity item = null;

		if (StringUtils.isBlank(value)) {
			return null;
		}

		List<GambleEntity> selectItems = null;
		for (UIComponent uic : comp.getChildren()) {
			if (uic instanceof UISelectItems) {

				selectItems = (List<GambleEntity>) ((UISelectItems) uic).getValue();

				if (selectItems != null && !selectItems.isEmpty()) {
					// selectItems.forEach(selectItem->logger.info("SelectItem: " + selectItem));
					Predicate<GambleEntity> predicate = i -> i.getName().equals(value);
					item = selectItems.stream().filter(predicate).findFirst().orElse(null);
				}
				/*
				 * for (GambleRuleEntity gambleRuleEntity : gambleRules) {
				 * logger.info("		Compare " + gambleRuleEntity.getFullName()+ " with " +
				 * fullName); if (gambleRuleEntity.getFullName().equals(fullName)) { return
				 * gambleRuleEntity; } }
				 */
			}
		}

		return item;
	}

}

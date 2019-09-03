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

import ua.com.footballgamble.model.entity.GambleRuleEntity;

@FacesConverter(value = "gambleRuleEntityConverter")
//@RequestScoped
public class GambleRuleEntityConverter implements Converter<GambleRuleEntity> {
	public static final Logger logger = LoggerFactory.getLogger(GambleRuleEntityConverter.class);

	/*
	 * @Inject private AppEntitiesLists appEntitiesLists;
	 */

	@Override
	public GambleRuleEntity getAsObject(FacesContext ctx, UIComponent uiComponent, String objectName) {
		// logger.info("Get object by string: " + objectName);
		// logger.info("is appEntitiesLists=null: " + (appEntitiesLists==null));

		try {
			// return appEntitiesLists.getGambleRuleEntityByFullName(objectName);
			return getSelectedItemAsEntity(uiComponent, objectName);
		} catch (NumberFormatException e) {
			throw new ConverterException(new FacesMessage(objectName + " is not a valid Rule name"), e);
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, GambleRuleEntity value) {
		// logger.info("Get String from object: " + value);
		if (value == null) {
			return "";
		}

		/*
		 * if (value instanceof GambleRuleEntity) { return String.valueOf(((Warehouse)
		 * modelValue).getId()); } else { throw new ConverterException(new
		 * FacesMessage(modelValue + " is not a valid Warehouse")); }
		 */

		return String.valueOf(value.getFullName());
	}

	private GambleRuleEntity getSelectedItemAsEntity(UIComponent comp, String value) {
		GambleRuleEntity item = null;

		if (StringUtils.isBlank(value)) {
			return null;
		}

		List<GambleRuleEntity> selectItems = null;
		for (UIComponent uic : comp.getChildren()) {
			if (uic instanceof UISelectItems) {

				selectItems = (List<GambleRuleEntity>) ((UISelectItems) uic).getValue();

				if (selectItems != null && !selectItems.isEmpty()) {
					// selectItems.forEach(selectItem->logger.info("SelectItem: " + selectItem));
					Predicate<GambleRuleEntity> predicate = i -> i.getFullName().equals(value);
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

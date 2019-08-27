package ua.com.footballgamble.converter.faces;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ua.com.footballgamble.contloller.CompetitionController;
import ua.com.footballgamble.model.entity.SeasonEntity;

@FacesConverter(value = "competitionSeasonEntityConverter")
public class CompetitionSeasonEntityConverter implements Converter<SeasonEntity> {
	public static final Logger logger = LoggerFactory.getLogger(CompetitionSeasonEntityConverter.class);

	@Override
	public SeasonEntity getAsObject(FacesContext ctx, UIComponent uiComponent, String seasonId) {
		logger.info("Get object: " + seasonId);
		long id;
		try {
			id = Long.valueOf(seasonId);
		} catch (Exception e) {
			return null;
		}

		ValueExpression vex = ctx.getApplication().getExpressionFactory().createValueExpression(ctx.getELContext(),
				"#{competitionController}", CompetitionController.class);

		CompetitionController competitionController = (CompetitionController) vex.getValue(ctx.getELContext());

		for (SeasonEntity season : competitionController.getSelectedCompetition().getSeasons()) {
			if (id == season.getId()) {
				return season;
			}
		}

		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, SeasonEntity value) {
		logger.info("Get String: " + value);
		if (value == null) {
			return null;
		}
		SeasonEntity season = (SeasonEntity) value;

		return String.valueOf(season.getId());

	}

}

package ua.com.footballgamble.contloller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import ua.com.footballgamble.model.EventType;
import ua.com.footballgamble.model.entity.GambleEntity;
import ua.com.footballgamble.model.entity.GambleUserScore;
import ua.com.footballgamble.primefaces.FacesContextUtils;
import ua.com.footballgamble.primefaces.PrimeFacesMessageUtils;
import ua.com.footballgamble.service.GambleServiceImpl;

@Named(value = "gamblesController")
@ViewScoped
public class GamblesController implements Serializable {
	private static final long serialVersionUID = -1;
	public static final Logger logger = LoggerFactory.getLogger(GamblesController.class);
	@Autowired
	private GambleServiceImpl gambleService;

	private List<GambleUserScore> scoreTable;
	private List<GambleUserScore> filteredScoreTable;

	private List<GambleEntity> gambles;
	private List<GambleEntity> filteredGambles;
	private GambleEntity selectedGamble;

	@PostConstruct
	public void loadData() {
		logger.info("PostConstruct loadData");
		gambles = gambleService.findAll();
	}

	public String onAddNewGamble() {
		logger.info("On Add new Gamble");
		GambleEntity gambleEntity = new GambleEntity();
		gambleEntity.setId(getNextId());

		FacesContextUtils.putSessionMapObject("selectedGamble", gambleEntity);
		FacesContextUtils.putSessionMapObject("eventType", EventType.ADD);

		return "gamble.html?faces-redirect=true";
	}

	public Long getNextId() {
		Long maxId = 0l;

		for (GambleEntity gamble : gambles) {
			if (gamble.getId() > maxId) {
				maxId = gamble.getId();
			}
		}
		return ++maxId;
	}

	public String onView(GambleEntity gamble) {
		logger.info("Get Gamble for View: " + gamble);
		FacesContextUtils.putSessionMapObject("selectedGamble", gamble);
		FacesContextUtils.putSessionMapObject("eventType", EventType.VIEW);
		return "gamble.html?faces-redirect=true";
	}

	public String onEditGamble(GambleEntity gamble) {
		logger.info("Get user for Gamble: " + gamble);

		FacesContextUtils.putSessionMapObject("selectedGamble", gamble);
		FacesContextUtils.putSessionMapObject("eventType", EventType.EDIT);
		return "gamble.html?faces-redirect=true";
	}

	public void onDeleteGamble(GambleEntity gamble) {
		logger.info("onDeleteGamble: " + gamble);

		try {
			gambleService.deleteById(gamble.getId());
			gambles.remove(gamble);
			PrimeFacesMessageUtils.addGlobalInfoMessage("Gamble '" + gamble.getName() + "' was deleted");
		} catch (Exception ex) {
			logger.error("Error on DELETE for " + gamble + ": " + ex.getMessage());
			PrimeFacesMessageUtils
					.addGlobalErrorMessage("Error on DELETE for id" + gamble.getId() + ": " + ex.getMessage());
		}
	}

	public void onViewScoreTable(GambleEntity gamble) {
		logger.info("Get Score Table for Gable: " + gamble);
		scoreTable = gambleService.getGambleUsersScores(gamble.getId());
	}

	// Getters-Setters

	public List<GambleEntity> getGambles() {
		return gambles;
	}

	public void setGambles(List<GambleEntity> gambles) {
		this.gambles = gambles;
	}

	public List<GambleEntity> getFilteredGambles() {
		return filteredGambles;
	}

	public void setFilteredGambles(List<GambleEntity> filteredGambles) {
		this.filteredGambles = filteredGambles;
	}

	public GambleEntity getSelectedGamble() {
		return selectedGamble;
	}

	public void setSelectedGamble(GambleEntity selectedGamble) {
		this.selectedGamble = selectedGamble;
	}

	public List<GambleUserScore> getScoreTable() {
		return scoreTable;
	}

	public void setScoreTable(List<GambleUserScore> scoreTable) {
		this.scoreTable = scoreTable;
	}

	public List<GambleUserScore> getFilteredScoreTable() {
		return filteredScoreTable;
	}

	public void setFilteredScoreTable(List<GambleUserScore> filteredScoreTable) {
		this.filteredScoreTable = filteredScoreTable;
	}

}

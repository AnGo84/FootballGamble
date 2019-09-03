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
		FacesContextUtils.putSessionMapObject("selectedGamble", new GambleEntity());
		FacesContextUtils.putSessionMapObject("eventType", EventType.ADD);

		return "gamble.html?faces-redirect=true";
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

}

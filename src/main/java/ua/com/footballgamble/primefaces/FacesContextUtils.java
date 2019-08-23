package ua.com.footballgamble.primefaces;

import java.util.Map;

import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FacesContextUtils {
	private static final Logger log = LoggerFactory.getLogger(FacesContextUtils.class);

	public static void putRequestMapObject(String keyName, Object object) {
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put(keyName, object);
	}

	public static Object getRequestMapObject(String keyName) {

		return FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get(keyName);
	}

	public static void putSessionMapObject(String keyName, Object object) {
		log.info("put Key: " + keyName + " obj: " + object);
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(keyName, object);
		/*
		 * log.info("Key: " + keyName); log.info("Object: " +
		 * FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(
		 * keyName));
		 */
	}

	public static Object getSessionMapObject(String keyName) {
		/*
		 * log.info("get Key: " + keyName); log.info("Object: " +
		 * FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(
		 * keyName));
		 */
		return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(keyName);
	}

	public static void clearMaps() {
		logExternalContextMaps();

		/*
		 * FacesContext.getCurrentInstance().getExternalContext().getRequestMap().clear(
		 * );
		 * FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear(
		 * );
		 */
	}

	private static void logExternalContextMaps() {
		log.info("RequestMap: " + FacesContext.getCurrentInstance().getExternalContext().getRequestMap().size());
		Map<String, Object> maps = FacesContext.getCurrentInstance().getExternalContext().getRequestMap();
		maps.forEach((k, v) -> log.info("Key : " + k + " Value : " + v));
		log.info("SessionMap: " + FacesContext.getCurrentInstance().getExternalContext().getSessionMap().size());

		maps = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		maps.forEach((k, v) -> log.info("Key : " + k + " Value : " + v));
	}

	public static void clearMaps(String[] args) {
		// logExternalContextMaps();
		for (String string : args) {
			FacesContext.getCurrentInstance().getExternalContext().getRequestMap().remove(string);
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(string);
		}

	}
}

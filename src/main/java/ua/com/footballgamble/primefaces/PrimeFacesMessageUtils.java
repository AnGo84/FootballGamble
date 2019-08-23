package ua.com.footballgamble.primefaces;

import java.util.Iterator;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PrimeFacesMessageUtils {
	private static final Logger log = LoggerFactory.getLogger(PrimeFacesMessageUtils.class);

	public static void clearOldGlobalMessages() {
		log.info("Clear ALL old messages");
		Iterator<FacesMessage> facesMessags = FacesContext.getCurrentInstance().getMessages();
		while (facesMessags.hasNext()) {
			facesMessags.next();
			facesMessags.remove();
		}
	}

	public static void addGlobalInfoMessage(String message) {
		log.info("Show Info mes: " + message);
		addGlobalMessage(FacesMessage.SEVERITY_INFO, "Info", message);
		/*
		 * FacesContext context = FacesContext.getCurrentInstance();
		 * context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
		 * message));
		 */
	}

	public static void addGlobalErrorMessage(String message) {
		log.info("Show Error mes: " + message);
		addGlobalMessage(FacesMessage.SEVERITY_ERROR, "Error", message);
	}

	public static void addGlobalWarnMessage(String message) {
		log.info("Show Warn mes: " + message);
		addGlobalMessage(FacesMessage.SEVERITY_WARN, "Warrning", message);

	}

	public static void addGlobalMessage(Severity severity, String title, String message) {
		log.info("Show severity" + severity + " '" + title + "': " + message);
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(severity, title, message));
	}

	/*
	 * public static void showErrorsMessage(ProcessingParameters parameters) { if
	 * (!ResultType.OK.equals(ResultType.valueOf(parameters.getResult()))) { String
	 * errorMsg = ""; for (Parameter element :
	 * parameters.getErrorParams().getParameters()) { errorMsg += element.getValue()
	 * + "; "; } if (errorMsg.equals("")) { errorMsg = parameters.getResult(); }
	 * addGlobalErrorMessage(errorMsg); } }
	 */
}

package ua.com.footballgamble.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.com.footballgamble.contloller.CompetitionController;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.StringWriter;

@Controller
public class AppErrorController implements ErrorController {
	//public static final Logger logger = LoggerFactory.getLogger(AppErrorController.class);
	@RequestMapping("/error")
	@ResponseBody
	public String handleError(HttpServletRequest request) {

		Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
		Exception exception = (Exception) request.getAttribute("javax.servlet.error.exception");
		//logger.error("Exception message: " + exception.getMessage(),exception);
		if( exception!=null){
			exception.printStackTrace();
		}
		String exceptionStackTrace = getExceptionStackTrace(exception);

		return String.format("<html><body><h2>Error Page</h2><div>Status code: <b>%s</b></div>"
						+ "<div>Exception Message: <b>%s</b></div>"
						+ "<div>Trace: <b>%s</b></div>"
						+"<body></html>",
				statusCode, exception==null? "N/A": exception.getMessage(), exceptionStackTrace);
	}

	private String getExceptionStackTrace(Exception exception) {
		if (exception==null){
			return "";
		}
		StringWriter sw = new StringWriter();
		exception.printStackTrace(new PrintWriter(sw));
		String exceptionAsString = sw.toString();
		return exceptionAsString;
	}

	@Override
	public String getErrorPath() {
		return "/error";
	}
}

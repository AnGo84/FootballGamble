package ua.com.footballgamble;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;

//@SpringBootApplication(scanBasePackages = { "ua.com.footballgamble" })
@SpringBootApplication
public class FootballGambleApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(FootballGambleApplication.class, args);
	}

	/*@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(FootballGambleApplication.class);
	}*/


	/*@Override
	protected WebApplicationContext run(SpringApplication application) {
		return super.run(application);
	}*/

}

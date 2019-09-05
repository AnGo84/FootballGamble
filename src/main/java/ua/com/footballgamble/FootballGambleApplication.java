package ua.com.footballgamble;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

//@SpringBootApplication(scanBasePackages = { "ua.com.footballgamble" })
@SpringBootApplication
public class FootballGambleApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(FootballGambleApplication.class, args);
	}

	/*
	 * @Override protected SpringApplicationBuilder
	 * configure(SpringApplicationBuilder application) { return
	 * application.sources(FootballGambleApplication.class); }
	 */

	/*
	 * @Override protected WebApplicationContext run(SpringApplication application)
	 * { return super.run(application); }
	 */

}

package ua.com.footballgamble.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import ua.com.footballgamble.service.AppUserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private AppUserDetailsServiceImpl appUserDetailsService;
	@Autowired
	private AppAccessDeniedHandler accessDeniedHandler;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		try {
			// not needed as JSF 2.2 is implicitly protected against CSRF
			/*
			 * http.csrf().disable();
			 * http.userDetailsService(userDetailsService()).authorizeRequests().antMatchers
			 * ("/").permitAll() //
			 * .antMatchers("/**.jsf").permitAll().antMatchers("/javax.faces.resource/**").
			 * permitAll().anyRequest()
			 * .antMatchers("/**.jsf").permitAll().antMatchers("/**.xhtml").permitAll()
			 * .antMatchers("/javax.faces.resource/**").permitAll().anyRequest() //
			 * .authenticated().and().formLogin().loginPage("/login.jsf").permitAll()
			 * .authenticated().and().formLogin().loginPage("/login.xhtml").permitAll() //
			 * .failureUrl("/login.jsf?error=true") .failureUrl("/login.xhtml?error=true")
			 * // .defaultSuccessUrl("/test_product_list_form.jsf") //
			 * .defaultSuccessUrl("/index.jsf").and().logout().logoutUrl("/logout").
			 * logoutSuccessUrl("/login.jsf")
			 * .defaultSuccessUrl("/index.xhtml").and().logout().logoutUrl("/logout")
			 * .logoutSuccessUrl("/login.xhtml").deleteCookies("JSESSIONID");
			 */

			http.authorizeRequests().antMatchers("/login*").permitAll().antMatchers("/logout*").permitAll()
					.antMatchers("/js/**").permitAll().antMatchers("/css/**").permitAll()
					.antMatchers("/javax.faces.resource/**").permitAll().anyRequest().authenticated().and().formLogin()
					.loginPage("/login.xhtml").defaultSuccessUrl("/index.xhtml").failureUrl("/login.xhtml?error=1")
					.and().csrf().disable().logout().logoutSuccessUrl("/login.xhtml?logout").and().exceptionHandling()
					.accessDeniedHandler(accessDeniedHandler);

		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}

		// require all requests to be authenticated except for the resources

		// The pages does not require login
		/*
		 * http.authorizeRequests() .antMatchers("/", "/main", "/login", "/logout",
		 * "/forgotPassword", "/resetPassword", "/catalog**") .permitAll();
		 */

		// http.authorizeRequests().antMatchers("/javax.faces.resource/**").permitAll().anyRequest().authenticated();
		// http.authorizeRequests().antMatchers("/pages/**").permitAll().anyRequest().authenticated();
		// login
		/*
		 * http.formLogin().loginPage("/login.xhtml").permitAll()
		 * .usernameParameter("username") .passwordParameter("password")
		 * .failureUrl("/login.xhtml?error=true") .defaultSuccessUrl("/index.xhtml");
		 */
		/* "/pages/test_products.xhtml" */
		// logout
		// http.logout().logoutSuccessUrl("/login.xhtml");

	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		/*
		 * auth.inMemoryAuthentication().withUser("user").password("{noop}1234").roles(
		 * "USER").and().withUser("admin") .password("{noop}5678").roles("ADMIN");
		 */
		auth.userDetailsService(appUserDetailsService).passwordEncoder(passwordEncoder());
	}

}

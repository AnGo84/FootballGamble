package ua.com.footballgamble.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import ua.com.footballgamble.contloller.RestTemplateResponseErrorHandler;
import ua.com.footballgamble.model.user.User;
import ua.com.footballgamble.model.user.UserRole;

@Service
//@PropertySource(ignoreResourceNotFound = true, value = "classpath:footballgamble.properties")
public class AppUserDetailsServiceImpl implements UserDetailsService {
	public static final Logger logger = LoggerFactory.getLogger(AppUserDetailsServiceImpl.class);
	@Value("${football.api.path}")
	private String apiPath;

	@Autowired
	private HttpHeadersImpl httpHeaders;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		logger.info("Get loadUserByUserName: " + userName);
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());

		// HttpEntity<String> request = new
		// HttpEntity<String>(httpHeaders.getHeaders("admin", "123"));

		ResponseEntity<User> respEntity = restTemplate.exchange(apiPath + "/users/login/" + userName, HttpMethod.GET,
				httpHeaders.getHttpAuthEntity(), User.class);
		logger.info("Get respEntity is null: " + respEntity);
		User appUser = respEntity.getBody();
		logger.info("Get User is: " + appUser);
		if (appUser == null) {
			String errorTXT = "AppUser " + userName + " was not found in the database";
			logger.error(errorTXT);
			throw new UsernameNotFoundException(errorTXT);
		}

		// [ROLE_ADMIN, ROLE_ACCOUNTANT, ROLE_MANAGER,..]

		List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
		if (appUser.getRoles() != null) {
			for (UserRole role : appUser.getRoles()) {
				// ROLE_ADMIN, ROLE_ACCOUNTANT, ROLE_MANAGER..
				GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role.getName());
				grantList.add(authority);
			}
		}

		UserDetails userDetails = (UserDetails) new org.springframework.security.core.userdetails.User(
				appUser.getLogin(), appUser.getPassword(), appUser.isActive(), true, true, appUser.isActive(),
				grantList);

		return userDetails;
	}
}

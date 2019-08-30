package ua.com.footballgamble.converter.faces;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import ua.com.footballgamble.model.entity.GambleCompetition;
import ua.com.footballgamble.model.entity.GambleRuleEntity;
import ua.com.footballgamble.model.entity.GambleUser;

@Component
@SessionScope
public class AppEntitiesLists {
	private List<GambleRuleEntity> gambleRules;
	private List<GambleCompetition> gambleCompetitions;
	private List<GambleUser> gambleUsers;

	//
	public GambleRuleEntity getGambleRuleEntityById(Long gambleRuleEntityId) {
		for (GambleRuleEntity gambleRuleEntity : gambleRules) {
			if (gambleRuleEntity.getId() == gambleRuleEntityId) {
				return gambleRuleEntity;
			}
		}
		return null;
	}

	public GambleRuleEntity getGambleRuleEntityByFullName(String fullName) {
		if (StringUtils.isBlank(fullName)) {
			return null;
		}
		for (GambleRuleEntity gambleRuleEntity : gambleRules) {
			if (gambleRuleEntity.getFullName().equals(fullName)) {
				return gambleRuleEntity;
			}
		}
		return null;
	}

	public GambleUser getGambleUserEntityByLogin(String login) {
		if (StringUtils.isBlank(login)) {
			return null;
		}
		for (GambleUser user : gambleUsers) {
			if (user.getLogin().equals(login)) {
				return user;
			}
		}
		return null;
	}

	public GambleCompetition getGambleCompetitionByName(String name) {
		if (StringUtils.isBlank(name)) {
			return null;
		}
		for (GambleCompetition competition : gambleCompetitions) {
			if (competition.getName().equals(name)) {
				return competition;
			}
		}
		return null;
	}

	// Getters & Setters
	public List<GambleRuleEntity> getGambleRules() {
		return gambleRules;
	}

	public void setGambleRules(List<GambleRuleEntity> gambleRules) {
		this.gambleRules = gambleRules;
	}

	public List<GambleUser> getGambleUsers() {
		System.out.println("GambleUsers: " + gambleUsers);
		return gambleUsers;
	}

	public void setGambleUsers(List<GambleUser> gambleUsers) {
		this.gambleUsers = gambleUsers;
	}

	public List<GambleCompetition> getGambleCompetitions() {
		System.out.println("GambleCompetitions: " + gambleCompetitions);
		return gambleCompetitions;
	}

	public void setGambleCompetitions(List<GambleCompetition> gambleCompetitions) {
		this.gambleCompetitions = gambleCompetitions;
	}

}

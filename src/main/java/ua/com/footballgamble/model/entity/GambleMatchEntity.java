package ua.com.footballgamble.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

public class GambleMatchEntity {
	private String id;
	private Long gambleId;
	private Long competitionId;
	private String competitionName;
	private Long seasonId;
	private Long matchId;
	private MatchEntity match;
	private String stage;
	private Long userId;
	private GambleUser user;
	private GambleRuleEntity rule;
	private Integer scoreFullTimeHomeTeam;
	private Integer scoreFullTimeAwayTeam;
	private Integer scoreExtraTimeHomeTeam;
	private Integer scoreExtraTimeAwayTeam;
	private Integer scorePenaltiesHomeTeam;
	private Integer scorePenaltiesAwayTeam;
	private Integer total;

	private boolean edited;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getGambleId() {
		return gambleId;
	}

	public void setGambleId(Long gambleId) {
		this.gambleId = gambleId;
	}

	public Long getCompetitionId() {
		return competitionId;
	}

	public void setCompetitionId(Long competitionId) {
		this.competitionId = competitionId;
	}

	public String getCompetitionName() {
		return competitionName;
	}

	public void setCompetitionName(String competitionName) {
		this.competitionName = competitionName;
	}

	public Long getSeasonId() {
		return seasonId;
	}

	public void setSeasonId(Long seasonId) {
		this.seasonId = seasonId;
	}

	public Long getMatchId() {
		return matchId;
	}

	public void setMatchId(Long matchId) {
		this.matchId = matchId;
	}

	public MatchEntity getMatch() {
		return match;
	}

	public void setMatch(MatchEntity match) {
		this.match = match;
	}

	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public GambleUser getUser() {
		return user;
	}

	public void setUser(GambleUser user) {
		this.user = user;
	}

	public GambleRuleEntity getRule() {
		return rule;
	}

	public void setRule(GambleRuleEntity rule) {
		this.rule = rule;
	}

	public Integer getScoreFullTimeHomeTeam() {
		return scoreFullTimeHomeTeam;
	}

	public void setScoreFullTimeHomeTeam(Integer scoreFullTimeHomeTeam) {
		this.scoreFullTimeHomeTeam = scoreFullTimeHomeTeam;
	}

	public Integer getScoreFullTimeAwayTeam() {
		return scoreFullTimeAwayTeam;
	}

	public void setScoreFullTimeAwayTeam(Integer scoreFullTimeAwayTeam) {
		this.scoreFullTimeAwayTeam = scoreFullTimeAwayTeam;
	}

	public Integer getScoreExtraTimeHomeTeam() {
		return scoreExtraTimeHomeTeam;
	}

	public void setScoreExtraTimeHomeTeam(Integer scoreExtraTimeHomeTeam) {
		this.scoreExtraTimeHomeTeam = scoreExtraTimeHomeTeam;
	}

	public Integer getScoreExtraTimeAwayTeam() {
		return scoreExtraTimeAwayTeam;
	}

	public void setScoreExtraTimeAwayTeam(Integer scoreExtraTimeAwayTeam) {
		this.scoreExtraTimeAwayTeam = scoreExtraTimeAwayTeam;
	}

	public Integer getScorePenaltiesHomeTeam() {
		return scorePenaltiesHomeTeam;
	}

	public void setScorePenaltiesHomeTeam(Integer scorePenaltiesHomeTeam) {
		this.scorePenaltiesHomeTeam = scorePenaltiesHomeTeam;
	}

	public Integer getScorePenaltiesAwayTeam() {
		return scorePenaltiesAwayTeam;
	}

	public void setScorePenaltiesAwayTeam(Integer scorePenaltiesAwayTeam) {
		this.scorePenaltiesAwayTeam = scorePenaltiesAwayTeam;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public boolean isEdited() {
		return edited;
	}

	public void setEdited(boolean edited) {
		this.edited = edited;
	}

	//
	@JsonIgnore
	public boolean isScheduled() {
		boolean result = false;
		if (match.getUtcDateAsDate().compareTo(new Date()) > 0) {
			result = true;
		}
		return result;
	}

	@JsonIgnore
	public Date getMatchDate() {
		if (match == null) {
			return null;
		}

		return match.getUtcDateAsDate();
	}

	@JsonIgnore
	public String getShortCompetitionName() {
		if (StringUtils.isBlank(competitionName)) {
			return "";
		}

		return (competitionName.length() < 20 ? competitionName : competitionName.substring(0, 21));
	}

	@JsonIgnore
	public String getUserLogin() {
		if (user == null || StringUtils.isBlank(user.getLogin())) {
			return "";
		}

		return (user.getLogin());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((competitionId == null) ? 0 : competitionId.hashCode());
		result = prime * result + ((competitionName == null) ? 0 : competitionName.hashCode());
		result = prime * result + (edited ? 1231 : 1237);
		result = prime * result + ((gambleId == null) ? 0 : gambleId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((match == null) ? 0 : match.hashCode());
		result = prime * result + ((matchId == null) ? 0 : matchId.hashCode());
		result = prime * result + ((rule == null) ? 0 : rule.hashCode());
		result = prime * result + ((scoreExtraTimeAwayTeam == null) ? 0 : scoreExtraTimeAwayTeam.hashCode());
		result = prime * result + ((scoreExtraTimeHomeTeam == null) ? 0 : scoreExtraTimeHomeTeam.hashCode());
		result = prime * result + ((scoreFullTimeAwayTeam == null) ? 0 : scoreFullTimeAwayTeam.hashCode());
		result = prime * result + ((scoreFullTimeHomeTeam == null) ? 0 : scoreFullTimeHomeTeam.hashCode());
		result = prime * result + ((scorePenaltiesAwayTeam == null) ? 0 : scorePenaltiesAwayTeam.hashCode());
		result = prime * result + ((scorePenaltiesHomeTeam == null) ? 0 : scorePenaltiesHomeTeam.hashCode());
		result = prime * result + ((seasonId == null) ? 0 : seasonId.hashCode());
		result = prime * result + ((stage == null) ? 0 : stage.hashCode());
		result = prime * result + ((total == null) ? 0 : total.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GambleMatchEntity other = (GambleMatchEntity) obj;
		if (competitionId == null) {
			if (other.competitionId != null)
				return false;
		} else if (!competitionId.equals(other.competitionId))
			return false;
		if (competitionName == null) {
			if (other.competitionName != null)
				return false;
		} else if (!competitionName.equals(other.competitionName))
			return false;
		if (edited != other.edited)
			return false;
		if (gambleId == null) {
			if (other.gambleId != null)
				return false;
		} else if (!gambleId.equals(other.gambleId))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (match == null) {
			if (other.match != null)
				return false;
		} else if (!match.equals(other.match))
			return false;
		if (matchId == null) {
			if (other.matchId != null)
				return false;
		} else if (!matchId.equals(other.matchId))
			return false;
		if (rule == null) {
			if (other.rule != null)
				return false;
		} else if (!rule.equals(other.rule))
			return false;
		if (scoreExtraTimeAwayTeam == null) {
			if (other.scoreExtraTimeAwayTeam != null)
				return false;
		} else if (!scoreExtraTimeAwayTeam.equals(other.scoreExtraTimeAwayTeam))
			return false;
		if (scoreExtraTimeHomeTeam == null) {
			if (other.scoreExtraTimeHomeTeam != null)
				return false;
		} else if (!scoreExtraTimeHomeTeam.equals(other.scoreExtraTimeHomeTeam))
			return false;
		if (scoreFullTimeAwayTeam == null) {
			if (other.scoreFullTimeAwayTeam != null)
				return false;
		} else if (!scoreFullTimeAwayTeam.equals(other.scoreFullTimeAwayTeam))
			return false;
		if (scoreFullTimeHomeTeam == null) {
			if (other.scoreFullTimeHomeTeam != null)
				return false;
		} else if (!scoreFullTimeHomeTeam.equals(other.scoreFullTimeHomeTeam))
			return false;
		if (scorePenaltiesAwayTeam == null) {
			if (other.scorePenaltiesAwayTeam != null)
				return false;
		} else if (!scorePenaltiesAwayTeam.equals(other.scorePenaltiesAwayTeam))
			return false;
		if (scorePenaltiesHomeTeam == null) {
			if (other.scorePenaltiesHomeTeam != null)
				return false;
		} else if (!scorePenaltiesHomeTeam.equals(other.scorePenaltiesHomeTeam))
			return false;
		if (seasonId == null) {
			if (other.seasonId != null)
				return false;
		} else if (!seasonId.equals(other.seasonId))
			return false;
		if (stage == null) {
			if (other.stage != null)
				return false;
		} else if (!stage.equals(other.stage))
			return false;
		if (total == null) {
			if (other.total != null)
				return false;
		} else if (!total.equals(other.total))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GambleMatchEntity [id=");
		builder.append(id);
		builder.append(", gambleId=");
		builder.append(gambleId);
		builder.append(", competitionId=");
		builder.append(competitionId);
		builder.append(", competitionName=");
		builder.append(competitionName);
		builder.append(", seasonId=");
		builder.append(seasonId);
		builder.append(", matchId=");
		builder.append(matchId);
		builder.append(", match=");
		builder.append(match);
		builder.append(", stage=");
		builder.append(stage);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", user=");
		builder.append(user);
		builder.append(", rule=");
		builder.append(rule);
		builder.append(", scoreFullTimeHomeTeam=");
		builder.append(scoreFullTimeHomeTeam);
		builder.append(", scoreFullTimeAwayTeam=");
		builder.append(scoreFullTimeAwayTeam);
		builder.append(", scoreExtraTimeHomeTeam=");
		builder.append(scoreExtraTimeHomeTeam);
		builder.append(", scoreExtraTimeAwayTeam=");
		builder.append(scoreExtraTimeAwayTeam);
		builder.append(", scorePenaltiesHomeTeam=");
		builder.append(scorePenaltiesHomeTeam);
		builder.append(", scorePenaltiesAwayTeam=");
		builder.append(scorePenaltiesAwayTeam);
		builder.append(", total=");
		builder.append(total);
		builder.append(", edited=");
		builder.append(edited);
		builder.append("]");
		return builder.toString();
	}

}

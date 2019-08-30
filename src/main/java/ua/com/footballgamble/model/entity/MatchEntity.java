package ua.com.footballgamble.model.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonRootName;

import ua.com.footballgamble.utils.DateTimeUtils;

@JsonRootName("match")
public class MatchEntity {

	private long id;
	private SeasonEntity season;
	private String utcDate;
	private String status;
	private String matchday;
	private int stageId;
	private String stage;
	private String group;
	private TeamEntity homeTeam;
	private TeamEntity awayTeam;
	private String winner;
	private String duration;
	private Integer scoreFullTimeHomeTeam;

	private Integer scoreFullTimeAwayTeam;

	private Integer scoreExtraTimeHomeTeam;

	private Integer scoreExtraTimeAwayTeam;

	private Integer scorePenaltiesHomeTeam;

	private Integer scorePenaltiesAwayTeam;

	private String lastUpdated;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public SeasonEntity getSeason() {
		return season;
	}

	public void setSeason(SeasonEntity season) {
		this.season = season;
	}

	public String getUtcDate() {
		return utcDate;
	}

	public void setUtcDate(String utcDate) {
		this.utcDate = utcDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMatchday() {
		return matchday;
	}

	public void setMatchday(String matchday) {
		this.matchday = matchday;
	}

	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public TeamEntity getHomeTeam() {
		return homeTeam;
	}

	public void setHomeTeam(TeamEntity homeTeam) {
		this.homeTeam = homeTeam;
	}

	public TeamEntity getAwayTeam() {
		return awayTeam;
	}

	public void setAwayTeam(TeamEntity awayTeam) {
		this.awayTeam = awayTeam;
	}

	public String getWinner() {
		return winner;
	}

	public void setWinner(String winner) {
		this.winner = winner;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
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

	public String getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(String lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public int getStageId() {
		return stageId;
	}

	public void setStageId(int stageId) {
		this.stageId = stageId;
	}

	/**/
	public Date getUtcDateAsDate() {
		return DateTimeUtils.getDateFromString(utcDate);
	}

	public String getDisplayUtcDate() {
		return DateTimeUtils.getDateForDisplay(utcDate);
	}

	public String getScoreTotalHomeTeam() {
		String total = "";
		if (scoreFullTimeHomeTeam != null) {
			total = String.valueOf(scoreFullTimeHomeTeam);
		}
		if (scoreExtraTimeHomeTeam != null) {
			total = total + " ,ext: " + String.valueOf(scoreExtraTimeHomeTeam);
		}
		if (scorePenaltiesHomeTeam != null) {
			total = total + " ,pen: " + String.valueOf(scorePenaltiesHomeTeam);
		}
		return total;
	}

	public String getScoreTotalAwayTeam() {
		String total = "";
		if (scoreFullTimeAwayTeam != null) {
			total = String.valueOf(scoreFullTimeAwayTeam);
		}
		if (scoreExtraTimeAwayTeam != null) {
			total = total + " ,ext: " + String.valueOf(scoreExtraTimeAwayTeam);
		}
		if (scorePenaltiesAwayTeam != null) {
			total = total + " ,pen: " + String.valueOf(scorePenaltiesAwayTeam);
		}
		return total;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((awayTeam == null) ? 0 : awayTeam.hashCode());
		result = prime * result + ((duration == null) ? 0 : duration.hashCode());
		result = prime * result + ((group == null) ? 0 : group.hashCode());
		result = prime * result + ((homeTeam == null) ? 0 : homeTeam.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((lastUpdated == null) ? 0 : lastUpdated.hashCode());
		result = prime * result + ((matchday == null) ? 0 : matchday.hashCode());
		result = prime * result + ((scoreExtraTimeAwayTeam == null) ? 0 : scoreExtraTimeAwayTeam.hashCode());
		result = prime * result + ((scoreExtraTimeHomeTeam == null) ? 0 : scoreExtraTimeHomeTeam.hashCode());
		result = prime * result + ((scoreFullTimeAwayTeam == null) ? 0 : scoreFullTimeAwayTeam.hashCode());
		result = prime * result + ((scoreFullTimeHomeTeam == null) ? 0 : scoreFullTimeHomeTeam.hashCode());
		result = prime * result + ((scorePenaltiesAwayTeam == null) ? 0 : scorePenaltiesAwayTeam.hashCode());
		result = prime * result + ((scorePenaltiesHomeTeam == null) ? 0 : scorePenaltiesHomeTeam.hashCode());
		result = prime * result + ((season == null) ? 0 : season.hashCode());
		result = prime * result + ((stage == null) ? 0 : stage.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((utcDate == null) ? 0 : utcDate.hashCode());
		result = prime * result + ((winner == null) ? 0 : winner.hashCode());
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
		MatchEntity other = (MatchEntity) obj;
		if (awayTeam == null) {
			if (other.awayTeam != null)
				return false;
		} else if (!awayTeam.equals(other.awayTeam))
			return false;
		if (duration == null) {
			if (other.duration != null)
				return false;
		} else if (!duration.equals(other.duration))
			return false;
		if (group == null) {
			if (other.group != null)
				return false;
		} else if (!group.equals(other.group))
			return false;
		if (homeTeam == null) {
			if (other.homeTeam != null)
				return false;
		} else if (!homeTeam.equals(other.homeTeam))
			return false;
		if (id != other.id)
			return false;
		if (lastUpdated == null) {
			if (other.lastUpdated != null)
				return false;
		} else if (!lastUpdated.equals(other.lastUpdated))
			return false;
		if (matchday == null) {
			if (other.matchday != null)
				return false;
		} else if (!matchday.equals(other.matchday))
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
		if (season == null) {
			if (other.season != null)
				return false;
		} else if (!season.equals(other.season))
			return false;
		if (stage == null) {
			if (other.stage != null)
				return false;
		} else if (!stage.equals(other.stage))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (utcDate == null) {
			if (other.utcDate != null)
				return false;
		} else if (!utcDate.equals(other.utcDate))
			return false;
		if (winner == null) {
			if (other.winner != null)
				return false;
		} else if (!winner.equals(other.winner))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MatchEntity [id=");
		builder.append(id);
		builder.append(", season=");
		builder.append(season);
		builder.append(", utcDate=");
		builder.append(utcDate);
		builder.append(", status=");
		builder.append(status);
		builder.append(", matchday=");
		builder.append(matchday);
		builder.append(", stageId=");
		builder.append(stageId);
		builder.append(", stage=");
		builder.append(stage);
		builder.append(", group=");
		builder.append(group);
		builder.append(", homeTeam=");
		builder.append(homeTeam);
		builder.append(", awayTeam=");
		builder.append(awayTeam);
		builder.append(", winner=");
		builder.append(winner);
		builder.append(", duration=");
		builder.append(duration);
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
		builder.append(", lastUpdated=");
		builder.append(lastUpdated);
		builder.append("]");
		return builder.toString();
	}

}

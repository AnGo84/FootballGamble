
package ua.com.footballgamble.model.entity;

import java.util.List;

import ua.com.footballgamble.utils.SeasonUtils;

//@JsonRootName("season")
public class SeasonEntity {

	private long id;
	private String startDate;
	private String endDate;
	private Integer currentMatchday;
	private TeamEntity winner;
	private List<MatchEntity> matches = null;
	private List<SeasonStage> stages = null;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Integer getCurrentMatchday() {
		return currentMatchday;
	}

	public void setCurrentMatchday(Integer currentMatchday) {
		this.currentMatchday = currentMatchday;
	}

	public TeamEntity getWinner() {
		return winner;
	}

	public void setWinner(TeamEntity winner) {
		this.winner = winner;
	}

	public List<MatchEntity> getMatches() {
		return matches;
	}

	public void setMatches(List<MatchEntity> matches) {
		this.matches = matches;
	}

	public List<SeasonStage> getStages() {
		return stages;
	}

	public void setStages(List<SeasonStage> stages) {
		this.stages = stages;
	}
	/* ** */

	public String getSeasonName() {
		return SeasonUtils.getYearFromDate(startDate) + " / " + SeasonUtils.getYearFromDate(endDate);
	}

	public String getSeasonYear() {
		return SeasonUtils.getYearFromDate(startDate);
	}

	public int getSeasonYearInt() {
		return SeasonUtils.getIntYearFromDate(startDate);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof SeasonEntity)) return false;

		SeasonEntity that = (SeasonEntity) o;

		if (id != that.id) return false;
		if (startDate != null ? !startDate.equals(that.startDate) : that.startDate != null) return false;
		if (endDate != null ? !endDate.equals(that.endDate) : that.endDate != null) return false;
		if (currentMatchday != null ? !currentMatchday.equals(that.currentMatchday) : that.currentMatchday != null)
			return false;
		if (winner != null ? !winner.equals(that.winner) : that.winner != null) return false;
		if (matches != null ? !matches.equals(that.matches) : that.matches != null) return false;
		return stages != null ? stages.equals(that.stages) : that.stages == null;
	}

	@Override
	public int hashCode() {
		int result = (int) (id ^ (id >>> 32));
		result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
		result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
		result = 31 * result + (currentMatchday != null ? currentMatchday.hashCode() : 0);
		result = 31 * result + (winner != null ? winner.hashCode() : 0);
		result = 31 * result + (matches != null ? matches.hashCode() : 0);
		result = 31 * result + (stages != null ? stages.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("SeasonEntity{");
		sb.append("id=").append(id);
		sb.append(", startDate='").append(startDate).append('\'');
		sb.append(", endDate='").append(endDate).append('\'');
		sb.append(", currentMatchday=").append(currentMatchday);
		sb.append(", winner=").append(winner);
		sb.append(", matches=").append(matches);
		sb.append(", stages=").append(stages);
		sb.append('}');
		return sb.toString();
	}
}

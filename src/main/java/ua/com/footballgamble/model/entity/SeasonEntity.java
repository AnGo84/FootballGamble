
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((currentMatchday == null) ? 0 : currentMatchday.hashCode());
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
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
		SeasonEntity other = (SeasonEntity) obj;
		if (currentMatchday == null) {
			if (other.currentMatchday != null)
				return false;
		} else if (!currentMatchday.equals(other.currentMatchday))
			return false;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (id != other.id)
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
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
		builder.append("SeasonEntity [id=");
		builder.append(id);
		builder.append(", startDate=");
		builder.append(startDate);
		builder.append(", endDate=");
		builder.append(endDate);
		builder.append(", currentMatchday=");
		builder.append(currentMatchday);
		builder.append(", winner=");
		builder.append(winner);
		builder.append(", matches=");
		builder.append(matches);
		builder.append("]");
		return builder.toString();
	}

}

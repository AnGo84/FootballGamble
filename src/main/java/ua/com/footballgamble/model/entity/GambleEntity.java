package ua.com.footballgamble.model.entity;

import java.util.List;

public class GambleEntity {
	private Long id;
	private String name;
	private String description;
	private boolean active;
	private List<GambleCompetition> competitons;
	private List<GambleUser> participants;
	private GambleRuleEntity rule;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public List<GambleCompetition> getCompetitons() {
		return competitons;
	}

	public void setCompetitons(List<GambleCompetition> competitons) {
		this.competitons = competitons;
	}

	public List<GambleUser> getParticipants() {
		return participants;
	}

	public void setParticipants(List<GambleUser> participants) {
		this.participants = participants;
	}

	public GambleRuleEntity getRule() {
		return rule;
	}

	public void setRule(GambleRuleEntity rule) {
		this.rule = rule;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Gamble [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", description=");
		builder.append(description);
		builder.append(", active=");
		builder.append(active);
		builder.append(", competitons=");
		builder.append(competitons);
		builder.append(", participants=");
		builder.append(participants);
		builder.append(", rule=");
		builder.append(rule);
		builder.append("]");
		return builder.toString();
	}

}

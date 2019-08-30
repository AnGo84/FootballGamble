package ua.com.footballgamble.model.entity;

import java.util.Date;

public class SeasonStage {
	private int id;
	private String name;
	private Date from;
	private Date till;
	private boolean active;

	public SeasonStage() {
		super();
		active = true;
		// TODO Auto-generated constructor stub
	}

	public SeasonStage(String name, Date from, Date till) {
		super();
		this.name = name;
		this.from = from;
		this.till = till;
		this.active = true;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getFrom() {
		return from;
	}

	public void setFrom(Date from) {
		this.from = from;
	}

	public Date getTill() {
		return till;
	}

	public void setTill(Date till) {
		this.till = till;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (active ? 1231 : 1237);
		result = prime * result + ((from == null) ? 0 : from.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((till == null) ? 0 : till.hashCode());
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
		SeasonStage other = (SeasonStage) obj;
		if (active != other.active)
			return false;
		if (from == null) {
			if (other.from != null)
				return false;
		} else if (!from.equals(other.from))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (till == null) {
			if (other.till != null)
				return false;
		} else if (!till.equals(other.till))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SeasonStage [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", from=");
		builder.append(from);
		builder.append(", till=");
		builder.append(till);
		builder.append(", active=");
		builder.append(active);
		builder.append("]");
		return builder.toString();
	}

}

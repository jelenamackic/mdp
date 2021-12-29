package model;

import java.io.Serializable;
import java.util.Objects;

public class Zsmdp implements Serializable{
	private String location;
	private String idZSMDP;
	
	public Zsmdp() {
		super();
	}
	
	public Zsmdp(String location, String idZSMDP) {
		super();
		this.location = location;
		this.idZSMDP = idZSMDP;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getIdZSMDP() {
		return idZSMDP;
	}
	public void setIdZSMDP(String idZSMDP) {
		this.idZSMDP = idZSMDP;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idZSMDP, location);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Zsmdp other = (Zsmdp) obj;
		return idZSMDP == other.idZSMDP && Objects.equals(location, other.location);
	}

	@Override
	public String toString() {
		return "Zsmdp [location=" + location + ", idZSMDP=" + idZSMDP + "]";
	}
	
	
}

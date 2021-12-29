package model;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {
	private String username;
	private String password;
	private String idZSMDP;
	
	public User() {
		super();
	}
	public User(String username, String password, String idZSMDP) {
		super();
		this.username = username;
		this.password = password;
		this.idZSMDP = idZSMDP;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getIdZSMDP() {
		return idZSMDP;
	}
	public void setIdZSMDP(String idZSMDP) {
		this.idZSMDP = idZSMDP;
	}
	@Override
	public int hashCode() {
		return Objects.hash(idZSMDP, password, username);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(idZSMDP, other.idZSMDP) && Objects.equals(password, other.password)
				&& Objects.equals(username, other.username);
	}
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", idZSMDP=" + idZSMDP + "]";
	}
	
}

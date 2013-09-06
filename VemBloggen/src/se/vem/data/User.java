package se.vem.data;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: User
 *
 */

@Entity
public class User implements Serializable {

	   
	@Id @GeneratedValue
	private long user_id;
	private String username;
	private String password;
	private int user_rights;
	
	private static final long serialVersionUID = 1L;

	public User() {
		super();
	}   
	public long getUser_id() {
		return this.user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}   
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}   
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}   
	public int getUser_rights() {
		return this.user_rights;
	}

	public void setUser_rights(int user_rights) {
		this.user_rights = user_rights;
	}
	@Override
	public String toString() {
		return "User [user id=" + user_id + ", username=" + username + "]";
	}
	
	
   
}

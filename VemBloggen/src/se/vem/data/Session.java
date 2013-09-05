package se.vem.data;

import java.io.Serializable;
import java.lang.String;
import java.sql.Time;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Session
 *
 */

@Entity
public class Session implements Serializable {

	   
	@Id 
	private long session_id;
	private String username;
	private Time date;
	
	private static final long serialVersionUID = 1L;

	public Session() {
		super();
	}   
	public long getSession_id() {
		return this.session_id;
	}

	public void setSession_id(long session_id) {
		this.session_id = session_id;
	}   
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}   
	public Time getDate() {
		return this.date;
	}

	public void setDate(Time date) {
		this.date = date;
	}
   
}

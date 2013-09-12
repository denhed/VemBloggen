package se.vem.data;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Comments
 *
 */

@Entity
public class Comments implements Serializable {

	   
	@Id @GeneratedValue
	private long comments_id;
	private String poster;
	private String text;
	private String notice;
	
	private static final long serialVersionUID = 1L;

	public Comments() {
		super();
	}   
	public long getComments_id() {
		return this.comments_id;
	}

	public void setComments_id(long comments_id) {
		this.comments_id = comments_id;
	}   
	public String getPoster() {
		return this.poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}   
	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}   
	public String getNotice() {
		return this.notice;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}
	@Override
	public String toString() {
		return "Comments [comments_id= " + comments_id + ", poster= " + poster
				+ ", text= " + text + "]";
	}
   
}

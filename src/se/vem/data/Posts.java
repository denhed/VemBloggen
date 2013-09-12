package se.vem.data;

import java.io.Serializable;
import java.lang.String;
import java.sql.Timestamp;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Posts
 *
 */

@Entity
public class Posts implements Serializable {

	   
	@Id @GeneratedValue
	private long posts_id;
	private String title;
	private String text;
	private Timestamp date;
	private long comments_id;
	private long blogs_id;
	private static final long serialVersionUID = 1L;
	

	public Posts() {
		super();
	}   
	public long getPosts_id() {
		return this.posts_id;
	}

	public void setPosts_id(long posts_id) {
		this.posts_id = posts_id;
	}   
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}   
	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}   
	public Timestamp getDate() {
		return this.date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}   
	public long getComments_id() {
		return this.comments_id;
	}

	public void setComments_id(long comments_id) {
		this.comments_id = comments_id;
	}
	
	public long getBlogs_id() {
		return this.blogs_id;
	}

	public void setBlogs_id(long blogs_id) {
		this.blogs_id = blogs_id;
	}
	@Override
	public String toString() {
		return "Posts [posts_id = " + posts_id + ", title = " + title + ", text = "
				+ text + "]";
	}
	
	
   
}

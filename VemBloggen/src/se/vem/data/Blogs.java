package se.vem.data;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Blogs
 *
 */

@Entity
public class Blogs implements Serializable {

	   
	@Id @GeneratedValue
	private long blog_id;
	private String title;
	private long posts_id;
	private long owner_id;
	
	private static final long serialVersionUID = 1L;

	public Blogs() {
		super();
	}   
	public long getBlog_id() {
		return this.blog_id;
	}

	public void setBlog_id(long blog_id) {
		this.blog_id = blog_id;
	}   
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}   
	public long getPosts_id() {
		return this.posts_id;
	}

	public void setPosts_id(long posts_id) {
		this.posts_id = posts_id;
	}   
	public long getOwner_id() {
		return this.owner_id;
	}

	public void setOwner_id(long owner_id) {
		this.owner_id = owner_id;
	}
   
}

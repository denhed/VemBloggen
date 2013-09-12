package se.vem.databas;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import se.vem.data.Posts;

public class PostsMapper {
	private static PostsMapper postsMapper = new PostsMapper();
	private DatabaseConnection connection = null;
	private static Logger logg = null;
	
	private PostsMapper(){
		logg = Logger.getLogger(PostsMapper.class.getCanonicalName());
		connection = DatabaseConnection.getInstance();
		logg.info("Singeltobject created");
	}
	
	public static PostsMapper getInstance(){
		return postsMapper;
	}
	
	public Posts createPost(Posts post){
		EntityManager em = connection.getEntityManager();
		try{
			em.getTransaction().begin();
			em.persist(post);
			em.getTransaction().commit();
		}finally{
			if(em.getTransaction().isActive()){
				em.getTransaction().rollback();
			}
			em.close();	
		}
		
		return post;
	}
	public Posts removePost(long posts_id){
		EntityManager em = connection.getEntityManager();
		Posts removePost;
		
		try{
			em.getTransaction().begin();
			removePost = em.find(Posts.class,posts_id);
			em.remove(posts_id);
			em.getTransaction().commit();
		}finally{
			if(em.getTransaction().isActive()){
				em.getTransaction().rollback();
			}
			em.close();
		}
		return removePost;
	}
	
	public List<Posts> getAllPosts(){
		EntityManager em = connection.getEntityManager();
		List<Posts> allPosts = null;
		em.getTransaction().begin();
		
		try{
			TypedQuery<Posts> p = em.createQuery("SELECT posts FROM Posts posts",Posts.class);
			allPosts = p.getResultList();
			em.getTransaction().commit();
		}finally{
			if(em.getTransaction().isActive()){
				em.getTransaction().rollback();
			}
			em.close();	
		}
		if(allPosts == null)
			allPosts = new ArrayList<Posts>();
		return allPosts;
	}
	

	public Posts editPost(long posts_id, String title,
			String text){
		EntityManager em = connection.getEntityManager();
		em.getTransaction().begin();
		Posts editPost;
		
		try{
			editPost = em.find(Posts.class, posts_id);
			editPost.setTitle(title);
			editPost.setText(text);
			em.getTransaction().commit();
		}finally{
			if(em.getTransaction().isActive()){
				em.getTransaction().rollback();
			}
			em.close();
		}
		return editPost;
	}
	
	/**
	 * Hämtar alla posts objekt med visst blog_id.
	 * @return retunerar alla posts objekt.
	 */
	public List<Posts> getPostsWithBlogID(long blog_id){
		
		EntityManager em = connection.getEntityManager();
		List<Posts> allPosts = new ArrayList<Posts>();
		
		em.getTransaction().begin();
		
		try {
			TypedQuery<Posts> posts = em.createQuery("Select posts FROM Posts posts WHERE Posts.comments_id =:blogs_id", Posts.class); 
			posts.setParameter("blogs_id", blog_id);
			allPosts = posts.getResultList();
			em.getTransaction();
		} finally {
			if(em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			em.close();
		}
		return allPosts;
	}

}
	

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
				
		}
		em.close();
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

}
	

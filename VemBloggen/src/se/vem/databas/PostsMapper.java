package se.vem.databas;

import java.util.logging.Logger;

import javax.persistence.EntityManager;

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
			if(em.getTransaction().isActive())
				em.getTransaction().rollback();
		}
		em.close();
		return post;
	}
}
	

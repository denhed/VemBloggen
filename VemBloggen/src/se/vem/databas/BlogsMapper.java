package se.vem.databas;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import se.vem.data.Blogs;


public class BlogsMapper {
	
	private static BlogsMapper blogsMapper = new BlogsMapper();
	private DatabaseConnection connection = null;
	private static Logger logg = null;
	
	private BlogsMapper() {
		logg = Logger.getLogger(BlogsMapper.class.getCanonicalName());
		connection = DatabaseConnection.getInstance();
		logg.info("Singeltobject created");
	}
	
	public static BlogsMapper getInstance() {
		return blogsMapper;
	}
	
	/**
	 * Sparar nytt blogs objekt i databasen. (Blogs.class).
	 * @return retunerar ett Blogs objekt, det som sparades.
	 */
	public Blogs createBlog(Blogs blog){
		EntityManager em = connection.getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(blog);
			em.getTransaction().commit();
		} finally {
			if(em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}	
		}
		em.close();
		return blog;
	} 
	
	/**
	 * Redigerar ett Blogs objekt från Blogs-tabellen (Blogs.class).
	 * @return retunerar det redigerade Blogs objektet.
	 */
	public Blogs getBlog(int id){
		EntityManager em = connection.getEntityManager();
		Blogs blog = null;
		
		em.getTransaction().begin();
		
		try {
			
			TypedQuery<Blogs> blogQuery = em.createQuery("SELECT blogs FROM Blogs blogs WHERE Blogs.blog_id = :blogId", Blogs.class); 
			blogQuery.setParameter("blogId", id); //sätter parameter (namn på parametern i queryn, värdet som skickas in).
			blog  = blogQuery.getSingleResult(); // vi tar emot ett objekt.
			em.getTransaction();
		} finally {
			if(em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			em.close();
		}
		return blog;
	} 
	
	
	/**
	 * Hämtar alla Blogs objekt från Blogs-tabellen (Blogs.class).
	 * @return retunerar alla Blogs objekt.
	 */
	public List<Blogs> listAllBlogs() {
		EntityManager em = connection.getEntityManager();
		List<Blogs> allBlogs = new ArrayList<Blogs>();
		
		em.getTransaction().begin();
		
		try {
			TypedQuery<Blogs> b = em.createQuery("SELECT blogs FROM Blogs blogs", Blogs.class); //hämtar blogs obj.
			allBlogs = b.getResultList();
			em.getTransaction();
		} finally {
			if(em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			em.close();
		}
		return allBlogs;
	}
	
	
	
	
	
	
	
/*
	
	
	public Blogs removeBlog(Blogs blogID){
		return blog;
	} 
	
	
	public List<Posts> listPosts(Blogs blogName){
		return blog;
	} 
*/
}


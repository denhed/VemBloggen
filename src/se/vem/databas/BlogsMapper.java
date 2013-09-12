package se.vem.databas;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import se.vem.data.Blogs;
import se.vem.data.Posts;


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
	 * Hämtar en specifik blog med blog_id. (Blogs.class).
	 * @return 
	 * 1. retunerar ett blog objekt.
	 * 2. hittades inget objekt retuneras null.
	 */
	public Blogs getBlog(long blog_id){
		EntityManager em = connection.getEntityManager();
		
		Blogs tempBlog = em.find(Blogs.class, blog_id);//hitta rätt obj som ska raderas. Return null if not find.
		
		if(tempBlog != null){
		
			em.getTransaction().begin();
			
			try {
				tempBlog = em.find(Blogs.class, blog_id);
				em.getTransaction();
			} finally {
				if(em.getTransaction().isActive()) {
					em.getTransaction().rollback();
				}
				em.close();
			}
			return tempBlog;
		}else {
			return null;// om vi inte hittar ett obj retunera null.
		}
	} 
	
	/**
	 * Tar emot ett blog_id och det värden som ska ändras i databasen(Blogs.class).
	 * @return 
	 * 1. retunerar det uppdaterade objekt.
	 * 2. hittades inget objekt retuneras null.
	 */
	public  Blogs editBlog(long blog_id, String title){
		EntityManager em = connection.getEntityManager();
	
		Blogs tempBlog = em.find(Blogs.class, blog_id);//hitta rätt obj som ska raderas. Return null if not find.
		
		if(tempBlog != null){
			try {
				em.getTransaction().begin();
				tempBlog.setTitle(title);  //Uppdaterar titeln.
				em.getTransaction().commit();
									
			} finally {
				if(em.getTransaction().isActive()) {
					em.getTransaction().rollback();
				}
				em.close();
			}
			return tempBlog;
		}else {
			return null;// om vi inte hittar ett obj retunera null.
		}
		
		
	}
	
	/**
	 * Hämtar ett Blogs objekt från Blogs-tabellen (Blogs.class) med 
	 * blog_id och därefter raderas objektet.
	 * @return 
	 * Värden som retuneras:
	 * 1. objekt raderas, 1 retuneras.
	 * 2. inget raderades, 2 retuneras.
	 * 3. hittade inget objekt, 3 retuneras.
	 */
	public int removeBlog(long blog_id){
		
		EntityManager em = connection.getEntityManager();
		
		int status = 0;
		Blogs tempBlog = em.find(Blogs.class, blog_id);//hitta rätt obj som ska raderas. Return null if not find.
		
		if(tempBlog != null){
			try {
				em.getTransaction().begin();
				em.remove(tempBlog);  //Raderar objektet.
				em.getTransaction().commit();
				status = 1; //objekt raderat
			} finally {
				if(em.getTransaction().isActive()) {
					em.getTransaction().rollback();
					status = 2; // något gick fel.
				}
				em.close();
			}
		}else{
			status = 3; //hittade inget objekt.
		}
		
		return status;
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

	public List<Posts> listPosts(Blogs blogName){
		return blog;
	} 
*/
}


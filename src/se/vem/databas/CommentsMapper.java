package se.vem.databas;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import se.vem.data.Comments;
import se.vem.data.Posts;

public class CommentsMapper {
	private static CommentsMapper commentsMapper = new CommentsMapper();

	private DatabaseConnection connection = null;

	private static Logger logg = null;

	private CommentsMapper() {
		logg = Logger.getLogger(CommentsMapper.class.getCanonicalName());
		connection = DatabaseConnection.getInstance();
		logg.info("Singelobject created");
	}

	public static CommentsMapper getInstance() {
		return commentsMapper;
	}
	
	public Comments addComment(Comments comments) {
		EntityManager em = connection.getEntityManager();
		try{
			em.getTransaction().begin();
			em.persist(comments);
			em.getTransaction().commit();
		}finally{
			if(em.getTransaction().isActive()){
				em.getTransaction().rollback();
			}
			em.close();
		}
		return comments;
	}
	
	
	public List<Comments> listComments() {
		EntityManager em = connection.getEntityManager();
		List<Comments> listComment = new ArrayList<Comments>();

		em.getTransaction().begin();

		try {
			TypedQuery<Comments> c = em.createQuery("SELECT comments FROM Comments comments",
					Comments.class);
			listComment = c.getResultList();
			em.getTransaction().commit();
		} finally {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			em.close();
		}

		return listComment;
	}
	public Comments removeComment(long comments_id){
		EntityManager em = connection.getEntityManager();
		Comments removeComment;
		
		try{
			em.getTransaction().begin();
			removeComment = em.find(Comments.class,comments_id);
			em.remove(removeComment);
			em.getTransaction().commit();
		}finally{
			if(em.getTransaction().isActive()){
				em.getTransaction().rollback();
			}
			em.close();
		}
		return removeComment;
	}

}
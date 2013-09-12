package se.vem.databas;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import se.vem.data.Comments;

public class CommentsMapper {
	private static CommentsMapper commentsMapper = new CommentsMapper();

	private static DatabaseConnection connection = null;

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
		try {
			em.getTransaction().begin();
			em.persist(comments);
			em.getTransaction().commit();
		} finally {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
		}
		em.close();

		return comments;

	}
	
	
	public List<Comments> listComments() {
		EntityManager em = connection.getEntityManager();
		List<Comments> listComment = new ArrayList<Comments>();

		em.getTransaction().begin();

		try {
			TypedQuery<Comments> c = em.createQuery("SELECT user FROM User user",
					Comments.class);
			listComment = c.getResultList();
			em.getTransaction();
		} finally {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			em.close();
		}

		return listComment;
	}

}
package se.vem.databas;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import se.vem.data.User;

public class UserMapper {
	private static UserMapper userMapper = new UserMapper();
	
	private DatabaseConnection connection = null;
	
	private static Logger logg = null;
	
	private UserMapper() {
		logg = Logger.getLogger("se.vemprojekt.databas");
		connection = DatabaseConnection.getInstance();
		logg.info("Singeltobject created");
	}
	
	public static UserMapper getInstance() {
		return userMapper;
	}
	
	public User register(User user) {
		EntityManager em = connection.getEntityManager();
		try {
			em.getTransaction().begin();

			em.persist(user);

			em.getTransaction().commit();
		} finally {
			if(em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}	
		}
		em.close();
		
		return user;
		
	}
	public List<User> getAllUsers() {
		EntityManager em = connection.getEntityManager();
		List<User> allUsers = new ArrayList<User>();
		
		em.getTransaction().begin();
		
		try {
			TypedQuery<User> u = em.createQuery("SELECT user FROM User user", User.class);
			allUsers = u.getResultList();
			em.getTransaction();
		} finally {
			if(em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			em.close();
		}
		
		return allUsers;
	}

}


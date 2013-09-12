package se.vem.databas;

import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DatabaseConnection {
	
	private static DatabaseConnection singelDatabaseConnection = null;
	
	private EntityManagerFactory emf = null;
	
	private static Logger logg = Logger.getLogger("se.vemprojekt.databas");
	
	private DatabaseConnection() {
	}
	
	public static DatabaseConnection getInstance() {
		if(singelDatabaseConnection == null) {
			singelDatabaseConnection = new DatabaseConnection();
		}
		
		return singelDatabaseConnection;
	}
	
	public EntityManager getEntityManager() {
		if(emf == null) {
			logg.info("Create a EntityManagerFactory");
			emf = Persistence.createEntityManagerFactory("$objectdb/db/vemprojekt.odb");
		}
		
		return emf.createEntityManager();
	}
	
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		emf.close();
	}
	
}

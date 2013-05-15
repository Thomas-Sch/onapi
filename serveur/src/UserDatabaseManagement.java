import java.sql.ResultSet;
import java.sql.SQLException;

import database.Database;

/**
 * 
 * @author Fabio
 *
 */
public class UserDatabaseManagement {
	
	private Database database;
	
	public UserDatabaseManagement(String dbName){
		database = new Database(dbName);
	}
	
	/**
	 * Ouvre la connexion à la base de données.
	 */
	public void openConnection(){
		database.connect();
	}
	/**
	 * Creer un utilisateur dans la base de donnée.
	 * @param login
	 * @param password
	 * @return
	 */
	public boolean createUser(String login, String password){
		ResultSet result = database.getResultOf("SELECT 1 from User where name='"+login+"'");
		try {
			if(result.next())
				return false;
			else
				database.execute("INSERT INTO User(name, password) VALUES('"+login+"', '"+ password +"')");
				return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Test la connection avec le login password de l'utilisateur.
	 * @param login
	 * @param password
	 * @return correct ou non
	 */
	public boolean checkUserConnection(String login, String password){
		ResultSet result = database.getResultOf("SELECT 1 from User where name='"+login+"' and password='"+ password +"'");
		try {
			if(result.next())
				return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return false;
	}
	
	/**
	 * Termine la connexion avec la base de données
	 * @return
	 */
	public boolean closeConnection(){
		return database.disconnect();
	}

	
}

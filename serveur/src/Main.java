import java.util.Scanner;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final String DATABASE_PATH = "C:\\Users\\Fabio\\Downloads\\sqlite-shell-win32-x86-3071602\\onapi.db";

		int option;
		String login;
		String password;
		
		UserDatabaseManagement uc = new UserDatabaseManagement(DATABASE_PATH);
		Scanner saisie = new Scanner(System.in);
		Scanner saisieLogin = new Scanner(System.in);
		Scanner saisiePassword = new Scanner(System.in);

	
		System.out.println("1 - creer utilisateur");
		System.out.println("2 - tester connection");
		System.out.print("option >");
		option = saisie.nextInt();

		if(option == 2){
			System.out.print("Login >");
			login = saisieLogin.nextLine();
			System.out.print("Password >");
			password = saisiePassword.nextLine();
			
			uc.openConnection();
			if(uc.checkUserConnection(login, password)){
				System.out.println("Connection OK");
			}else{
				System.out.println("Connection failed");
			}
		}else{
			System.out.print("Nom d'utilisateur a enregistrer >");
			login = saisieLogin.nextLine();
			
			System.out.print("Mot de passe du compte >");
			password = saisiePassword.nextLine();
			
			uc.openConnection();
			if(uc.createUser(login, password))
				System.out.println("Le compte a bien ete cree");
			else
				System.out.println("Un utilisateur possede deja ce nom d'utilisateur");
		}
		saisie.close();
		saisieLogin.close();
		saisiePassword.close();
		uc.closeConnection();
	}

}

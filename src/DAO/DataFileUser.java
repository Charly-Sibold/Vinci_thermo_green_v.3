package DAO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;

import control.Controller;
import modele.User;

public class DataFileUser {

	private Controller monController;

	public DataFileUser(Controller aController) throws ParseException {

		this.monController = aController;
		//lireCSV("data\\user.csv");
	}
	
	public boolean lireCSV() throws ParseException {

		try {
			File f = new File("data\\user.csv");
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);

			try {
				// Chaque ligne est un enregistrement de donn�es
				String records = br.readLine();

				// Chaque enregistrement contient des champs
				String[] fields = null;
				String user = null;
				String password = null;

				while (records != null) {
					// Affecte les champs de l'enregistrement courant dans un
					// tableau de chaine
					fields = records.split(";");

					// Affecte les champs aux param�tre du constructeur de
					// User
					user = fields[1];
					password = fields[2];
					
					if(monController.getLeLogin().getTxtLogin().getText().equals(user)) {
						char[] passwordChars = monController.getLeLogin().getPwdPassword().getPassword();
						String pwd = new String(passwordChars);
						if(BCrypt.checkpw(pwd, password)) {
							monController.setLeUser(new User(user , password));
							return true;	
						}
					
					}
									
					// Enregistrement suivant
					records = br.readLine();
				}
				
				br.close();
				fr.close();
			} catch (IOException exception) {
				System.out.println("Erreur lors de la lecture : " + exception.getMessage());
			}
		} catch (FileNotFoundException exception) {
			System.out.println("Le fichier n'a pas �t� trouv�");
		}
		return false;		
	}
}

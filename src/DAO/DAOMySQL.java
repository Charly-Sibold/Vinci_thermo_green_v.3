package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;

import control.Controller;
import modele.Mesure;
import modele.SQLMesures;
import modele.SQLUsers;
import modele.User;

import java.sql.SQLException;

public class DAOMySQL {

	// specification
	Controller monController;
	String URL;
	String userDB;
	String password;
	Connection myConnection;
	Statement myStatement;

	// implementation
	public DAOMySQL(Controller aController) throws SQLException {
		this.monController = aController;
		// driver load
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// connection creation
		this.URL = "jdbc:mysql://localhost:3306/thermo_green";
		this.userDB = "root";
		this.password = "p@ssw0rdSIO";
		this.myConnection = DriverManager.getConnection(URL, userDB, password);
		// statement creation
		this.myStatement = myConnection.createStatement();

	}

	public boolean listerLesUtilisateurs() throws SQLException {

		String theQuery = "Select user_name , password FROM user;";
		ResultSet theResultSet = myStatement.executeQuery(theQuery);

		ArrayList<SQLUsers> SQLusers = new ArrayList<>();

		while (theResultSet.next()) {
			String user = theResultSet.getString("user_name");
			String password = theResultSet.getString("password");

			if (monController.getLeLogin().getTxtLogin().getText().equals(user)) {
				char[] passwordChars = monController.getLeLogin().getPwdPassword().getPassword();
				String pwd = new String(passwordChars);
				if (BCrypt.checkpw(pwd, password)) {
					monController.setLeUser(new User(user, password));
					return true;
				} else {
					// Mot de passe incorrect
					return false;
				}
			} else {
				// Nom d'utilisateur non trouvé dans la base de données
				return false;
			}
		}
		return false;

	}

	public ArrayList<SQLMesures> listerLesmesures() throws SQLException {

		String theQuery = "Select numZone , horoDate , fahrenheit FROM mesures;";
		ResultSet theResultSet = myStatement.executeQuery(theQuery);
		
		ArrayList<SQLMesures> SQLMesures = new ArrayList<>();
		
		while(theResultSet.next()) {
		String numZone = theResultSet.getString("numZone");
		Date horoDate = theResultSet.getDate("horoDate");
		float fahrenheit = theResultSet.getFloat("fahrenheit");
		SQLMesures laMesure = new SQLMesures(numZone, horoDate, fahrenheit);
        SQLMesures.add(laMesure); // Ajoutez l'objet à la liste SQLMesures
		
		}
		return SQLMesures;
	}

}

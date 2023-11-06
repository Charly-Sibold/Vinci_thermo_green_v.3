/**
 * @author J�r�me Valenti 
 */
package control;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.ResourceBundle;

import DAO.BCrypt;
import DAO.DAOMySQL;
import DAO.DataFileMesure;
import DAO.DataFileUser;
import modele.Mesure;
import modele.SQLMesures;
import modele.SQLUsers;
import modele.User;
import view.ConsoleGUI;
import view.Login;

/**
 * <p>
 * Le cont&ocirc;lleur :
 * </p>
 * <ol>
 * <li>lit les mesures de temp�rature dans un fichier texte</li>
 * <li>retourne la collection des mesures<br />
 * </li>
 * </ol>
 * 
 * @author J�r�me Valenti
 * @version 2.0.0
 *
 */
public class Controller {

	/**
	 * <p>
	 * Les mesures lues dans le fichier des relev�s de temp�ratures
	 * </p>
	 */
	private ArrayList<Mesure> lesMesures = new ArrayList<Mesure>();
	private ArrayList<User> lesUser = new ArrayList<User>();
	private ArrayList<SQLUsers> SQLUsers = new ArrayList<SQLUsers>();
	private ArrayList<SQLMesures> SQLMesures = new ArrayList<SQLMesures>();
	private User leUser;
	private ConsoleGUI laConsole;
	private DataFileMesure myDataFile;
	private DataFileUser myDataUser;
	private Login leLogin;
	private Scanner myScanner;
	BCrypt myHash;
	DAOMySQL myDAOmySQL;

	public Controller() throws ParseException, SQLException {

		//lit la base de données
		this.myDAOmySQL = new DAOMySQL(this);
		// Lit le fichier des users
		//this.myDataUser = new DataFileUser(this);
		// Lit le fichier des mesures
		this.myDataFile = new DataFileMesure(this);
		this.myDataFile.lireCSV("data\\mesures.csv");
		// Construit et affiche l'IHM
		this.leLogin = new Login(this);
		this.leLogin.setLocation(100, 100);
		this.leLogin.setVisible(true);
		// this.leLogin = my
		this.laConsole = new ConsoleGUI(this);
		this.laConsole.setLocation(100, 100);
		// this.laConsole.setVisible(true);
	}

	/**
	 * <p>
	 * Filtre la collection des mesures en fonction des param&egrave;tres :
	 * </p>
	 * <ol>
	 * <li>la zone (null = toutes les zones)</li>
	 * <li>la date de d&eacute;but (null = &agrave; partir de l'origine)</li>
	 * <li>la date de fin (null = jusqu'&agrave; la fin)<br />
	 * </li>
	 * </ol>
	 */
	// public void filtrerLesMesure(String laZone, Date leDebut, Date lafin) {
	public ArrayList<Mesure> filtrerLesMesure(String laZone) {
		// Parcours de la collection
		// Ajout � laSelection des objets qui correspondent aux param�tres
		// Envoi de la collection
		ArrayList<Mesure> laSelection = new ArrayList<Mesure>();
		for (Mesure mesure : lesMesures) {
			if (laZone.compareTo("*") == 0) {
				laSelection.add(mesure);
			} else {
				if (laZone.compareTo(mesure.getNumZone()) == 0) {
					laSelection.add(mesure);
				}
			}
		}
		return laSelection;
	}

	/**
	 * <p>
	 * Retourne la collection des mesures
	 * </p>
	 * 
	 * @return ArrayList<Mesure>
	 */
	public ArrayList<Mesure> getLesMesures() {

		return lesMesures;
	}

	public void setLesMesures(ArrayList<Mesure> lesMesures) {
		this.lesMesures = lesMesures;
	}

	public ArrayList<User> getlesUser() {

		return lesUser;
	}

	public void setlesUser(ArrayList<User> lesUser) {

		this.lesUser = lesUser;
	}

	public Login getLeLogin() {
		return leLogin;
	}

	public void setLeLogin(Login leLogin) {
		this.leLogin = leLogin;
	}

	public User getLeUser() {
		return leUser;
	}

	public void setLeUser(User leUser) {
		this.leUser = leUser;
	}

	public DataFileMesure getMyDataFile() {
		return myDataFile;
	}

	public void setMyDataFile(DataFileMesure myDataFile) {
		this.myDataFile = myDataFile;
	}

	public DataFileUser getMyDataUser() {
		return myDataUser;
	}

	public void setMyDataUser(DataFileUser myDataUser) {
		this.myDataUser = myDataUser;
	}

	public ConsoleGUI getLaConsole() {
		return laConsole;
	}

	public void setLaConsole(ConsoleGUI laConsole) {
		this.laConsole = laConsole;
	}

	public BCrypt getMyHash() {
		return myHash;
	}

	public void setMyHash(BCrypt myHash) {
		this.myHash = myHash;
	}

	public DAOMySQL getMyDAOmySQL() {
		return myDAOmySQL;
	}

	public void setMyDAOmySQL(DAOMySQL myDAOmySQL) {
		this.myDAOmySQL = myDAOmySQL;
	}

	public ArrayList<SQLUsers> getSQLUsers() {
		return SQLUsers;
	}

	public void setSQLUsers(ArrayList<SQLUsers> sQLUsers) {
		SQLUsers = sQLUsers;
	}

	public ArrayList<SQLMesures> getSQLMesures() {
		return SQLMesures;
	}

	public void setSQLMesures(ArrayList<SQLMesures> sQLMesures) {
		SQLMesures = sQLMesures;
	}
}

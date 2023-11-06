package DAO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import control.Controller;
import modele.Mesure;

public class DataFileMesure {
	
	//private ArrayList<Mesure> lesMesures = new ArrayList<Mesure>();
	
	private Controller monController;

	public DataFileMesure(Controller aController) throws ParseException {

		this.monController = aController;
		//lireCSV("data\\mesures.csv");
	}


	public void lireCSV(String filePath) throws ParseException {

		try {
			File f = new File(filePath);
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);

			try {
				// Chaque ligne est un enregistrement de donn�es
				String records = br.readLine();

				// Chaque enregistrement contient des champs
				String[] fields = null;
				String numZone = null;
				Date horoDate = null;
				float fahrenheit;

				while (records != null) {
					// Affecte les champs de l'enregistrement courant dans un
					// tableau de chaine
					fields = records.split(";");

					// Affecte les champs aux param�tre du constructeur de
					// mesure
					numZone = fields[0];
					horoDate = strToDate(fields[1]);
					fahrenheit = Float.parseFloat(fields[2]);

					// Instancie une Mesure
					Mesure laMesure = new Mesure(numZone, horoDate, fahrenheit);
		
					monController.getLesMesures().add(laMesure);
					

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
	}
	
	/**
	 * <p>Convertion d'une String en Date</p>
	 * 
	 * @param strDate
	 * @return Date
	 * @throws ParseException
	 */
	private Date strToDate(String strDate) throws ParseException {

		SimpleDateFormat leFormat = null;
		Date laDate = new Date();
		leFormat = new SimpleDateFormat("yy-MM-dd kk:mm");

		laDate = leFormat.parse(strDate);
		return laDate;
	}
}

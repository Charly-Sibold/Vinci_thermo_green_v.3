package modele;

import java.util.Date;

public class SQLMesures {
     /*
	 * <p>numZone contient le num�ro de la zone mesur�e</p>
	 */
	private String numZone;
	/**
	 * <p>horoDate contient la date et l'heure de la mesure au format aa-mm-jj hh:mm</p>
	 */
	private Date horoDate;  
	/**
	 * <p>valFahrenheit contient la valeur de la temp�rature mesur�e en degr� Fahrenheit</p>
	 */
	private float fahrenheit; 

	public SQLMesures() {
		this.numZone = new String();
		this.horoDate = new Date();
		this.fahrenheit = 0.0f;	
	}
	
	public SQLMesures(String pZone, Date pDate, float pFahrenheit) {

		this.numZone = pZone;
		this.horoDate = pDate;
		this.fahrenheit = pFahrenheit;
	}

	public String getNumZone() {
		return numZone;
	}

	public void setNumZone(String numZone) {
		this.numZone = numZone;
	}

	public Date getHoroDate() {
		return horoDate;
	}

	public void setHoroDate(Date horoDate) {
		this.horoDate = horoDate;
	}

	public float getFahrenheit() {
		return fahrenheit;
	}

	public void setFahrenheit(float valFahrenheit) {
		this.fahrenheit = valFahrenheit;
	}

	/**
	 * <p>Convertit Fahrenheit en �Celsius</p> 
	 * @since 2.0.0
	 * @return float t�Celsius
	 */
	public float getCelsius() {
		//return (float) (valFahrenheit - 32) / 1.8)
		return (fahrenheit - 32.0f) / 1.8f;
	}
}


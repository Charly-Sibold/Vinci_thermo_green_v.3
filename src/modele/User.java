package modele;

import javax.swing.JButton;

public class User {
	
	private String theUser;
	private String password;
	private JButton login;
	
	public User() {
		this.theUser = new String();
		this.password = new String();
		
	}
	
	public User(String pUser , String pPassword) {
		
		this.theUser = pUser;
		this.password = pPassword;
		
	}
	

	public String getUser() {
		return theUser;
	}

	public void setUser(String user) {
		this.theUser = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public JButton getLogin() {
		return login;
	}

	public void setLogin(JButton login) {
		this.login = login;
	}

}

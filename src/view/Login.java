package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import control.Controller;
import modele.User;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.border.EmptyBorder;
import java.awt.Choice;
import java.util.ResourceBundle;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JComboBox;

public class Login extends JFrame {

	private Controller monController;

	private JFrame frame;
	private JPanel panel;
	private JLabel lblLogin;
	private JTextField txtLogin;
	private JLabel lblPassword;
	private JPasswordField pwdPassword;
	private JButton btnLogin;
	private User unUser;
	private ArrayList<User> lesUser;
	private ResourceBundle messages;
	private JComboBox choiceLangue;

	/**
	 * Create the application.
	 * 
	 * @throws ParseException
	 * @throws SQLException
	 */
	public Login(Controller aController) throws ParseException, SQLException {
		setTitle("Vinci_thermo_green");
		this.monController = aController;
		lesUser = monController.getlesUser();
		// this.myResult = monController.getMyDAOmySQL().listerLesUtilisateurs();

		// frame = new JFrame();
		this.setBounds(100, 100, 535, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel = new JPanel();
		this.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		lblLogin = new JLabel("Utilisateur : ");
		lblLogin.setBounds(76, 61, 88, 39);
		lblLogin.setFont(new Font("Consolas", Font.PLAIN, 18));
		panel.add(lblLogin);
		txtLogin = new JTextField();
		txtLogin.setBounds(187, 65, 130, 30);
		txtLogin.setFont(new Font("Consolas", Font.PLAIN, 13));
		panel.add(txtLogin);
		txtLogin.setColumns(10);

		lblPassword = new JLabel("mot de passe :");
		lblPassword.setBounds(65, 119, 130, 30);
		lblPassword.setFont(new Font("Consolas", Font.PLAIN, 18));
		panel.add(lblPassword);
		pwdPassword = new JPasswordField();
		pwdPassword.setBounds(187, 119, 130, 30);
		panel.add(pwdPassword);

		btnLogin = new JButton("se connecter");
		panel.add(btnLogin);
		btnLogin.setBounds(192, 214, 120, 39);
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// this.setVisible(false);
			}
		});
		btnLogin.setFont(new Font("Consolas", Font.PLAIN, 15));
		
		choiceLangue = new JComboBox();
		choiceLangue.setBounds(376, 24, 120, 21);
		panel.add(choiceLangue);
		
		choiceLangue.addItem("French");
		choiceLangue.addItem("English");
		choiceLangue.addItem("Spanish");
		
		choiceLangue.addItemListener(new ItemListener()  {
		    public void itemStateChanged(ItemEvent e){
		        if (e.getStateChange() == ItemEvent.SELECTED) {
		            String selectedLanguage = (String) choiceLangue.getSelectedItem();
		            Locale selectedLocale = new Locale("fr", "FR");

		            if (selectedLanguage.equals("English")) {
		                selectedLocale = new Locale("en", "US");
		            } else if (selectedLanguage.equals("Spanish")) {
		                selectedLocale = new Locale("es", "ES");
		            }

		            if (selectedLocale != null) {
		                Locale.setDefault(selectedLocale);
		                // Chargez les ressources dans la langue sélectionnée
		                messages = ResourceBundle.getBundle("resources.Messages");
		                
		                // Mettez à jour les libellés et le texte du bouton
		                lblLogin.setText(messages.getString("loginLabel"));
		                lblPassword.setText(messages.getString("passwordLabel"));
		                btnLogin.setText(messages.getString("loginButton"));
		            }
		        }
		    }
		});
	
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Clique sur le bouton login");
				System.out.println("login : " + txtLogin.getText());
				char[] passwordChars = pwdPassword.getPassword();
				String password = new String(passwordChars);
				System.out.println("password: " + password);
				/*
				 * try { monController.getMyDataUser().lireCSV(); } catch (ParseException e1) {
				 * // TODO Auto-generated catch block e1.printStackTrace(); }
				 */
				try {
					monController.getMyDAOmySQL().listerLesUtilisateurs();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (monController.getLeUser() != null) {
					monController.getLaConsole().setVisible(true);
					monController.getLeLogin().setVisible(false);
				}
			}
		});
	}

	public JTextField getTxtLogin() {
		return txtLogin;
	}

	public void setTxtLogin(JTextField txtLogin) {
		this.txtLogin = txtLogin;
	}

	public JPasswordField getPwdPassword() {
		return pwdPassword;
	}

	public void setPwdPassword(JPasswordField pwdPassword) {
		this.pwdPassword = pwdPassword;
	}
}

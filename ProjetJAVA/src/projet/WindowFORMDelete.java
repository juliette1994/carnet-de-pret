package projet;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class WindowFORMDelete {
	private JFrame deleteFrame = new JFrame("Supprimer une personne de votre carnet");
	// Form FIELDS
    private JLabel nom;
    private JLabel prenom;
    private JTextField nomTextField;
    private JTextField prenomTextField;
    // Buttons
    private JButton validerBtn;
    private JButton menuBtn;
    private String errorMessage;
	public WindowFORMDelete() {
		//Changer la couleur du background en couleur BEET
		Color Beet = Color.decode("#985396");

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		deleteFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//TITLE
		JPanel titlePanel = new JPanel();
        JLabel formTitle = new JLabel("Qui voulez-vous enlever de votre carnet ?");
        
        titlePanel.add(formTitle);
        titlePanel.setBackground(Beet);
        
        panel.add(titlePanel);
        
        JPanel infosPanel = new JPanel();
        infosPanel.setLayout(new GridLayout(2, 2, 0, 0));
        //NOM
        nom = new JLabel("Nom");
        nom.setAlignmentX(Component.CENTER_ALIGNMENT);
        infosPanel.add(nom);
        
        nomTextField = new JTextField();
        nomTextField.setAlignmentX(Component.CENTER_ALIGNMENT);
        infosPanel.add(nomTextField);
        
        //PRENOM
        prenom = new JLabel("Prénom");
        prenom.setAlignmentX(Component.CENTER_ALIGNMENT);
        infosPanel.add(prenom);
        
        prenomTextField = new JTextField();
        prenomTextField.setAlignmentX(Component.CENTER_ALIGNMENT);
        infosPanel.add(prenomTextField);
        
        panel.add(infosPanel);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));
        buttonsPanel.setBackground(Beet);
        
        //VALIDER
        validerBtn = new JButton("Valider");
        validerBtn.setAlignmentY(Component.CENTER_ALIGNMENT);
        validerBtn.addActionListener(new callDeleteWindow());
        buttonsPanel.add(validerBtn);
        
        //MENU
        menuBtn = new JButton("Accueil");
        menuBtn.setAlignmentY(Component.CENTER_ALIGNMENT);
        menuBtn.addActionListener(new WindowPrincipal());
        buttonsPanel.add(menuBtn);
        
        panel.add(buttonsPanel);
        
        infosPanel.setBackground(Color.WHITE);
        
        deleteFrame.setContentPane(panel);
        deleteFrame.getContentPane().setBackground(Beet);
        deleteFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        deleteFrame.setSize(500,150);
        deleteFrame.setVisible(true);
	}
	
	public  class   WindowPrincipal implements   ActionListener
	{
		public  void    actionPerformed(ActionEvent e)
		{
			deleteFrame.setVisible(false);
			new WindowHOME();
		}
	}
	
	public  class   callDeleteWindow implements   ActionListener
    {
        public  void    actionPerformed(ActionEvent e)
        {
        	deleteFrame.setVisible(false);
        	if (nomTextField.getText().equals("") || prenomTextField.getText().equals("")) {
        		errorMessage = "<html>Vous avez un ou plusieurs champs vides, veuillez les remplir avant de les valider!</html>";
        		new WindowERRORRemainOnSamePage(errorMessage);
        		deleteFrame.setVisible(true);
        	}
        	else
        		new WindowDELETE(nomTextField.getText(), prenomTextField.getText());
        }
    }
}

package projet;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import projet.WindowADD;

public class WindowFORMAdd {
	private JFrame addFrame = new JFrame("Ajouter une personne à mon carnet");
	// Form FIELDS
    private JLabel nom;
    private JLabel prenom;
    private JLabel itemNom;
    private JLabel itemType;
    private JLabel itemDate;
    private JTextField nomTextField;
    private JTextField prenomTextField;
    private JTextField itemNomTextField;
    private JTextField itemTypeTextField;
    private JLabel itemDateTime;
    // Buttons
    private JButton validerBtn;
    private JButton menuBtn;
    private String errorMessage;
	public WindowFORMAdd() {
		//Changer la couleur du background en couleur CLOVER
		Color LightCoral = Color.decode("#F08080");

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		addFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//TITLE
		JPanel titlePanel = new JPanel();
        JLabel formTitle = new JLabel("Qui vous a empruntés un item ?");
        
        titlePanel.add(formTitle);
        titlePanel.setBackground(LightCoral);
        
        panel.add(titlePanel);
        
        JPanel infosPanel = new JPanel();
        infosPanel.setLayout(new GridLayout(5, 2, 0, 0));
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
        
        //NOM ITEM
        itemNom = new JLabel("Nom de l'item");
        itemNom.setAlignmentX(Component.CENTER_ALIGNMENT);
        infosPanel.add(itemNom);
        
        itemNomTextField = new JTextField();
        itemNomTextField.setAlignmentX(Component.CENTER_ALIGNMENT);
        infosPanel.add(itemNomTextField);
        
        //TYPE ITEM
        itemType = new JLabel("Type de l'item");
        itemType.setAlignmentX(Component.CENTER_ALIGNMENT);
        infosPanel.add(itemType);
        
        itemTypeTextField = new JTextField();
        itemTypeTextField.setAlignmentX(Component.CENTER_ALIGNMENT);
        infosPanel.add(itemTypeTextField);
        
        //DATE ITEM
        itemDate = new JLabel("Date de l'ajout de l'item");
        itemDate.setAlignmentX(Component.CENTER_ALIGNMENT);
        infosPanel.add(itemDate);
        
        LocalDateTime today = LocalDateTime.now();
        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        itemDateTime = new JLabel(today.format(formatterDate));
        itemDateTime.setAlignmentX(Component.CENTER_ALIGNMENT);
        infosPanel.add(itemDateTime);
        
        panel.add(infosPanel);
        
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));
        buttonsPanel.setBackground(LightCoral);
        
        //VALIDER
        validerBtn = new JButton("Valider");
        validerBtn.setAlignmentY(Component.CENTER_ALIGNMENT);
        validerBtn.addActionListener(new callAddWindow());
        buttonsPanel.add(validerBtn);
        
        //MENU
        menuBtn = new JButton("Accueil");
        menuBtn.setAlignmentY(Component.CENTER_ALIGNMENT);
        menuBtn.addActionListener(new WindowPrincipal());
        buttonsPanel.add(menuBtn);
        
        panel.add(buttonsPanel);
        infosPanel.setBackground(Color.WHITE);
        
        addFrame.setContentPane(panel);
		addFrame.getContentPane().setBackground(LightCoral);
		addFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addFrame.setSize(500,220);
		addFrame.setVisible(true);
	}
	
	public  class   WindowPrincipal implements   ActionListener
	{
		public  void    actionPerformed(ActionEvent e)
		{
			addFrame.setVisible(false);
			new WindowHOME();
		}
	}
	
	public  class   callAddWindow implements   ActionListener
    {
        public  void    actionPerformed(ActionEvent e)
        {
        	addFrame.setVisible(false);
        	if (nomTextField.getText().equals("") || prenomTextField.getText().equals("") || itemNomTextField.getText().equals("") || itemTypeTextField.getText().equals("") || itemDateTime.getText().equals("")) {
        		errorMessage = "<html>Vous avez un ou plusieurs champs vides, veuillez les remplir avant de les valider!</html>";
        		new WindowERRORRemainOnSamePage(errorMessage);
        		addFrame.setVisible(true);
        	}
        	else
        		new WindowADD(nomTextField.getText(), prenomTextField.getText(), itemNomTextField.getText(), itemTypeTextField.getText(), itemDateTime.getText());
        }
    }
}

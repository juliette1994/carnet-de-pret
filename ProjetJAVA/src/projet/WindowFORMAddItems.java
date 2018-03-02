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

public class WindowFORMAddItems {
	private JFrame addFrame;
	// Form FIELDS
    private JLabel itemNom;
    private JLabel itemType;
    private JLabel itemDate;
    private JTextField itemNomTextField;
    private JTextField itemTypeTextField;
    private JLabel itemDateTime;
    // Buttons
    private JButton validerBtn;
    private JButton menuBtn;
    private String errorMessage;
    private String nom;
    private String prenom;
	public WindowFORMAddItems(String nomUser, String prenomUser) {
		nom = nomUser;
		prenom = prenomUser;
		addFrame = new JFrame("Quel item voulez-vous ajouter à " + prenomUser + " " + nomUser + " ?");
		//Changer la couleur du background en couleur MediumAquaMarine 
		Color MediumAquaMarine = Color.decode("#66CDAA");

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		addFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//TITLE
		JPanel titlePanel = new JPanel();
        JLabel formTitle = new JLabel("Quel est l'item ?");
        
        titlePanel.add(formTitle);
        titlePanel.setBackground(MediumAquaMarine);
        
        panel.add(titlePanel);
        
        JPanel infosPanel = new JPanel();
        infosPanel.setLayout(new GridLayout(3, 2, 0, 0));
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
        buttonsPanel.setBackground(MediumAquaMarine);
        
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
		addFrame.getContentPane().setBackground(MediumAquaMarine);
		addFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addFrame.setSize(500,165);
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
        	if (itemNomTextField.getText().equals("") || itemTypeTextField.getText().equals("")) {
        		errorMessage = "<html>Vous avez un ou plusieurs champs vides, veuillez les remplir avant de les valider!</html>";
        		new WindowERRORRemainOnSamePage(errorMessage);
        		addFrame.setVisible(true);
        	}
        	else {
        		METHODEAddItemToUserToXML adder = new METHODEAddItemToUserToXML();
        		adder.checkXML(nom, prenom, itemNomTextField.getText(), itemTypeTextField.getText(), itemDateTime.getText());
        	}
        }
    }
}

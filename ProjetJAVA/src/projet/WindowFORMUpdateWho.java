package projet;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class WindowFORMUpdateWho {
	private JFrame updateFrame = new JFrame("Mettre à jour les données d'une personne de votre carnet");
	private JLabel nom;
	private JLabel prenom;
	private JTextField nomTextField;
	private JTextField prenomTextField;
	// Buttons
    private JButton validerBtn;
    private JButton menuBtn;
	private List<Element> listItems;
	private Element personSelected;
	private String errorMessage;
	public WindowFORMUpdateWho() {
		//Changer la couleur du background en couleur PERSIMMON
		Color Persimmon = Color.decode("#E43E22");

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		updateFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//TITLE
		JPanel titlePanel = new JPanel();
		JLabel formTitle = new JLabel("Quelle est la personne dont vous voulez mettre à jour ses informations ?");

		titlePanel.add(formTitle);
		titlePanel.setBackground(Persimmon);

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
        buttonsPanel.setBackground(Persimmon);
        
        //VALIDER
        validerBtn = new JButton("Valider");
        validerBtn.setAlignmentY(Component.CENTER_ALIGNMENT);
        validerBtn.addActionListener(new callUpdateWindow());
        buttonsPanel.add(validerBtn);
        
        //MENU
        menuBtn = new JButton("Accueil");
        menuBtn.setAlignmentY(Component.CENTER_ALIGNMENT);
        menuBtn.addActionListener(new WindowPrincipal());
        buttonsPanel.add(menuBtn);
        
        panel.add(buttonsPanel);
        
        infosPanel.setBackground(Color.WHITE);

		updateFrame.setContentPane(panel);
		updateFrame.getContentPane().setBackground(Persimmon);
		updateFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		updateFrame.setSize(500,150);
		updateFrame.setVisible(true);
	}

	public int checkXML() {
		String filename = "src/projet/mon_carnet_de_pret.xml";
		File file = new File(filename);
		int userExists = 0;
		if (file.exists()) {
			userExists = checkUserExists(filename, userExists);
			return (userExists);
		}
		else {
			METHODECheckXMLInexistant checker = new METHODECheckXMLInexistant();
			checker.CheckIfFileInexistant(file, filename);
		}
		return (userExists);
	}

	public  int checkUserExists(String filename, int userExists) {
		SAXBuilder builder = new SAXBuilder();
		File xmlFile = new File(filename);
		try {
			Document document = (Document)builder.build(xmlFile);
			Element carnet = document.getRootElement();
			List<Element> listPersonnes = carnet.getChildren("personne");
			if (listPersonnes.size() >= 2) {
				for (int counterPersonnes = 1; counterPersonnes < listPersonnes.size(); counterPersonnes++) {
					Element personne = (Element)listPersonnes.get(counterPersonnes);
					String nom = personne.getChild("nom").getText();
					String prenom = personne.getChild("prenom").getText();
					if (nom.equals(nomTextField.getText()) && prenom.equals(prenomTextField.getText())) {
						listItems = personne.getChild("items").getChildren("item");
						personSelected = personne;
						userExists = 1;
					}
				}
			}
			else {
				userExists = 2;
				updateFrame.setVisible(false);
				String errorMessage = "<html>Aucune personne dans votre carnet!</html>";
				new WindowERRORRedirectionToHOME(errorMessage);
			}
		}
		catch (IOException ioException) {
			System.out.println(ioException.getMessage());
		}
		catch (JDOMException jdomexException) {
			System.out.println(jdomexException.getMessage());
		}
		return (userExists);
	}

	public  class   WindowPrincipal implements   ActionListener
	{
		public  void    actionPerformed(ActionEvent e)
		{
			updateFrame.setVisible(false);
			new WindowHOME();
		}
	}
	
	public  class   callUpdateWindow implements   ActionListener
	{
		public  void    actionPerformed(ActionEvent e)
		{
			updateFrame.setVisible(false);
			if (nomTextField.getText().equals("") || prenomTextField.getText().equals("")) {
				errorMessage = "<html>Vous avez un ou plusieurs champs vides, veuillez les remplir avant de les valider!</html>";
				new WindowERRORRemainOnSamePage(errorMessage);
				updateFrame.setVisible(true);
			}
			else {
				int result = checkXML();
				if (result == 1) {
					new WindowUPDATE(nomTextField.getText(), prenomTextField.getText(), listItems, personSelected);
				}
				else if (result == 0) {
					String errorMessage = "<html>" + prenomTextField.getText() + " " + nomTextField.getText() + " n'est pas présent dans votre carnet.</html>";
					prenomTextField.setText("");
					nomTextField.setText("");
					new WindowERRORRemainOnSamePage(errorMessage);
					updateFrame.setVisible(true);
				}
			}
		}
	}
}

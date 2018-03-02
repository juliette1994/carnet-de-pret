package projet;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.jdom2.Element;

public class WindowUPDATE {
	private JFrame updateFrame;
	// Buttons
    private JButton menuBtn;
	private JButton updateBtn;
	private JPanel panel;
	private Element personne;
	public WindowUPDATE(String nomUser, String prenomUser, List<Element> listItems, Element personSelected) {
		//Changer la couleur du background en couleur PAPAYA
		Color Papaya = Color.decode("#F68B69");
		personne = personSelected;
		JLabel nom;
		JLabel prenom;
		JLabel itemNom;
		JLabel itemType;
		JLabel itemDate;
		JTextField nomTextField;
		JTextField prenomTextField;
		JTextField itemNomTextField;
		JTextField itemTypeTextField;
		JLabel itemDateTextField;
		updateFrame = new JFrame("Mettre à jour les données de " + prenomUser + " " + nomUser);

		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		updateFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//TITLE
		JPanel titlePanel = new JPanel();
		JLabel formTitle = new JLabel("Informations de " + prenomUser + " " + nomUser);

		titlePanel.add(formTitle);
		titlePanel.setBackground(Papaya);

		panel.add(titlePanel);

		JPanel infosPanel = new JPanel();
		infosPanel.setLayout(new GridLayout(2 + (3 * listItems.size()), 2, 0, 0));
		//NOM
		nom = new JLabel("Nom");
		nom.setAlignmentX(Component.CENTER_ALIGNMENT);
		infosPanel.add(nom);

		nomTextField = new JTextField(nomUser);
		nomTextField.setAlignmentX(Component.CENTER_ALIGNMENT);
		infosPanel.add(nomTextField);

		//PRENOM
		prenom = new JLabel("Prénom");
		prenom.setAlignmentX(Component.CENTER_ALIGNMENT);
		infosPanel.add(prenom);

		prenomTextField = new JTextField(prenomUser);
		prenomTextField.setAlignmentX(Component.CENTER_ALIGNMENT);
		infosPanel.add(prenomTextField);

		for (int counterItems = 0; counterItems < listItems.size(); counterItems++) {
			//ITEM NOM
			itemNom = new JLabel("Nom de l'item n°" + (counterItems + 1));
			itemNom.setAlignmentX(Component.CENTER_ALIGNMENT);
			infosPanel.add(itemNom);

			itemNomTextField = new JTextField(listItems.get(counterItems).getChild("nomObjet").getText());
			itemNomTextField.setAlignmentX(Component.CENTER_ALIGNMENT);
			//itemNomTextField.addActionListener(new callUpdateWindow());
			infosPanel.add(itemNomTextField);

			//ITEM TYPE
			itemType = new JLabel("Type de l'item n°" + (counterItems + 1));
			itemType.setAlignmentX(Component.CENTER_ALIGNMENT);
			infosPanel.add(itemType);

			itemTypeTextField = new JTextField(listItems.get(counterItems).getChild("typeObjet").getText());
			itemTypeTextField.setAlignmentX(Component.CENTER_ALIGNMENT);
			infosPanel.add(itemTypeTextField);

			//ITEM DATE
			itemDate = new JLabel("Date de l'ajout de l'item n°" + (counterItems + 1));
			itemDate.setAlignmentX(Component.CENTER_ALIGNMENT);
			infosPanel.add(itemDate);

			LocalDateTime today = LocalDateTime.now();
	        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
	        itemDateTextField = new JLabel(today.format(formatterDate));
	        itemDateTextField.setAlignmentX(Component.CENTER_ALIGNMENT);
	        infosPanel.add(itemDateTextField);
		}

		panel.add(infosPanel);

		JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));
        buttonsPanel.setBackground(Papaya);
        
        //UPDATER
      	updateBtn = new JButton("Mettre à jour les données");
      	updateBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
      	updateBtn.addActionListener(new callUpdateInfosWindow());
      	buttonsPanel.add(updateBtn);
        
        //MENU
        menuBtn = new JButton("Accueil");
        menuBtn.setAlignmentY(Component.CENTER_ALIGNMENT);
        menuBtn.addActionListener(new WindowPrincipal());
        buttonsPanel.add(menuBtn);
        
        panel.add(buttonsPanel);
        
        infosPanel.setBackground(Color.WHITE);

		updateFrame.setContentPane(panel);
		updateFrame.getContentPane().setBackground(Papaya);
		updateFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		updateFrame.setSize(500, 120 + (listItems.size() * 90));
		updateFrame.setVisible(true);
	}

	public  class   WindowPrincipal implements   ActionListener
	{
		public  void    actionPerformed(ActionEvent e)
		{
			updateFrame.setVisible(false);
			new WindowHOME();
		}
	}
	
	public List<JTextField> findTextFields(Container panel, Class<JTextField> textFields) {
		return Stream.concat(
				Arrays.stream(panel.getComponents())
				.filter(textFields::isInstance)
				.map(textFields::cast),
				Arrays.stream(panel.getComponents())
				.filter(Container.class::isInstance)
				.map(Container.class::cast)
				.flatMap(newPanel -> findTextFields(newPanel, textFields).stream())
				).collect(Collectors.toList());
	}

	public  class   callUpdateInfosWindow implements   ActionListener
	{
		public  void    actionPerformed(ActionEvent e)
		{
			List<JTextField> listTextFields = findTextFields(panel, JTextField.class);
			updateFrame.setVisible(false);
			METHODEUpdateUserInfosToXML updateUser = new METHODEUpdateUserInfosToXML();
			if (!updateUser.UpdateUserInfosToXML("src/projet/mon_carnet_de_pret.xml", personne, listTextFields))
				updateFrame.setVisible(true);
		}
	}
}
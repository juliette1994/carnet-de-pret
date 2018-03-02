package projet;

import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.JDOMException;

public class METHODEDisplayUsersFromXML {
	private JFrame displayFrame = new JFrame("Afficher les personnes");
	private String[][] listItems;
	private String errorMessage;
	public void checkXML() {
		String filename = "src/projet/mon_carnet_de_pret.xml";
		File file = new File(filename);
		if (file.exists()) {
			diplayInfosXML(filename);
		}
		else {
			METHODECheckXMLInexistant checker = new METHODECheckXMLInexistant();
			checker.CheckIfFileInexistant(file, filename);
		}
	}

	public  void diplayInfosXML(String filename) {
		SAXBuilder builder = new SAXBuilder();
		File xmlFile = new File(filename);
		try {
			Document document = (Document)builder.build(xmlFile);
			Element carnet = document.getRootElement();
			List<Element> listPersonnes = carnet.getChildren("personne");
			if (listPersonnes.size() > 1) {
				//Changer la couleur du background en couleur CLOVER
				Color Clover = Color.decode("#A1CE5E");
				JPanel panel = new JPanel();
				panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
				panel.setBackground(Clover);
				JPanel txtPanel = new JPanel();
				txtPanel.setBackground(Clover);
				JLabel nbPersonnesList = new JLabel((listPersonnes.size() - 1) + " personnes vous ont empruntées quelques choses.");
				nbPersonnesList.setAlignmentX(Component.CENTER_ALIGNMENT);
				txtPanel.add(nbPersonnesList);
				panel.add(txtPanel);
				JPanel buttonPanel = new JPanel();
				buttonPanel.setBackground(Clover);
				buttonPanel.setLayout(new GridLayout(listPersonnes.size(), 1, 5, 10));
				int counter = 0;
				listItems = new String[getNbItemsFromXML(listPersonnes)][5];
				for (int counterPersonnes = 1; counterPersonnes < listPersonnes.size(); counterPersonnes++) {
					Element personne = (Element)listPersonnes.get(counterPersonnes);
					Element items = (Element)listPersonnes.get(counterPersonnes).getChild("items");
					List<Element> listPersonItems = items.getChildren("item");
					for (int counterItems = 0; counterItems < listPersonItems.size(); counterItems++, counter++) {
						listItems[counter][0] = personne.getChild("nom").getText();
						listItems[counter][1] = personne.getChild("prenom").getText();
						listItems[counter][2] = listPersonItems.get(counterItems).getChild("nomObjet").getText();
						listItems[counter][3] = listPersonItems.get(counterItems).getChild("dateObjet").getText();
						listItems[counter][4] = listPersonItems.get(counterItems).getChild("typeObjet").getText();
					}
					JButton button = new JButton(personne.getChild("nom").getText() + " " + personne.getChild("prenom").getText());
					button.setActionCommand(personne.getChild("nom").getText() + " " + personne.getChild("prenom").getText());
					button.addActionListener(new WindowUserInfos());
					button.setAlignmentX(Component.CENTER_ALIGNMENT);
					buttonPanel.add(button);
				}
				JButton menu = new JButton("Menu");
				menu.setActionCommand("Menu");
				menu.addActionListener(new WindowPrincipal());
				menu.setAlignmentX(Component.CENTER_ALIGNMENT);
				panel.add(new JScrollPane(buttonPanel));
				panel.add(menu);
				displayFrame.setSize(500,200);
				displayFrame.add(panel);
				displayFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				displayFrame.setVisible(true);
			}
			else {
				errorMessage = "<html>Aucune personne dans votre carnet!</html>";
				new WindowERRORRedirectionToHOME(errorMessage);
			}
		}
		catch (IOException ioException) {
			System.out.println(ioException.getMessage());
		}
		catch (JDOMException jdomexException) {
			System.out.println(jdomexException.getMessage());
		}
	}
	//Compter le nombre total d'items dans mon carnet
	public  int	getNbItemsFromXML(List<Element> listPersonnes)
	{
		int nbItems = 0;
		for (int counterPersonnes = 1; counterPersonnes < listPersonnes.size(); counterPersonnes++) {
			Element items = (Element)listPersonnes.get(counterPersonnes).getChild("items");
			nbItems = nbItems + items.getChildren("item").size();
		}
		return (nbItems);
	}
	//Action de redirection vers la fenêtre d'accueil
	public  class   WindowPrincipal implements   ActionListener
	{
		public  void    actionPerformed(ActionEvent e)
		{
			displayFrame.setVisible(false);
			new WindowHOME();
		}
	}
	//Action de redirection vers la fenêtre contenant les informations de la personne selectionnée (cliquer sur la personne)
	public  class   WindowUserInfos implements   ActionListener
	{
		public  void    actionPerformed(ActionEvent e)
		{
			displayFrame.setVisible(false);
			new WindowDISPLAYUserInfos(listItems, ((JButton) e.getSource()).getActionCommand());
		}
	}
}

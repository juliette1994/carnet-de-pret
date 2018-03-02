package projet;

import java.util.List;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.FileOutputStream;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.JDOMException;
import org.jdom2.output.XMLOutputter;
import org.jdom2.output.Format;

public class METHODEAddItemToUserToXML {
	private JFrame addFrame = new JFrame("Ajouter un item");
	private String errorMessage;
	private String successMessage;
	public void checkXML(String nomUser, String prenomUser, String itemNom, String itemType, String itemDate) {
		String filename = "src/projet/mon_carnet_de_pret.xml";
		File file = new File(filename);
		if (!file.exists()) {
			METHODECheckXMLInexistant checker = new METHODECheckXMLInexistant();
			checker.CheckIfFileInexistant(file, filename);
		}
		updateXMLAfterAdd(file, filename, nomUser, prenomUser, itemNom, itemType, itemDate);
	}

	public  void updateXMLAfterAdd(File xmlFile, String filename, String nomUser, String prenomUser, String itemNom, String itemType, String itemDate) {
		try {
			SAXBuilder builder = new SAXBuilder();
			Document document = (Document)builder.build(xmlFile);
			document = addItemToPersonneToCarnet(builder, nomUser, prenomUser, itemDate, itemNom, itemType, xmlFile, document);
			XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
			xmlOutputter.output(document, new FileOutputStream(filename));
		}
		catch (IOException ioException) {
				System.out.println(ioException.getMessage());
		}
		catch (JDOMException jdomexException) {
			System.out.println(jdomexException.getMessage());
		}
	}

	public Document addItemToPersonneToCarnet(SAXBuilder builder, String userNom, String userPrenom, String userItemDate, String userItemNom, String userItemType, File xmlFile, Document document) {
		Element carnet = document.getRootElement();
		List<Element> listPersonnes = carnet.getChildren("personne");
		Boolean personExists = false;
		int counterPersonnes = 0;
		while (counterPersonnes < listPersonnes.size() && !personExists) {
			Element personne = (Element)listPersonnes.get(counterPersonnes);
			String nom = personne.getChild("nom").getText();
			String prenom = personne.getChild("prenom").getText();
			if (nom.equals(userNom) && prenom.equals(userPrenom)) {
				successMessage = "<html>On a ajouté l'item suivant à " + userNom + " " + userPrenom + ":<br><br>Nom de l'item ajouté:		" + userItemNom + "<br>Type de l'item ajouté:		" + userItemType + "<br>Date de l'ajout de l'item:		" + userItemDate + "</html>";
				new WindowSUCCESS(successMessage);
				addNewItemToPerson(personne.getChild("items"), userItemDate, userItemNom, userItemType);
				personExists = true;
			}
			counterPersonnes = counterPersonnes + 1;
		}
		if (!personExists) {
			errorMessage = "<html>" + userNom + " " + userPrenom + " n'existe pas! Veuillez vérifier votre carnet.</html>";
			new WindowERRORRedirectionToHOME(errorMessage);
		}
		return document;
	}

	public void addNewItemToPerson(Element items, String userItemDate, String userItemNom, String userItemType) {
		Element item = new Element("item");

		Element itemNom = new Element("nomObjet");
		itemNom.setText(userItemNom);
		item.addContent(itemNom);

		Element itemType = new Element("typeObjet");
		itemType.setText(userItemType);
		item.addContent(itemType);

		Element itemDate = new Element("dateObjet");
		itemDate.setText(userItemDate);
		item.addContent(itemDate);

		items.addContent(item);
	}

	public  class   WindowPrincipal implements   ActionListener
	{
		public  void    actionPerformed(ActionEvent e)
		{
			addFrame.setVisible(false);
			new WindowHOME();
		}
	}
}

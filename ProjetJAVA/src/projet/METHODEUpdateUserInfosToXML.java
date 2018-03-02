package projet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import javax.swing.JTextField;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class METHODEUpdateUserInfosToXML {
	private String errorMessage;
	private String successMessage;
	public Boolean UpdateUserInfosToXML(String filename, Element personne, List<JTextField> listTextFields) {
		Boolean emptyFieldsExists = checkListForEmptyTextFields(listTextFields);
		if (emptyFieldsExists) {
			errorMessage = "<html>Vous avez un ou plusieurs champs vides, veuillez les remplir avant de les valider!</html>";
			new WindowERRORRemainOnSamePage(errorMessage);
			return (false);
		}
		else {
			updateXMLAfterDelete(new File(filename), filename, personne.getChild("nom").getText(), personne.getChild("prenom").getText());
			createNewPersonWithInfosOldOne(listTextFields, filename, personne);
			successMessage = "<html>Les informations de " + personne.getChild("prenom").getText() + " " + personne.getChild("nom").getText() + " ont bien été modifiées!</html>";
			new WindowSUCCESS(successMessage);
		}
		return (true);
	}

	public Boolean checkListForEmptyTextFields(List<JTextField> listTextFields) {
		for (int counterTextFields = 0; counterTextFields < listTextFields.size(); counterTextFields++) {
			if (listTextFields.get(counterTextFields).getText().equals("")) {
				return (true);
			}
		}
		return (false);
	}

	public void createNewPersonWithInfosOldOne(List<JTextField> listTextFields, String filename, Element person) {
		SAXBuilder builder = new SAXBuilder();
		File xmlFile = new File(filename);
		try {
			Document document = (Document)builder.build(xmlFile);
			Element carnet = document.getRootElement();
			Element personne = new Element("personne");
			carnet.addContent(personne);

			Element nom = new Element("nom");
			nom.setText(listTextFields.get(0).getText());
			personne.addContent(nom);

			Element prenom = new Element("prenom");
			prenom.setText(listTextFields.get(1).getText());
			personne.addContent(prenom);

			List<Element> listItems = person.getChild("items").getChildren("item");
			Element items = new Element("items");
			int counterItemsFromPerson = 0;
			for (int counterItems = 2; counterItems < listTextFields.size(); counterItems = counterItems + 2) {
				Element item = new Element("item");
				Element itemNom = new Element("nomObjet");
				itemNom.setText(listTextFields.get(counterItems).getText());
				item.addContent(itemNom);
				
				Element itemType = new Element("typeObjet");
				itemType.setText(listTextFields.get(counterItems + 1).getText());
				item.addContent(itemType);
				String itemDateFromPerson = listItems.get(counterItemsFromPerson).getChild("dateObjet").getText();
				Element itemDate = new Element("dateObjet");
				itemDate.setText(itemDateFromPerson);
				item.addContent(itemDate);

				items.addContent(item);
				counterItemsFromPerson = counterItemsFromPerson + 1;
			}
			personne.addContent(items);
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

	public  void updateXMLAfterDelete(File xmlFile, String filename, String nomUser, String prenomUser) {
		try {
			SAXBuilder builder = new SAXBuilder();
			Document document = (Document)builder.build(xmlFile);
			document = deletePersonneFromCarnet(builder, nomUser, prenomUser, xmlFile, document);
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

	public Document deletePersonneFromCarnet(SAXBuilder builder, String userNom, String userPrenom, File xmlFile, Document document) {
		Element carnet = document.getRootElement();
		List<Element> listPersonnes = carnet.getChildren("personne");
		int counterPersonnes = 1;
		while (counterPersonnes < listPersonnes.size()) {
			Element personne = (Element)listPersonnes.get(counterPersonnes);
			String nom = personne.getChild("nom").getText();
			String prenom = personne.getChild("prenom").getText();
			if (nom.equals(userNom) && prenom.equals(userPrenom)) {
				personne.getParent().removeContent(personne);
			}
			counterPersonnes = counterPersonnes + 1;
		}
		return document;
	}
}
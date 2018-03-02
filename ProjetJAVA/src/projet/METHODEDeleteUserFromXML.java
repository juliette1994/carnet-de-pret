package projet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class METHODEDeleteUserFromXML {
	private String errorMessage;
	private String successMessage;
	public void checkXMLExists(String nomUser, String prenomUser) {
		String filename = "src/projet/mon_carnet_de_pret.xml";
		File file = new File(filename);
		if (!file.exists()) {
			METHODECheckXMLInexistant checker = new METHODECheckXMLInexistant();
			checker.CheckIfFileInexistant(file, filename);
		}
		else {
				updateXMLAfterDelete(file, filename, nomUser, prenomUser);
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
		Boolean personExists = false;
		int counterPersonnes = 0;
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		while (counterPersonnes < listPersonnes.size() && !personExists) {
			Element personne = (Element)listPersonnes.get(counterPersonnes);
			String nom = personne.getChild("nom").getText();
			String prenom = personne.getChild("prenom").getText();
			if (nom.equals(userNom) && prenom.equals(userPrenom)) {
				personne.getParent().removeContent(personne);
				successMessage = "<html>" + prenom + " " + nom + " a bien été supprimé de votre carnet!</html>";
				new WindowSUCCESS(successMessage);
				personExists = true;
			}
			counterPersonnes = counterPersonnes + 1;
		}
		if (!personExists) {
			errorMessage = "<html>On ne peut pas supprimer cette personne, étant donné qu'elle n'existe pas dans votre carnet!</html>";
			new WindowERRORRedirectionToHOME(errorMessage);
		}
		return document;
	}
}

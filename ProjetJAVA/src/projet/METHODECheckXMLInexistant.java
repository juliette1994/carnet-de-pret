package projet;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class METHODECheckXMLInexistant {
	private String errorMessage;
	public void CheckIfFileInexistant(File file, String filename) {
		errorMessage = "<html>Votre fichier n'existe pas!<br>Nous allons le créer et y ajouter une personne DEFAULT.</html>";
		new WindowERRORRedirectionToHOME(errorMessage);
		try {
			file.createNewFile();
			Element carnet = new Element("carnet");
			Document document = new Document(carnet);
			Element personne = new Element("personne");
			Element nom = new Element("nom");
			nom.setText("DEFAULT");
			personne.addContent(nom);
			Element prenom = new Element("prenom");
			prenom.setText("DEFAULT");
			personne.addContent(prenom);
			Element items = new Element("items");
			personne.addContent(items);
			document.getRootElement().addContent(personne);
			
			XMLOutputter xmlOutput = new XMLOutputter();
			xmlOutput.setFormat(Format.getPrettyFormat());
			FileWriter writer = new FileWriter(filename);
			xmlOutput.output(document, writer);
			writer.close();
		}
		catch (IOException exception) {
			System.out.println(exception.getMessage());
		}
	}
}

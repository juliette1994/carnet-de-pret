package projet;

public class WindowDELETE {
	public WindowDELETE(String nom, String prenom) {
		METHODEDeleteUserFromXML deleter = new METHODEDeleteUserFromXML();
		deleter.checkXMLExists(nom, prenom);
	}
}

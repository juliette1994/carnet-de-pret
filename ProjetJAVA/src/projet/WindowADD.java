package projet;

public class WindowADD {
	public WindowADD(String nom, String prenom, String itemNom, String itemType, String itemDate) {
		METHODEAddUserToXML adder = new METHODEAddUserToXML();
		adder.checkXML(nom, prenom, itemNom, itemType, itemDate);
	}
}
package projet;

public	class	WindowDISPLAY {
	public	WindowDISPLAY() {
		//Redirection à la méthode display les personnes de mon carnet
		METHODEDisplayUsersFromXML displayer = new METHODEDisplayUsersFromXML();
		displayer.checkXML();
	}
}
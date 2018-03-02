package projet;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class WindowDISPLAYUserInfos {
	private JFrame displayFrame;
	
	public WindowDISPLAYUserInfos(String[][] listItems, String btnName) {
		displayInfosItems(listItems, btnName, nbItemsFromUser(listItems, btnName));
	}
	
	public void displayInfosItems(String[][] listItems, String btnName, int nbItems) {
		//Changer la couleur du background en couleur SAGE
		Color Sage = Color.decode("#DBDFC3");
		displayFrame = new JFrame("Items de " + btnName);
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		JPanel txtPanel = new JPanel();

		txtPanel.setBackground(Sage);
		String itemsUser = "<html><br><br>" + btnName + " possède " + nbItems + " items.<br><br>";
		String itemsUserEND = "</html>";
		int numeroItem = 1;
		for (int counterItem = 0; counterItem < listItems.length; counterItem++) {
			if (btnName.equals(listItems[counterItem][0] + " " + listItems[counterItem][1])) {
				itemsUser = itemsUser + "<p>" + numeroItem + ")	Nom de l'item: " + listItems[counterItem][2] + "<br><br>   Type de l'item: " + listItems[counterItem][4] + "<br><br>   Date de l'ajout de l'item: " + listItems[counterItem][3] + "</p><br>";
				numeroItem = numeroItem + 1;
			}
		}
		itemsUser = itemsUser + itemsUserEND;
		JLabel fileExistsMessage = new JLabel(itemsUser);
		fileExistsMessage.setAlignmentX(Component.CENTER_ALIGNMENT);
		txtPanel.add(fileExistsMessage);
		panel.add(txtPanel);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(Sage);
		JButton menu = new JButton("Menu");
		menu.setActionCommand("Menu");
		menu.addActionListener(new WindowPrincipal());
		menu.setAlignmentX(Component.CENTER_ALIGNMENT);
		buttonPanel.add(menu);
		panel.add(buttonPanel);
		panel.setBackground(Sage);
		displayFrame.add(panel);
		if (nbItems > 2)
			displayFrame.setSize(300, 180 + (100 * nbItems));
		else if (nbItems == 2)
			displayFrame.setSize(300, 180 * nbItems);
		else
			displayFrame.setSize(300, 280);
		displayFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		displayFrame.setVisible(true);
	}
	
	public  int    nbItemsFromUser(String[][] listItems, String btnName)
    {
		int nbItems = 0;
		for (int counter = 0; counter < listItems.length; counter++) {
			if (btnName.equals(listItems[counter][0] + " " + listItems[counter][1]))
				nbItems = nbItems + 1;
		}
		return (nbItems);
    }
	
	public  class   WindowPrincipal implements   ActionListener
    {
        public  void    actionPerformed(ActionEvent e)
        {
        	displayFrame.setVisible(false);
        	new WindowDISPLAY();
        }
    }
}

package projet;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class WindowERRORRemainOnSamePage {
	private JFrame errorFrame = new JFrame("Message d'erreur");
	public WindowERRORRemainOnSamePage(String errorMessage) {
		UIManager.put("OptionPane.background", Color.white);
		UIManager.put("Panel.background", Color.white);
		UIManager.put("OptionPane.messageForeground", Color.black);
		JOptionPane.showMessageDialog(errorFrame,
				errorMessage,
				"Message d'erreur",
				JOptionPane.ERROR_MESSAGE);
	}
}

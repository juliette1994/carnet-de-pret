package projet;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class WindowSUCCESS {
	private JFrame successFrame = new JFrame("Message de réussite");
	public WindowSUCCESS(String successMessage) {
		UIManager.put("OptionPane.background", Color.white);
		UIManager.put("Panel.background", Color.white);
		UIManager.put("OptionPane.messageForeground", Color.black);
		JOptionPane.showMessageDialog(successFrame,
				successMessage,
				"Message de réussite",
				JOptionPane.PLAIN_MESSAGE);
		new WindowHOME();
	}
}

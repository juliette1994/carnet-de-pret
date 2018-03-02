package projet;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public	class	WindowHOME extends JFrame 
{
	//In order to help you serialize exceptions correctly
	//private static final long serialVersionUID = 1L;
	//Boutons du menus de démarrage
	private	JButton display;
	private	JButton add;
	private	JButton addItem;
	private JButton delete;
	private JButton deleteItem;
	private JButton update;
	//Fenêtre d'accueil
	public	WindowHOME()
	{
		//Titre de la fenêtre d'accueil
		super("PAGE D'ACCUEIL");
		//Panel est un container class qui apporte un espace, où l'on peut ajouter des composants
		JPanel panel = new JPanel();
		//Permet de fermer la fenêtre lorsque l'on clique sur "X"
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Permet de mettre une bonne dimension à la fenêtre
		setSize(500, 195);
		//Swing class qui permet de placer selon l'axe des ordonnées, les composants suivants
		BoxLayout layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
		panel.setLayout(layout);
		//bouton DISPLAY
		display = new JButton("Afficher les personnes de mon carnet");
		display.setAlignmentX(Component.CENTER_ALIGNMENT);
		display.addActionListener(new WindowDisplay());
		panel.add(display);
		//bouton ADD
		add = new JButton("Ajouter une nouvelle personne à mon carnet");
		add.setAlignmentX(Component.CENTER_ALIGNMENT);
		add.addActionListener(new WindowAdd());
		panel.add(add);
		//bouton ADDITEM
		addItem = new JButton("Ajouter un item à une personne de mon carnet");
		addItem.setAlignmentX(Component.CENTER_ALIGNMENT);
		addItem.addActionListener(new WindowAdditems());
		panel.add(addItem);
		//bouton DELETE
		delete = new JButton("Supprimer une personne de mon carnet");
		delete.setAlignmentX(Component.CENTER_ALIGNMENT);
		delete.addActionListener(new WindowDelete());
		panel.add(delete,Component.CENTER_ALIGNMENT);
		//bouton DELETEITEM
		deleteItem = new JButton("Supprimer un ou plusieurs items d'une personne de mon carnet");
		deleteItem.setAlignmentX(Component.CENTER_ALIGNMENT);
		deleteItem.addActionListener(new WindowDeleteItems());
		panel.add(deleteItem,Component.CENTER_ALIGNMENT);
		//bouton UPDATE
		update = new JButton("Mettre à jour les informations d'une personne");
		update.setAlignmentX(Component.CENTER_ALIGNMENT);
		update.addActionListener(new WindowUpdate());
		panel.add(update,Component.CENTER_ALIGNMENT);
		//Place le component dans le JFrame
		setContentPane(panel);
		//Changer la couleur du background en couleur LAKE
		Color Lake = Color.decode("#1A86A8");
		getContentPane().setBackground(Lake);
		//Rend visible la fenêtre
		setVisible(true);
	}
	//Redirection à la fenêtre Display les personnes de mon carnet
	public	class	WindowDisplay implements   ActionListener
	{
		public	void	actionPerformed(ActionEvent e)
		{
			dispose();
			new WindowDISPLAY();
		}
	}
	//Redirection à la fenêtre Add formulaire pour ajouter une personne dans mon carnet
		public  class   WindowAdditems implements   ActionListener
		{
			public  void    actionPerformed(ActionEvent e)
			{
				dispose();
				new WindowFORMAddItemsWho();
			}
		}
	//Redirection à la fenêtre Add formulaire pour ajouter une personne dans mon carnet
	public  class   WindowAdd implements   ActionListener
	{
		public  void    actionPerformed(ActionEvent e)
		{
			dispose();
			new WindowFORMAdd();
		}
	}
	//Redirection à la fenêtre Delete formulaire pour supprimer un ou plusieurs items de mon carnet
		public  class   WindowDeleteItems implements   ActionListener
		{
			public  void    actionPerformed(ActionEvent e)
			{
				dispose();
				new WindowFORMDeleteItems();
			}
		}
	//Redirection à la fenêtre Delete formulaire pour supprimer une personne de mon carnet
	public  class   WindowDelete implements   ActionListener
	{
		public  void    actionPerformed(ActionEvent e)
		{
			dispose();
			new WindowFORMDelete();
		}
	}
	//Redirection à la fenêtre Update formulaire pour mettre à jour les informations d'une personne de mon carnet
	public  class   WindowUpdate implements   ActionListener
	{
		public  void    actionPerformed(ActionEvent e)
		{
			dispose();
			new WindowFORMUpdateWho();
		}
	}
	//Le début de l'application, lancement de la fenêtre d'acccueil
	public  static  void    main(String args[])
	{
		new WindowHOME();
	}
}

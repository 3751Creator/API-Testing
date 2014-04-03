package fr.updater.main;

import fr.update.util.FrameComponent;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * User: Alexandre
 */
public class Frame extends FrameComponent {

	public static JButton   Button_MAJ = new JButton("Mettre à jour"),
							Button_Rafraichir = new JButton("Rafraichir"),
							Button_Redefinir = new JButton("Redéfinir le fichier mods");

	public static JTextPane TextPane_List = new JTextPane();

	public static JScrollPane ScrollPane_List = new JScrollPane(TextPane_List);

	private static JLabel Label_Couleurs = new JLabel("<html><font color=\"orange\">Mise à jour</font>    " +
															"<font color=\"green\">Ajout</font>    " +
															"<font color=\"red\">Supression</font>    " +
															"<font color=\"blue\">Config</font></html>");

	public static JProgressBar ProgressBar_Download = new JProgressBar();

	private void Button(){
		addJButton(Button_MAJ, 5, 5, 205, 0, null, false, true);
		addJButton(Button_Rafraichir, Button_MAJ.getWidth() + 10, 5, 205, 0, null, false, true);
		addJButton(Button_Redefinir, 5, Button_MAJ.getHeight() + 10, 415, 0, null, false, true);
	}

	private void TextPane(){
		addTextPane(TextPane_List, 5, 5+Button_MAJ.getHeight()+5+Button_Redefinir.getHeight()+5, 415, 325, ScrollPane_List);
		Console.setName(TextPane_List);
		TextPane_List.setEditable(false);
	}

	private void Label(){
		addLabel(Label_Couleurs, 113, 390, 0, 0, null);
	}

	private void ProgressBar(){
		addProgressBar(ProgressBar_Download, 0, 408, 424, 0, "Pourcentage de téléchargement", 0, 0, 100);
	}

	private void init(){
		Button();
		TextPane();
		Label();
		ProgressBar();
	}

	public Frame(){
		setTitle("Server Mod Updater  v." + Main.Version());
		setLayout(null);

		URL url = ClassLoader.getSystemResource("logo.png");
		Toolkit kit = Toolkit.getDefaultToolkit();
		Image img = kit.createImage(url);
		setIconImage(img);

		init();
	}
}

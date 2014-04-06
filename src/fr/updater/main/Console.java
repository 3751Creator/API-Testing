package fr.updater.main;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;

/**
 * User: Alexandre
 *
 * Class pour sortir des types de messages différent dans la console.
 */

/*
	TODO: do as [Origin error] MSG
	 so setFoobar(String A, String B)
 */

public class Console {

	private static StyledDocument Text = null;

	/**
	 * @param Name Gets the name of the JTextPane to be able to use the Console features.
	 */
	public static void setName(JTextPane Name){
		Text = Name.getStyledDocument();
	}

	private static void ConsoleError(){
		if(Text == null){
			//System.err.println("[Console API] No Name set for the JTextPane\n[Console API] Please do Console.setName(JTextPane);");
		}
	}

	private static SimpleAttributeSet Colors = new SimpleAttributeSet();


	public static enum Level {
		CLASSIC,
		MAJ,
		AJOUT,
		SUPRESSION,
		CONFIG,
		NOMAJ;
	}

	public static void add(Level level,String Message){
		switch(level){
			case CLASSIC:
				try{
					StyleConstants.setForeground(Colors, Color.BLACK);
					Text.insertString(Text.getLength(), "\n\t- Mise à jour # " + Message + " -\n\n", Colors);
				}catch(Exception e) { ConsoleError(); }
				break;
			case MAJ:
				try{
					Color ORANGE = new Color(252, 156, 51);
					StyleConstants.setForeground(Colors, ORANGE);
					Text.insertString(Text.getLength(), Message + "\n", Colors);
				}catch(Exception e) { ConsoleError(); }
				break;
			case AJOUT:
				Color GREEN = new Color(58, 157, 52);
				StyleConstants.setForeground(Colors, GREEN);
				try{
					Text.insertString(Text.getLength(), Message + "\n", Colors);
				}catch(Exception e) { ConsoleError(); }
				break;
			case SUPRESSION:
				Color RED = new Color(183, 19, 0);
				StyleConstants.setForeground(Colors, RED);
				try{
					Text.insertString(Text.getLength(), Message + "\n", Colors);
				}catch(Exception e) { ConsoleError(); }
				break;
			case CONFIG:
				StyleConstants.setForeground(Colors, Color.BLUE);
				try{
					Text.insertString(Text.getLength(), Message + "\n", Colors);
				}catch(Exception e) { ConsoleError(); }
				break;
			case NOMAJ:
				StyleConstants.setForeground(Colors, Color.BLACK);
				try{
					Text.insertString(Text.getLength(), "\t\t" + Message + "\n", Colors);
				}catch(Exception e) { ConsoleError(); }
				break;
		}
	}

	/**
	 * @param R Red Color. 0 <= R <= 255  |  [0;255]
	 * @param G Green Color. 0 <= R <= 255  |  [0;255]
	 * @param B Blue Color. 0 <= R <= 255  |  [0;255]
	 * @param level Level of the message
	 * @param Message Adds a custom message with a specific givin color to the console.
	 */
	public static void addCustom(String level, int R, int G, int B, String Message){
		try{
			Color CUSTOM = new Color(R, G, B);
			StyleConstants.setForeground(Colors, CUSTOM);
		}catch(Exception e){
			System.err.println("RBG colors must be in between 0 and 255");
		}
		try{
			Text.insertString(0, "[" + level + "] " + Message + "\n", Colors);
		}catch(Exception e) { ConsoleError(); }
	}
}
package fr.updater.main;

import javax.swing.*;

/**
 * User: Alexandre
 */
public class Main {
	public static String Version(){
		return "2.0";
	}

	public static void main(String[] args){

		try{
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		}catch (Exception e){
			System.out.println("Unable to load Windows look and feel");
		}

		Start();

		try{
			new Check();
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	public static void Start(){
		Frame gui = new Frame();

		gui.setSize(430, 450);
		gui.setLocationRelativeTo(null);
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.setVisible(true);
		gui.setResizable(false);
	}
}

package fr.update.util;

import fr.updater.main.Check;
import fr.updater.main.Download;
import fr.updater.main.Frame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * User: Alexandre
 */
public class ActionFrame extends JFrame implements ActionListener{

	Check check = new Check();

	@Override
	public void actionPerformed(ActionEvent e){
		Object src = e.getSource();
		if(src == Frame.Button_Rafraichir){
			Frame.TextPane_List.setText("");
			try{
				new Check();
			}catch(Exception e1){
				e1.printStackTrace();
			}
		}
		if(src == Frame.Button_Redefinir){

			check.ModFile();
		}
		if(src == Frame.Button_MAJ){
			Thread t1 = new Thread(new Download());

			t1.start();
		}
	}
}

package fr.testapi.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * User: Alexandre
 */
public class ActionFrame implements ActionListener{

	public void actionPerformed(ActionEvent e){
		Object src = e.getSource();
		if(src == Frame.JButton_1){
			System.out.println("yo BUT 1");
		}
		if(src == Frame.JButton_2){
			System.out.println("yo BUT 2");
		}
		if(src == Frame.JButton_3){
			System.out.println("yo BUT 3");
		}
		if(src == Frame.JButton_4){
			System.out.println("yo BUT 4");
		}
	}
}

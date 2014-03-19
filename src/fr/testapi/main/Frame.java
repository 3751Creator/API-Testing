package fr.testapi.main;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.util.ArrayList;

/**
 * User: Alexandre
 */

public class Frame extends FrameComponent{

	public static JButton  JButton_1 = new JButton("fhtfh"),
					JButton_2 = new JButton("fhtfh 2"),
					JButton_3 = new JButton("fhtfh 3"),
					JButton_4 = new JButton("fhtfh 4");


	private void init(){
		addJButton(JButton_1, null, null, false, true, true, true);
		addJButton(JButton_2, "width 200", null, false, true, true, true);
		addJButton(JButton_3, "wrap", "YO", false, true, true, true);
		addJButton(JButton_4, "h 50, w 150", null, false, true, true, true);

	}

	public Frame(){
		setLayout(new MigLayout());
		setTitle("API TESTING");

		init();
	}
}
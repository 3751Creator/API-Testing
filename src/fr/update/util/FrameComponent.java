package fr.update.util;

import javax.swing.*;
import java.awt.*;

/**
 * User: Alexandre
 */
public abstract class FrameComponent extends JFrame {

	private ActionFrame Action = new ActionFrame();

	/**
	 * Adds JButton
	 *
	 * @param ButtonName        Butotn Object
	 * @param args              Arguments for
	 * @param ToolTip           Tooltip when hover
	 * @param FocusedPainted
	 * @param Opaque
	 * @param ContentAreaFilled
	 * @param BorderPainted
	 */
	public void addJButton(JButton ButtonName, String args, String ToolTip, boolean FocusedPainted, boolean Opaque, boolean ContentAreaFilled, boolean BorderPainted){

		ButtonName.addActionListener(Action);

		ButtonName.setToolTipText(ToolTip);
		ButtonName.setFocusPainted(FocusedPainted);
		ButtonName.setOpaque(Opaque);
		ButtonName.setContentAreaFilled(ContentAreaFilled);
		ButtonName.setBorderPainted(BorderPainted);

		add(ButtonName, args);

		//TODO add custom Layout add   // TODO ADD PANEL
	}

	/**
	 *
	 * @param ButtonName
	 * @param args
	 * @param ToolTip
	 * @param FocusedPainted
	 * @param Opaque
	 */
	public void addJButton(JButton ButtonName, String args, String ToolTip, boolean FocusedPainted, boolean Opaque){

		ButtonName.addActionListener(Action);

		ButtonName.setToolTipText(ToolTip);
		ButtonName.setFocusPainted(FocusedPainted);
		ButtonName.setOpaque(Opaque);

		add(ButtonName, args);

		//TODO add custom Layout add
	}

	/**
	 *
	 * @param ButtonName
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param ToolTip
	 * @param FocusedPainted
	 * @param Opaque
	 */
	public void addJButton(JButton ButtonName, int x, int y, int width, int height, String ToolTip, boolean FocusedPainted, boolean Opaque){

		Dimension Button_Size = ButtonName.getPreferredSize();
		if(width == 0){
			ButtonName.setBounds(x, y, Button_Size.width, height);
		}if(height == 0){
			ButtonName.setBounds(x, y, width, Button_Size.height);
		}if(width == 0 && height == 0){
			ButtonName.setBounds(x, y, Button_Size.width, Button_Size.height);
		}if(width != 0 && height != 0){
			ButtonName.setBounds(x, y, width, height);
		}
		ButtonName.addActionListener(Action);

		ButtonName.setOpaque(Opaque);
		ButtonName.setToolTipText(ToolTip);
		ButtonName.setFocusPainted(FocusedPainted);

		add(ButtonName);

		//TODO add custom Layout add
	}

	/**
	 *
	 * @param ButtonName
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param ToolTip
	 * @param FocusedPainted
	 * @param Opaque
	 * @param ContentAreaFilled
	 * @param BorderPainted
	 */
	public void addJButton(JButton ButtonName, int x, int y, int width, int height, String ToolTip, boolean FocusedPainted, boolean Opaque, boolean ContentAreaFilled, boolean BorderPainted){

		Dimension Button_Size = ButtonName.getPreferredSize();
		if(width == 0){
			ButtonName.setBounds(x, y, Button_Size.width, height);
		}if(height == 0){
			ButtonName.setBounds(x, y, width, Button_Size.height);
		}if(width == 0 && height == 0){
			ButtonName.setBounds(x, y, Button_Size.width, Button_Size.height);
		}if(width != 0 && height != 0){
			ButtonName.setBounds(x, y, width, height);
		}
		ButtonName.addActionListener(Action);

		ButtonName.setOpaque(Opaque);
		ButtonName.setToolTipText(ToolTip);
		ButtonName.setFocusPainted(FocusedPainted);

		add(ButtonName);

		//TODO add custom Layout add
	}

	/**---------------------------------------------------------------------------------------------------------------------------------------------*/

	/**
	 * Add JLabel
	 *
	 * @param LabelName
	 * @param args
	 * @param ToolTip
	 */
	public void addLabel(JLabel LabelName, String args, String ToolTip){

		LabelName.setToolTipText(ToolTip);

		add(LabelName, args);
	}

	public void addLabel(JLabel LabelName, int x, int y, int width, int height, String ToolTip){

		Dimension LabelName_Size = LabelName.getPreferredSize();
		if(width == 0){
			LabelName.setBounds(x, y, LabelName_Size.width, height);
		}if(height == 0){
			LabelName.setBounds(x, y, width, LabelName_Size.height);
		}if(width == 0 && height == 0){
			LabelName.setBounds(x, y, LabelName_Size.width, LabelName_Size.height);
		}if(width != 0 && height != 0){
			LabelName.setBounds(x, y, width, height);
		}

		LabelName.setToolTipText(ToolTip);

		add(LabelName);
	}

	/**---------------------------------------------------------------------------------------------------------------------------------------------*/

	public void addTextArea(JTextArea TextAreaName, int x, int y, int width, int height, String ToolTip, boolean Enabled, boolean Editable){

		Dimension TextAreaName_Size = TextAreaName.getPreferredSize();
		if(width == 0){
			TextAreaName.setBounds(x, y, TextAreaName_Size.width, height);
		}if(height == 0){
			TextAreaName.setBounds(x, y, width, TextAreaName_Size.height);
		}if(width == 0 && height == 0){
			TextAreaName.setBounds(x, y, TextAreaName_Size.width, TextAreaName_Size.height);
		}if(width != 0 && height != 0){
			TextAreaName.setBounds(x, y, width, height);
		}

		TextAreaName.setLineWrap(true);
		TextAreaName.setWrapStyleWord(false);
		TextAreaName.setFont(new Font("Arial", Font.PLAIN, 12));
		TextAreaName.setEditable(Editable);
		TextAreaName.setEnabled(Enabled);
		TextAreaName.setToolTipText(ToolTip);

		add(TextAreaName);

	}

	public void addTextArea(JTextArea TextAreaName, int x, int y, int width, int height, boolean Enabled, boolean Editable, JScrollPane SrollPaneNane){

		Dimension SrollPaneNane_Size = SrollPaneNane.getPreferredSize();
		if(width == 0){
			SrollPaneNane.setBounds(x, y, SrollPaneNane_Size.width, height);
		}if(height == 0){
			SrollPaneNane.setBounds(x, y, width, SrollPaneNane_Size.height);
		}if(width == 0 && height == 0){
			SrollPaneNane.setBounds(x, y, SrollPaneNane_Size.width, SrollPaneNane_Size.height);
		}if(width != 0 && height != 0){
			SrollPaneNane.setBounds(x, y, width, height);
		}

		TextAreaName.setLineWrap(true);
		TextAreaName.setWrapStyleWord(false);
		TextAreaName.setFont(new Font("Arial", Font.PLAIN, 12));
		TextAreaName.setEditable(Editable);
		TextAreaName.setEnabled(Enabled);

		add(SrollPaneNane);
	}

	public void addTextPane(JTextPane TextPaneName, int x, int y, int width, int height, String ToolTip, boolean Enabled, boolean Editable){

		Dimension TextAreaName_Size = TextPaneName.getPreferredSize();
		if(width == 0){
			TextPaneName.setBounds(x, y, TextAreaName_Size.width, height);
		}if(height == 0){
			TextPaneName.setBounds(x, y, width, TextAreaName_Size.height);
		}if(width == 0 && height == 0){
			TextPaneName.setBounds(x, y, TextAreaName_Size.width, TextAreaName_Size.height);
		}if(width != 0 && height != 0){
			TextPaneName.setBounds(x, y, width, height);
		}

		TextPaneName.setFont(new Font("Arial", Font.PLAIN, 12));
		TextPaneName.setEditable(Editable);
		TextPaneName.setEnabled(Enabled);
		TextPaneName.setToolTipText(ToolTip);

		add(TextPaneName);

	}

	public void addTextPane(JTextPane TextAreaName, int x, int y, int width, int height, JScrollPane SrollPaneNane){

		Dimension SrollPaneNane_Size = SrollPaneNane.getPreferredSize();
		if(width == 0){
			SrollPaneNane.setBounds(x, y, SrollPaneNane_Size.width, height);
		}if(height == 0){
			SrollPaneNane.setBounds(x, y, width, SrollPaneNane_Size.height);
		}if(width == 0 && height == 0){
			SrollPaneNane.setBounds(x, y, SrollPaneNane_Size.width, SrollPaneNane_Size.height);
		}if(width != 0 && height != 0){
			SrollPaneNane.setBounds(x, y, width, height);
		}

	/*	TextPaneName.setFont(new Font("Arial", Font.PLAIN, 12));
		TextPaneName.setEditable(Editable);
		TextPaneName.setEnabled(Enabled);*/

		add(SrollPaneNane);
	}

	/**---------------------------------------------------------------------------------------------------------------------------------------------*/

	public void addProgressBar(JProgressBar ProgressBarName, int x, int y, int width, int height, String ToolTip, int FirstValue, int Start, int End){

		Dimension ProgressBarName_Size = ProgressBarName.getPreferredSize();
		if(width == 0){
			ProgressBarName.setBounds(x, y, ProgressBarName_Size.width, height);
		}if(height == 0){
			ProgressBarName.setBounds(x, y, width, ProgressBarName_Size.height);
		}if(width == 0 && height == 0){
			ProgressBarName.setBounds(x, y, ProgressBarName_Size.width, ProgressBarName_Size.height);
		}if(width != 0 && height != 0){
			ProgressBarName.setBounds(x, y, width, height);
		}

		ProgressBarName.setToolTipText(ToolTip);
		ProgressBarName.setValue(FirstValue);
		ProgressBarName.setMinimum(Start);
		ProgressBarName.setMaximum(End);

		add(ProgressBarName);
	}
}
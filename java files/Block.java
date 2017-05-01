package com.orestis.puzzle;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Block extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5861508087715175900L;
	private int position;
	private JLabel label;
	private Color color;
	private boolean isBlank;
	
	public Block(int position, JLabel label, Color color, boolean isBlank) {
		this.position = position;
		this.label = label;
		this.label.setFont(new Font("SansSerif", Font.BOLD, 76));
		this.add(label);
		this.color = color;
		this.setBackground(color);
		this.isBlank = isBlank;
		
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public JLabel getLabel() {
		return label;
	}

	public void setLabel(JLabel label) {
		this.label = label;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public boolean isBlank() {
		return isBlank;
	}

	public void setBlank(boolean isBlank) {
		this.isBlank = isBlank;
	}
	
}

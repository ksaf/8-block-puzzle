package com.orestis.puzzle;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Board extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5506668536977665918L;
	private ArrayList<Block> blocks;
	private int numOfMoves;
	private int size = 3;

	public Board(ArrayList<Block> blocks, int size) {
		this.blocks = blocks;
		this.size = size;
	}

	public int getNumOfMoves() {
		return numOfMoves;
	}

	public void setNumOfMoves(int numOfMoves) {
		this.numOfMoves = numOfMoves;
	}

	public ArrayList<Block> getBlocks() {
		return blocks;
	}

	public void setBlocks(ArrayList<Block> blocks) {
		this.blocks = blocks;
	}
		
	public void initBoard() {
		GridLayout grid = new GridLayout(size, size, 10, 10);
		this.setLayout(grid);
		this.setBackground(Color.black);
		for(Block b : blocks){
			this.add(b);
		}
		
	}
	
	public void moveBlock(int clickedPosition) {
		
		if(clickedPosition-1 >= 0){
			if(blocks.get(clickedPosition-1).isBlank()){
				if(clickedPosition % 3 != 0){
					swap2Blocks(clickedPosition, clickedPosition-1);
					numOfMoves++;
				}			
			}
		}
		if(clickedPosition+1 < blocks.size()){
			if(blocks.get(clickedPosition+1).isBlank()){
				if(clickedPosition % 3 != 2){
					swap2Blocks(clickedPosition, clickedPosition+1);
					numOfMoves++;
				}	
			}
		}
		if(clickedPosition-3 >= 0){
			if(blocks.get(clickedPosition-3).isBlank()){
				swap2Blocks(clickedPosition, clickedPosition-3);
				numOfMoves++;
			}
		}
		if(clickedPosition+3 < blocks.size()){
			if(blocks.get(clickedPosition+3).isBlank()){
				swap2Blocks(clickedPosition, clickedPosition+3);
				numOfMoves++;
			}
		}
		int victoryPoints = 0;
		for(int i=0; i<8; i++){
			if(blocks.get(i).getLabel().getText() != ""){
				if( Integer.parseInt(blocks.get(i).getLabel().getText()) == i+1 ){
					victoryPoints++;
				}
			}
		}
		if(victoryPoints==8){
			JOptionPane.showMessageDialog(null, "You won in "+ numOfMoves +" moves!");
			numOfMoves = 0;
		}
	}

	public void suffleBoard() {
		Random r1 = new Random();
		for (int x = 0; x < 100; x++) {
			int position1 = r1.nextInt(size*size);
			int position2 = r1.nextInt(size*size);
		
			swap2Blocks(position1, position2);
		}
		numOfMoves=0;
	}
	
	public void swap2Blocks(int position1, int position2) {
		Block tempBlock1 = blocks.get(position1);
		blocks.get(position1).setPosition(position2);
		
		Block tempBlock2 = blocks.get(position2);
		blocks.get(position2).setPosition(position1);				
		
		blocks.set(position1, tempBlock2);
		blocks.set(position2, tempBlock1);
		
		if(blocks.get(position1).isBlank() || blocks.get(position2).isBlank()){
			blocks.get(position2).setBlank(!blocks.get(position1).isBlank());
			blocks.get(position1).setBlank(!blocks.get(position2).isBlank());
		}
		
		for (Block b : blocks) {
			this.add(b);
		}
		validate();
	}

}

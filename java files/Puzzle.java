package com.orestis.puzzle;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Puzzle extends JFrame implements ActionListener, MouseListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4474668936816926890L;
	private ArrayList<Block> blocks;
	private int size;
	private Board board;
	private JButton newGame;
	private JPanel movesPanel;
	private JLabel numOfMoves;
	private String BackGroundmusicFile = "/marimba.wav";
	private String ButtonMusicFile = "/button-press.wav";
	private SoundPlayer background;
	private SoundPlayer buttonPressedSound;
	
	public Puzzle () {
		init();
		setSize(500,500);
		setVisible(true);
	}
	
	public void init() {
		
		background = new SoundPlayer(BackGroundmusicFile);
		buttonPressedSound = new SoundPlayer(ButtonMusicFile);
		background.play(true);
		
		//initialise blocks and board
		blocks = new ArrayList<Block>();
		size = 3;
		for(int i=1; i<size*size; i++){
			blocks.add(new Block(i-1,new JLabel(Integer.toString(i)), Color.pink, false));
			blocks.get(i-1).addMouseListener(this);
		}
		blocks.add(new Block(size*size,new JLabel(""), Color.black, true));
		blocks.get(8).addMouseListener(this);

		board = new Board(blocks,size);
		board.initBoard();
		
		//add new game button
		newGame = new JButton("New Game");
		newGame.addActionListener(this);
		JPanel newGamePanel = new JPanel();
		newGamePanel.add(newGame);
		
		//add moves counter
		movesPanel = new JPanel();
		JLabel moves = new JLabel("Moves : ");
		numOfMoves = new JLabel("0");
		movesPanel.add(moves);
		movesPanel.add(numOfMoves);
		
		//set the layout and put the panels in it
		BorderLayout bord = new BorderLayout();
		setLayout(bord);
		add(newGamePanel, BorderLayout.NORTH);
		add(board, BorderLayout.CENTER);
		add(movesPanel, BorderLayout.SOUTH);
		
			
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}


	public void mouseClicked(MouseEvent e) {
		Object source = e.getSource();
		//if a block is clicked
		int clickedPosition = blocks.indexOf(source);
		board.moveBlock(clickedPosition);
		buttonPressedSound.play(false);
		numOfMoves.setText(Integer.toString(board.getNumOfMoves()));
	}
	public void mouseEntered(MouseEvent e) {	
	}
	public void mouseExited(MouseEvent e) {
	}
	public void mousePressed(MouseEvent e) {
	}
	public void mouseReleased(MouseEvent e) {
	}

	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		//if new game is clicked
		if (source == newGame){
			board.suffleBoard();
			numOfMoves.setText(Integer.toString(board.getNumOfMoves()));
			background.stop();
			background.play(true);
		}
	}
	
	public static void main (String[] args){
		new Puzzle();
	}

}

package snakegame;

import java.awt.Color;

import javax.swing.JFrame;



public class Snakexe {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		SnakeGameplay panel = new SnakeGameplay();
		
		frame.setBounds(10, 10, 901, 700);
		frame.setBackground(Color.DARK_GRAY);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);

	}

}

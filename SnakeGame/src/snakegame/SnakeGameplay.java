package snakegame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.Timer;


import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class SnakeGameplay extends JPanel implements KeyListener, ActionListener	{
	
	
	private int[] snakeXlength = new int[750];
	private int[] snakeYlength = new int[750];
	
	private boolean left = false;
	private boolean right = false;
	private boolean up = false;
	private boolean down = false;
	
	private ImageIcon rightmouth;
	private ImageIcon upmouth;
	private ImageIcon downmouth;
	private ImageIcon leftmouth;
	
	private  int lenghthofsnake = 3; 
	
	private Timer timer;
	
	private int delay = 100;
	
	private int [] enemyXpos = {25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,
	                            55,550,575,600,625,650,675,700,725,750,775,800,825,850};
	
	private int [] enemyYpos = {75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,
										55,550,575,600,625};
	
	private ImageIcon enemyimage ;
	
	private Random random = new Random();

	private int xpos = random.nextInt(34);
	private int ypos = random.nextInt(23);
	
	private int score = 0;
	
	private int moves = 0;
	
	private ImageIcon snakeimage;
	
	private ImageIcon titleImage;
	
	public SnakeGameplay() {
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();
		
		
	}
	
	public void paint(Graphics g) {
		
		if(moves == 0 ) {
			
			snakeXlength[2] = 50;
			snakeXlength[1] = 75;
			snakeXlength[0] = 100;
			
			snakeYlength[2] = 100;
			snakeYlength[1] = 100;
			snakeYlength[0] = 100;
			
		}
		
		
		// draw title image border
		g.setColor(Color.white);
		g.drawRect(24, 10, 851, 55);
		
		
		// draw the title image 
		titleImage = new ImageIcon("snaketitle.jpg");
		titleImage.paintIcon(this, g, 25, 11);
		
		//draw border for gameplay
		g.setColor(Color.WHITE);
		g.drawRect(24, 74, 851, 577);
	
	
		// draw background for the Snakegameplay 
	
		g.setColor(Color.black);
		g.fillRect(25, 75, 850, 575);
	
		// desenhar a pontução 
		g.setColor(Color.WHITE);
		g.setFont(new Font("arial", Font.PLAIN, 14));
		g.drawString("Scores: "+score, 780 , 30);
		
		// desenhar o tamanho da snake
			g.setColor(Color.WHITE);
			g.setFont(new Font("arial", Font.PLAIN, 14));
			g.drawString("Tamanho: "+lenghthofsnake, 780 , 50);
		
		
		rightmouth = new ImageIcon("rightmouth.png");
		rightmouth.paintIcon(this, g, snakeXlength[0], snakeYlength[0]);
		
		
		// indice a especifica a cabeça da cobra 
		for(int a = 0; a< lenghthofsnake;  a ++ ) {
			
			
		//	adiciona imagem a cabeça da cobra seja ela para cima 
			// para baixo, lado  ...
			if(a == 0 && right) {

				rightmouth = new ImageIcon("rightmouth.png");
				rightmouth.paintIcon(this, g, snakeXlength[a], snakeYlength[a]);
			}
			if(a == 0 && left) {

				leftmouth = new ImageIcon("leftmouth.png");
				leftmouth.paintIcon(this, g, snakeXlength[a], snakeYlength[a]);
			}
			if(a == 0 && down) {

				downmouth = new ImageIcon("downmouth.png");
				downmouth.paintIcon(this, g, snakeXlength[a], snakeYlength[a]);
			}
			if(a == 0 && up) {

				upmouth = new ImageIcon("upmouth.png");
				upmouth.paintIcon(this, g, snakeXlength[a], snakeYlength[a]);
			}
			
			if(a != 0) {
				snakeimage = new ImageIcon("snakeimage.png");
				snakeimage.paintIcon(this, g, snakeXlength[a], snakeYlength[a]);
			
			}
			
		}
		
		//arquivos do enemy add image 
		enemyimage = new ImageIcon("enemy.png");
		
		// verifica se o enemyposition é o mesmo da cabeca da cobra 
		
		if((enemyXpos[xpos] == snakeXlength[0] &&
				enemyYpos[ypos] == snakeYlength[0])) {
			
			score++;
			lenghthofsnake++;
			xpos = random.nextInt(34);
			ypos = random.nextInt(23);
		
	
	}
	
		enemyimage.paintIcon(this, g, enemyXpos[xpos], enemyYpos[ypos]);
		
		
		/* checando a colisao do player  usando a cabeça como 
		 a colisao */
		
		for ( int b = 1; b < lenghthofsnake; b++) {
			
			if(snakeXlength[b] == snakeXlength[0] &&
					snakeYlength[b] == snakeYlength[0]) {
			
			// stopando comando para nao haver entradas	
				
				right = false;
				left = false;
				up = false;
				down = false;
				
		// escrevendo o game over 
				g.setColor(Color.white);
				g.setFont(new Font( "arial", Font.BOLD, 50));
				g.drawString("FIM DE JOGO !!!", 300 , 300);
		
			
				g.setFont(new Font( "arial", Font.BOLD, 20));
				g.drawString("Pressione espaço para recomeçar. ",
						300, 340);
			
			}
		}
		
	
		
		g.dispose();
	}

	// Acoes do player atraves de keys events 
	
	@Override
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			moves = 0; 
			score = 0;
			lenghthofsnake = 3;
			repaint();
			
		}
		
		// key event validando posições com booleanos
		// pode ser melhorado o if and else com switch
		// ## codigo tem que ser limpo ##
		if(e.getKeyCode() == KeyEvent.VK_D) {
			
			moves ++;
			
			right = true;
			
			if(!left) {
				right = true;
			
			}else {
				
				right = false;
				left = true;
				
			}
			
			up = false;
			down = false;
		}
		
		// tecla para a esquerda
if(e.getKeyCode() == KeyEvent.VK_A) {
			
			moves ++;
			
			left = true;
			
			if(!right) {
				left = true;
			
			}else {
				
				left = false;
				right = true;
				
			}
			
			up = false;
			down = false;
		}

  			// teclas up 
	if(e.getKeyCode() == KeyEvent.VK_W) {
	
	moves ++;
	
	up = true;
	
	if(!down) {
		up = true;
	
	}else {
		
		up = false;
		down  = true;
		
	}
	
	left = false;
	right = false;
}

		// teclas down 
	if(e.getKeyCode() == KeyEvent.VK_S) {
		
		moves ++;
		
		down = true;
		
		if(!up) {
			down = true;
		
		}else {
			
			down = false;
			up  = true;
			
		}
		
		left = false;
		right = false;
	}

	
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();
		
		if(right) {
			
			for(int r = lenghthofsnake -1; r >= 0; r-- ) {
			
				snakeYlength[r+1] = snakeYlength[r];
			}
			
			for(int r = lenghthofsnake;  r >= 0; r -- ) {
				
				if(r == 0) {
					snakeXlength[r] = snakeXlength[r] + 25 ;
					
				}else {
					snakeXlength[r] = snakeXlength[r -1];
				}
				if(snakeXlength[r] > 850) {
					
					snakeXlength[r] = 25;
				}
			}
			
			repaint();
		}
		
		if(left) {
					

			for(int r = lenghthofsnake -1; r >= 0; r-- ) {
			
				snakeYlength[r+1] = snakeYlength[r];
			}
			
			for(int r = lenghthofsnake;  r >= 0; r -- ) {
				
				if(r == 0) {
					snakeXlength[r] = snakeXlength[r] - 25 ;
					
				}else {
					snakeXlength[r] = snakeXlength[r -1];
				}
				if(snakeXlength[r] < 25) {
					
					snakeXlength[r] = 850;
				}
			}
			
			repaint();
			
			
		}
		
		if(up) {

			for(int r = lenghthofsnake -1; r >= 0; r-- ) {
			
				snakeXlength[r+1] = snakeXlength[r];
			}
			
			for(int r = lenghthofsnake;  r >= 0; r -- ) {
				
				if(r == 0) {
					snakeYlength[r] = snakeYlength[r] - 25 ;
					
				}else {
					snakeYlength[r] = snakeYlength[r -1];
				}
				if(snakeYlength[r] < 75) {
					
					snakeYlength[r] = 625;
				}
			}
			
			repaint();
			
		}
		
		if(down) {
			
			for(int r = lenghthofsnake -1; r >= 0; r-- ) {
				
				snakeXlength[r+1] = snakeXlength[r];
			}
			
			for(int r = lenghthofsnake;  r >= 0; r -- ) {
				
				if(r == 0) {
					snakeYlength[r] = snakeYlength[r] + 25 ;
					
				}else {
					snakeYlength[r] = snakeYlength[r -1];
				}
				if(snakeYlength[r] > 625) {
					
					snakeYlength[r] = 75;
				}
			}
			
			repaint();
		}
		
	}
}

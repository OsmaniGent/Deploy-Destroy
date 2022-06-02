//package main;
//
//import java.awt.*;
//import java.awt.event.MouseEvent;
//import setup.Manager;
//import setup.Window;
//import loja.*;
//
//public class PlayGame implements Manager {
//
//	public static final int WIDTH = 1200, HEIGHT = 600, FPS = 60, TPS = 60;
//	public static final String GAME_NAME = "DEPLOY DESTROY";
//	
//	BoardGame bg;
//	
//	PlayGame(){
//		bg = new BoardGame(WIDTH,HEIGHT);
//	}
//	
//	public static void main(String[] args) {
//		
//		Window wind = new Window(WIDTH+16, HEIGHT+39, FPS, TPS, GAME_NAME, new PlayGame());
//		
////		wind.getJFrame().add(e);
//	}
//
//	@Override
//	public void draw(Graphics g) {
//		
//		g.setColor(Color.WHITE);
//		g.fillRect(0,0,WIDTH,HEIGHT);
//		g.setColor(Color.BLACK);
//		
//		bg.draw(g);
////		g.drawLine(0,0,100,100);
//		
//	}
//
//	@Override
//	public void tick() {
//		bg.tick();
//	}
//
//	@Override
//	public void mouseDragged(MouseEvent e) {
//		
//		
//	}
//
//	@Override
//	public void mouseMoved(MouseEvent e) {
//		bg.callFunction(e.getX(), e.getY());
////		System.out.println(e.getX());
//		
//	}
//
//	@Override
//	public void mouseClicked(MouseEvent e) {
//		bg.callAnotherFunction(e.getX(), e.getY());
//		
//	}
//
//	@Override
//	public void mouseEntered(MouseEvent e) {
//		
////		System.out.println(e.getX());
//		
//	}
//
//	@Override
//	public void mouseExited(MouseEvent e) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void mousePressed(MouseEvent e) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void mouseReleased(MouseEvent e) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void keyPressed(int e) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void keyReleased(int e) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void keyTyped(int e) {
//		// TODO Auto-generated method stub
//		
//	}
//
//}
package main;

import java.awt.*;

import javax.swing.JPanel;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import setup.Manager;
import setup.Window;
import loja.*;

public class PlayGame extends JPanel implements Manager {

	public static final int WIDTH = 1200, HEIGHT = 600, FPS = 60, TPS = 60;
	public static final String GAME_NAME = "DEPLOY DESTROY";

	public int gameState;
	public final int titleState = 0;
	public final int playState = 1;
	public final int endState = 2;
	public final int instructionState = 3;
	private int winner;

	GameMenu gm;
	BoardGame bg;
	Thread gameThread;
	

	PlayGame() {
		winner = 0;
		gameState = titleState;
		bg = new BoardGame(WIDTH, HEIGHT);
		gm = new GameMenu();
	}

	public static void main(String[] args) {

		Window wind = new Window(WIDTH + 16, HEIGHT + 39, FPS, TPS, GAME_NAME, new PlayGame());

//		wind.getJFrame().add(e);
	}

	@Override
	public void draw(Graphics g) {

		g.setColor(Color.WHITE);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		g.setColor(Color.BLACK);
		if (gameState == titleState) {
			gm.drawStartMenu(g, 0);
		} 
		else if(gameState == endState) {
			gm.drawStartMenu(g, winner);
		}
		else if (gameState == playState) {
			bg.draw(g);
		}else if(gameState == instructionState){
			gm.drawInstruction(g);
		}

	}

	@Override
	public void tick() {
		if (gameState == 1) {
			this.winner = bg.tick();
			if(winner != -1)
				this.gameState = endState;
		}
				

	}

	@Override
	public void mouseDragged(MouseEvent e) {

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		bg.callFunction(e.getX(), e.getY());
		if (gameState == 0 || gameState == endState)
			gm.hover(e.getX(), e.getY());
//		System.out.println(e.getX());

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		bg.callAnotherFunction(e.getX(), e.getY());
		if (gameState == 0 || gameState == endState) {
			if (gm.clicked(e.getX(), e.getY()) == 0) {
				gameState = playState;
				this.bg = new BoardGame(this.WIDTH, this.HEIGHT);
			} else if (gm.clicked(e.getX(), e.getY()) == 2)
				System.exit(1);
			else if(gm.clicked(e.getX(), e.getY()) == 1) {
				gameState = instructionState;
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {

//		System.out.println(e.getX());

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(int e) {
		int code = e;
		if (gameState == titleState || gameState == endState) {
			

			if (code == KeyEvent.VK_UP || code == KeyEvent.VK_W) {
				gm.selectorUp();
			} else if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
				gm.selectorDown();
			} else if (code == KeyEvent.VK_ENTER) {
				if (gm.selector == 0) {
					gameState = playState;
					bg = new BoardGame(this.WIDTH, this.HEIGHT);
				} else if (gm.selector == 2) {
					System.exit(1);
				}
				else if(gm.selector == 1) {
					gameState = instructionState;
				}
			}
		}else if(gameState == instructionState) {
			if (code == KeyEvent.VK_ENTER) {
				gameState = titleState;
			}
		}
	}

	@Override
	public void keyReleased(int e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(int e) {
		// TODO Auto-generated method stub

	}

}

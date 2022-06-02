package setup;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;

import setup.Manager;
import setup.Window;

public class WindowTest implements Manager{
	
	public static final int WIDTH = 800, HEIGHT = 600, FPS = 120, TPS = 120;
	
	int sdi = 0;
	int boxX = 1;
	int bmw = 1;
	boolean moveRight = true;
	boolean moveDown = true;
	int randomInt = 0;
	int temp;
	ArrayList<Integer> dotXs = new ArrayList<Integer>();
	Iterator<Integer> itX; 
	ArrayList<Integer> dotYs = new ArrayList<Integer>();
	Iterator<Integer> itY; 
	long initTime = System.currentTimeMillis();
	int[] x;
	int[] y;
	
	public WindowTest() {
		x = new int[6];
		y = new int[6];
		x[0] = 100;
		y[0] = 100;
		x[1] = 100;
		y[1] = 150;
		x[2] = 200;
		y[2] = 50;
		x[3] = 300;
		y[3] = 150;
		x[4] = 300;
		y[4] = 100;
		x[5] = 200;
		y[5] = 0;
	}
	
	public void draw(Graphics g) {
		
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, WIDTH, HEIGHT);
//		if(boxX == WIDTH - 100 || boxX == 0 || bmw == HEIGHT - 100 || bmw == 0)
//			randomInt = (int)(Math.random()*12);
		g.setColor(Color.BLACK);
		g.fillPolygon(x, y, 5);
//		itX = dotXs.iterator();
//		itY = dotYs.iterator();
//		
//		while(itX.hasNext() && itY.hasNext()) {
//			g.setColor(Color.BLACK);
//			g.fillOval(itX.next(),itY.next(),2,2);
////			System.out.println(itX.next());
//		}
//		
//		if(randomInt == 0)
//			g.setColor(Color.RED);
//		else if(randomInt == 1)
//			g.setColor(Color.BLACK);
//		else if(randomInt == 2)
//			g.setColor(Color.YELLOW);
//		else
//			g.setColor(Color.GREEN);
//		
//		g.fillRect(boxX, bmw, 100, 100);
	}
	
	public void tick() {
//		if(bmw > HEIGHT)
//			bmw = 1;
//		if(boxX > WIDTH)
//			boxX = 1;
//		if(boxX + 100 == WIDTH) {
//			moveRight = false;
//			temp = randomInt;
//			while(true) {
//				randomInt = (int)(Math.random()*4);
//				if(randomInt != temp)
//					break;
//			}
//		}
//		if(boxX == -1) {
//			moveRight = true;
//			temp = randomInt;
//			while(true) {
//				randomInt = (int)(Math.random()*4);
//				if(randomInt != temp)
//					break;
//			}
//		}
//		if(bmw + 100 == HEIGHT) {
//			moveDown = false;
//			temp = randomInt;
//			while(true) {
//				randomInt = (int)(Math.random()*4);
//				if(randomInt != temp)
//					break;
//			}
//		}
//		if(bmw == -1) {
//			moveDown = true;
//			temp = randomInt;
//			while(true) {
//				randomInt = (int)(Math.random()*4);
//				if(randomInt != temp)
//					break;
//			}
//		}
//		
//		if(moveRight)
//			boxX++;
//		else
//			boxX--;
//		if(moveDown)
//			bmw++;
//		else
//			bmw--;
//		
////		if(sdi == 0) {
////			dotXs.add(1);
////			dotYs.add(1);
////			sdi = 1;
////		}
//		if(System.currentTimeMillis() - initTime >= 3) {
//			dotXs.add(boxX + 50);
//			dotYs.add(bmw + 50);
//			if(dotXs.size() == 300)
//				dotXs.remove(0);
//			if(dotYs.size() == 300)
//				dotYs.remove(0);
//			initTime = System.currentTimeMillis();
//		}
//		
	}
	
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		Window wind = new Window(WIDTH, HEIGHT, FPS, TPS, "Window Title", new WindowTest());
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		
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

package loja;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
//import javax.swing.JButton;
//import javax.swing.JFrame;
import javax.swing.JPanel;



import setup.Window;

public class GameMenu extends JPanel implements Runnable {
	
	public static final int WIDTH = 1200, HEIGHT = 600;
	
	private int selector = 0;
	private int blockWidth = WIDTH/20;
	private int frameHeight = blockWidth;
	private int hovering;
	private String text1 = "Start Game";
	private String text2 = "Instructions";
	private String text3 = "Quit";
	private int startWidth = getWidth();
	private int startHeight = getHeight();
	private boolean hovered;
	private BufferedImage img; 
	public static final int FPS = 60;
	Sound sound = new Sound();
	
	
//	JFrame frame = new JFrame();
//	
//	JButton startBtn;
//	JButton instructionBtn;
//	JButton quitBtn;
//	

  
  
  
//  TileManager tileM = new TileManager(this);
  
//  KeyHandler keyH = new KeyHandler();
  
//  Sound music = new Sound();
//  
//  Sound se = new Sound();
//  
//  public CollisionChecker cChecker = new CollisionChecker(this);
//  
//  public AssetSetter aSetter = new AssetSetter(this);
	Thread gameThread;
  
 
  
  public GameMenu() {
    
    setDoubleBuffered(true);
//    addKeyListener(this.keyH);
    setFocusable(true);
    try
	{
	    img = ImageIO.read( getClass().getResourceAsStream("8c65ajMzi.png"));
	}
	catch ( IOException exc )
	{
	    //TODO: Handle exception.
	}
    
    playMusic(3);
   
  }
  
  public int getSelector(){
  	return this.selector;
  }

  public void startGameThread() {
    this.gameThread = new Thread(this);
    this.gameThread.start();
  }
  
  public void run() {
    double drawInterval = (1000000000 / this.FPS);
    double delta = 0.0D;
    long lastTime = System.nanoTime();
    long timer = 0L;
    int drawCount = 0;
    while (this.gameThread != null) {
      long currentTime = System.nanoTime();
      delta += (currentTime - lastTime) / drawInterval;
      timer += currentTime - lastTime;
      lastTime = currentTime;
      if (delta >= 1.0D) {
        update();
        repaint();
        delta--;
        drawCount++;
      } 
      if (timer >= 1000000000L) {
        drawCount = 0;
        timer = 0L;
      } 
    } 
  }
  
  public void update() {
//    this.player.update();
  }
  
  
	public void drawStartMenu(Graphics g, int num) {
		
		if(num == 0)
			drawMenu(g, "Deploy&Destroy");
		else if(num == 1)
			drawMenu(g,"White is the Winner");
		else if(num == 2)
			drawMenu(g,"Black is the Winner");
		else if(num == 3)
			drawMenu(g,"Game was a Draw");
		
	}
	
	public void drawMenu(Graphics g, String txt) {
		
		g.setColor(new Color(14, 131, 133));
		g.fillRect(0, 0, 1200, 600);
		
		g.setFont(g.getFont().deriveFont(Font.ITALIC, 75F));
		g.setColor(new Color(0,0,0,0));
		g.fillRect(blockWidth*(5),HEIGHT/4 - blockWidth, 500,500);
		String text = txt;
//		String text1, text2, text3;
		int x = blockWidth*(5);
		int y = HEIGHT/4 - blockWidth;
		
		
		g.setColor(Color.BLACK);
		g.drawString(text, x+5, y+5);
		
		
		g.setColor(Color.WHITE);
		g.drawString(text, x, y);
		
		g.setFont(g.getFont().deriveFont(Font.BOLD, 40F));
		
//		text1 = "Start Game";
		x = blockWidth*(8);
		y = HEIGHT/2;
		g.drawString(text1, x, y);
		
		getWidth(g,text1);
		getHeight(g);
		
		
		
		
		
		if(selector == 0) {
			g.drawImage( img, blockWidth*(7),  HEIGHT/2 - 50, 50, 70,this );
		}
		
//		text2 = "Instructions";
		x = blockWidth*(8);
		y = HEIGHT/2 + blockWidth;
		g.drawString(text2, x, y);
		
		if(selector == 1) {
			g.drawImage( img, blockWidth*(7),  HEIGHT/2 - 50 + blockWidth, 50, 70,this );
		}
//		
//		text3 = "Quit";
		x = blockWidth*(9);
		y = HEIGHT/2 + blockWidth*2;
		g.drawString(text3, x, y);

		
		if(selector == 2) {
			g.drawImage( img, blockWidth*(7),  HEIGHT/2 - 50 + blockWidth*2, 50, 70,this );
		}
		
	}
	
public void drawInstruction(Graphics g) {
		
		g.setColor(new Color(14, 131, 133));
		g.fillRect(0, 0, 1200, 600);
		
		g.setFont(g.getFont().deriveFont(Font.ITALIC, 15F));
		g.setColor(new Color(0,0,0,0));
		g.fillRect(blockWidth*(5),HEIGHT/4 - blockWidth, 500,500);
		String text = "The game is played on a 16-position board, which is initially empty. Players toss the coin at the beginning and\r\n"
				+ "whoever wins plays first.";
		
		g.setColor(Color.black);
		g.drawString(text,40,130);
		
		text = "Each player has an “army” of numbers in their possession, that is, numbers from 1 to 8. The game has two\r\n"
				+ "phases, deployment and destruction.";
		
		g.drawString(text,40,170);
		
		text = "During the deployment phase, a player chooses any of their available numbers and puts it on the board in any\r\n"
				+ "available (empty) square.";
				
		g.drawString(text, 40 ,210);
		
		text = "The other player does the same until all sixteen numbers (8 for each player) are\r\n"
				+ "placed on the board.";
		
		g.drawString(text, 40 ,250);
		
		text = "Then, the destruction phase begins in which the order of play is preserved. Whoever plays first, selects a\r\n"
				+ "number of the other player and destroys it.";
		
		g.drawString(text, 40 ,290);
		
		text = "Destruction of a number N of the\r\n"
				+ "opponent is possible only if in the surrounding squares (immediate left and right) the player has numbers\r\n"
				+ "whose sum exceeds N.";
		
		g.drawString(text, 40 ,330);
		
		text = "If a player cannot play anymore, the game continues with the other player destroying\r\n"
				+ "as many numbers as possible.";
		
		g.drawString(text, 40 ,370);
//		
		text = "When no-one can play anymore, the game ends. The winner is the player who\r\n"
				+ "has more armies (numbers) than the other player left on the board.";
		
		g.drawString(text, 40 ,410);
		
		text = "If they both have the same number of \r\n"
				+ "armies, the winner is the one whose sum of armies is greater than the other’s. Otherwise, it’s a draw.";
		
		g.drawString(text, 40 ,450);

		
		text = "In order to go back please press ENTER";
		g.setFont(g.getFont().deriveFont(Font.ITALIC, 20F));
		g.setColor(Color.WHITE);
		g.drawString(text, 400, 550);
		
		
	  }
	
	public void playMusic(int i) {
		sound.setFile(i);
		sound.play();
		sound.loop();
	}
	public void stopMusic() {
		sound.stop();
	}

	public void changeSelector(int num) {
		
		if(num == 1) {
			selector = 0;
		}
		else if(num == 2) {
			selector = 1;
		}
		else {
			selector = 2;
		}
		hovered = true;
	}
	
	public int getWidth(Graphics g,String text) {
		
		startWidth = g.getFontMetrics().stringWidth(text);
		
		return startWidth;
		
		
	}
	
	public int getHeight(Graphics g) {
		
		startHeight = g.getFontMetrics().getHeight();
		
		return startHeight;
	}
	
	public void selectorUp() {
		
		selector--;
		if(selector < 0) {
			selector = 2;
		}
		
	}
	public void selectorDown() {
		
		selector++;
		if(selector > 2) {
			selector = 0;
		}
		
	}
	
	public void hover(int x, int y) {
//		
				if(x >= blockWidth*(8) && x <= blockWidth*(8) + startWidth + 10 && y >= HEIGHT/2 - 30 && y <= HEIGHT/2 - 50 + startHeight) {
					changeSelector(1);
					
				}
				else if(x >= blockWidth*(8) && x <= blockWidth*(8) + startWidth + 10 && y >= HEIGHT/2 + blockWidth - 30 && y <= HEIGHT/2 + blockWidth - 50 + startHeight) {
					changeSelector(2);
				}
				else if(x >= blockWidth*(8) && x <= blockWidth*(8) + startWidth + 10 && y >= HEIGHT/2 + blockWidth*2 - 30 && y <= HEIGHT/2 + blockWidth*2 - 50 + startHeight) {
					changeSelector(3);
				}
				else {
					hovered = false;
				}
		
		
	}
	
	public int clicked(int x, int y) {
//		
		if(hovered) {
	
			return selector;
		}
		
		return -1;
	}
	
//	public void actionPerformed(ActionEvent event) {		
//	}
//	
//	public void startBtn_actionPerformed(ActionEvent event) {		
//	}
//	
//	public void instructionBtn_actionPerformed(ActionEvent event) {		
//
//		
//	}
//	
//	public void quitBtn_actionPerformed(ActionEvent event) {		
//	
//
//	}
	
	
}
package loja;
import java.awt.Graphics;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.Color;
import java.awt.Font;

public class Button {
	private int x;
	private int y;
	private int frameHeight;
	private int frameWidth;
	private int blockHeight;
	private int blockWidth;
	private boolean right;
	private String text;
//	Color color;
	private boolean hovered;
	private boolean clicked;
	
	public Button(int frameHeight, int frameWidth, boolean right, String text) {
		this.frameHeight = frameHeight;
		this.frameWidth = frameWidth;
		this.right = right;
		this.text = text;
		blockHeight = frameHeight/10;
		
		this.y = 3*frameHeight/4 - blockHeight/2;
		if(right) {
			blockWidth = frameWidth/4;
			this.x = 3*frameWidth/4 - blockWidth/2;
		}
		else {
			blockWidth = frameWidth/6;
			this.x = frameWidth/4 - blockWidth/2; 
		}
		this.hovered = false;
		this.clicked = false;
	}
	public void draw(Graphics g) {
		if(!hovered) {
			g.setColor(Color.BLACK);
			g.drawRect(this.x, this.y, this.blockWidth, this.blockHeight);
		}
		else {
			if(right)
				g.setColor(new Color(32,191,69));
			else
				g.setColor(Color.RED);
			g.fillRect(this.x, this.y, this.blockWidth, this.blockHeight);
		}
		
		drawText(g);
	}

	private void drawText(Graphics g) {
		int x = this.x + this.blockWidth/2;
		int y = this.y + this.blockHeight/2;
		if(!hovered) {
			if(this.right)
				g.setColor(new Color(32,191,69));
			else
				g.setColor(Color.RED);
		}
		else
			g.setColor(Color.BLACK);
		g.setFont(new Font(Font.MONOSPACED, Font.PLAIN, this.blockHeight));
		g.drawString(this.text,x - (getTextWidthOrHeight("Width", text, g)/2),y + getTextWidthOrHeight("Height", text, g)/4);
	}
	private static int getTextWidthOrHeight(String choice,String text, Graphics g) {
		
		Font font = g.getFont();
		FontRenderContext frc = new FontRenderContext(new AffineTransform(), true, true);
		
		if(choice.equals("Height"))
			return (int)(font.getStringBounds(text, frc).getHeight());
		else if(choice.equals("Width"))
			return (int)(font.getStringBounds(text, frc).getWidth());
		else
			return 0;
	}
	
	public void tick() {
		
	}
	
	public void mouseMoved(int x, int y) {
		if(!hovered) {
			if(x >= this.x && x <= this.x + this.blockWidth && y >= this.y && y <= this.y + blockHeight) {
				this.hovered = true;
			}
		}
		else {
			if(x < this.x || x > this.x + this.blockWidth || y < this.y || y > this.y + blockHeight)
				this.hovered = false;
		}
	}
	
	public boolean mouseClicked(int x, int y) {
		if(x >= this.x && x <= this.x + this.blockWidth && y >= this.y && y <= this.y + blockHeight) {
			this.clicked = true;
		}
		return clicked;
	}
	
}

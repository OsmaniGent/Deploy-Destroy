package loja;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;

public class PhaseAnimation {
	private String text;
	private int frameHeight;
	private int frameWidth;
	private int textX;
//	int bgX;
//	boolean bgMoving;
	private boolean textMoving;
//	boolean qr;
	private int textWidth;
	private boolean rightAnimation;
	private int time;
	private boolean end;
	
	public PhaseAnimation(String text, int frameHeight, int frameWidth) {
//		qr = false;
		time = 90;
		end = false;
		this.text = text;
		this.frameHeight = frameHeight;
		this.frameWidth = frameWidth;
		this.textX = frameWidth;
		rightAnimation = true;
//		this.bgX = frameWidth;
//		bgMoving = false;
		textMoving = false;
		textWidth = this.getTextWidthOrHeight("Width", text, new Font("TimesRoman", Font.PLAIN, frameHeight / 5));
	}

	public void start() {
		textMoving = true;
	}

	public void draw(Graphics g) {
		drawBG(g);
		drawText(g);
	}

	public void drawBG(Graphics g) {
		g.setColor(new Color(253,158,158));
//		g.fillRect(this.bgX, this.frameHeight / 2 - frameHeight / 8, this.frameWidth, frameHeight / 4);
		g.fillRect(0,this.frameHeight/2 - frameHeight/8,this.frameWidth, frameHeight/4);
	}

	public boolean tick() {
//		if (this.bgMoving || qr)
//			this.moveBG();
		if (this.textMoving)
			this.moveText();
		else{
			time++;
			if(time > 100) {
				this.textMoving = true;
//				this.rightAnimation = false;
			}
		}
		
		return end;
	}



	public void moveText() {
//		System.out.println("Edon");
		this.textX -= 30;
		
		if(this.rightAnimation && textX < (this.frameWidth - this.textWidth)/2) {
			this.rightAnimation = false;
			this.textMoving = false;
			time = 0;
		}
		
		if (this.textX + this.textWidth <= -100) {
//			System.out.println("EDON");
			this.textMoving = false;
			this.end = true;
			
//			this.bgMoving = true;
			
		}
	}

	private void drawText(Graphics g) {
		int x = this.textX;
		int y = frameHeight / 2;

		g.setColor(Color.WHITE);
		g.setFont(new Font(Font.MONOSPACED, Font.PLAIN, frameHeight / 5));
		
//		this.textWidth = getTextWidthOrHeight("Width", text, g);
		
		g.drawString(this.text, x, y + getTextWidthOrHeight("Height", text, g.getFont())/4);
	}

	private static int getTextWidthOrHeight(String choice, String text, Font font) {

//		Font font = g.getFont();
		FontRenderContext frc = new FontRenderContext(new AffineTransform(), true, true);

		if (choice.equals("Height"))
			return (int) (font.getStringBounds(text, frc).getHeight());
		else if (choice.equals("Width"))
			return (int) (font.getStringBounds(text, frc).getWidth());
		else
			return 0;

	}

	public void end() {

	}
}

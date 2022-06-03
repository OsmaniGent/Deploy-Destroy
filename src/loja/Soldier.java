package loja;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;

public class Soldier {
	private int number;
	private int positionOnBoard;
	
	//TRUE if White, FALSE if Black
	private boolean	owner;
	
	private int x;
	private int y;
	
	private int size;
	private int normalSize;
	private boolean isHovered;
	
	public Soldier(int position, boolean owner, int number, int x, int y, int size) {
		positionOnBoard = position;
		this.x = x;
		this.y = y;
		this.owner = owner;
		this.number = number;
		this.size = size;
		this.normalSize = size;
		this.isHovered = false;
	}
	
	public boolean isHovered() {
		return isHovered;
	}

	public void setHovered(boolean isHovered) {
		this.isHovered = isHovered;
	}

	public int getPositionOnBoard() {
		return positionOnBoard;
	}

	public void setPositionOnBoard(int positionOnBoard) {
		this.positionOnBoard = positionOnBoard;
	}


	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setNumber(int num) {
		number = num;
	}
	public int getNumber() {
		return number;
	}
	public void setOwner(boolean owner) {
		this.owner = owner;
	}
	public boolean getOwner() {
		return owner;
	}
	
	public void draw(Graphics g) {

		
		if(owner) {
			g.setColor(Color.WHITE);
			g.fillOval(x,y,size,size);
			g.setColor(Color.BLACK);
			g.drawOval(x,y,size,size);
			
			drawNumber(x,y,g);
		}
		else {
			g.setColor(Color.BLACK);
			g.fillOval(x,y,size,size);
			drawNumber(x,y,g);
		}
		
	}
	
	private static int getNumWidthOrHeight(String choice,int num, Graphics g) {
		String text = ""+num;
		Font font = g.getFont();
		FontRenderContext frc = new FontRenderContext(new AffineTransform(), true, true);
		
		if(choice.equals("Height"))
			return (int)(font.getStringBounds(text, frc).getHeight());
		else if(choice.equals("Width"))
			return (int)(font.getStringBounds(text, frc).getWidth());
		else
			return 0;
	}
	
	private void drawNumber(int x, int y, Graphics g) {
		x = x + size/2;
		y = y + size/2;
		if(this.owner)
			g.setColor(Color.BLACK);
		else
			g.setColor(Color.WHITE);
		if(this.positionOnBoard == -10)
			g.setFont(new Font(Font.MONOSPACED, Font.PLAIN, size - size/4));
		else
			g.setFont(new Font(Font.MONOSPACED, Font.PLAIN, size));
		g.drawString(this.number+"",x - (getNumWidthOrHeight("Width", this.number, g)/2),y + getNumWidthOrHeight("Height", this.number, g)/4);
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		int change = size - this.size;
		this.size = size;
		this.x -= change/2;
		this.y -= change/2;
		
	}
	public void reset() {
		int change = this.normalSize - this.size;
		this.size = normalSize;
		this.x -= change/2;
		this.y -= change/2;
	}
}
package loja;

import java.awt.Graphics;
import java.awt.Color;

public class RedCarpet {
	private int frameHeight;
	private int frameWidth;
	private int x;
	private int yStart;
	private int position;
	private int[] xVerts;
	private int[] yVerts;
	private int vertices;
	private int blockWidth;
	
	public RedCarpet(int frameHeight, int frameWidth, int position, int blockWidth) {
		super();
		this.frameHeight = frameHeight;
		this.frameWidth = frameWidth;
		this.position = position;
		this.blockWidth = blockWidth;
		this.setX();
		this.yStart = 7*this.frameHeight/8;
		this.vertices = 6;
		this.xVerts = new int[6];
		this.yVerts = new int[6];
		
	}
	public void tick() {
		for(int i = 0; i < this.vertices; i++) {
			yVerts[i]-= 10;
		}
		if(yVerts[5] < (this.frameHeight - blockWidth)/2)
			this.normalYPositions();
	}
	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawLine(this.x, this.yStart, this.x, this.frameHeight/2);
		g.drawLine(this.x + blockWidth, this.yStart, this.x + blockWidth, this.frameHeight/2);
		g.fillPolygon(this.xVerts, this.yVerts, this.vertices);
	}
	private void normalYPositions() {
		this.yVerts[0] = this.yStart + this.blockWidth;
		this.yVerts[1] = this.yStart + this.blockWidth + 20;
		this.yVerts[2] = this.yStart + 20;
		this.yVerts[3] = this.yStart + this.blockWidth + 20;
		this.yVerts[4] = this.yStart + this.blockWidth;
		this.yVerts[5] = this.yStart;
	}
	public void setPosition(int position) {
		this.position = position;
		setX();
		this.xVerts[0] = this.x;
		this.xVerts[1] = this.x;
		this.xVerts[2] = this.x + blockWidth/2;
		this.xVerts[3] = this.x + blockWidth;
		this.xVerts[4] = this.x + blockWidth;
		this.xVerts[5] = this.x + blockWidth/2;
	}
	private void setX() {
		this.x = blockWidth*(position+2);
	}
	public int getPosition() {
		return this.position;
	}
}

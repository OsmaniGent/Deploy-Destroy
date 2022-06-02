package loja;

import java.awt.Graphics;
import java.awt.Color;

public class Spear {
	int frameHeight;
	int frameWidth;
	int position;
	int x;
	int y;
	int width;
	boolean toBeDestroyed;
	
	
	public Spear(int frameHeight, int frameWidth, int position, int width) {
		this.frameHeight = frameHeight;
		this.frameWidth = frameWidth;
		this.position = position;
		this.width = width;
		this.x = width * (position + 2);
		this.y = -frameHeight;
		toBeDestroyed = true;
	}
	
	private void reset() {
		this.y = -frameHeight;
		toBeDestroyed = true;
	}
	
	public int tick() {
		this.y+= 23;
		if(toBeDestroyed && y > 0) {
			toBeDestroyed = false;
			return 1;
		}
		else if(y > frameHeight) {
			
			return 2;
		}
		return 0;
	}
	public int getPosition() {
		return this.position;
	}
	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(x,y,width,frameHeight-50);
		g.fillPolygon(new int[] {x,x+width,x+width/2}, new int[] {y+frameHeight-50,y+frameHeight-50,y+frameHeight},3);
	}
	public void setPosition(int i) {
		this.position = i;
		this.x = this.width * (i + 2);
		reset();
	}
}

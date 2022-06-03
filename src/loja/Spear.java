package loja;

import java.awt.Graphics;
import java.awt.Color;

public class Spear {
	private int frameHeight;
	private int frameWidth;
	private int position;
	private int x;
	private int y;
	private int width;
	private boolean toBeDestroyed;

	//TRUE for White, FALSE for Black
	private boolean owner;

	public Spear(int frameHeight, int frameWidth, int position, int width) {
		this.frameHeight = frameHeight;
		this.frameWidth = frameWidth;
		this.position = position;
		this.width = width;
		this.x = width * (position + 2);
		this.y = -frameHeight;
		toBeDestroyed = true;
		owner = true;
	}

	private void reset() {
		this.y = -frameHeight;
		toBeDestroyed = true;
	}

	public int tick() {
		this.y += 23;
		if (toBeDestroyed && y > 0) {
			toBeDestroyed = false;
			return 1;
		} else if (y > frameHeight) {

			return 2;
		}
		return 0;
	}

	public int getPosition() {
		return this.position;
	}

	public void draw(Graphics g) {
		if (owner) {
			g.setColor(Color.WHITE);
			g.fillRect(x, y, width, frameHeight - 50);
			g.fillPolygon(new int[] { x, x + width, x + width / 2 },
					new int[] { y + frameHeight - 50, y + frameHeight - 50, y + frameHeight }, 3);
			g.setColor(Color.BLACK);
			g.drawRect(x, y, width, frameHeight - 50);
			g.drawPolygon(new int[] { x, x + width, x + width / 2 },
					new int[] { y + frameHeight - 50, y + frameHeight - 50, y + frameHeight }, 3);
			
		} else {
			g.setColor(Color.BLACK);
			g.fillRect(x, y, width, frameHeight - 50);
			g.fillPolygon(new int[] { x, x + width, x + width / 2 },
					new int[] { y + frameHeight - 50, y + frameHeight - 50, y + frameHeight }, 3);
		}
	}

	public void setPosition(int i, boolean owner) {
		this.position = i;
		this.x = this.width * (i + 2);
		this.owner = owner;
		reset();
	}
}

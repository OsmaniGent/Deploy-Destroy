package loja;

import java.awt.Graphics;
import java.awt.Color;

public class Coin {
	int coinX;
	int coinY;
	boolean rotating;
	int speed;
	boolean heads;
	int frameHeight;
	int frameWidth;
	int coinWidth;
	int coinHeight;
	boolean choosing;
	int time;
	Color[] colors;
	int currentColor;
	Button rightButton;
	Button leftButton;
	boolean lucky;
	boolean shrinking;
	boolean moving;

	public Coin(int frameHeight, int frameWidth) {
		this.speed = 20;
		this.frameHeight = frameHeight;
		this.frameWidth = frameWidth;
		rotating = false;
		this.coinWidth = this.frameWidth / 10;
		this.coinHeight = this.coinWidth;
		coinX = (frameWidth / 2) - coinWidth / 2;
		coinY = (frameHeight / 2) - coinWidth / 2;
		this.choosing = true;
		time = 0;
		colors = new Color[2];
		colors[0] = Color.RED;
		colors[1] = new Color(32, 191, 69);
		currentColor = 0;
		rightButton = new Button(this.frameHeight, this.frameWidth, true, "GREEN");
		leftButton = new Button(this.frameHeight, this.frameWidth, false, "RED");
		shrinking = true;
		moving = true;
	}

	public void draw(Graphics g) {

		g.setColor(colors[this.currentColor]);
		g.fillOval(coinX, coinY, this.coinWidth, this.coinHeight);
		g.setColor(Color.BLACK);
		g.drawOval(coinX, coinY, this.coinWidth, this.coinHeight);

		if (this.choosing) {
			leftButton.draw(g);
			rightButton.draw(g);
		}
	}

	public int tick() {
		if (choosing) {
			time++;
			if (time > 40 && choosing) {
				if (currentColor == 0) {
					currentColor = 1;
				} else {
					currentColor = 0;
				}
				time = 0;
			}
		}
		if (rotating) {
			rotateCoin();
		}
		if (!choosing && !rotating) {
			time++;
			if (time == 60) {
				if (this.lucky)
					return 1;
				return 2;
			}
		}

		return 0;

	}

	public void rotateCoin() {

		if (shrinking) {
			this.coinHeight -= 26;
			this.coinY += 13;
		} else {
			this.coinHeight += 26;
			this.coinY -= 13;
		}

		if (moving && time > 1) {
			this.coinY -= speed;
			speed--;
			time = 0;
		}

		if (coinHeight <= 2) {
			shrinking = false;
			changeColor();
		} else if (!shrinking && coinHeight >= coinWidth) {
			if (moving) {
				shrinking = true;
			} else {
				if (this.heads && this.currentColor == 0) {
					rotating = false;
//					System.out.println(this.lucky);

				} else if (!this.heads && this.currentColor == 1) {
					rotating = false;
//					System.out.println(this.lucky);
				} else {
					shrinking = true;
				}
			}
		}
		if (this.coinY > this.frameHeight / 2)
			moving = false;
		time++;
	}

	public void changeColor() {
		if (this.currentColor == 0)
			currentColor++;
		else
			currentColor--;
	}

	public void mouseMoved(int x, int y) {
		this.rightButton.mouseMoved(x, y);
		this.leftButton.mouseMoved(x, y);
	}

	public void mouseClicked(int x, int y) {
		if (this.choosing) {
			if (this.rightButton.mouseClicked(x, y)) {
				randomChoice();
				if (this.heads == false) {
					lucky = true;
				} else {
					lucky = false;
				}
				this.choosing = false;
				this.rotating = true;
			} else if (this.leftButton.mouseClicked(x, y)) {
				randomChoice();
				if (this.heads) {
					lucky = true;
				} else {
					lucky = false;
				}
				this.choosing = false;
				this.rotating = true;

			}
		}
	}

	public void randomChoice() {
		if (Math.random() < 0.5) {
			this.heads = true;
		} else {
			this.heads = false;
		}

	}

}

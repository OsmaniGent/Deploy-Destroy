package loja;

import java.util.ArrayList;
import java.awt.*;

public class Player {

	public static final int ARMY_SIZE = 8;



	private ArrayList<Soldier> army;
//	int armySize;
	private int frameHeight;
	private int frameWidth;
	private int blockWidth;
	private boolean human;
	private int yPos;
	private int hoveredSoldier;

	public Player(boolean human, int frameWidth, int frameHeight, int blockWidth) {
		army = new ArrayList<Soldier>(ARMY_SIZE);
		this.human = human;
		this.frameWidth = frameWidth;
		this.frameHeight = frameHeight;
		this.blockWidth = blockWidth;
		this.hoveredSoldier = -1;
		if (human)
			yPos = frameHeight - frameHeight / 8;
		else
			yPos = frameHeight / 8 - blockWidth;
		fillArmy();

	}

	public ArrayList<Soldier> getArmy() {
		return army;
	}

	private void fillArmy() {
		int xStartingPos = 6 * blockWidth;
//		int yStartingPos;
		int soldierOwner;
//		if(human) {
////			yStartingPos = frameHeight - frameHeight/8;
//			soldierOwner = 1;
//		}
//		else {
////			yStartingPos = frameHeight/8 - blockWidth;
//			soldierOwner = 2;
//		}

		for (int i = 0; i < this.ARMY_SIZE; i++) {
			if (i < 4)
				army.add(
						new Soldier(i, human, i + 1, xStartingPos + blockWidth * i - 10 * (4 - i), yPos, blockWidth));
			else
				army.add(
						new Soldier(i, human, i + 1, xStartingPos + blockWidth * i + 10 * (i - 4), yPos, blockWidth));
		}

	}

	public void deployArmy(int number, int positionOnBoard, Soldier[] board) {
//		if(board[positionOnBoard] == null) {
		board[positionOnBoard] = getSoldierWithNum(number);
		if (board[positionOnBoard] == null)
			System.out.println("DEKAAAAAAAA");
		board[positionOnBoard].setPositionOnBoard(positionOnBoard);
		board[positionOnBoard].setY(this.frameHeight / 2 - this.blockWidth / 2);
		board[positionOnBoard].setX(blockWidth * (2 + positionOnBoard));
		this.army.remove(getSoldierWithNum(number));
		setAppropriateCoordinates();
//		}

	}

	private Soldier getSoldierWithNum(int num) {
		for (int i = 0; i < this.army.size(); i++) {
			if (army.get(i).getNumber() == num)
				return army.get(i);
		}
		return null;
	}

	private void setAppropriateCoordinates() {
		int xStartingPos = frameWidth / 40 * ((20 - this.army.size()));
		for (int i = 0; i < this.army.size(); i++) {
			this.army.get(i).setX(xStartingPos + blockWidth * i);
			this.army.get(i).setPositionOnBoard(i);
		}
		if (this.army.size() % 2 == 0) {
			int middle = army.size() / 2;
			for (int i = 0; i < army.size(); i++) {
				if (i < middle)
					army.get(i).setX(army.get(i).getX() - 10 * (middle - i));
				else
					army.get(i).setX(army.get(i).getX() + 10 * (i - middle));
			}
		} else {
			int middle = army.size() / 2;
			for (int i = 0; i < army.size(); i++) {
				if (i < middle)
					army.get(i).setX(army.get(i).getX() - 10 * (middle - i));
				else if (i > middle)
					army.get(i).setX(army.get(i).getX() + 10 * (i - middle));
			}
		}

	}

	public void draw(Graphics g) {
		for (int i = 0; i < this.army.size(); i++) {
			army.get(i).draw(g);
		}
	}

	public void hover(int x, int y) {
		if (hoveredSoldier != -1) {
			if (x <= this.army.get(hoveredSoldier).getX()
					|| x >= this.army.get(hoveredSoldier).getX() + this.army.get(hoveredSoldier).getSize()
					|| y < this.army.get(hoveredSoldier).getY()
					|| y > this.army.get(hoveredSoldier).getY() + this.army.get(hoveredSoldier).getSize()) {

				this.army.get(hoveredSoldier).reset();
//				this.army.get(hoveredSoldier).setX(this.army.get(hoveredSoldier).getX() + 5);
//				this.army.get(hoveredSoldier).setY(this.army.get(hoveredSoldier).getY() + 5);
				this.army.get(hoveredSoldier).setHovered(false);
				hoveredSoldier = -1;

			}
		} else {
			for (int i = 0; i < this.army.size(); i++) {
				Soldier s = army.get(i);
				if (x >= s.getX() && x <= s.getX() + s.getSize() && y >= s.getY() && y <= s.getY() + s.getSize()) {
					s.setSize(s.getSize() + 10);
//					s.setX(s.getX()-5);
//					s.setY(s.getY()-5);
					s.setHovered(true);
					this.hoveredSoldier = s.getPositionOnBoard();
					break;
				}
				hoveredSoldier = -1;
			}

		}
	}

	public Soldier click(int x, int y) {
		for (Soldier s : this.army) {
			if (x >= s.getX() && x <= s.getX() + s.getSize() && y >= s.getY() && y <= s.getY() + s.getSize()) {
				return s;
			}
		}
		return null;
	}

	public Soldier deployRandomSoldier() {
//		ArrayList<Integer> positions = new ArrayList<Integer>();
//		for(int i = 0; i < board.length;i++) {
//			if(board[i] == null)
//				positions.add(i);
//		}
		int randomSoldier = (int) (Math.random() * (army.size()));
		return army.get(randomSoldier);
//		int randomPosition = (int) (Math.random()*(positions.size()));
//		
//		this.deployArmy(army.get(randomSoldier).getNumber(), positions.get(randomPosition), board);
	}

	public void hideSoldiersExcept(Soldier s) {
		for (int i = 0; i < this.army.size(); i++) {
			if (army.get(i) != s && army.get(i).getY() < this.frameHeight + 10) {
				army.get(i).setY(army.get(i).getY() + 3);
			}
		}
	}

	public void hideSoldiersFastExcept(Soldier s) {
		for (int i = 0; i < this.army.size(); i++) {
			army.get(i).setY(this.frameHeight + 10);
		}
	}
	
	public void perfectPosition() {
		for (int i = 0; i < this.army.size(); i++) {
			if (army.get(i).getY() - (army.get(i).getSize() - blockWidth) / 2 != yPos) {
				if (army.get(i).getY() - (army.get(i).getSize() - blockWidth) / 2 < yPos) {
					army.get(i).setY(army.get(i).getY() + 3);
					if (army.get(i).getY() - (army.get(i).getSize() - blockWidth) / 2 > yPos)
						army.get(i).setY(yPos + (army.get(i).getSize() - blockWidth) / 2);
				} else {
					army.get(i).setY(army.get(i).getY() - 3);
					if (army.get(i).getY() - (army.get(i).getSize() - blockWidth) / 2 < yPos) {
						army.get(i).setY(yPos + (army.get(i).getSize() - blockWidth) / 2);
					}
				}
			}
		}
	}
	
}
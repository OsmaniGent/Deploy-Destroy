package loja;

import java.awt.*;
import java.util.ArrayList;

import loja.Soldier;

public class BoardGame {

	private Soldier[] board;
	private int blockWidth;
	private int blockHeight;
	private int frameHeight;
	private int frameWidth;
	private Player whitePlayer;
	private Player blackPlayer;
	private Soldier readyToDeploy;

	private Coin coin;
	private boolean whiteTurn;
	private boolean tossCoinTime;
	private Sound sound = new Sound();
	private ArrayList<Soldier> destroyables;

	private boolean deploymentPhase;
	private boolean endGame;
	private Spear spear;

	// int coinY;
	// int coinX;

	private int colorShade = 230;
	private boolean blacker = true;
	private PhaseAnimation[] animations;
	private int currentAnimation;
	private boolean animation;

	private boolean deploying;

	private RedCarpet redCarpet;
	private boolean destroying;

	private boolean whiteDestroys;
	private int soldierNormalX;
	private int carpetPlace;
	private boolean drawCarpet;
	private int time;
	private ArrayList<Soldier> endWhiteArmy;
	private ArrayList<Soldier> endBlackArmy;
	private int whiteSum;
	private int blackSum;
	private boolean sizeCheck;
	private boolean sumCheck;
	private Soldier endWhiteSoldier;
	private Soldier endBlackSoldier;
	private int announceWinner;
	private boolean endForSure;

	public BoardGame(int frameWidth, int frameHeight) {
		endForSure = false;
		announceWinner = -1;
		endWhiteSoldier = null;
		endBlackSoldier = null;
		endWhiteArmy = new ArrayList<Soldier>();
		endBlackArmy = new ArrayList<Soldier>();
		whiteSum = 0;
		blackSum = 0;
		time = 0;
		whiteDestroys = false;
		destroying = false;
		carpetPlace = -1;
		drawCarpet = false;
		this.deploying = false;
		this.frameHeight = frameHeight;
		this.frameWidth = frameWidth;
		this.coin = new Coin(frameHeight, frameWidth);
		board = new Soldier[16];
		blockWidth = frameWidth / 20;
		blockHeight = blockWidth;
		whitePlayer = new Player(true, frameWidth, frameHeight, blockWidth);
		blackPlayer = new Player(false, frameWidth, frameHeight, blockWidth);
		whiteTurn = true;
		playMusic(0);
		this.deploymentPhase = true;
		this.tossCoinTime = true;
//		board[0] = new Soldier(0,1,4,blockWidth*3,frameHeight/2- blockHeight/2);
		destroyables = new ArrayList<Soldier>();
		animations = new PhaseAnimation[9];
		animations[0] = new PhaseAnimation("COIN FLIP", frameHeight, frameWidth);
		animations[1] = new PhaseAnimation("DEPLOYMENT", frameHeight, frameWidth);
		animations[2] = new PhaseAnimation("DESTRUCTION", frameHeight, frameWidth);
		animations[3] = new PhaseAnimation("WHITE PLAYS", frameHeight, frameWidth);
		animations[4] = new PhaseAnimation("BLACK PLAYS", frameHeight, frameWidth);
		animations[5] = new PhaseAnimation("END GAME", frameHeight, frameWidth);
		animations[6] = new PhaseAnimation("WHITE WINS", frameHeight, frameWidth);
		animations[7] = new PhaseAnimation("BLACK WINS", frameHeight, frameWidth);
		animations[8] = new PhaseAnimation("DRAW", frameHeight, frameWidth);
		currentAnimation = 0;
		animation = true;
		this.redCarpet = new RedCarpet(this.frameHeight, this.frameWidth, 0, this.blockWidth);
		spear = new Spear(frameHeight, frameWidth, 0, this.blockWidth);
//		pa.start();
		endGame = false;
		this.sizeCheck = false;
		this.sumCheck = false;
	}

	public boolean isBoardFull() {
		for (int i = 0; i < this.board.length; i++) {
			if (board[i] == null)
				return false;
		}
		return true;
	}

	private void deploySoldierSmooth() {
		if (whiteTurn) {
			this.readyToDeploy.setY(readyToDeploy.getY() - 10);

			if (readyToDeploy.getY() < (this.frameHeight - blockHeight) / 2) {
				whitePlayer.deployArmy(readyToDeploy.getNumber(), this.redCarpet.getPosition(), board);
				whiteTurn = false;
				deploying = false;
				this.readyToDeploy = null;
				if (isBoardFull()) {
					this.currentAnimation = 2;
					this.animation = true;
				} else
					time = -10;

			}
		} else {
			this.readyToDeploy.setY(readyToDeploy.getY() + 10);
			if (readyToDeploy.getY() > (this.frameHeight - blockHeight) / 2) {
				blackPlayer.deployArmy(readyToDeploy.getNumber(), this.redCarpet.getPosition(), board);
				whiteTurn = true;
				deploying = false;
				readyToDeploy = null;
				if (isBoardFull()) {
					this.currentAnimation = 2;
					this.animation = true;
				}
			}
		}
	}

	public boolean destroyRandom() {

		if (destroyables.size() != 0) {
			int randomnumber = (int) (Math.random()) * destroyables.size();
			this.destroying = true;
			this.spear.setPosition(this.destroyables.get(randomnumber).getPositionOnBoard(), false);
			return true;
		}
		return false;

	}

	public void orderSoldier() {
		boolean moveOn = false;
		for (int i = 0; i < this.endWhiteArmy.size(); i++) {
			if (endWhiteArmy.get(i).getX() != this.getBoardCellX(6) + blockWidth / 2) {
				moveOn = true;
				if (endWhiteArmy.get(i).getX() < this.getBoardCellX(6) + blockWidth / 2)
					endWhiteArmy.get(i).setX(endWhiteArmy.get(i).getX() + 1);
				else
					endWhiteArmy.get(i).setX(endWhiteArmy.get(i).getX() - 1);
			} else if (endWhiteArmy.get(i).getY() != frameHeight - frameHeight / 8 - blockHeight * i) {
				moveOn = true;
				if (endWhiteArmy.get(i).getY() < frameHeight - frameHeight / 8 - blockHeight * i)
					endWhiteArmy.get(i).setY(endWhiteArmy.get(i).getY() + 1);
				else
					endWhiteArmy.get(i).setY(endWhiteArmy.get(i).getY() - 1);
			}
		}
		for (int i = 0; i < this.endBlackArmy.size(); i++) {
			if (endBlackArmy.get(i).getX() != this.getBoardCellX(8) + blockWidth / 2) {
				moveOn = true;
				if (endBlackArmy.get(i).getX() < this.getBoardCellX(8) + blockWidth / 2)
					endBlackArmy.get(i).setX(endBlackArmy.get(i).getX() + 1);
				else
					endBlackArmy.get(i).setX(endBlackArmy.get(i).getX() - 1);
			} else if (endBlackArmy.get(i).getY() != frameHeight - frameHeight / 8 - blockHeight * i) {
				moveOn = true;
				if (endBlackArmy.get(i).getY() < frameHeight - frameHeight / 8 - blockHeight * i)
					endBlackArmy.get(i).setY(endBlackArmy.get(i).getY() + 1);
				else
					endBlackArmy.get(i).setY(endBlackArmy.get(i).getY() - 1);
			}
		}
		if (!moveOn) {
			endGame();
		}

	}

	public void addSoldiers() {
		boolean moveOnWhite = false;
		boolean moveOnBlack = false;
		for (int i = 0; i < this.endWhiteArmy.size(); i++) {
			if (this.endWhiteArmy.get(i).getY() != this.frameHeight / 2 - blockWidth / 2) {
				moveOnWhite = true;
				if (this.endWhiteArmy.get(i).getY() < this.frameHeight / 2 - blockWidth / 2) {
					this.endWhiteArmy.get(i).setY(this.endWhiteArmy.get(i).getY() + 1);
				} else {
					this.endWhiteArmy.get(i).setY(this.endWhiteArmy.get(i).getY() - 1);
				}
				if (this.endWhiteArmy.get(i).getY() == this.frameHeight / 2 - blockWidth / 2) {
					if (this.endWhiteSoldier == null) {
						this.endWhiteSoldier = new Soldier(-10, true, endWhiteArmy.get(i).getNumber(),
								this.endWhiteArmy.get(i).getX(), this.endWhiteArmy.get(i).getY(), blockWidth);
					} else {
						this.endWhiteSoldier
								.setNumber(this.endWhiteSoldier.getNumber() + this.endWhiteArmy.get(i).getNumber());
						this.endWhiteSoldier.setSize(this.endWhiteSoldier.getSize() + 2);
					}

				}
			}
			if (this.endBlackArmy.get(i).getY() != this.frameHeight / 2 - blockWidth / 2) {
				moveOnBlack = true;
				if (this.endBlackArmy.get(i).getY() < this.frameHeight / 2 - blockWidth / 2) {
					this.endBlackArmy.get(i).setY(this.endWhiteArmy.get(i).getY() + 1);
				} else {
					this.endBlackArmy.get(i).setY(this.endWhiteArmy.get(i).getY() - 1);
				}
				if (this.endBlackArmy.get(i).getY() == this.frameHeight / 2 - blockWidth / 2) {
					if (this.endBlackSoldier == null) {
						this.endBlackSoldier = new Soldier(-10, false, endBlackArmy.get(i).getNumber(),
								this.endBlackArmy.get(i).getX(), this.endBlackArmy.get(i).getY(), blockWidth);
					} else {
						this.endBlackSoldier
								.setNumber(this.endBlackSoldier.getNumber() + this.endBlackArmy.get(i).getNumber());
						this.endBlackSoldier.setSize(this.endBlackSoldier.getSize() + 2);
					}
				}
			}
		}
//		if (!moveOnWhite && this.endWhiteSoldier == null)
//			this.endWhiteSoldier = new Soldier(-1, true, this.whiteSum, this.endWhiteArmy.get(0).getX(),
//					this.endWhiteArmy.get(0).getY(), blockWidth);
//		if (!moveOnBlack && this.endBlackSoldier == null)
//			this.endBlackSoldier = new Soldier(-1, false, this.blackSum, this.endBlackArmy.get(0).getX(),
//					this.endBlackArmy.get(0).getY(), blockWidth);
		if (!moveOnBlack && !moveOnWhite) {
			time = 0;
			endForSure = true;
		}
	}

	public int tick() {
//		if(!this.destroying && !this.whiteDestroys && !this.deploymentPhase) {
//			if(!destroyRandom()) {
//				this.whiteDestroys = true;
//				this.destroyableSoldiers();
//			}
//		}
		if (announceWinner != -1)
			return announceWinner;
		if (this.animation) {
			if (this.animations[currentAnimation].tick()) {
				this.animation = false;
				if (currentAnimation == 3 || currentAnimation == 4) {
					currentAnimation = 1;
					animation = true;

				} else if (currentAnimation == 2) {

					this.deploymentPhase = false;
					this.destroyableSoldiers();
					if (this.destroyables.size() == 0) {
						this.whiteDestroys = !this.whiteDestroys;
						this.destroyableSoldiers();
						if (this.destroyables.size() == 0) {
							this.currentAnimation = 5;
							this.animation = true;
						}
					}
					if (!this.whiteDestroys)
						this.destroyRandom();
				} else if (this.currentAnimation == 5) {
					endGame = true;
					preEndGame();
				} else if (this.currentAnimation > 5 && this.currentAnimation < 9) {
					if (currentAnimation == 6)
						return 1;
					else if (currentAnimation == 7)
						return 2;
					else
						return 3;
				} else {
					this.currentAnimation = -1;
				}
			}
		}
		if (endGame && !this.sumCheck && !this.sizeCheck)

		{
			orderSoldier();
		} else if (this.sizeCheck) {
			this.animation = true;
			if (this.endBlackArmy.size() < this.endWhiteArmy.size())
				this.currentAnimation = 6;
			else if (this.endBlackArmy.size() > this.endWhiteArmy.size())
				this.currentAnimation = 7;
			else
				this.currentAnimation = 8;
		} else if (this.sumCheck) {
			if (!endForSure)
				this.addSoldiers();
			else {
				time++;
				if (time > 80) {
					this.animation = true;
					if (this.endBlackSoldier.getNumber() < this.endWhiteSoldier.getNumber())
						this.currentAnimation = 6;
					else if (this.endBlackSoldier.getNumber() > this.endWhiteSoldier.getNumber())
						this.currentAnimation = 7;
					else
						this.currentAnimation = 8;
				}

			}
		} else {
			if (this.destroying) {
				int z = this.spear.tick();
				if (z == 1)
					this.destroySoldier(this.board[this.spear.getPosition()]);
				else if (z == 2) {
					this.destroying = false;
					this.whiteDestroys = !this.whiteDestroys;
					this.destroyableSoldiers();
					if (this.destroyables.size() == 0) {
						this.whiteDestroys = !this.whiteDestroys;
						this.destroyableSoldiers();
						if (this.destroyables.size() == 0) {
							this.currentAnimation = 5;
							this.animation = true;
						}

					}
					if (!whiteDestroys)
						this.destroyRandom();
				}
			} else {
				if (time < 0)
					time++;
//		this.coin.tick();
//		this.animations[2].tick();
//		if(deploying)
//			this.move
				if (time > -1) {
					if (deploying)
						this.deploySoldierSmooth();
					else if (!whiteTurn && this.deploymentPhase && !animation && !this.tossCoinTime) {
						this.readyToDeploy = blackPlayer.deployRandomSoldier();
						this.redCarpet.setPosition(this.randomPlace());
						this.deploying = true;
						if (readyToDeploy != null)
							this.moveSoldierXTo(this.redCarpet.getPosition());
					}

					if (this.drawCarpet)
						this.redCarpet.tick();
					if (this.animation) {
						if (this.animations[currentAnimation].tick()) {
							this.animation = false;
							if (currentAnimation == 3 || currentAnimation == 4) {
								currentAnimation = 1;
								animation = true;

							} else if (currentAnimation == 2) {

								this.deploymentPhase = false;
								this.destroyableSoldiers();
								if (this.destroyables.size() == 0) {
									this.whiteDestroys = !this.whiteDestroys;
									this.destroyableSoldiers();
									if (this.destroyables.size() == 0) {
										this.currentAnimation = 5;
										this.animation = true;
									}
								}
								if (!this.whiteDestroys)
									this.destroyRandom();
							} else if (this.currentAnimation == 5) {
								endGame = true;
								preEndGame();
							} else {
								this.currentAnimation = -1;
							}
						}
					} else if (this.tossCoinTime) {
						int x = this.coin.tick();
						if (x == 1) {
							this.whiteDestroys = true;
							this.whiteTurn = true;
							this.currentAnimation = 3;
						} else if (x == 2) {
							this.whiteTurn = false;
							this.currentAnimation = 4;
						}
						if (x != 0) {
							this.tossCoinTime = false;
							this.animation = true;
						}
					}
				}
			}
			if (this.drawCarpet && this.readyToDeploy.getOwner())
				this.whitePlayer.hideSoldiersExcept(this.readyToDeploy);
			else if (!this.deploying || !whiteTurn)
				this.whitePlayer.perfectPosition();
		}
		return announceWinner;
	}

	public boolean shouldDrawBoard() {
		if (currentAnimation != 0 && currentAnimation != 3 && currentAnimation != 4)
			return true;
		return false;

	}

	public void end() {
		this.currentAnimation = 5;
		this.animation = true;
	}
	
	public void draw(Graphics g) {

		if (this.drawCarpet)
			this.redCarpet.draw(g);
		if (!this.tossCoinTime && shouldDrawBoard()) {
			if (this.readyToDeploy != null)
				this.highlightEmpty(g);
			if (!endGame)
				this.drawBoard(g);
			drawSoldiers(g);
			whitePlayer.draw(g);
			blackPlayer.draw(g);
			if (this.readyToDeploy != null)
				this.readyToDeploy.draw(g);
		} else if (!animation && this.tossCoinTime) {
			this.coin.draw(g);
		}

		if (this.destroying)
			this.spear.draw(g);
		else if (this.whiteDestroys && this.destroyables.size() != 0 && !this.endGame) {
			this.highlightDestroyables(g);
		}

		if (this.endBlackSoldier != null)
			endBlackSoldier.draw(g);
		if (this.endWhiteSoldier != null)
			endWhiteSoldier.draw(g);
		if (this.animation)
			this.animations[currentAnimation].draw(g);
	}

	public void highlightDestroyables(Graphics g) {
		this.blacker = !this.blacker;
		if(blacker)
			colorShade++;
		g.setColor(Color.BLACK);
		for (int i = 0; i < this.destroyables.size(); i++) {
			
			g.fillPolygon(
					new int[] { this.getBoardCellX(destroyables.get(i).getPositionOnBoard()) + blockWidth / 2,
							this.getBoardCellX(destroyables.get(i).getPositionOnBoard()) + blockWidth / 2 - colorShade,
							this.getBoardCellX(destroyables.get(i).getPositionOnBoard()) + blockWidth / 2
									+ colorShade },
					new int[] { this.frameHeight / 2 - blockHeight / 2,
							this.frameHeight / 2 - blockHeight / 2 - colorShade,
							this.frameHeight / 2 - blockHeight / 2 - colorShade },
					3);
			g.fillPolygon(
					new int[] { this.getBoardCellX(destroyables.get(i).getPositionOnBoard()) + blockWidth / 2,
							this.getBoardCellX(destroyables.get(i).getPositionOnBoard()) + blockWidth / 2 - colorShade,
							this.getBoardCellX(destroyables.get(i).getPositionOnBoard()) + blockWidth / 2
									+ colorShade },
					new int[] { this.frameHeight / 2 + blockHeight / 2,
							this.frameHeight / 2 + blockHeight / 2 + colorShade,
							this.frameHeight / 2 + blockHeight / 2 + colorShade },
					3);
		}
		
		if(colorShade > blockWidth/2 || colorShade < 0)
			colorShade = 1;
	}

	public void drawSoldiers(Graphics g) {
		for (int i = 0; i < this.board.length; i++) {
			if (board[i] != null)
				board[i].draw(g);
		}
	}

	private void drawBoard(Graphics g) {
		for (int i = 0; i < board.length; i++) {
			drawBlock(blockWidth * (i + 2), frameHeight / 2, g);
		}
	}

	private int getBoardCellX(int i) {
		return blockWidth * (i + 2);
	}

	private void drawBlock(int x, int y, Graphics g) {
		y = y - blockHeight / 2;
		g.setColor(Color.BLACK);
		g.drawLine(x, y, x, y + blockHeight);
		g.drawLine(x + blockWidth, y, x + blockWidth, y + blockHeight);
		g.drawLine(x, y, x + blockWidth, y);
		g.drawLine(x, y + blockHeight, x + blockWidth, y + blockHeight);

	}

	public void highlightEmpty(Graphics g) {
//		int x = 230;
//		boolean isGrowing = false;
		if (this.colorShade == 230)
			this.blacker = false;
		else if (colorShade == 150)
			blacker = true;
		if (blacker)
			colorShade += 2;
		else
			colorShade -= 2;

		Color c = new Color(colorShade, colorShade, colorShade);
		g.setColor(c);
//		System.out.println(c.getRed());
		for (int i = 0; i < this.board.length; i++) {
			if (board[i] == null) {

				g.fillRect(blockWidth * (i + 2), frameHeight / 2 - blockHeight / 2, blockWidth, blockHeight);
			}
		}
	}

	public void hover(int x, int y) {
		if (!deploying) {
			if (this.tossCoinTime)
				this.coin.mouseMoved(x, y);
			if (this.deploymentPhase)
				whitePlayer.hover(x, y);
			if (this.readyToDeploy != null)
				redCarpet(x, y);
		}
//		blackPlayer.hover(x, y);
	}

	public void moveSoldierXTo(int i) {
		this.readyToDeploy.setX(this.getBoardCellX(i));
	}

	public void redCarpet(int x, int y) {

		for (int i = 0; i < this.board.length; i++) {
			if (this.board[i] == null) {
				if (y > (this.frameHeight - blockHeight) / 2 && y < (this.frameHeight + blockHeight) / 2) {
					if (x > this.getBoardCellX(i) && x < this.getBoardCellX(i) + blockWidth) {
						carpetPlace = i;
						this.redCarpet.setPosition(carpetPlace);

						moveSoldierXTo(carpetPlace);
						drawCarpet = true;
					}
				}
			}
		}

		if (drawCarpet) {
			if (y < (this.frameHeight - blockHeight) / 2 || y > (this.frameHeight + blockHeight) / 2
					|| x < this.getBoardCellX(carpetPlace) || x > this.getBoardCellX(carpetPlace) + blockWidth) {
				drawCarpet = false;
				readyToDeploy.setX(this.soldierNormalX);
			}
		}
	}

	private int randomPlace() {
		ArrayList<Integer> positions = new ArrayList<Integer>();
		for (int i = 0; i < board.length; i++) {
			if (board[i] == null)
				positions.add(i);
		}
		int random = (int) (Math.random() * (positions.size()));
		return positions.get(random);
	}

	public void click(int x, int y) {
		if (!deploying && !destroying) {
			if (!this.animation) {
				if (this.tossCoinTime)
					this.coin.mouseClicked(x, y);
				else if (this.deploymentPhase) {
					if (readyToDeploy != null) {
						for (int i = 0; i < this.board.length; i++) {
							if (board[i] == null && x >= blockWidth * (i + 2) && x <= blockWidth * (i + 3)
									&& y >= (frameHeight - blockHeight) / 2 && y <= (frameHeight + blockHeight) / 2) {
//							whitePlayer.deployArmy(readyToDeploy.getNumber(), i, board);
								this.redCarpet.setPosition(i);
								this.drawCarpet = false;

								this.deploying = true;
								if (whiteTurn)
									this.whitePlayer.hideSoldiersFastExcept(this.readyToDeploy);
//							this.whiteTurn = false;
								playSE(1);
//							this.phaseChange();
//								if (!whiteTurn) {
//									blackPlayer.deployRandomSoldier(board);
//									whiteTurn = true;
//									this.phaseChange();
//								}
								break;
							}
						}
					}
				} else {
					for (int i = 0; i < destroyables.size(); i++) {
						if (x >= destroyables.get(i).getX()
								&& x <= destroyables.get(i).getX() + destroyables.get(i).getSize()
								&& y >= destroyables.get(i).getY()
								&& y <= destroyables.get(i).getY() + destroyables.get(i).getSize()) {
//							this.destroySoldier(destroyables.get(i));
//							this.turnChange();
//							destroyRandom();
//							this.turnChange();
							this.destroying = true;
							this.spear.setPosition(destroyables.get(i).getPositionOnBoard(), true);
							break;
						}
					}
				}
				if (!this.tossCoinTime && this.deploymentPhase) {
					if (!this.deploying && whiteTurn)
						readyToDeploy = whitePlayer.click(x, y);
					if (readyToDeploy != null) {
						readyToDeploy.reset();
						this.soldierNormalX = this.readyToDeploy.getX();

					}
				}
			}
		}
//		blackPlayer.click(x, y);
	}

	public void playMusic(int i) {

		sound.setFile(i);
		sound.play();
		sound.loop();

	}

	public void stopMusic() {

		sound.stop();

	}

	public void playSE(int i) {

		sound.setFile(i);
		sound.play();

	}

	public void destroyableSoldiers() {
//			this.whiteTurn = false;
		destroyables.clear();
		boolean flag = this.whiteDestroys;

		if ((this.board[0] != null && this.board[1] != null) && (this.board[0].getOwner() != flag
				&& this.board[1].getOwner() == flag && (board[0].getNumber() < board[1].getNumber())))
			destroyables.add(board[0]);
		if ((this.board[board.length - 1] != null && this.board[board.length - 2] != null)
				&& (this.board[board.length - 1].getOwner() != flag && this.board[board.length - 2].getOwner() == flag
						&& (board[board.length - 1].getNumber() < board[board.length - 2].getNumber())))
			destroyables.add(board[board.length - 1]);
		for (int i = 1; i < this.board.length - 1; i++) {

			if (this.board[i] != null && this.board[i].getOwner() != flag) {
				if ((this.board[i + 1] != null && this.board[i - 1] != null)
						&& (this.board[i + 1].getOwner() == flag && this.board[i - 1].getOwner() == flag)) {
					if (this.board[i + 1].getNumber() + this.board[i - 1].getNumber() > this.board[i].getNumber())
						destroyables.add(this.board[i]);
				} else if (this.board[i + 1] != null && this.board[i + 1].getOwner() == flag) {
					if (this.board[i + 1].getNumber() > this.board[i].getNumber())
						destroyables.add(this.board[i]);
				} else if (this.board[i - 1] != null && this.board[i - 1].getOwner() == flag) {
					if (this.board[i - 1].getNumber() > this.board[i].getNumber())
						destroyables.add(this.board[i]);
				}
			}

		}

//			else {
//				if(this.board[0] != null && this.board[1] != null && this.board[0].getOwner() && !this.board[1].getOwner() && (board[0].getNumber() < board[1].getNumber()))
//					destroyables.add(board[0]);
//				if(this.board[board.length-1] != null && this.board[board.length-2] != null && this.board[board.length-1].getOwner() && !this.board[board.length-2].getOwner() && (board[board.length-1].getNumber() < board[board.length-2].getNumber()))
//					destroyables.add(board[board.length-1]);
//				for(int i = 1; i < this.board.length - 1;i++) {
//					
//						if(this.board[i] != null && this.board[i].getOwner()) {
//							if(this.board[i+1] != null && this.board[i-1] != null && !this.board[i+1].getOwner() && !this.board[i-1].getOwner()) {
//								if(this.board[i+1].getNumber() + this.board[i-1].getNumber() > this.board[i].getNumber())
//									destroyables.add(this.board[i]);
//							}
//							else if(this.board[i+1] != null && !this.board[i+1].getOwner()) {
//								if(this.board[i+1].getNumber() > this.board[i].getNumber())
//									destroyables.add(this.board[i]);
//							}
//							else if(this.board[i-1] != null && !this.board[i-1].getOwner()) {
//								if(this.board[i-1].getNumber() > this.board[i].getNumber())
//									destroyables.add(this.board[i]);
//							}
//						}
//					
//					
//				}
//			}

	}

	public void phaseChange() {

		this.deploymentPhase = false;
		this.destroyableSoldiers();
	}

//	public void turnChange() {
//		this.whiteTurn = !this.whiteTurn;
//		if (!this.deploymentPhase)
//			destroyableSoldiers();
//		if (destroyables.size() == 0) {
//			callA_VeryDifferentFunction();
//		}
//	}

	public void destroySoldier(Soldier s) {

		for (int i = 0; i < this.board.length; i++) {
			if (s == board[i]) {
				board[i] = null;
				break;
			}
		}
	}

//	public void callA_VeryDifferentFunction() {
//
//		if (destroyables.size() == 0) {
//
//			if (this.destroyables.size() == 0) {
//				this.currentAnimation = 5;
//				this.animation = true;
//			}
//
//		}
//
//	}

	public void preEndGame() {
		for (int i = 0; i < board.length; i++) {

			if (this.board[i] != null) {
				if (this.board[i].getOwner()) {
					endWhiteArmy.add(this.board[i]);
				} else {
					endBlackArmy.add(this.board[i]);
				}
			}

		}
	}

	public void endGame() {

		this.endGame = true;
//		ArrayList<Integer> white = new ArrayList<Integer>();
//		ArrayList<Integer> black = new ArrayList<Integer>();

//		int a = white.size();
//		int b = black.size();
//		int sum = 0;
//		int sumb = 0;
		if (endWhiteArmy.size() > endBlackArmy.size()) {
			this.sizeCheck = true;
		} else if (endWhiteArmy.size() < endBlackArmy.size()) {
			this.sizeCheck = true;
		} else {
			this.sumCheck = true;
			for (int i = 0; i < endWhiteArmy.size(); i++) {
				this.whiteSum += endWhiteArmy.get(i).getNumber();
				this.blackSum += endBlackArmy.get(i).getNumber();
//				sum += white.get(i);
//				sumb += black.get(i);
//
//				System.out.println(sum);
//				System.out.println(sumb);
//
//				if (sum > sumb) {
//					System.out.println("White is winner");
//				} else if (sumb > sum) {
//					System.out.println("Black is winner");
//				} else {
//					System.out.println("Draw");
//				}
			}

		}

	}

	public void drawCoin(Graphics g) {

	}

}

package br.fsa.wumpus.kernel;

import java.util.Random;

import br.fsa.wumpus.interfaces.WumpusGame;

public class Kernel implements WumpusGame {
	public static final int EAST  = 0;
	public static final int SOUTH = 1;
	public static final int WEST  = 2;
	public static final int NORTH = 3;
	
	public static final int PLAYER = 1;	  // JOGADOR
	public static final int GOLD   = 1 << 1; // OURO
	public static final int WUMPUS = 1 << 2; // WUMPUS
	public static final int PIT    = 1 << 3; // POÇO
	public static final int BREEZE = 1 << 4; // BRISA
	public static final int STINK  = 1 << 5; // FEDOR
	public static final int SHINE  = 1 << 6; // CINTILÂNCIA
	
	Random random;
	
	private int[][] cave;
	private boolean arrow;
	private int dir;
	private boolean wumpus;
	
	public Kernel() {
		cave = new int[4][4];
		arrow = true;
		wumpus = true;
		dir = EAST;
		cave[3][0] = PLAYER;
		random = new Random();
		newGame();
	}
	
	public void newGame() {
		startBoard();
	}
	
	private void startBoard() {
		putGold();
		putWumpus();
		putPit();
		putPit();
		putPit();
	}
	
	private void putGold() {
		int x = 0;
		int y = 0;
		
		do {
			x = random.nextInt(4);
			y = random.nextInt(4);
		} while (!isValidPos(x, y));
		
		cave[x][y] |= GOLD;
		putSensors(x, y, GOLD);
	}
	
	private void putWumpus() {
		int x = 0;
		int y = 0;
		
		do {
			x = random.nextInt(4);
			y = random.nextInt(4);
		} while (!isValidPos(x, y));
		
		cave[x][y] |= WUMPUS;
		putSensors(x, y, WUMPUS);
	}
	
	private void putPit() {
		int x = 0;
		int y = 0;
		
		do {
			x = random.nextInt(4);
			y = random.nextInt(4);
		} while (!isValidPos(x, y));
		
		cave[x][y] |= PIT;
		putSensors(x, y, PIT);
	}
	
	private void putSensors(int x, int y, int TYPE) {
		if (x - 1 >= 0) {
			cave[x - 1][y] |= TYPE;
		}
		
		if (x + 1 < 4) {
			cave[x + 1][y] |= TYPE;
		}
		
		if (y - 1 >= 0) {
			cave[x][y - 1] |= TYPE;
		}
		
		if (y + 1 < 4) {
			cave[x][y + 1] |= TYPE;
		}
	}
	
	private boolean isValidPos(int x, int y) {
		if((x > 1 && y < 2) || ((cave[x][y] & GOLD) == GOLD) || ((cave[x][y] & WUMPUS) == WUMPUS) || ((cave[x][y] & PIT) == PIT)) {
			return false;
		} else {			
			return true;
		}
	}
	
	public String shoot() {
		if (!arrow) {
			return "0";
		}
		if (arrow) {
			arrow = false;
			if (testShoot()) {
				return "3";
			}
		}
		return "1";
	}
	
	private boolean testShoot() {
		int[] pos = getPlayerPos();
		
		if (dir == NORTH) {
			if (pos[0] - 1 >= 0) {
				if ((cave[pos[0] - 1][pos[1]] & WUMPUS) == WUMPUS) {
					wumpus = false;
					return true;
				}
			}
			
			if (pos[0] - 2 >= 0) {
				if ((cave[pos[0] - 2][pos[1]] & WUMPUS) == WUMPUS) {
					wumpus = false;
					return true;
				}
			}
			
			if (pos[0] - 3 >= 0) {
				if ((cave[pos[0] - 3][pos[1]] & WUMPUS) == WUMPUS) {
					wumpus = false;
					return true;
				}
			}
		}
		
		if (dir == SOUTH) {
			if (pos[0] + 1 < 4) {
				if ((cave[pos[0] + 1][pos[1]] & WUMPUS) == WUMPUS) {
					wumpus = false;
					return true;
				}
			}
			
			if (pos[0] + 2 < 4) {
				if ((cave[pos[0] + 2][pos[1]] & WUMPUS) == WUMPUS) {
					wumpus = false;
					return true;
				}
			}
			
			if (pos[0] + 3 < 4) {
				if ((cave[pos[0] + 3][pos[1]] & WUMPUS) == WUMPUS) {
					wumpus = false;
					return true;
				}
			}
		}
		
		if (dir == EAST) {
			if (pos[1] + 1 < 4) {
				if ((cave[pos[0]][pos[1] + 1] & WUMPUS) == WUMPUS) {
					wumpus = false;
					return true;
				}
			}
			
			if (pos[1] + 2 < 4) {
				if ((cave[pos[0]][pos[1] + 2] & WUMPUS) == WUMPUS) {
					wumpus = false;
					return true;
				}
			}
			
			if (pos[1] + 3 < 4) {
				if ((cave[pos[0]][pos[1] + 3] & WUMPUS) == WUMPUS) {
					wumpus = false;
					return true;
				}
			}
		}
		
		if (dir == WEST) {
			if (pos[1] - 1 >= 0) {
				if ((cave[pos[0]][pos[1] - 1] & WUMPUS) == WUMPUS) {
					wumpus = false;
					return true;
				}
			}
			
			if (pos[1] - 2 >= 0) {
				if ((cave[pos[0]][pos[1] - 2] & WUMPUS) == WUMPUS) {
					wumpus = false;
					return true;
				}
			}
			
			if (pos[1] - 3 >= 0) {
				if ((cave[pos[0]][pos[1] - 3] & WUMPUS) == WUMPUS) {
					wumpus = false;
					return true;
				}
			}
		}
		
		return false;
	}
	
	public int[] getPlayerPos() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if((cave[i][j] & PLAYER) == PLAYER) {
					int[] r = new int[] {i, j};
					return r;
				}
			}
		}
		int[] r = new int[] {-1, -1};
		return r;
	}
	
	public void turnR() {
		dir = ++dir % 4;
	}
	
	public void turnL() {
		dir--;
		if(dir < 0) {
			dir = 3;
		}
	}
	
	public void walk() {
		// TODO: Implementar método de andar
		int[] pos = getPlayerPos();
		
		if (dir == NORTH && pos[0] - 1 >= 0) {
			cave[pos[0]][pos[1]]--;
			cave[pos[0] - 1][pos[1]]++;
		}

		if (dir == SOUTH && pos[0] + 1 < 4) {
			cave[pos[0]][pos[1]]--;
			cave[pos[0] + 1][pos[1]]++;
		}
		
		if (dir == EAST && pos[1] + 1 < 4) {
			cave[pos[0]][pos[1]]--;
			cave[pos[0]][pos[1] + 1]++;
		}
		
		if (dir == WEST && pos[1] - 1 >= 0) {
			cave[pos[0]][pos[1]]--;
			cave[pos[0]][pos[1] - 1]++;
		}
	}
	
	public int[][] getCave() {
		return cave;
	}
	
	public boolean getArrow() {
		return arrow;
	}
	
	public boolean getWumpus() {
		return wumpus;
	}
	
	public int getDirection() {
		return dir;
	}
	
	public boolean hasDied() {
		int[] pos = getPlayerPos();
		if (((cave[pos[0]][pos[1]] & WUMPUS) == WUMPUS) ||
			((cave[pos[0]][pos[1]] & PIT) 	== PIT)) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean hasGold() {
		int[] pos = getPlayerPos();
		if ((cave[pos[0]][pos[1]] & GOLD) == GOLD) {
			return true;
		} else {
			return false;
		}
	}
	
}

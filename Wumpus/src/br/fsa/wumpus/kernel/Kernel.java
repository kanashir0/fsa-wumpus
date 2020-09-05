package br.fsa.wumpus.kernel;

import java.util.Random;

import fsa.br.wumpus.interfaces.WumpusGame;

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
		newGame();
		random = new Random();
	}
	
	public void newGame() {
		startBoard();
	}
	
	private void startBoard() {
		putGold();
		putWumpus();
		putPit();
		putSensors();
	}
	
	private void putGold() {
		int x = 0;
		int y = 0;
		
		do {
			x = random.nextInt(4);
			y = random.nextInt(4);
		} while (!isValidPos(x, y));
		
		cave[x][y] |= GOLD;
	}
	
	private void putWumpus() {
		int x = 0;
		int y = 0;
		
		do {
			x = random.nextInt(4);
			y = random.nextInt(4);
		} while (!isValidPos(x, y));
		
		cave[x][y] |= WUMPUS;
	}
	
	private void putPit() {
		int x = 0;
		int y = 0;
		
		do {
			x = random.nextInt(4);
			y = random.nextInt(4);
		} while (!isValidPos(x, y));
		
		cave[x][y] |= PIT;
	}
	
	private void putSensors() {
		// TODO: Implementar método de colocar os sensores
		
	}
	
	private boolean isValidPos(int x, int y) {
		if((x > 1 && y < 2) || ((cave[x][y] & GOLD) == GOLD) || ((cave[x][y] & WUMPUS) == WUMPUS) || ((cave[x][y] & PIT) == PIT)) {
			return false;
		} else {			
			return true;
		}
	}
	
	public boolean shoot() {
		if (arrow) {
			arrow = false;
			if(testShoot()) {
				return true;
			}
		}
		return false;
	}
	
	private boolean testShoot() {
		// TODO: Implementar método de atirar
		// Percorrer caminho na direção que o player está olhando e se tiver o Wumpus, ele matará.
		return true;
	}
	
	private int[] getPlayerPos() {
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
	}
	
	public int[][] getCave() {
		return cave;
	}
	
}

package br.fsa.wumpus.cli;

import br.fsa.wumpus.interfaces.WumpusGame;
import br.fsa.wumpus.kernel.Kernel;

public class WumpusCLI implements WumpusGame {
	
	Kernel game;
	String msg = "Game Starts";
	
	@Override
	public String shoot() {
		
		if(game.shoot().equals("3")) {
			msg = "You killed the Wumpus!";
		} else if (game.shoot().equals("1")) {
			msg = "You missed the Wumpus, caution he still alive!";
		} else if (game.shoot().equals("0")){
			msg = "You are out of arrows!";
		}
		
		updateBoard();
		return msg;
	}

	@Override
	public void turnL() {
		game.turnL();
		msg = "Turned left!";
		updateBoard();
		
	}

	@Override
	public void turnR() {
		game.turnR();
		msg = "Turned right";
		updateBoard();
		
	}

	@Override
	public void walk() {
		game.walk();
		if (game.hasDied()) {
			msg = "You walked and died!";
			System.exit(0);
		} else {
			msg = "You walked and found nothing.";
		}
		
		if (game.hasGold()) {
			msg = "You walked and found the Gold!!";
		}
		updateBoard();
	}

	@Override
	public void newGame() {
		game = new Kernel();
		updateBoard();
	}
	
	public void updateBoard() {
		System.out.println("\n------ WUMPUS WORLD ------\n");
		int[][] cave = game.getCave();
		
		for(int i = 0; i < 4; i++) {
			System.out.print("|");
			for(int j = 0; j < 4; j++) {
				System.out.print(cave[i][j] + "|");
			}
			System.out.println();
		}
		
		System.out.println("\n    Arrow: " + game.getArrow()
						 + "\n   Wumpus: " + game.getWumpus()
//						 + "\nGold: " + getGold()
						 + "\n Position: " + "(" + game.getPlayerPos()[0] + ", " + game.getPlayerPos()[1] + ")"
						 + "\nDirection: " + game.getDirection()
						 + "\n  Message: " + msg
						 + "\n\nYou can exit the game at any time by typing 'exit'\n "
		);
	}
	
	public void doNothing() {
		msg = "I do not understand, try another command!";
		updateBoard();
	}

}

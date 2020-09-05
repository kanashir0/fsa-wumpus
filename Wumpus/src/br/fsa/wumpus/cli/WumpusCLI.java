package br.fsa.wumpus.cli;

import br.fsa.wumpus.kernel.Kernel;
import fsa.br.wumpus.interfaces.WumpusGame;

public class WumpusCLI implements WumpusGame {
	
	Kernel game;
	
	@Override
	public boolean shoot() {
		// TODO Auto-generated method stub
		if(game.shoot()) {
			System.out.println("Flecha atirada");
			return true;
		} else {
			System.out.println("Sem flecha");
			return false;
		}
	}

	@Override
	public void turnL() {
		// TODO Auto-generated method stub
		game.turnL();
		atualizaBoard();
		
	}

	@Override
	public void turnR() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void walk() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void newGame() {
		// TODO Auto-generated method stub
		game = new Kernel();
	}
	
	private void atualizaBoard() {
		System.out.println("------ WUMPUS WORLD ------");
		int[][] cave = game.getCave();
		
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				
			}
		}
	}

}

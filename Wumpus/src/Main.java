import java.util.Scanner;

import br.fsa.wumpus.cli.WumpusCLI;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WumpusCLI game = new WumpusCLI();
		try (Scanner scan = new Scanner(System.in)) {
			game.newGame();
			
			System.out.print("Command: ");
			String cmd = "";
			while(!(cmd = scan.next()).toLowerCase().equals("exit")) {
				switch(cmd) {
				case "walk":
					game.walk();
					break;
					
				case "shoot":
					game.shoot();
					break;
				
				case "turnl":
					game.turnL();
					break;
					
				case "turnr":
					game.turnR();
					break;
					
				default:
					game.doNothing();
					break;
				}
			}
			if (cmd == "exit") {
				System.exit(0);
			}
		}
	}

}

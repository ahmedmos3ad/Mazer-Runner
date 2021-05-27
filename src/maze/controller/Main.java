package maze.controller;



public class Main {
	public static void main(String [] args) {
		Game game = new Game();
		game.getGui().setGame(game);
		game.getGui().FireUp();
	}
}

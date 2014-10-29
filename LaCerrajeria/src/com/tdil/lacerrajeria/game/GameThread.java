package com.tdil.lacerrajeria.game;

public class GameThread extends Thread {

	private Game game;
	
	@Override
	public void run() {
		while (GameStatus.STARTED == game.getStatus()) {
			game.advance();
			try {
				sleep(game.getPauseMillis());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

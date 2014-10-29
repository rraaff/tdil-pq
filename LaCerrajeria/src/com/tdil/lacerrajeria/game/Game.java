package com.tdil.lacerrajeria.game;

public class Game {

	private GameStatus status;
	
	private int lifes = 10;
	
	private int score = 0;
	
	private Board board;

	public GameStatus getStatus() {
		return status;
	}

	public void setStatus(GameStatus status) {
		this.status = status;
	}

	public int getLifes() {
		return lifes;
	}

	public void setLifes(int lifes) {
		this.lifes = lifes;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void advance() {
		// TODO Auto-generated method stub
		
	}

	public long getPauseMillis() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
}

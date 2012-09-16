package edu.madison.a2a;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.simple.graphics.SGButtonBase;
import org.simple.graphics.SGButtonEvent;
import org.simple.graphics.SGObject;
import org.simple.graphics.SGRoundedRect;
import org.simple.io.Player;
import org.simple.io.Player.TempPlayer;
import org.simple.io.ServerProgram;


public class A2AServer extends ServerProgram implements A2AConstants {
	
	private StartButton start;
	
	private List<A2APlayer> players;
	private int gameState;
	private A2APlayer currentHost;
	
	private void setGameState(int gameState) {
		this.gameState = gameState;
		blastCommand(new Command("set_game_state", gameState));
	}
	
	public static A2AServer getInstance() {
		return (A2AServer)ServerProgram.getInstance();
	}
	
	public void setup() {
		players = new ArrayList<A2APlayer>();
		RedCard.init();
		GreenCard.init();
		setGameState(PRE_GAME);
		start = new StartButton();
		this.addButton(start);
		this.startServer(55234);
	}
	
	private void startGame() {
		setGameState(WAIT_FOR_CARDS);
		for (A2APlayer p : players) {
			p.initDeck();
		}
	}

	@Override
	public void addShapesTo(List<SGObject> nextFrame) {
		start.addShapesTo(nextFrame);
	}

	private int playerId = 0;
	@Override
	public void playerJoined(TempPlayer p) {
		A2APlayer player = new A2APlayer(p, playerId);
		players.add(player);
		playerId++;
		System.out.println("Player connected!");
		switch(gameState) {
		case WAIT_FOR_CARDS:
		case CHOOSE_CARDS:
			player.initDeck();
		}
	}

	@Override
	public void playerDisconnected(Player p) {
		blastCommand(new Command("player_left", ((A2APlayer)p).getId()));
		((A2APlayer)p).onDisconnect();
		players.remove(p);
		System.out.println("Player "+((A2APlayer)p).getId()+" left.");
	}

	public int getGameState() {
		return gameState;
	}
	
	public A2APlayer getCurrentHost() {
		return currentHost;
	}
	
	public void blastCommand(Command command) {
		for (Player p : players) {
			p.sendCommand(command);
		}
	}
	
	private class StartButton extends SGButtonBase {

		protected StartButton() {
			super(new SGRoundedRect(0,0,300,200,Color.GREEN,true));
		}

		@Override
		public void onMousePress(SGButtonEvent e) {}
		@Override
		public void onHoverStart(SGButtonEvent e) {}
		@Override
		public void onHover(SGButtonEvent e) {}
		@Override
		public void onHoverEnd(SGButtonEvent e) {}
		@Override
		public void onMouseRelease(SGButtonEvent e) {}
		
		@Override
		public void onClick(SGButtonEvent e) {
			startGame();
		}
	}
	
	public static void main(String[] args) {
		new A2AServer();
	}
}

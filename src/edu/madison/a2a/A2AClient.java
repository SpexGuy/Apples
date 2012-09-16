package edu.madison.a2a;

import java.util.List;

import org.simple.graphics.SGObject;
import org.simple.io.ClientProgram;

public class A2AClient extends ClientProgram implements A2AConstants {
	
	private int score;
	private Hand redDeck;
	private GreenCard currentGreen;
	private RedCard selectedCard;
	private int gameState;
	
	public static A2AClient getInstance() {
		return (A2AClient)ClientProgram.getInstance();
	}
	
	public static void main(String[] args) {
		new A2AClient();
	}
	
	@Override
	public void addShapesTo(List<SGObject> nextFrame) {
		redDeck.addShapesTo(nextFrame);
	}
	
	@Override
	public void setup() {
		score = 0;
		redDeck = new Hand(0, FRAME_HEIGHT-HAND_HEIGHT, HAND_WIDTH, HAND_HEIGHT);
		this.getFrame().setDimensions(FRAME_WIDTH, FRAME_HEIGHT);
		this.connect("127.0.0.1", 55234);
	}
	
	@Override
	public void commandReceived(Object command) {
		Command message = (Command) command;
		String action = message.getCommand();
		if (action.equals("clear_selection")) {
			selectedCard = null;
		} else if (action.equals("select_card")) {
			selectedCard = (RedCard)message.getBaggage();
		} else if (action.equals("new_green_card")) {
			currentGreen = (GreenCard)message.getBaggage();
		} else if (action.equals("remove_card")) {
			redDeck.remove((RedCard)message.getBaggage());
		} else if (action.equals("add_card")) {
			redDeck.add((RedCard)message.getBaggage());
		} else if (action.equals("player_left")) {
			System.out.println("player "+message.getBaggage()+" left.");
		} else if (action.equals("set_game_state")) {
			this.gameState = (int)message.getBaggage();
		} else {
			System.err.println("Unknown command: "+action);
		}
	}

	@Override
	public Object requestReceived(Object request) {
		// TODO Auto-generated method stub
		return null;
	}

	public void hilight(RedCard card) {
		redDeck.select(card);
	}

	public Hand getHand() {
		return redDeck;
	}

	public void select(RedCard card) {
		sendCommand(new Command("select_card", card));
	}
}

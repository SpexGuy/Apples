package edu.madison.a2a;

import java.util.HashSet;
import java.util.Set;

import org.simple.io.Player;

public class A2APlayer extends Player implements A2AConstants {

	private final int id;

	private Set<RedCard> cards;
//	private String username;
	private RedCard selectedCard;
	private int score;
	
	public A2APlayer(TempPlayer temp, int id) {
		super(temp);
		this.id = id;
		this.cards = new HashSet<RedCard>();
//		this.username = "player"+id;
//		TODO: username
		this.score = 0;
	}
	
	public RedCard getSelectedCard() {
		return selectedCard;
	}
	
	public void removeCard(RedCard card) {
		cards.remove(card);
		sendCommand("remove_card", card);
	}
	public void addCard(RedCard card) {
		cards.add(card);
		sendCommand("add_card", card);
	}
	public void clearSelection() {
		RedCard.getDeck().add(selectedCard);
		selectedCard = null;
		sendCommand("clear_selection");
	}
	public void initDeck() {
		for (int c = 0; c < 5; c++) {
			addCard(RedCard.getDeck().drawRandomCard());
		}
	}
	
	public void sendCommand(String command, Object baggage) {
		sendCommand(new Command(command, baggage));
	}
	public void sendCommand(String command) {
		sendCommand(command, null);
	}
	
	public static void main(String[] args) {
		new A2AServer();
	}

	@Override
	protected void commandReceived(Object command) {
		if (command instanceof Command) {
			Command message = (Command)command;
			String action = message.getCommand();
			System.out.println("Command received: "+action);
			if (action.equals("set_username")) {
//				this.username = (String)message.getBaggage();
			} else if (action.equals("select_card")) {
				if (selectedCard == null &&
						A2AServer.getInstance().getGameState() == WAIT_FOR_CARDS &&
						A2AServer.getInstance().getCurrentHost() != this &&
						message.getBaggage() instanceof RedCard &&
						cards.contains(message.getBaggage())) {
					this.selectedCard = (RedCard)message.getBaggage();
					this.cards.remove(selectedCard);
					sendCommand("select_card", selectedCard);
					sendCommand("remove_card", selectedCard);
				} else {
					System.err.println("Cheating Client!");
					kick();
				}
			} else {
				System.err.println("unknown action: "+action);
			}
		} else {
			System.err.println("Poorly Made Cheating Client!");
			kick();
		}
	}

	@Override
	protected Object requestReceived(Object request) {
		return null;
	}

	public int getId() {
		return id;
	}
	
	public int getScore() {
		return score;
	}

	public void onDisconnect() {
		RedCard.getDeck().addAll(cards);
	}

}

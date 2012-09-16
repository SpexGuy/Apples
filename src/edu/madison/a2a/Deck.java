package edu.madison.a2a;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class Deck<T extends Card> {
	private List<T> cards;
	
	public Deck() {
		this.cards = new LinkedList<T>();
	}
	
	public T drawRandomCard() {
		if (cards.size() == 0) return null;
		return cards.remove(randomInt(0, cards.size()));
	}
	
	public void add(T card) {
		cards.add(card);
	}
	
	public void addAll(Collection<T> list) {
		this.cards.addAll(list);
	}
	
	public static int randomInt(int min, int max) {
		return (int)(Math.random()*(max-min)+min);
	}
}

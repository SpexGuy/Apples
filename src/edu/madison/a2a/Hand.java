package edu.madison.a2a;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.simple.graphics.SGObject;
import org.simple.graphics.SGRect;

public class Hand implements A2AConstants {
	
	private int x, y, width, height;
	private RedCard confirm;
	private List<RedCard> cards;
	private List<SGObject> background;
	private SubmitButton submit;
	
	public Hand(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		cards = new ArrayList<RedCard>();
		background = new ArrayList<SGObject>();
		background.add(new SGRect(x,y,width,height,Color.LIGHT_GRAY,true));
		background.add(new SGRect(x,y,width,height,5,Color.DARK_GRAY,false));
		submit = new SubmitButton();
	}
	
	public void remove(RedCard card) {
		A2AClient.getInstance().removeButton(card);
		if (confirm.equals(card)) {
			clearHilight();
		}
		cards.remove(card);
	}
	
	public void add(RedCard card) {
		A2AClient.getInstance().addButton(card);
		cards.add(card);
	}
	
	public void clearHilight() {
		A2AClient.getInstance().removeButton(submit);
		confirm = null;
	}
	
	public void select(RedCard card) {
		if (cards.contains(card)) {
			if (confirm != null && confirm.equals(card)) {
				clearHilight();
			} else {
				if (confirm == null) {
					A2AClient.getInstance().addButton(submit);
				}
				confirm = card;
			}
		}
	}
	
	public RedCard getHilighted() {
		return confirm;
	}
	
	public void addShapesTo(List<SGObject> nextFrame) {
		nextFrame.addAll(background);
		if (cards.size() == 0) {
			return;
		}
		int xOff = 0;
		if (cards.size() != 1) {
			xOff = Math.min((width-CARD_WIDTH-2*MED_GAP)/(cards.size()-1), CARD_WIDTH+MED_GAP);
		}
		int handWidth = (cards.size()-1)*xOff+CARD_WIDTH;
		int x = this.x+this.width/2-handWidth/2;
		int y = this.y+this.height/2-CARD_HEIGHT/2;
		for (Card card : cards) {
			if (card == confirm) {
				card.addShapesTo(nextFrame, x, y-SELECT_HEIGHT);
			} else {
				card.addShapesTo(nextFrame, x, y);
			}
			x += xOff;
		}
		if (confirm != null) {
			submit.getBackground().setX(confirm.getX());
			submit.getBackground().setY(confirm.getY()+CARD_HEIGHT);
			submit.addShapesTo(nextFrame);
		}
	}
}

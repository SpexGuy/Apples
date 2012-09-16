package edu.madison.a2a;

import java.awt.Color;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.List;

import org.simple.graphics.SGImage;
import org.simple.graphics.SGObject;
import org.simple.graphics.SGText;

public abstract class Card implements Serializable, A2AConstants {

	private static final long serialVersionUID = -1622503547166994282L;
	
	//Graphics
	protected transient SGImage background;
	protected transient SGText title;
	protected transient SGText description;

	protected Card(String title, String description, SGImage background) {
		this.title = new SGText(title,0,0,Color.BLACK);
		this.title.rotate(Math.PI/2.);
		this.description = new SGText(description, 0, 0, Color.BLACK);
		this.background = background;
	}

	public void addShapesTo(List<SGObject> nextFrame, int x, int y) {
		background.setX(x);
		background.setY(y);
		title.setX(x);
		title.setY(y);
		description.setX(x+CARD_DESC_START);
		description.setY(y+CARD_HEIGHT-description.getHeight()-MED_GAP);
		nextFrame.add(background);
		nextFrame.add(title);
		nextFrame.add(description);
	}
	
	private void writeObject(java.io.ObjectOutputStream out)
		     throws IOException {
		out.writeUTF(GreenCard.join(title.getText(), " "));
		out.writeUTF(GreenCard.join(description.getText(), " "));
	}
	private void readObject(ObjectInputStream in)
		     throws IOException, ClassNotFoundException {
		System.out.println("Reading object");
		String tStr = in.readUTF();
		String dStr = in.readUTF();
		this.title = new SGText(tStr,0,0,Color.BLACK);
		this.title.rotate(Math.PI/2.);
		this.description = new SGText(dStr, 0, 0, CARD_DESC_WIDTH, 14, Color.BLACK);
		resetBackground();
	}
	
	public int getX() {
		return background.getX();
	}
	public int getY() {
		return background.getY();
	}
	
	protected abstract void resetBackground();
}

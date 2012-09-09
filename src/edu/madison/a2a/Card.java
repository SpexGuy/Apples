package edu.madison.a2a;

import java.awt.Color;
import java.util.List;

import org.simple.graphics.SGImage;
import org.simple.graphics.SGObject;
import org.simple.graphics.SGText;

public abstract class Card {

	//Graphics
	protected SGImage background;
	protected SGText title;
	protected SGText description;

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
		description.setX(x);
		description.setY(y);
		nextFrame.add(background);
		nextFrame.add(title);
		nextFrame.add(description);
	}
}

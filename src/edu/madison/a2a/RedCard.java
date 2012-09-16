package edu.madison.a2a;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.simple.graphics.SGButton;
import org.simple.graphics.SGButtonEvent;
import org.simple.graphics.SGImage;
import org.simple.graphics.SGObject;
import org.simple.graphics.SGRect;

public class RedCard extends Card implements SGButton {

	private static final long serialVersionUID = 7257010176823832780L;
		
	//static
	protected static BufferedImage background;
	protected static SGImage getNewBackground() {
		if (background == null) {
			background = new SGImage(0,0,RED_CARD_FRONT_IMAGE_FILE).getImage();
		}
		return new SGImage(0,0,background);
	}

	public static final String RED_CARD_FRONT_IMAGE_FILE = "redcardfrontblank.jpg";
	public static final String RED_CARD_BACK_IMAGE_FILE = "redcardback.jpg";
	public static final String RED_CARD_FILE = "red_apples.A2AR";

	private static Deck<RedCard> cards;
	
	public static void init() {
		cards = new Deck<RedCard>();
		File f = new File(RED_CARD_FILE);
		FileReader r;
		BufferedReader reader;
		try {
			r = new FileReader(f);
			reader = new BufferedReader(r);
			while (true) {
				String line = reader.readLine();
				if (line == null || line.equals("")) {
					break;
				}
				System.out.println(line);
				String[] card = line.split("-=-");
				cards.add(new RedCard(card[0], card[1]));
			}
		} catch (FileNotFoundException e) {
			System.err.println("Red Card Config file not found!");
			System.exit(0);
		} catch (IOException e) {
			System.err.println("Error while loading from the Red Card Config file!");
			e.printStackTrace();
		}
	}
	
	public static Deck<RedCard> getDeck() {
		return cards;
	}
	
	
	
	//instance
	private transient SGObject hilighter;
	private transient boolean mouseOver;

	protected RedCard(String title, String description) {
		super(title, description, getNewBackground());
	}
	
	@Override
	protected void resetBackground() {
		super.background = getNewBackground();
		hilighter = new SGRect(0,0,super.background.getWidth(),
									  super.background.getHeight(),
									  new Color(100,100,100,30), true);
	}
	
	@Override
	public void addShapesTo(List<SGObject> nextFrame, int x, int y) {
		hilighter.setX(x);
		hilighter.setY(y);
		super.addShapesTo(nextFrame, x, y);
		if (mouseOver) {
			nextFrame.add(hilighter);
		}
	}
	
	@Override
	public boolean includes(int x, int y) {
		return x >= super.background.getX() &&
			   y >= super.background.getY() &&
			   x <= super.background.getX() + super.background.getWidth() &&
			   y <= super.background.getY() + super.background.getHeight();
	}

	@Override
	public void onMousePress(SGButtonEvent e) {
		A2AClient.getInstance().hilight(this);
	}
	
	@Override
	public void onHoverStart(SGButtonEvent e) {
		System.out.println("hover start");
		mouseOver = true;
	}

	@Override
	public void onHover(SGButtonEvent e) {
		
	}
	
	@Override
	public void onHoverEnd(SGButtonEvent e) {
		System.out.println("hover end");
		mouseOver = false;
	}

	@Override
	public void onMouseRelease(SGButtonEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClick(SGButtonEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAdd() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onRemove() {
		// TODO Auto-generated method stub
		
	}
}

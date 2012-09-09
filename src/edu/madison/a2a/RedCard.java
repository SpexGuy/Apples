package edu.madison.a2a;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.simple.graphics.SGImage;

public class RedCard extends Card {
	protected static BufferedImage background;
	protected static SGImage getNewBackground() {
		if (background == null) {
			background = new SGImage(0,0,RED_CARD_FRONT_IMAGE_FILE).getImage();
		}
		return new SGImage(0,0,background);
	}

	protected RedCard(String title, String description) {
		super(title, description, getNewBackground());
	}

	//static methods (card holders)
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
}

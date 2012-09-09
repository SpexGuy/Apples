package edu.madison.a2a;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.simple.graphics.SGImage;

public class GreenCard extends Card {
	
	protected static BufferedImage background;
	protected static SGImage getNewBackground() {
		if (background == null) {
			background = new SGImage(0,0,GREEN_CARD_IMAGE_FILE).getImage();
		}
		return new SGImage(0,0,background);
	}

	public static String join(String[] s, String delimiter) {
	    StringBuffer buffer = new StringBuffer();
	    for (int c = 0; c < s.length-1; c++) {
	        buffer.append(s[c]);
	        buffer.append(delimiter);
	    }
	    buffer.append(s[s.length-1]);
	    return buffer.toString();
	}
	
	protected GreenCard(String title, String[] synons) {
		super(title, join(synons, ", "), getNewBackground());
	}
	
	
	//static methods (card holders)
	public static final String GREEN_CARD_IMAGE_FILE = "";
	public static final String GREEN_CARD_FILE = "green_apples.A2AG";

	private static Deck<GreenCard> cards;
	
	public static void init() {
		File f = new File(GREEN_CARD_FILE);
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
				String[] parts = line.split(":");
				String[] synons = parts[1].split(",");
				cards.add(new GreenCard(parts[0], synons));
			}
		} catch (FileNotFoundException e) {
			System.err.println("Red Card Config file not found!");
			System.exit(0);
		} catch (IOException e) {
			System.err.println("Error while loading from the Red Card Config file!");
			e.printStackTrace();
		}
	}
	
	public static Deck<GreenCard> getDeck() {
		return cards;
	}

}

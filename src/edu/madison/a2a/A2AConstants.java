package edu.madison.a2a;

public interface A2AConstants {
	//states
	public static final int WAIT_FOR_CARDS = 0,
							CHOOSE_CARDS = 1,
							PRE_GAME = -1;
	
	//gameplay
	public static final int HAND_SIZE = 5;
	
	//graphics
	public static final int CARD_WIDTH = 201,
							CARD_HEIGHT = 308,
							CARD_DESC_START = 50,
							CARD_DESC_WIDTH = 145,
							SELECT_HEIGHT = 30,
							SMALL_GAP = 2,
							MED_GAP = 5,
							LARGE_GAP = 10;
	
	//meta graphics
	public static final int HAND_WIDTH = 5*CARD_WIDTH+4*MED_GAP+2*LARGE_GAP,
							HAND_HEIGHT = CARD_HEIGHT+2*LARGE_GAP,
							FRAME_WIDTH = HAND_WIDTH+CARD_WIDTH+2*LARGE_GAP,
							FRAME_HEIGHT = 960;
}

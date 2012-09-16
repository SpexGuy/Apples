package edu.madison.a2a;

import org.simple.graphics.SGButtonBase;
import org.simple.graphics.SGButtonEvent;
import org.simple.graphics.SGImage;

public class SubmitButton extends SGButtonBase {
	
	private static final String IMAGE_FILE = "submitcardbackground.png";
		
	protected SubmitButton() {
		super(new SGImage(0,0,IMAGE_FILE));
	}

	@Override
	public void onMousePress(SGButtonEvent e) {
		if (A2AClient.getInstance().getHand().getHilighted() != null) {
			A2AClient.getInstance().select(A2AClient.getInstance().getHand().getHilighted());
		}
	}

	@Override
	public void onHoverStart(SGButtonEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onHover(SGButtonEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onHoverEnd(SGButtonEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMouseRelease(SGButtonEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClick(SGButtonEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}

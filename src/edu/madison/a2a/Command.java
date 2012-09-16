package edu.madison.a2a;

import java.io.Serializable;

public class Command implements Serializable {
	private static final long serialVersionUID = 632291900166091137L;
	
	private Object baggage;
	private String command;
	
	public Command(String command, Object baggage) {
		this.command = command;
		this.baggage = baggage;
	}
	
	public Object getBaggage() {
		return baggage;
	}
	
	public String getCommand() {
		return command;
	}
}

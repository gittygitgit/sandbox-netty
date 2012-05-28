package com.notatracer.demo.tcpmulticast;

public class SessionRequest {
	private String sessionId;
	private int startingSequenceNumber;

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public int getStartingSequenceNumber() {
		return startingSequenceNumber;
	}

	public void setStartingSequenceNumber(int startingSequenceNumber) {
		this.startingSequenceNumber = startingSequenceNumber;
	}

}

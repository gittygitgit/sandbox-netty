package com.notatracer.demo.tcpmulticast;

public class ActiveSession extends Session {
	private int lastSequenceNumberBroadcast;

	public int getLastSequenceNumberBroadcast() {
		return lastSequenceNumberBroadcast;
	}

	public void setLastSequenceNumberBroadcast(int lastSequenceNumberBroadcast) {
		this.lastSequenceNumberBroadcast = lastSequenceNumberBroadcast;
	}

}

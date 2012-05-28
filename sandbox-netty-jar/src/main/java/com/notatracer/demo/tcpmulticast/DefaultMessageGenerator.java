package com.notatracer.demo.tcpmulticast;

import java.util.ArrayList;
import java.util.List;

public class DefaultMessageGenerator implements MessageGenerator<String> {

	private List<MessageListener<String>> listeners = new ArrayList<MessageListener<String>>();
	int counter = 0;

	public void generate() throws InterruptedException {
		System.out.println("here");
		String msg = "message" + counter++;
		broadcast(msg);
		Thread.sleep(500);
	}

	private void broadcast(String msg) {
		for (MessageListener<String> l : listeners) {
			l.onMessage(msg);
		}
	}

	public void addListener(MessageListener<String> listener) {
		this.listeners.add(listener);
	}

}

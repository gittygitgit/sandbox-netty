package com.notatracer.demo.tcpmulticast;

public class AbstractSessionOutputStream implements SessionOutputStream {

	PersistenceStore store = null;
	MessageBus bus = null;

	public void write(Object o) {
		store.add(o);
		bus.post(o);
	}

}

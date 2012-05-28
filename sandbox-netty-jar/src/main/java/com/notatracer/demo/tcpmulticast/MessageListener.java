package com.notatracer.demo.tcpmulticast;

public interface MessageListener<T extends Object> {
	public void onMessage(T message);
}

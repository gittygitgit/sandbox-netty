package com.notatracer.demo.tcpmulticast;

public interface MessageGenerator<T extends Object> {
	public void generate() throws InterruptedException;
	public void addListener(MessageListener<T> listener);
}

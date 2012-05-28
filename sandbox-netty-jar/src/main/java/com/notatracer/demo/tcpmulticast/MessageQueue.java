package com.notatracer.demo.tcpmulticast;

public interface MessageQueue<T extends Object> {
	public void add(T message);
	public int size();
	public void setMessageListener(MessageListener<T> listener);
}

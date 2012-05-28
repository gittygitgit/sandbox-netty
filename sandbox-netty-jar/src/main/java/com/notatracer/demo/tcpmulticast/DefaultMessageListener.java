package com.notatracer.demo.tcpmulticast;

public class DefaultMessageListener implements MessageListener<String> {

	public void onMessage(String message) {
		System.out.println("onMessage [message=" + message + "]");
	}

}

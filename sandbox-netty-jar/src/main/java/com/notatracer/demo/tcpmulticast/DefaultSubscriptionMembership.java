package com.notatracer.demo.tcpmulticast;


public class DefaultSubscriptionMembership implements SubscriptionMembershipDelegate {

	MessageListener<Object> handler;

	public void onMessage(Object event) {
		handler.onMessage(event);
	}

}

package com.notatracer.demo.tcpmulticast;


public class SessionBus implements MessageBus {

	private SubscriptionMembershipDelegate subscribers = new DefaultSubscriptionMembership();

	public void post(Object event) {
		dispatch(event);
	}

	private void dispatch(Object event) {
		subscribers.onMessage(event);
	}
}

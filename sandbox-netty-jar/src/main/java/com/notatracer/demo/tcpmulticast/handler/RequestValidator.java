package com.notatracer.demo.tcpmulticast.handler;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;
import org.jboss.netty.channel.group.DefaultChannelGroup;

import com.notatracer.demo.tcpmulticast.SessionRequest;

public class RequestValidator extends SimpleChannelUpstreamHandler {

	private String activeSession;

	public RequestValidator(String sessionToServe) {
		activeSession = sessionToServe;
	}

	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
		super.messageReceived(ctx, e);
		SessionRequest request = (SessionRequest) e.getMessage();
		if (!validate(request)) {
			e.getChannel().close();
		} else {
			ctx.getPipeline().addLast("subscription-handler", new SubscriptionHandler(new DefaultChannelGroup()));
		}
	}

	private boolean validate(SessionRequest request) {
		return request.getSessionId() == null || request.getSessionId().equals(activeSession);
	}
}

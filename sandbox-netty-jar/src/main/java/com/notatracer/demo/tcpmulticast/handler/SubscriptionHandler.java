package com.notatracer.demo.tcpmulticast.handler;

import org.jboss.netty.channel.ChannelEvent;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.jboss.netty.channel.group.ChannelGroup;

public class SubscriptionHandler extends SimpleChannelHandler {

	private ChannelGroup channelGroup = null;

	public SubscriptionHandler(ChannelGroup channelGroup) {
		super();
		this.channelGroup = channelGroup;
	}

	@Override
	public void handleDownstream(ChannelHandlerContext ctx, ChannelEvent e) throws Exception {
		super.handleDownstream(ctx, e);
	}

	@Override
	public void channelOpen(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
		super.channelOpen(ctx, e);
		channelGroup.add(ctx.getChannel());
		if (channelGroup.size() == 1)
			ctx.getPipeline().addLast("message-gen", new MessageGenHandler(channelGroup));
	}

	@Override
	public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
		if (channelGroup.isEmpty())
			ctx.getPipeline().remove("message-gen");
		super.channelClosed(ctx, e);
	}

}

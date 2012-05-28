package com.notatracer.example.channelgroup;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.jboss.netty.channel.group.ChannelGroup;

public class FooHandler extends SimpleChannelHandler {
	private ChannelGroup allChannels = null;

	public FooHandler(ChannelGroup allChannels) {
		super();
		this.allChannels = allChannels;
	}

	@Override
	public void channelOpen(ChannelHandlerContext ctx, ChannelStateEvent e) {
		// Add all open channels to the global group so that they are
		// closed on shutdown.
		allChannels.add(e.getChannel());
	}
}

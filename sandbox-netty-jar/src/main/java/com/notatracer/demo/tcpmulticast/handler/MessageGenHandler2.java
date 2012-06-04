package com.notatracer.demo.tcpmulticast.handler;

import org.jboss.netty.channel.SimpleChannelHandler;
import org.jboss.netty.channel.group.ChannelGroup;
import org.jboss.netty.channel.group.ChannelGroupFuture;
import org.jboss.netty.channel.group.ChannelGroupFutureListener;

import com.notatracer.demo.tcpmulticast.MessageListener;

public class MessageGenHandler2 extends SimpleChannelHandler implements MessageListener<Object> {

	private ChannelGroup channelGroup;

	public MessageGenHandler2(ChannelGroup channelGroup) {
		super();
		this.channelGroup = channelGroup;
	}

	public void onMessage(Object message) {
		ChannelGroupFuture write = channelGroup.write(message);
		write.addListener(new ChannelGroupFutureListener2());
	}

	class ChannelGroupFutureListener2 implements ChannelGroupFutureListener {

		public void operationComplete(ChannelGroupFuture future) throws Exception {
			System.out.println("write completed on all ");
		}

	}

}

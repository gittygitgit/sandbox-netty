package com.notatracer.example.time;

import java.util.Date;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;


import static org.jboss.netty.buffer.ChannelBuffers.*;

public class TimeClientHandler extends SimpleChannelHandler {

	private final ChannelBuffer buf = dynamicBuffer();

	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
		// ChannelBuffer m = (ChannelBuffer) e.getMessage();
		// buf.writeBytes(m);
		//
		// if (buf.readableBytes() >= 4) {
		// long currentTimeMillis = buf.readInt() * 1000L;
		// System.out.println(new Date(currentTimeMillis));
		// e.getChannel().close();
		// }
		UnixTime m = (UnixTime) e.getMessage();
		System.out.println(m);
		e.getChannel().close();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) {
		e.getCause().printStackTrace();
		e.getChannel().close();
	}
}

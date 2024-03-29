package com.notatracer.example.time;

import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelFutureListener;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.SimpleChannelHandler;


public class TimeServerHandler extends SimpleChannelHandler {
	@Override
	public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) {
		// Channel ch = e.getChannel();
		//
		// ChannelBuffer time = ChannelBuffers.buffer(4);
		// time.writeInt((int) (System.currentTimeMillis() / 1000));
		//
		// ChannelFuture f = ch.write(time);
		//
		// f.addListener(new ChannelFutureListener() {
		// public void operationComplete(ChannelFuture future) {
		// Channel ch = future.getChannel();
		// ch.close();
		// }
		// });

		UnixTime time = new UnixTime((int)(System.currentTimeMillis() / 1000));
		ChannelFuture f = e.getChannel().write(time);
		f.addListener(ChannelFutureListener.CLOSE);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) {
		e.getCause().printStackTrace();
		e.getChannel().close();
	}
}

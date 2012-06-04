package com.notatracer.demo.tcpmulticast.handler;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.replay.ReplayingDecoder;
import org.jboss.netty.handler.codec.replay.VoidEnum;

import com.notatracer.demo.tcpmulticast.SessionRequest;

public class RequestDecoder extends ReplayingDecoder<VoidEnum> {

	@Override
	protected Object decode(ChannelHandlerContext ctx, Channel channel, ChannelBuffer buffer, VoidEnum state)
			throws Exception {
		char msgType = (char) buffer.readByte();
		String sessionId = null;
		int firstSequenceNumber = -1;
		if (msgType != 'L') {
			return null;
		} else {
			sessionId = new String(buffer.readBytes(4).array()); 
			byte[] b = new byte[4];
			buffer.getBytes(5, b);
			firstSequenceNumber = Integer.parseInt(new String(b));
		}
		return new SessionRequest(sessionId, firstSequenceNumber);
	}
}

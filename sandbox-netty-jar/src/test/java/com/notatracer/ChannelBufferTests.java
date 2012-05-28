package com.notatracer;

import java.nio.ByteOrder;

import junit.framework.TestCase;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;

public class ChannelBufferTests extends TestCase {

	
	public void testCreateBuffer() {
		ChannelBuffer buff = ChannelBuffers.directBuffer(256);
		buff = ChannelBuffers.dynamicBuffer();
		byte[] bArray = new byte[256];
		buff=ChannelBuffers.wrappedBuffer(bArray);
	}
	
	public void testBuffer_BigEndian() {
		ChannelBuffer buff = ChannelBuffers.wrappedBuffer("Mike test".getBytes());
		assertEquals(9, buff.capacity());
		assertEquals('M', (char)buff.getByte(0));
		for (int i = 0; i < buff.capacity(); i++) {
			byte b = buff.getByte(i);
			System.out.println((char)b);
		}
	}

	public void testBuffer_LittleEndian() {
		ChannelBuffer buff = ChannelBuffers.wrappedBuffer(ByteOrder.LITTLE_ENDIAN,"Mike test".getBytes());
		assertEquals(9, buff.capacity());
//		assertEquals('t', (char)buff.getByte(0));
		for (int i = 0; i < buff.capacity(); i++) {
			byte b = buff.getByte(i);
			System.out.println((char)b);
		}
	}

	public void testBuffer_LittleEndian2() {
		ChannelBuffer buff = ChannelBuffers.directBuffer(ByteOrder.LITTLE_ENDIAN, 10);
		buff.writeBytes("Hello".getBytes());
		assertEquals(10, buff.capacity());
//		assertEquals('t', (char)buff.getByte(0));
		for (int i = 0; i < buff.capacity(); i++) {
			byte b = buff.getByte(i);
			System.out.println((char)b);
		}
	}
	
	public void test1() {
		ChannelBuffer buff = ChannelBuffers.wrappedBuffer("Mike test".getBytes());
		System.out.println(buff.readerIndex());
		System.out.println(buff.writerIndex());
		System.out.println(buff.readable());
		System.out.println(buff.capacity());
		System.out.println(buff.readableBytes());
		System.out.println(buff.isDirect());
		System.out.println(buff.toString());
		System.out.println((char)buff.readByte());
		
	}

}

package com.notatracer;

import java.nio.ByteOrder;

import junit.framework.TestCase;

import org.apache.commons.lang.ArrayUtils;
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
	
	public void test2() {
		ChannelBuffer buff = ChannelBuffers.wrappedBuffer("L12340000".getBytes());
		byte[] b = "0000".getBytes();
		System.out.println(b.length);
		System.out.println(buff.getInt(5));
		System.out.println(Integer.toBinaryString(10));
		System.out.println(new Integer(808464432).toString());
		
		System.out.println(Integer.parseInt("00110000001100000011000000110000", 2));
	}
	
	

}

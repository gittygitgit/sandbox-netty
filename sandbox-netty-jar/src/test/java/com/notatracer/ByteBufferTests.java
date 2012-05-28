package com.notatracer;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import junit.framework.TestCase;

public class ByteBufferTests extends TestCase {

	public void testCharacterEncoding() {
		System.out.println(System.getProperty("file.encoding"));
	}

	public void testByteBuffer_EndiannessCharacters() {

		// Char is a 16-bit Unicode character.

		// Cp1252 encoding encodes char's as 2 characters.
		ByteBuffer buff = ByteBuffer.wrap(new byte[10]);

		// endianness is only applicable where a single data item is split
		// across multiple addresses. In this case, each character is
		// stored as two bytes, with the endianness determining the
		// way in which the bytes are stored.
		buff.asCharBuffer().put("Hello");
		System.out.println(toString(buff.array()));
		assertEquals(ByteOrder.BIG_ENDIAN, buff.order());
		buff.rewind();
		buff.order(ByteOrder.LITTLE_ENDIAN);
		buff.asCharBuffer().put("Hello");
		System.out.println(toString(buff.array()));
	}

	public void testByteBuffer_EndiannessInt() {
		// int is a 32 bit signed 2's complement integer

		// Cp1252 encoding encodes char's as 2 characters.
		ByteBuffer buff = ByteBuffer.wrap(new byte[4]);

		// endianness is only applicable where a single data item is split
		// across multiple addresses. In this case, each character is
		// stored as two bytes, with the endianness determining the
		// way in which the bytes are stored.
		buff.asIntBuffer().put(127);
		System.out.println(toString(buff.array()));
		assertEquals(ByteOrder.BIG_ENDIAN, buff.order());
		buff.rewind();
		buff.order(ByteOrder.LITTLE_ENDIAN);
		buff.asIntBuffer().put(127);
		System.out.println(toString(buff.array()));
	}

	public void testByteBuffer() {
		// Cp1252 encoding encodes char's as 2 characters.
		ByteBuffer buff = ByteBuffer.wrap(new byte[10]);

		buff.asCharBuffer().put("Hello");
		System.out.println(toString(buff.array()));
		assertEquals(ByteOrder.BIG_ENDIAN, buff.order());
		buff.rewind();
		buff.order(ByteOrder.LITTLE_ENDIAN);
		buff.asCharBuffer().put("Hello");
		System.out.println(toString(buff.array()));
	}

	public void testByteBuffer_puts() {
		ByteBuffer buff = ByteBuffer.allocate(20);
		assertEquals(20, buff.capacity());
		assertEquals(0, buff.position());
		assertEquals(20, buff.limit());
		assertTrue(buff.limit() <= buff.capacity());

		buff.putInt(4234);
		assertEquals(20, buff.capacity());
		assertEquals(4, buff.position());
		assertEquals(20, buff.limit());

		buff.putChar('R');
		assertEquals(6, buff.position());

	}

	public void testMarkAndReset() {
		// Allocate creates a HeapBuffer
		ByteBuffer buff = ByteBuffer.allocate(10);
		buff.put("test".getBytes());
		assertEquals(4, buff.position());

		buff.mark();
		buff.put("again".getBytes());
		assertEquals(9, buff.position());

		buff.reset();
		assertEquals(4, buff.position());
	}

	public void testAbsolutePut() {
		ByteBuffer buff = ByteBuffer.allocate(10);
		assertEquals(0, buff.position());
		buff.put(3, (byte) 'T');
		assertEquals(0, buff.position());
		// Only HeapBuffers support the array method
		byte[] array = buff.array();
		assertEquals(10, array.length);
		assertEquals((byte) 'T', array[3]);
	}

	public void testOverflow() {
		ByteBuffer buff = ByteBuffer.allocate(10);
		try {
			buff.put("Happy Birthday".getBytes());
			fail("Should have failed with BufferOverflowException.");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void testAbsoluteGet() {
		ByteBuffer buff = ByteBuffer.allocate(10);
	}

	private static String toString(byte[] array) {
		StringBuffer sb = new StringBuffer("[");
		for (int i = 0; i < array.length; i++) {
			sb.append(array[i]);
			if (i < array.length - 1)
				sb.append(", ");
		}
		sb.append("]");
		return sb.toString();
	}
}

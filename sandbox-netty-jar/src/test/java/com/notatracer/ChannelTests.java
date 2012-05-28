package com.notatracer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

import junit.framework.TestCase;

public class ChannelTests extends TestCase {
	public void testSimpleWrite() throws IOException {
		File f = new File("c:\test.out");
		FileOutputStream fos = new FileOutputStream("c:\\test.out");
		FileChannel channel = fos.getChannel();
		assertTrue(channel.isOpen());
		ByteBuffer buff = ByteBuffer.allocate(20);
		buff.put("Happy Birthday".getBytes());
		// If you don't flip before writing, you'll just write 3 null bytes to
		// the file.
		buff.flip();
		channel.write(buff);

		assertEquals(14, channel.position());
	}

	public void testCharBuffer() throws IOException {
		File f = new File("c:\test.out");
		FileOutputStream fos = new FileOutputStream("c:\\test.out");
		FileChannel channel = fos.getChannel();

		// there is a Buffer implementation for all primitives
		CharBuffer cb = CharBuffer.allocate(256);
		cb.append("Once upon a time there was a cat named Boo...");
		cb.flip();
		System.out.println(Charset.defaultCharset());
		Charset charset = Charset.forName("windows-1252");
		channel.write(charset.encode(cb));
		charset.encode(cb);
	}
}

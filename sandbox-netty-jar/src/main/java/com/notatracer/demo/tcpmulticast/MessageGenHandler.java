package com.notatracer.demo.tcpmulticast;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.LifeCycleAwareChannelHandler;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.jboss.netty.channel.group.ChannelGroup;
import org.jboss.netty.channel.group.ChannelGroupFuture;
import org.jboss.netty.channel.group.ChannelGroupFutureListener;

public class MessageGenHandler extends SimpleChannelHandler implements MessageListener<String>,
		LifeCycleAwareChannelHandler {

	private ExecutorService executor = Executors.newSingleThreadExecutor();

	private MessageGenerator<String> mg = new DefaultMessageGenerator();

	Future<?> generateHandle = null;

	private ChannelGroup channelGroup;

	public MessageGenHandler(ChannelGroup channelGroup) {
		super();
		this.channelGroup = channelGroup;
		mg.addListener(this);
		generateHandle = executor.submit(new DefaultMessageGeneratorTask(mg));
	}

	public void onMessage(String message) {
		ChannelGroupFuture write = channelGroup.write(message);
		write.addListener(new ChannelGroupFutureListener2());
	}

	protected static final class DefaultMessageGeneratorTask implements Runnable {
		private MessageGenerator<String> mg = null;

		public DefaultMessageGeneratorTask(MessageGenerator<String> mg) {
			this.mg = mg;
		}

		public void run() {
			try {
				while (true) {
					if (Thread.interrupted()) // Clears interrupted status!
						throw new InterruptedException();

					mg.generate();
				}
			} catch (InterruptedException e) {
				System.out.println("interrupted");
			}

		}

	}

	class ChannelGroupFutureListener2 implements ChannelGroupFutureListener {

		public void operationComplete(ChannelGroupFuture future) throws Exception {
			System.out.println("write completed on all ");
		}

	}

	public void afterAdd(ChannelHandlerContext ctx) throws Exception {
		// NOOP
	}

	public void beforeRemove(ChannelHandlerContext ctx) throws Exception {
		generateHandle.cancel(true);
		executor.shutdown();
	}

	public void afterRemove(ChannelHandlerContext ctx) throws Exception {
		// NOOP
	}

	public void beforeAdd(ChannelHandlerContext ctx) throws Exception {
		// NOOP
	}
}

package com.notatracer.demo.tcpmulticast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class GeneratorRunner {

	/**
	 * @param args
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public static void main(String[] args) throws InterruptedException, IOException {
		ExecutorService executor = Executors.newSingleThreadExecutor();

		MessageGenerator<String> mg = new DefaultMessageGenerator();

		Future<?> generateHandle = null;
		Thread.sleep(2000);

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = null;
		while ((line = br.readLine()) != null) {
			if (line.equals("start")) {
				if (generateHandle != null && !generateHandle.isDone())
					;
				// NOOP
				generateHandle = executor.submit(new DefaultMessageGeneratorTask(mg));
			} else if (line.equals("stop")) {
				if (generateHandle == null || generateHandle.isDone())
					;
				generateHandle.cancel(true);
			}
		}
		executor.shutdown();

	}

	protected static final class DefaultMessageGeneratorTask implements Runnable {
		private MessageGenerator mg = null;

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

}

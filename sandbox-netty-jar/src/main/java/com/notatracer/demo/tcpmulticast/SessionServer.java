package com.notatracer.demo.tcpmulticast;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;
import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

public class SessionServer {

	private ActiveSession activeSession = null;

	public static void main(String[] args) throws InterruptedException, IOException {

		CommandLineParser parser = new PosixParser();

		Options options = new Options();

		options.addOption(OptionBuilder.hasArg().isRequired().withDescription("Session to serve")
				.withArgName("session").create("session"));
		HelpFormatter formatter = new HelpFormatter();
		formatter.printHelp("SessionServer", options);
		String sessionToServe = null;
		try {
			// parse the command line arguments
			CommandLine line = parser.parse(options, args);

			if (line.hasOption("session"))
				sessionToServe = line.getOptionValue("session");
		} catch (ParseException exp) {
			System.out.println("Unexpected exception:" + exp.getMessage());
			return;
		}

		System.out.println("Starting server [session: " + sessionToServe + "]");
		// Configure the server.
		ServerBootstrap bootstrap = new ServerBootstrap(new NioServerSocketChannelFactory(
				Executors.newCachedThreadPool(), Executors.newCachedThreadPool()));
		
		
		bootstrap.setPipelineFactory(new SessionServerPipelineFactory(sessionToServe));

		// Bind and start to accept incoming connections.
		bootstrap.bind(new InetSocketAddress(8080));
	}
}

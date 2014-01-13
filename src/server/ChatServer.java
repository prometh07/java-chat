package server;

import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import java.io.*;

/**
 * @author Radosław Luter (radekpl2@gmail.com)
 *
 */
public class ChatServer implements Runnable {
	private int port = 1410;
	private ServerSocket serverSocket;
	private ConcurrentMap<String, ServerCommand> serverCommands = new ConcurrentHashMap<String, ServerCommand>();
	private ConcurrentMap<String, Client> connectedClients = new ConcurrentHashMap<String, Client>();
	private boolean isRunning = false;
	
	public ChatServer(int port) {
		this();
		this.port = port;
	}
	
	public ChatServer() {
		serverCommands.put((new Disconnect()).getCommand(), new Disconnect());
		serverCommands.put((new Login()).getCommand(), new Login());
		serverCommands.put((new GetUsersInfo()).getCommand(), new GetUsersInfo());
		serverCommands.put((new Broadcast()).getCommand(), new Broadcast());
		serverCommands.put((new Send()).getCommand(), new Send());
	}

	@Override
	public void run() {
		isRunning = true;
		
		try {
			serverSocket = new ServerSocket(port);
		}
		catch (IOException | IllegalArgumentException e) {
			System.out.println("Could not bind socket to given port number.");
			System.out.println(e);
			System.exit(1);
		}
		
		while(isRunning) {
			Socket clientSocket = null;
			
			try {
				clientSocket = serverSocket.accept();
			} 
			catch (IOException e) {
				logEvent("Could not accept incoming connection.");
			}
			
			Client client = new Client(clientSocket, this);
			connectedClients.put(client.getID(), client);
			Thread clientThread = new Thread(client);
			clientThread.start();
			logEvent("Accepted new connection from " + clientSocket.getRemoteSocketAddress().toString());
			client.send("@SERVER:HELLO");
		}
	}

	public static void main(String[] args) {
		ChatServer server = null;
		
		if (args.length == 1) {
			try {
				int port = Integer.parseInt(args[0]);
				server = new ChatServer(port);
			}
			catch (NumberFormatException e) {
				System.out.println("The port number argument could not be casted to int.");
				System.exit(1);
			}
		}
		else {
			server = new ChatServer();
		}
		
		Thread serverThread = new Thread(server);
		serverThread.start();
	}
	
	public void logEvent(String event) {
		System.out.println(new Date() + ": " + event);
	}
}
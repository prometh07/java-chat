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
	private ConcurrentMap<String, ServerCommand> serverCommands = new ConcurrentHashMap<String, ServerCommand>();
	private ConcurrentMap<String, Client> connectedClients = new ConcurrentHashMap<String, Client>();
	boolean isRunning = false;
	
	public ChatServer(int port) {
		this();
		this.port = port;
	}
	
	public ChatServer() {
		serverCommands.put((new Disconnect()).getCommand(), new Disconnect());
		serverCommands.put((new Login()).getCommand(), new Login());
		serverCommands.put((new GetUsers()).getCommand(), new GetUsers());
		serverCommands.put((new Broadcast()).getCommand(), new Broadcast());
		serverCommands.put((new Send()).getCommand(), new Send());
	}

	@Override
	public void run() {
		try (ServerSocket serverSocket = createServerSocket()) {
			listenForClients(serverSocket);
		} 
		catch (IOException e) {
			System.out.println("Could not bind socket to given port number.");
			System.out.println(e);
			System.exit(1);
		}
	}

	private ServerSocket createServerSocket() throws IOException {
		return new ServerSocket(port);
	}

	private void listenForClients(ServerSocket serverSocket) {
		isRunning = true;
		while(isRunning) {
			try {
				Socket clientSocket = createClientSocket(serverSocket);
				Client client = createClient(clientSocket);
				startClientThread(client);
				logEvent("Accepted new connection from " + clientSocket.getRemoteSocketAddress().toString());
			}
			catch (IOException e) {
				logEvent("Could not accept incoming connection.");
			}
		}
	}

	private Socket createClientSocket(ServerSocket serverSocket) throws IOException {
		Socket clientSocket = serverSocket.accept();
		return clientSocket;
	}

	private Client createClient(Socket clientSocket) {
		Client client = new Client(clientSocket, this);
		connectedClients.put(client.getLogin(), client);
		return client;
	}
	
	private void startClientThread(Client client) {
		Thread clientThread = new Thread(client);
		clientThread.start();
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

	public boolean isLoginUsed(String login) {
		if (connectedClients.containsKey(login)) {
			return true;
		}
		return false;
	}

	public ConcurrentMap<String, Client> getConnectedClients() {
		return connectedClients;
	}
}
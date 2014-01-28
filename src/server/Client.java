package server;

import java.io.*;
import java.net.*;
import java.util.concurrent.ConcurrentMap;

/**
 * @author Radosław Luter (radekpl2@gmail.com)
 * 
 */

class Client implements Runnable {
	private String login;
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	private final ChatServer server;
	private boolean isLoggedIn = false;

	Client(Socket socket, ChatServer server) {
		this.server = server;
		this.socket = socket;
		login = socket.getRemoteSocketAddress().toString();
	}

	@Override
	public void run() {
		try {
			in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(), true);
		} catch (IOException e) {
			server.logEvent("Could not create input/output socket streams for "
					+ socket.getRemoteSocketAddress().toString());
			return;
		}

		send("@SERVER:HELLO");

		while (true) {
			String incomingData = null;
			try {
				incomingData = in.readLine();
				if (incomingData == null) {
					throw new IOException();
				}
			} catch (IOException e) {
				server.logEvent("Connection with client " + getLogin() + " lost.");
				return;
			}

		}

	}

	public boolean isLoggedIn() {
		return isLoggedIn;
	}

	public void setLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}

	public String getLogin() {
		return login;
	}
	
	public boolean setLogin(String login) {
		ChatServer server = getServer();
		ConcurrentMap<String, Client> connectedClients = server.getConnectedClients();
		if (connectedClients.putIfAbsent(login, this) == null) {
			return false;
		}
		connectedClients.remove(getLogin());
		this.login = login;
		return true;
	}

	public ChatServer getServer() {
		return server;
	}

	public void send(String message) {
		out.println(message);
	}

}

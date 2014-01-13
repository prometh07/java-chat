package server;

import java.io.*;
import java.net.*;

/**
 * @author Radosław Luter (radekpl2@gmail.com)
 *
 */

class Client implements Runnable {
	private String id; 
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	private ChatServer server;
	
	Client(Socket socket, ChatServer server) {
		this.server = server;
		this.socket = socket;
		id = socket.getRemoteSocketAddress().toString();
	}
	
	@Override
	public void run() {
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(), true);
		}
		catch (IOException e) {
			server.logEvent("Could not create input/output socket streams for " + socket.getRemoteSocketAddress().toString());
			return;
		}
		
		while(true) {
			String incomingData = null;
			try {
				incomingData = in.readLine();
				if(incomingData == null) {
					throw new IOException();
				}
			}
			catch (IOException e) {
				server.logEvent("Connection with client " + getID() + " lost.");
				return;
			}
			
			
		}
		
	}

	public String getID() {
		return id;
	}

	public ChatServer getServer() {
		return server;
	}
	
	public void send(String message) {
		out.println(message);
	}
	
}

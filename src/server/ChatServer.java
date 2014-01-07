package server;

import java.net.ServerSocket;
import java.util.*;

/**
 * @author Radosław Luter (radekpl2@gmail.com)
 *
 */
public class ChatServer implements Runnable {
	
	private int serverPort = 1410;
	private ServerSocket serverSocket;
	private Map<String, ServerCommand> serverCommands = new HashMap<String, ServerCommand>();
	private Map<String, Connection> connectedClients = new HashMap<String, Connection>();
	
	public ChatServer(int serverPort) {
		this();
		this.serverPort = serverPort;
	}
	
	public ChatServer() {
		serverCommands.put((new Connect()).getCommand(), new Connect());
		serverCommands.put((new Disconnect()).getCommand(), new Disconnect());
		serverCommands.put((new Login()).getCommand(), new Login());
		serverCommands.put((new GetUsersInfo()).getCommand(), new GetUsersInfo());
		serverCommands.put((new Broadcast()).getCommand(), new Broadcast());
		serverCommands.put((new Send()).getCommand(), new Send());
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	public static void main(String[] args) {
		ChatServer server;
		
		if (args.length == 1) {
			try {
				int port = Integer.parseInt(args[0]);
				server = new ChatServer(port);
			}
			catch(NumberFormatException ex) {
				System.out.println("The port number argument could not be casted to int.");
			}
		}
		else {
			server = new ChatServer();
		}
	}
	
}
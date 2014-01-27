/**
 * 
 */
package server;

import java.util.concurrent.ConcurrentMap;

/**
 * @author Radosław Luter (radekpl2@gmail.com)
 *
 */
class Login implements ServerCommand {

	@Override
	public String getCommand() {
		return "login";
	}

	@Override
	public void executeCommand(Client client, String login) {
		if (client.isLoggedIn()) {
			client.send("@SERVER:INUSE");
			return;
		}
		if (!isLoginValid(login)) {
			client.send("@SERVER:WRONG");
			return;
		}
		
		ChatServer server = client.getServer();
		ConcurrentMap<String, Client> connectedClients = server.getConnectedClients();
		if(connectedClients.putIfAbsent(client.getID(), client) == null) {
			client.send("@SERVER:INUSE");
			return;
		}
		
		client.setLoggedIn(true);
		client.send("@SERVER:OK");
	}
	
	private boolean isLoginValid(String login) {
		return false;
	}

}

/**
 * 
 */
package server;

import java.util.*;
import java.util.concurrent.ConcurrentMap;

/**
 * @author Radosław Luter (radekpl2@gmail.com)
 * 
 */
class GetUsers implements ServerCommand {

	@Override
	public String getCommand() {
		return "users";
	}

	@Override
	public void executeCommand(Client client, String message) {
		ChatServer server = client.getServer();
		ConcurrentMap<String, Client> connectedClients = server.getConnectedClients();
		String users = "@USERS:" + getUsers(connectedClients);
		client.send(users);
	}

	String getUsers(ConcurrentMap<String, Client> connectedClients) {
		List<String> listOfLogins = new ArrayList<String>();

		for (String clientLogin : connectedClients.keySet()) {
			listOfLogins.add(clientLogin);
		}
		Collections.sort(listOfLogins);

		StringBuilder builder = new StringBuilder();
		for (String clientLogin : listOfLogins) {
			builder.append(clientLogin);
			builder.append(",");
		}
		builder.setLength(builder.length() - 1);
		String users = builder.toString();
		
		return users;
	}

}
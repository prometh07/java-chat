/**
 * 
 */
package server;

import java.util.Map.Entry;
import java.util.concurrent.*;

/**
 * @author Radosław Luter (radekpl2@gmail.com)
 * 
 */
class Broadcast implements ServerCommand {

	@Override
	public String getCommand() {
		return "broadcast";
	}

	@Override
	public void executeCommand(Client client, String message) {
		ChatServer server = client.getServer();
		ConcurrentMap<String, Client> connectedClients = server.getConnectedClients();

		for (Entry<String, Client> entry : connectedClients.entrySet()) {
			if (entry.getKey() != client.getLogin()) {
				Client receiver = entry.getValue();
				receiver.send("@BROADCAST:" + message);
			}
		}
	}

}

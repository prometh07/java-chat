/**
 * 
 */
package server;

import java.util.Map.Entry;
import java.util.concurrent.*;

/**
 * The Class Broadcast. Used to broadcast a message to all chat users.
 *
 * @author Radosław Luter (radekpl2@gmail.com)
 */
class Broadcast implements ServerCommand {
	/** (non-Javadoc)
	 * Gets command.
	 * @see server.ServerCommand#getCommand()
	 */
	@Override
	public String getCommand() {
		return "broadcast";
	}

	/** (non-Javadoc)
	 * Broadcasts a message to to other users.
	 * @see server.ServerCommand#executeCommand(server.Client, java.lang.String)
	 */
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

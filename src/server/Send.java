/**
 * 
 */
package server;

import java.util.concurrent.ConcurrentMap;


/**
 * @author Radosław Luter (radekpl2@gmail.com)
 *
 */
class Send implements ServerCommand {

	@Override
	public String getCommand() {
		return "send";
	}

	@Override
	public void executeCommand(Client client, String message) {
		String[] arguments = message.split(" ", 2);
		if (arguments.length != 2) {
			client.send("@SERVER:WRONG");
			return;
		}
		String receiverLogin = arguments[0];
		message = arguments[1];
		
		ChatServer server = client.getServer();
		ConcurrentMap<String, Client> connectedClients = server.getConnectedClients();
		Client receiver = connectedClients.get(receiverLogin);
		if (receiver == null) {
			client.send("@SERVER:WRONG");
			return;
		}
		receiver.send("@MESSAGE:" + message);
	}

}

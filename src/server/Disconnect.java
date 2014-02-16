/**
 * 
 */
package server;

/**
 * @author Radosław Luter (radekpl2@gmail.com)
 *
 */
class Disconnect implements ServerCommand {

	@Override
	public String getCommand() {
		return "disconnect";
	}

	@Override
	public void executeCommand(Client client, String message) {
		ServerCommand broadcast = client.getServer().getServerCommands().get("broadcast");
		broadcast.executeCommand(client, client.getLogin() + "disconnected from server.");
		client.send("You've been disconnected from chat server. Bye!");
		client.closeConnection();
	}

}

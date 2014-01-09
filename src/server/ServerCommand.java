package server;

/**
 * @author Radosław Luter (radekpl2@gmail.com)
 *
 */
public interface ServerCommand {
	public String getCommand();
	public void executeCommand(ChatServer server, Client client, String message);
}

package server;

// TODO: Auto-generated Javadoc
/**
 * The Interface ServerCommand. Used to implement server commands.
 *
 * @author Radosław Luter (radekpl2@gmail.com)
 */
public interface ServerCommand {
	
	/**
	 * Gets the command.
	 *
	 * @return the command
	 */
	public String getCommand();
	
	/**
	 * Executes the command.
	 *
	 * @param client the client
	 * @param message the message
	 */
	public void executeCommand(Client client, String message);
}

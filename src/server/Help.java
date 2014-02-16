/**
 * 
 */
package server;

import java.util.concurrent.ConcurrentMap;

/**
 * @author Radosław Luter (radekpl2@gmail.com)
 *
 */
public class Help implements ServerCommand {

	/* (non-Javadoc)
	 * @see server.ServerCommand#getCommand()
	 */
	@Override
	public String getCommand() {
		return "help";
	}

	/* (non-Javadoc)
	 * @see server.ServerCommand#executeCommand(server.Client, java.lang.String)
	 */
	@Override
	public void executeCommand(Client client, String message) {
		//ConcurrentMap<String, ServerCommand> serverCommands = server.getServerCommands();
		// TODO Auto-generated method stub

	}

}

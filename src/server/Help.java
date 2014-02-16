/**
 * 
 */
package server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
		ConcurrentMap<String, ServerCommand> serverCommands = client.getServer().getServerCommands();
		String commands = getCommands(serverCommands);
		client.send("These are server commands:\n" + commands);
	}

	private String getCommands(ConcurrentMap<String, ServerCommand> serverCommands) {
		List<String> listOfCommands = new ArrayList<String>();
		
		for (String command : serverCommands.keySet()) {
			listOfCommands.add(command);
		}
		
		Collections.sort(listOfCommands);
		StringBuilder builder = new StringBuilder();
		for (String command : listOfCommands) {
			builder.append(command);
			builder.append("\n");
		}
		builder.setLength(builder.length() - 1);
		String commands = builder.toString();
		
		return commands;
	}
}

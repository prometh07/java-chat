/**
 * 
 */
package server;

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
		// TODO Auto-generated method stub
		
	}

}

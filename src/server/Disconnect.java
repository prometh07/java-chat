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
		// TODO Auto-generated method stub
		
	}

}

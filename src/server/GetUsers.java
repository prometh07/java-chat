/**
 * 
 */
package server;

/**
 * @author Radosław Luter (radekpl2@gmail.com)
 *
 */
class GetUsers implements ServerCommand {

	@Override
	public String getCommand() {
		return "users";
	}

	@Override
	public void executeCommand(Client client, String message) {
		// TODO Auto-generated method stub
		
	}

}
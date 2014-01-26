/**
 * 
 */
package server;

/**
 * @author Radosław Luter (radekpl2@gmail.com)
 *
 */
class Login implements ServerCommand {

	@Override
	public String getCommand() {
		return "login";
	}

	@Override
	public void executeCommand(Client client, String login) {
	}
	
	private boolean isValidLogin(String login) {
		return false;
	}

}

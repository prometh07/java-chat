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
		if (client.isLoggedIn()) {
			client.send("@SERVER:INUSE");
			return;
		}
		if (!isLoginValid(login)) {
			client.send("@SERVER:WRONG");
			return;
		}
		if (!client.setLogin(login)) {
			client.send("@SERVER:INUSE");
			return;
		}
		client.setLoggedIn(true);
		client.send("@SERVER:OK");
	}

	boolean isLoginValid(String login) {
		return login.matches("[A-Za-z0-9_]+");
	}
}

/**
 * 
 */
package server;


/**
 * @author Radosław Luter (radekpl2@gmail.com)
 *
 */
class Send implements ServerCommand {

	@Override
	public String getCommand() {
		return "send";
	}

	@Override
	public void executeCommand(Client client, String message) {
		// TODO Auto-generated method stub
		
	}

}

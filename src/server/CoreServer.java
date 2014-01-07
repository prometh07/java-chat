package server;

/**
 * @author Radosław Luter (radekpl2@gmail.com)
 *
 */
class Connect implements ServerCommand {

	@Override
	public String getCommand() {
		return "connect";
	}

	@Override
	public void executeCommand(ChatServer server, Connection client,
			String message) {
		// TODO Auto-generated method stub
		
	}

}

class Disconnect implements ServerCommand {

	@Override
	public String getCommand() {
		return "disconnect";
	}

	@Override
	public void executeCommand(ChatServer server, Connection client,
			String message) {
		// TODO Auto-generated method stub
		
	}

}

class Login implements ServerCommand {

	@Override
	public String getCommand() {
		return "login";
	}

	@Override
	public void executeCommand(ChatServer server, Connection client,
			String message) {
		// TODO Auto-generated method stub
		
	}

}

class GetUsersInfo implements ServerCommand {

	@Override
	public String getCommand() {
		return "users";
	}

	@Override
	public void executeCommand(ChatServer server, Connection client,
			String message) {
		// TODO Auto-generated method stub
		
	}

}

class Broadcast implements ServerCommand {

	@Override
	public String getCommand() {
		return "broadcast";
	}

	@Override
	public void executeCommand(ChatServer server, Connection client,
			String message) {
		// TODO Auto-generated method stub
		
	}

}

class Send implements ServerCommand {

	@Override
	public String getCommand() {
		return "send";
	}

	@Override
	public void executeCommand(ChatServer server, Connection client,
			String message) {
		// TODO Auto-generated method stub
		
	}

}
/**
 * 
 */
package server;

/**
 * @author Radosław Luter (radekpl2@gmail.com)
 *
 */
class CommandArguments {
	String name;
	String arguments;
	public CommandArguments(String data) {
		arguments = "";
		name = data;
		int index = data.indexOf(" ");
		if (index != -1) {
			name = data.substring(0, index);
			arguments = data.substring(index+1);
		}
	}
}

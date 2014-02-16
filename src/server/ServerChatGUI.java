/**
 * 
 */
package server;

import java.awt.BorderLayout;

import javax.swing.*;

/**
 * @author Radosław Luter (radekpl2@gmail.com)
 *
 */
public class ServerChatGUI {
	private ChatServer server;
	private JFrame frame;
	private JButton runButton;
	private JTextField portField;
	private JToolBar bar;
	private JTextArea logArea;
	/**
	 * @param args
	 */
	
	public ServerChatGUI() {
		server = new ChatServer();
		createGUI();
	}
	
	private void createGUI() {
		createFrame();
		createBar();
		createlogArea();
		frame.setVisible(true);
	}

	private void createlogArea() {
		logArea = new JTextArea();
		logArea.setEditable(false);
		frame.add(logArea);
	}

	private void createBar() {
		bar = new JToolBar();
		bar.setFloatable(false);
		
		portField = new JTextField();
		bar.add(portField);
		
		runButton = new JButton("run");	
		bar.add(runButton);
		
		frame.add(bar, BorderLayout.NORTH);
	}

	private void createFrame() {
		frame = new JFrame("Chat server v.0.1");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 500);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ServerChatGUI app = new ServerChatGUI();
	}
}

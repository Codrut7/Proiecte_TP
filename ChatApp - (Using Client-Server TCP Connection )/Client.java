import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
	/**
 * Client class represents the person that will connect to the Server/ChatApp
 * @author cordu
 * @since 10/01/2018
 *
 */
public class Client implements Runnable {
	private static final String serverName ="DESKTOP-13H4KF3" ;
	private Socket clientSocket;
	private static MainGUI gui;
/**
 * The constructor connects the client to the server using the Socket .
 * @exception IOException for serverError
 */
public Client() {
	try {
		this.clientSocket = new Socket(serverName,6600);
		gui = new MainGUI();
		gui.preDisplay();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
/**
 * Thread that sends the inputs from the client and creates a thread that handles the response from server
 */
@Override
public void run() {
	try {
		PrintStream output = new PrintStream(clientSocket.getOutputStream());
		gui.setOutput(output);
		new Thread(new ReceivedMessagesHandler(clientSocket.getInputStream(),gui)).start(); // create a thread that receives the messages from the server for each client and prints them
} catch (IOException e) {
	// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
}


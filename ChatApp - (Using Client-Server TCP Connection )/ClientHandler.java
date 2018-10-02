import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
	/**
 * Class used to handle the Clients that join the chatRoom .
 * (they get information about what others Clients send to server)
 * @author cordu
 * @since 10/01/2018
 */
public class ClientHandler implements Runnable {
	private Socket client;
	private Server server;
	
	/**
 * The constructor will initialize the outputStream list . 
 * We can use it to communicate with other clients .
 */
public ClientHandler(Socket client,Server server) {
	this.client = client;
	this.server = server;
}
/**
 * The run function will get the input from client, and update the other clients.
 * @exception IOException for the dataStream
 */
@Override
public void run() {
	try {
		while(true) {
		Scanner s = new Scanner(client.getInputStream());
		while(s.hasNextLine()) {
		String temp = s.nextLine();
		synchronized(server) {
			server.broadcastMessage(temp);
			}
		}
		}
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}

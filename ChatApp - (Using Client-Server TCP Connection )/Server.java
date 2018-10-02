import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
	/**
 * Server class will accept any Client connection + creates a thread for each of them.
 * @author cordu
 * @since 10/1/2018
 */
public class Server implements Runnable {
	private ServerSocket serverSocket;
	private List<Socket> dataClienti;
	
	/**
 * The constructor will initialize the serverSocket and the arrayList.
 * @exception IOException for the Socket initialization
 */
public Server() {
	try {
		this.serverSocket = new ServerSocket(6600);
		this.dataClienti = new ArrayList<>();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
/**
 * The thread function that will loop endlessly and wait for clients to connect.
 */
@Override
public void run() {
	while(true) {
		try {
			Socket newClient = serverSocket.accept();
			dataClienti.add(newClient); // get the client so we can communicate its data
			new Thread(new ClientHandler(newClient,this)).start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
/**
 * Method used to send the message to everyClient that has joined the server
 * @param the message to broadcast
 * @return void
 * @throws IOException 
 */
	public void broadcastMessage(String message) throws IOException {
		for(Socket i : dataClienti) {
			PrintStream output = new PrintStream(i.getOutputStream());
			output.println(message);
		}
	}
}

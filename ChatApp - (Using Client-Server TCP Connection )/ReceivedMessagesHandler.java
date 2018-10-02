import java.io.InputStream;
import java.util.Scanner;

public class ReceivedMessagesHandler implements Runnable {
	InputStream stream;
	MainGUI gui;
	/**
 * Constructor that initializes the inputStream ( so we can print the response from the server )
 * @param stream
 */
public ReceivedMessagesHandler(InputStream stream,MainGUI gui) {
	this.stream = stream;
	this.gui = gui;
}
/**
 * Thread receives messages from the server and prints them out in the screen 
 */
	@Override
	public void run() {
		Scanner s = new Scanner(stream);
		while(s.hasNextLine()) {
			String tmp = s.nextLine();
			gui.chatBox.append("<" + "User" + ">:  " + tmp + "\n");
		}	
	}

}

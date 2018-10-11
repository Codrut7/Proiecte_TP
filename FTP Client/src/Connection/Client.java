package Connection;

import java.io.*;
import java.net.Socket;

/**
 * The client sends the file to another client via a server .
 * Created by cordu on 10/9/2018.
 */
public class Client implements Runnable {
    private static final String serverName = "DESKTOP-13H4KF3"; // The host of the server
    private Socket clientSocket; // The client socket used for file transimision.
    private File file;

    /**
     * The constructor initializes the connection to the server and the file to be sent .
     * @throws IOException
     */
    public Client() throws IOException {
        clientSocket = new Socket(serverName,6700);
        file = new File("C:\\Users\\cordu\\Desktop\\alabala.txt");
    }

    /**
     * 1 -> Get the file size and data .
     * 2 -> Send a file to the server .
     * 3 -> Create a separate thread that handles the output from the server .
     */
    @Override
    public void run() {
        try {
            //1
            byte bytes[] = new byte[4096];
            InputStream fileInputStream = new FileInputStream(file);
            int count;
            //2
            OutputStream outputStream = clientSocket.getOutputStream();
            while( (count = fileInputStream.read(bytes)) > 0){          // citim din fiisier
                outputStream.write(bytes,0,count);             // scriem catre server
            }
            //3
            InputStream inputStream = clientSocket.getInputStream();
            new Thread(new ReceivedFileHandler(inputStream)).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

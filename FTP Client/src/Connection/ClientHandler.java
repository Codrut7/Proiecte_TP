package Connection;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 * Class that handles the client input.
 * Created by cordu on 10/9/2018.
 */
public class ClientHandler implements Runnable {
    private Socket client;
    private Server server;

    public ClientHandler(Socket client,Server server) {
        this.client = client;
        this.server = server;
    }

    /**
     * Get the file from the client and broadcast it to the other clients.
     */
    @Override
    public void run() {
        try {
            InputStream inputStream = client.getInputStream();
            byte[] bytes = new byte[4096];
            inputStream.read(bytes,0,bytes.length);
            server.broadcastBytes(bytes,client);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

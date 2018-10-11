package Connection;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * The server accepts the client connections and broadcast to them the file .
 * Created by cordu on 10/9/2018.
 */
public class Server implements Runnable {
    private ServerSocket serverSocket;
    private List<Socket> clientList;

    /**
     * Initialize the server that accepts the sockets and the connected Clients list .
     * @throws IOException
     */
    public Server() throws IOException {
        serverSocket = new ServerSocket(6700);
        clientList = new ArrayList<>();
    }

    /**
     * Create a new thread that handles the file sent by the client to the server.
     */
    @Override
    public void run() {
        while(clientList.size()<2){
            try {
                Socket client = serverSocket.accept();
                clientList.add(client);
                new Thread(new ClientHandler(client,this)).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Broadcast to the other clients the 4096 bytes from the input .
     * @param bytes
     * @param client
     * @throws IOException
     */
    public void broadcastBytes(byte[] bytes, Socket client) throws IOException {
        for(Socket i : clientList){
                OutputStream outputStream = i.getOutputStream();
                outputStream.write(bytes,0,bytes.length);
        }
    }
}

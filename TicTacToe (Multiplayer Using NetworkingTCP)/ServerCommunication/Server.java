package ServerCommunication;

import GUI.GameGUI;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * The server accepts the connection from both players and sends the data to a HandlerClass .
 * Created by cordu on 10/7/2018.
 */
public class Server implements Runnable {
    private ServerSocket server;
    private List<Socket> players;

    /**
     * Establish a server bind to the port .
     * @exception  IOException from the server socket
     */
    public Server(){
        try {
            server = new ServerSocket(5500);
            players = new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Socket> getPlayers() {
        return players;
    }

    @Override
    public void run() {
        while(true){
            try {
                Socket player = server.accept();
                players.add(player);
                new Thread(new PlayerHandler(player,this)).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * This method broadcasts to the players what tht GUI should look like .
     * 1 -> It gets the input move from the playerHandler and sends back to both the players the GUI .
     * 2 -> It sends the data back to the players.
     * @param x
     * @param player
     * @throws IOException
     */
    public void broadcastMove(GameGUI x, Socket player) throws IOException {
        for(Socket i : players){
            if(i!=player) {
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(i.getOutputStream());
                objectOutputStream.writeObject(x);
                objectOutputStream.flush();
            }
        }
    }
}

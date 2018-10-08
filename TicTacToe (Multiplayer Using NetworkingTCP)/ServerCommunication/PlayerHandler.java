package ServerCommunication;
import GUI.GameGUI;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

/**
 * Class that handles the Player actions and sends them back to the server.
 * Created by cordu on 10/7/2018.
 */
public class PlayerHandler implements Runnable {
    private Socket player;
    private Server server;

    /**
     * Initialise a client handler that can get the input from the client and send a response to the server .
     *  @param player
     * @param server
     */
    public PlayerHandler(Socket player, Server server) {
        this.player = player;
        this.server = server;
    }

    /**
     *  Create the inputStream once so we can read the input from the player .
     *  Tell the server to broadcast the gotten input to the other player .
     */
    @Override
    public void run() {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(player.getInputStream());
            while(true) {
                GameGUI x = (GameGUI) objectInputStream.readObject();
                server.broadcastMove(x, player);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

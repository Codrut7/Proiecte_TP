package ServerCommunication;

import GUI.GameGUI;

import javax.swing.*;
import java.io.*;
import java.net.Socket;

/**
 * The player send his input to the server to be processed
 * Created by cordu on 10/7/2018.
 */
public class Player implements Runnable {
    private static final String serverName = "DESKTOP-13H4KF3";
    private Socket socket;
    private GameGUI gui;

    /**
     * Initialize the communication to the server and the game JFrame .
     */
    public Player() {
        try {
            this.socket = new Socket(serverName, 5500);
            gui = new GameGUI();
            JFrame window = new JFrame("Tic-Tac-Toe");
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.getContentPane().add(gui);
            window.setBounds(300, 200, 300, 300);
            window.setVisible(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The client makes an input and sends it to the server .
     * 1 -> Create the outputStream first . ( So we can write our input to the server ).
     * 2 -> Create our inputStream each time one of the players does a move and read the output from the server .
     * 3 -> Set the player GUI similar to what the server tells us .
     */
    @Override
    public void run() {
        try {
            //1
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            gui.setOutputStream(objectOutputStream);
            while(true) {
                //2
                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

                /**
                 *      FMM AM STAT 4 ORE LA ASTA .
                 *  DE FIECARE DATA CAND TRIMITI UN OBIECT PRIN OUTPUTSTREAM ( IN CAZU ASTA DIN GAMEGUI class ) TREBUIE SA CREEZI O INSTANTA NOUA .
                 *
                 */

                GameGUI newGui = (GameGUI) objectInputStream.readObject();

                //3
                for(int i = 0 ; i < gui.buttons.length ; i++){
                    gui.buttons[i].setText(newGui.buttons[i].getText());
                    gui.alternate = newGui.alternate;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
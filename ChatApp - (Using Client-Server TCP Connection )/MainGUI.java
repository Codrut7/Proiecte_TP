

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class MainGUI {

    static MainGUI mainGUI;
    JFrame newFrame = new JFrame("My frame");
JButton sendMessage;
JTextField messageBox;
JTextArea chatBox;
JTextField usernameChooser;
JFrame preFrame;
PrintStream stream;


public void preDisplay() {
    newFrame.setVisible(false);
    preFrame = new JFrame("Choose your username!");
    usernameChooser = new JTextField();
    JLabel chooseUsernameLabel = new JLabel("Pick a username:");
    JButton enterServer = new JButton("Enter Chat Server");
    JPanel prePanel = new JPanel(new GridBagLayout());

    GridBagConstraints preRight = new GridBagConstraints();
    preRight.anchor = GridBagConstraints.EAST;
    GridBagConstraints preLeft = new GridBagConstraints();
    preLeft.anchor = GridBagConstraints.WEST;
    preRight.weightx = 2.0;
    preRight.fill = GridBagConstraints.HORIZONTAL;
    preRight.gridwidth = GridBagConstraints.REMAINDER;

    prePanel.add(chooseUsernameLabel, preLeft);
    prePanel.add(usernameChooser, preRight);
    preFrame.add(BorderLayout.CENTER, prePanel);
    preFrame.add(BorderLayout.SOUTH, enterServer);
    preFrame.setVisible(true);
    preFrame.setSize(300, 300);

    enterServer.addActionListener(new enterServerButtonListener());
}

public void display() {
    newFrame.setVisible(true);
    JPanel southPanel = new JPanel();
    newFrame.add(BorderLayout.SOUTH, southPanel);
    southPanel.setBackground(Color.BLUE);
    southPanel.setLayout(new GridBagLayout());

    messageBox = new JTextField(30);
    sendMessage = new JButton("Send Message");
    chatBox = new JTextArea();
    chatBox.setEditable(false);
    newFrame.add(new JScrollPane(chatBox), BorderLayout.CENTER);

    chatBox.setLineWrap(true);

    GridBagConstraints left = new GridBagConstraints();
    left.anchor = GridBagConstraints.WEST;
    GridBagConstraints right = new GridBagConstraints();
    right.anchor = GridBagConstraints.EAST;
    right.weightx = 2.0;

    southPanel.add(messageBox, left);
    southPanel.add(sendMessage, right);

    chatBox.setFont(new Font("Serif", Font.PLAIN, 15));
    sendMessage.addActionListener(new sendMessageButtonListener());
    newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    newFrame.setSize(470, 300);
}

class sendMessageButtonListener implements ActionListener {
	
    public void actionPerformed(ActionEvent event) {
        if (messageBox.getText().length() < 1) {
            // do nothing 
        } else if (messageBox.getText().equals(".clear")) {
            chatBox.setText("Cleared all messages\n");
            messageBox.setText("");
        } else { 			
				stream.println(messageBox.getText());
				messageBox.setText("");
        }
    }
}

String username;

class enterServerButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
        username = usernameChooser.getText();
        if (username.length() < 1) {System.out.println("No!"); }
            else {
            preFrame.setVisible(false);
            display();
            }
        }

    }
   


	public void setOutput(PrintStream output) {
		this.stream = output;
		
	}
}
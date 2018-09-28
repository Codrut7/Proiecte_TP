package PresentationLayer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView {

    private static JFrame frame = new JFrame("Demo application");
    private static JPanel panel = new JPanel();

    public static void main(String[] args) {
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        placeComponents(panel);

        frame.setVisible(true);
    }

    private static void placeComponents(JPanel panel) {

        panel.setLayout(null);

        JLabel userLabel = new JLabel("User");
        userLabel.setBounds(10, 10, 80, 25);
        panel.add(userLabel);

        JTextField userText = new JTextField(20);
        userText.setBounds(100, 10, 160, 25);
        panel.add(userText);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10, 40, 80, 25);
        panel.add(passwordLabel);

        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(100, 40, 160, 25);
        panel.add(passwordText);

        JButton loginButton = new JButton("login");
        loginButton.setBounds(10, 80, 80, 25);
        panel.add(loginButton);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                JPanel panel = new JPanel();
            }
        });

        JButton registerButton = new JButton("register");
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.remove(panel);
                RegisterView registerView = new RegisterView();
                frame.add(registerView.getPanel());
                frame.revalidate();
                frame.repaint();
            }
        });
        registerButton.setBounds(180, 80, 80, 25);
        panel.add(registerButton);
    }

}

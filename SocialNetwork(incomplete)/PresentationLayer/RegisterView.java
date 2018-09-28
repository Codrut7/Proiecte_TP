package PresentationLayer;

import BusinessLogicLayer.PersonDatabaseLogic;
import ModelLayer.Person;
import com.mysql.cj.xdevapi.SqlDataResult;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class RegisterView {
    private static JPanel panel = new JPanel();

    public RegisterView(){
        placeComponents(panel);
    }
    private static void placeComponents(JPanel panel) {

        panel.setLayout(null);

        JLabel firstName = new JLabel("First Name");
        firstName.setBounds(10, 10, 80, 25);
        panel.add(firstName);

        JTextField firstNameTextField = new JTextField(20);
        firstNameTextField.setBounds(100, 10, 160, 25);
        panel.add(firstNameTextField);

        JLabel secondName = new JLabel("Second Name");
        secondName.setBounds(10, 40, 80, 25);
        panel.add(secondName);

        JTextField secondNameTextField = new JTextField(20);
        secondNameTextField.setBounds(100, 40, 160, 25);
        panel.add(secondNameTextField);

        JLabel email = new JLabel("Email");
        email.setBounds(10, 70, 80, 25);
        panel.add(email);

        JTextField emailTextField = new JTextField(20);
        emailTextField.setBounds(100, 70, 160, 25);
        panel.add(emailTextField);

        JLabel password = new JLabel("Password");
        password.setBounds(10,100,80,25);
        panel.add(password);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(100,100,160,25);
        panel.add(passwordField);

        JButton registerButton = new JButton("REGISTER");
        registerButton.setBounds(10,140,150,25);
        panel.add(registerButton);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Person p = new Person(firstNameTextField.getText(),secondNameTextField.getText(),emailTextField.getText(),passwordField.getText());
                PersonDatabaseLogic dbLogic= new PersonDatabaseLogic();
                try{
                    if(dbLogic.insertPerson(p)){
                        JOptionPane popup = new JOptionPane();
                        popup.createDialog("Register succesfull");
                    }else{
                       throw new IllegalArgumentException("hopa ca nu e bun");
                    }
                }catch(SQLException z) {

                }
            }
        });

    }

    public static JPanel getPanel() {
        return panel;
    }
}
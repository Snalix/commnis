package commnis.client;

import javax.swing.*;
import javax.swing.plaf.nimbus.State;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class RegistrationForm extends JFrame implements ActionListener {

    JPanel panel;
    JLabel user_label, password_label, message;
    JTextField userName_text;
    JPasswordField password_text;
    JButton submit, cancel;
    ImageIcon logo = new ImageIcon("logo.png");

    RegistrationForm() {
        user_label = new JLabel();
        user_label.setText("User Name :");
        user_label.setForeground(Color.yellow);
        user_label.setFont(new Font("MV Boli", Font.PLAIN, 30));
        userName_text = new JTextField();
        userName_text.setBackground(Color.black);
        userName_text.setForeground(Color.yellow);
        userName_text.setFont(new Font("MV Boli", Font.PLAIN, 30));
        password_label = new JLabel();
        password_label.setText("Password :");
        password_label.setFont(new Font("MV Boli", Font.PLAIN, 30));
        password_label.setForeground(Color.yellow);
        password_text = new JPasswordField();
        password_text.setBackground(Color.black);
        password_text.setForeground(Color.yellow);
        password_text.setFont(new Font("MV Boli", Font.PLAIN, 30));
        password_text.setPreferredSize(new Dimension(200, 24));
        submit = new JButton("SUBMIT");
        panel = new JPanel(new GridLayout(3, 1));
        submit.setFont(new Font("MV Boli", Font.PLAIN, 30));
        submit.setBackground(Color.YELLOW);
        submit.setForeground(Color.BLACK);
        panel.add(user_label);
        this.setIconImage(logo.getImage());
        panel.add(userName_text);
        panel.add(password_label);
        panel.add(password_text);
        panel.setBackground(Color.BLACK);
        message = new JLabel();
        panel.add(message);
        panel.add(submit);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        submit.addActionListener(this);
        add(panel, BorderLayout.CENTER);
        setTitle("COMMNIS.CHAT/SIGN UP");
        setSize(450, 350);
        setVisible(true);
    }

    public static void main(String[] args) {
        new RegistrationForm();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String userName = userName_text.getText();
        String password = password_text.getText();
        message.setText(" Hello " + userName + "");
        message.setFont(new Font("MV Boli", Font.PLAIN, 20));
        message.setForeground(Color.yellow);
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe", "admin", "admin");
            Statement state = con.createStatement();
            state.executeQuery("INSERT INTO users(ID,USERNAME,PASSWORD) VALUES(" + 2 + ",'" + userName + "','" + password +"')");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        WelcomeWindow w = new WelcomeWindow();

    }
}
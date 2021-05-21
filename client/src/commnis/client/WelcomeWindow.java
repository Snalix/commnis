package commnis.client;

import commnis.client.network.TCPConnection;
import commnis.client.network.TCPConnectionListener;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class WelcomeWindow extends JFrame implements ActionListener, TCPConnectionListener {

    private static final JFrame frame = new JFrame();
    private static final JLabel label = new JLabel();
    private static final JTextField field = new JTextField("");
    JButton roomOne = new JButton();
    JButton roomTwo = new JButton();
    private static final ImageIcon logo = new ImageIcon("logo.png");
    private static final ImageIcon welcome = new ImageIcon("newWelcome.png");
    private static final Border border = BorderFactory.createLineBorder(Color.green,3);
    private static final JButton signUp = new JButton("Sign Up");
    private static final JButton signIn = new JButton("Sign In");

    private static final int WIDTH = 500;
    private static final int HEIGHT = 500;

    public static void main(String[] args) {

        SwingUtilities.invokeLater(WelcomeWindow::new);
    }

   public WelcomeWindow() {

        frame.setTitle("COMMNIS.CHAT/WELCOME PAGE");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        frame.setVisible(true);
        frame.add(label);
        frame.setIconImage(logo.getImage());
        frame.getContentPane().setBackground(Color.black);

        signUp.setBounds(200, 400, 60, 40);
        signUp.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
        signUp.setContentAreaFilled(false);
        signUp.setForeground(Color.yellow);
        signUp.addActionListener(e -> {
           if(e.getSource() == signUp){
               RegistrationForm r = new RegistrationForm();
           }
       });
       signIn.setBounds(200, 430, 60, 40);
       signIn.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
       signIn.setContentAreaFilled(false);
       signIn.setForeground(Color.yellow);
       signIn.addActionListener(e -> {
           if(e.getSource() == signIn){
              LoginForm l = new LoginForm();
           }
       });

        roomOne.setBounds(180, 300, 100, 40);
        roomOne.setText("ROOM #1");
        roomOne.setBackground(Color.yellow);
        roomOne.setBorder(new LineBorder(Color.green,3));
        roomOne.addActionListener(e -> {
            if(e.getSource() == roomOne){
                Room c = new Room();
            }
                });

        roomTwo.setBounds(180, 350, 100, 40);
        roomTwo.setText("ROOM #2");
        roomTwo.setBackground(Color.yellow);
        roomTwo.setBorder(new LineBorder(Color.green,3));
        roomTwo.setEnabled(true);
        roomTwo.addActionListener(e -> {
            if(e.getSource() == roomTwo){
                Room t = new Room();
                t.setTitle("COMMNIS.CHAT/ROOM2");
            }
        });

        label.add(roomOne);
        label.add(roomTwo);
        label.add(field);
        label.add(signUp);
        label.add(signIn);
        label.setIcon(welcome);
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.BOTTOM);
        label.setFont(new Font("MV Boli", Font.PLAIN, 30));
        label.setIconTextGap(-25);
        label.setBorder(border);
        label.setForeground(Color.black);
        label.setVerticalAlignment(JLabel.TOP);
        label.setHorizontalAlignment(JLabel.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void onConnectionReady(TCPConnection tcpConnection) {

    }

    @Override
    public void onReceiveString(TCPConnection tcpConnection, String value) {

    }

    @Override
    public void onDisconnect(TCPConnection tcpConnection) {

    }

    @Override
    public void onException(TCPConnection tcpConnection, Exception e) {

    }
}

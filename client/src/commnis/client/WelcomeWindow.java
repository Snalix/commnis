package commnis.client;

import commnis.network.TCPConnection;
import commnis.network.TCPConnectionListener;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class WelcomeWindow extends JFrame implements ActionListener , TCPConnectionListener {

    private static JFrame frame = new JFrame();
    private static JLabel label = new JLabel();
    private static JTextField field = new JTextField("");
    private static JButton roomOne = new JButton();
    private static JButton roomTwo = new JButton();
    private static ImageIcon logo = new ImageIcon("logo.png");
    private static ImageIcon welcome = new ImageIcon("newWelcome.png");
    private static Border border = BorderFactory.createLineBorder(Color.green,3);

    private static final int WIDTH = 500;
    private static final int HEIGHT = 500;

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new WelcomeWindow();
            }
        });
    }

   public WelcomeWindow() {
        frame.setTitle("COMMNIS.CHAT/WELCOME PAGE");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        frame.setVisible(true);
        frame.add(label);
        frame.setIconImage(logo.getImage());
        frame.getContentPane().setBackground(Color.black);

        roomOne.setBounds(180, 300, 100, 40);
        roomOne.setText("ROOM #1");
        roomOne.setBackground(Color.yellow);
        roomOne.setBorder(new LineBorder(Color.green,3));
        roomOne.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == roomOne){
                    RoomOne c = new RoomOne();
                    roomOne.setEnabled(false);
                    roomOne.setBackground(Color.gray);
                }
            }
        });

        roomTwo.setBounds(180, 350, 100, 40);
        roomTwo.setText("ROOM #2");
        roomTwo.setBackground(Color.yellow);
        roomTwo.setBorder(new LineBorder(Color.green,3));
        roomTwo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == roomTwo){
                    RoomOne t = new RoomOne();
                    roomTwo.setEnabled(false);
                    roomTwo.setBackground(Color.gray);
                }
            }
        });

        label.add(roomOne);
        label.add(roomTwo);
        label.add(field);
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
    public void onConectionReady(TCPConnection tcpConnection) {

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

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

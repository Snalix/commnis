package commnis.client;

import commnis.network.TCPConnection;
import commnis.network.TCPConnectionListener;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Room extends JFrame implements ActionListener , TCPConnectionListener {

    private static final String IP_ADDR = "commnischat.sytes.net";
    private static final int PORT = 8110;
    private static final int WIDTH = 500;
    private static final int HEIGHT = 500;

    public static void main(String[] args) {

        SwingUtilities.invokeLater(Room::new);
    }

     JTextArea log = new JTextArea();
     JTextField fieldNickname = new JTextField("Nikita");
     JTextField  fieldInput  = new  JTextField("");
     JButton send = new JButton();
     ImageIcon logo = new ImageIcon("logo.png");
     Border border = BorderFactory.createLineBorder(Color.green,3);

     private TCPConnection connection;

    public Room(){
        log.setEditable(false);
        log.setLineWrap(true);
        log.setBackground(Color.black);
        log.setForeground(Color.white);
        log.setBorder(border);
        log.setFont(new Font("Serif",Font.PLAIN,18));

        this.setTitle("COMMNIS.CHAT/ROOM1");
        this.setIconImage(logo.getImage());

        fieldNickname.setEditable(true);
        fieldNickname.setBackground(Color.BLACK);
        fieldNickname.setForeground(Color.WHITE);
        fieldNickname.setBorder(new LineBorder(Color.green,2));

        fieldInput.addActionListener(this);
        fieldInput.setBorder(new LineBorder(Color.green,2));
        fieldInput.setForeground(Color.white);
        fieldInput.setBackground(Color.BLACK);
        fieldInput.grabFocus();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setAlwaysOnTop(true);
        setVisible(true);

        add(log,BorderLayout.CENTER);
        add(fieldInput,BorderLayout.SOUTH);
        add(fieldNickname,BorderLayout.PAGE_START);

        send.setBounds(180, 300, 100, 40);
        send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String msg = fieldInput.getText();
                if(e.getSource() == send && msg.equals("") ) return;
                fieldInput.setText(null);
                connection.sendString(fieldNickname.getText() + ": " + msg);
            }
        });
        try {
            connection = new TCPConnection(this, IP_ADDR, PORT);
        } catch (IOException e) {
            printMsg("Connection exception: " + e);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String msg = fieldInput.getText();
        if(e.getSource() == send && msg.equals("") ) return;
        fieldInput.setText(null);
        connection.sendString(fieldNickname.getText() + ": " + msg );
    }

    @Override
    public void onConnectionReady(TCPConnection tcpConnection) {
        printMsg("Connection is ready!" );
    }

    @Override
    public void onReceiveString(TCPConnection tcpConnection, String value) {
        printMsg(value);
    }

    @Override
    public void onDisconnect(TCPConnection tcpConnection) {
        printMsg("Disconnected!TryAgain!" );
    }

    @Override
    public void onException(TCPConnection tcpConnection, Exception e) {
        printMsg("Connection exception: " + e);
    }

    private synchronized void printMsg(String msg){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                log.append(msg + "\n");
                log.setCaretPosition(log.getDocument().getLength());
            }
        });
    }
}

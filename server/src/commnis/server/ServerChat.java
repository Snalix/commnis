package commnis.server;

import commnis.network.TCPConnectionListener;
import commnis.network.TCPConnection;


import java.io.IOException; // input/ouput exception.
import java.net.ServerSocket;
import java.util.ArrayList;

public class ServerChat implements TCPConnectionListener {
    public static void main(String[] args) {
        new ServerChat();
    }

    private final ArrayList<TCPConnection> connections = new ArrayList<>();

    private ServerChat(){
        System.out.println("SERVER IS RUNNING");
        try(ServerSocket serverSocket = new ServerSocket(8181)){
            while(true){
                try{
                    new TCPConnection(this, serverSocket.accept()); //accept() - е блоиран докато няма връзка.Връща сокет.

                }catch (IOException e){
                    System.out.println("TCP Connection exception" + e);
                }
            }

        }catch (IOException e){
            throw new RuntimeException(e);
        }

    }

    @Override
    public synchronized void onConectionReady(commnis.network.TCPConnection tcpConnection) {
        connections.add(tcpConnection);
        sendToAllConnections("Client connected: " + tcpConnection);
    }

    @Override
    public synchronized void onReceiveString(commnis.network.TCPConnection tcpConnection, String value) {
        sendToAllConnections(value);
    }

    @Override
    public synchronized void onDisconnect(commnis.network.TCPConnection tcpConnection) {
        connections.remove(tcpConnection);
        sendToAllConnections("Client disconnected: " + tcpConnection);

    }

    @Override
    public synchronized void onException(commnis.network.TCPConnection tcpConnection, Exception e) {
        System.out.println("TCP Connection exception: " + e);
    }

    private void sendToAllConnections(String value) {
        System.out.println(value);
        for (int i = 0; i < connections.size(); i++) {
            connections.get(i).sendString(value);
        }

    }

}

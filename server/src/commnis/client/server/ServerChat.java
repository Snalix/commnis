package commnis.client.server;

import commnis.client.Room;
import commnis.client.network.TCPConnectionListener;
import commnis.client.network.TCPConnection;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.HashMap;

public class ServerChat implements TCPConnectionListener {
    public static void main(String[] args) {
        new ServerChat();
    }

    private final ArrayList<TCPConnection> connections = new ArrayList<>();
    private static final HashMap<String, Room> rooms = new HashMap<>();

    private boolean alternate = true;

    private ServerChat(){
        System.out.println("SERVER IS RUNNING");
        try(ServerSocket serverSocket = new ServerSocket(50123)){
            Room r1 = new Room("Room 1");
            Room r2 = new Room("Room 2");
            rooms.put("Room 1", r1);
            rooms.put("Room 2", r2);
            while(true){
                try{
                    if(alternate) {
                        TCPConnection incoming = new TCPConnection(r1, serverSocket.accept()); //accept() - е блоиран докато няма връзка.Връща сокет.
                        User newUser = new User(incoming, "Potrebitel");
                        newUser.joinRoom(r1);
                        // Room r = rooms.get("imeto na staqta");
                        alternate = !alternate;
                    }else {
                        TCPConnection incoming = new TCPConnection(r2, serverSocket.accept()); //accept() - е блоиран докато няма връзка.Връща сокет.
                        User newUser = new User(incoming, "Potrebitel");
                        newUser.joinRoom(r2);
                        alternate = !alternate;
                    }

                }catch (IOException e){
                    System.out.println("TCP Connection exception" + e);
                }
            }

        }catch (IOException e){
            throw new RuntimeException(e);
        }

    }

    @Override
    public synchronized void onConnectionReady(TCPConnection tcpConnection) {
        connections.add(tcpConnection);
        //sendToAllConnections("Client connected: " + tcpConnection);
    }

    @Override
    public synchronized void onReceiveString(TCPConnection tcpConnection, String value) {
        //sendToAllConnections(value);
    }

    @Override
    public synchronized void onDisconnect(TCPConnection tcpConnection) {
        connections.remove(tcpConnection);
        //sendToAllConnections("Client disconnected: " + tcpConnection);

    }

    @Override
    public synchronized void onException(TCPConnection tcpConnection, Exception e) {
        System.out.println("TCP Connection exception: " + e);
    }

    private void sendToAllConnections(String value) {
        System.out.println(value);
        for (int i = 0; i < connections.size(); i++) {
            connections.get(i).sendString(value);
        }

    }

    public static void addUserToRoom(User u, String roomName) {
        rooms.get(roomName).addUser(u);
    }

}

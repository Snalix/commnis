package commnis.client.server;

import commnis.client.*;
import commnis.client.network.*;

public class User {

    private String username;
    private String password;

    private TCPConnection clientConnection;
    private Room clientRoom;

    User () {}

    User(TCPConnection c, String username) {
        this.clientConnection = c;
        this.username = username;
    }

    User(TCPConnection c, String username, Room r) {
        this.clientConnection = c;
        this.clientRoom = r;
        this.username = username;
    }

    void joinRoom(Room r) {
        this.clientRoom = r;
        r.addUser(this);
    }

    void leaveRoom() {
        this.clientRoom = null;
    }

    public void getMessage(String message) {
        clientRoom.printMsg(message);
    }
}

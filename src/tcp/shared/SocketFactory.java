package tcp.shared;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketFactory {
    public static ServerSocket spawnServerSocket(int port) {
        ServerSocket socket= null;
        try {
            socket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return socket;
    }

    public static Socket spawnClientSocket(String address, int port) {
        Socket socket= null;
        try {
            socket = new Socket(address, port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return socket;
    }
}

package udp.shared;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class SocketFactory {
    public static DatagramSocket SpawnSocket(String address, int port) {
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket(port, InetAddress.getByName(address));
        } catch (UnknownHostException | SocketException e) {
            System.out.println("Failed to create socket");
        }

        return socket;
    }
}

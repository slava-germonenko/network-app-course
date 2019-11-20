package udp.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class CustomUdpServer {
    private DatagramSocket socket;
    private IRequestProcessor requestProcessor;
    private int bufferSize;

    public CustomUdpServer(IRequestProcessor processor, DatagramSocket socket, int bufferSize) {
        this.socket = socket;
        this.requestProcessor = processor;
        this.bufferSize = bufferSize;
    }

    public void run() {
        System.out.println("Server is on.");

        while (true) {
            System.out.println("Waiting for request...");
            String request = tryGetRequest();
            if (request == null || request.equals("/exit")) return;

            trySendResponse(requestProcessor.execute(request));
        }
    }

    private String tryGetRequest() {
        DatagramPacket packet = new DatagramPacket(new byte[bufferSize], bufferSize);
        String request = null;

        try {
            socket.receive(packet);
            request = new String(packet.getData(), 0, packet.getLength());
            System.out.println("Request received: " + request);
        } catch (IOException e) {
            System.out.println("Failed to receive message!");
        }

        return request;
    }

    private void trySendResponse(String response) {
        byte[] data = response.getBytes();
        try {
            DatagramPacket packet = new DatagramPacket(data, data.length, InetAddress.getByName("localhost"), 8000);
            socket.send(packet);
            System.out.println("Response sent: " + response);
        } catch (IOException e) {
            System.out.println("Failed to send response!");
        }
    }
}

package tcp.server;

import tcp.shared.StreamFactory;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class CustomTcpServer {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;
    private IRequestProcessor processor;

    CustomTcpServer(ServerSocket serverSocket, IRequestProcessor messageProcessor) {
        this.serverSocket = serverSocket;
        this.processor = messageProcessor;
        this.clientSocket = null;
        this.inputStream = null;
        this.outputStream = null;
    }

    public void run() throws IOException {
        System.out.println("Server is on");
        clientSocket = tryAccept();
        if(clientSocket == null) return;

        outputStream = StreamFactory.ExtractOutputStreamFromSocket(clientSocket);
        if (outputStream == null) return;
        outputStream.flush();

        inputStream = StreamFactory.ExtractInputStreamFromSocket(clientSocket);
        if(inputStream == null) return;

        while (true) {
            String message = "";
            message = tryRead();
            if(message != null) {
                tryWrite(processor.execute(message));
            } else {
                return;
            }
        }
    }

    private Socket tryAccept() {
        Socket socket = null;
        try {
            socket = serverSocket.accept();
            System.out.println("Client connected.");
        } catch (IOException ignored) {
            System.out.println("Client failed to connect.");
        }
        return socket;
    }

    private String tryRead() {
        String message = null;
        try {
            message = inputStream.readObject().toString();
            System.out.println("Message received: " + message);
        } catch (IOException | ClassNotFoundException ignored) {
            System.out.println("Failed to receive message!");
        }
        return message;
    }

    private void tryWrite(String message) {
        try {
            outputStream.writeObject(message);
            outputStream.flush();
            System.out.println("Message sent: " + message);
        } catch (IOException ignored) {
            System.out.println("Failed to send message!");
        }
    }
}

package tcp.client;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import tcp.shared.StreamFactory;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class WindowController {
    public TextField ip;
    public TextField port;
    public TextField ticket;
    public TextArea output;
    public Pane inputPanel;

    private Socket socket;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;


    public void connect() {
        try {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }

            socket = new Socket(InetAddress.getByName(ip.getText()), Integer.parseInt(port.getText()));

            inputStream = StreamFactory.ExtractInputStreamFromSocket(socket);
            outputStream = StreamFactory.ExtractOutputStreamFromSocket(socket);
            outputStream.flush();

            inputPanel.setDisable(false);
        }
        catch (Exception exception) {
            output.appendText(exception.getMessage()+"\n");
        }
    }

    public void sendRequest() {
        if(socket == null) return;

        try {
            outputStream.writeObject(ticket.getText());
            output.appendText(inputStream.readObject().toString() + '\n');
        } catch (IOException | ClassNotFoundException e) {
            output.appendText(e.getLocalizedMessage()+"\n");
        }
    }
}

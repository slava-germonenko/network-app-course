package tcp.shared;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class StreamFactory {
    public static ObjectInputStream ExtractInputStreamFromSocket(Socket source) {
        ObjectInputStream inputStream = null;
        try {
            inputStream = new ObjectInputStream(source.getInputStream());
        } catch (IOException e) {
            System.out.println("Failed to create input stream");
        }
        return inputStream;
    }

    public static ObjectOutputStream ExtractOutputStreamFromSocket(Socket source) {
        ObjectOutputStream outputStream = null;
        try {
            outputStream = new ObjectOutputStream(source.getOutputStream());
        } catch (IOException e) {
            System.out.println("Failed to create output stream");
        }
        return outputStream;
    }
}

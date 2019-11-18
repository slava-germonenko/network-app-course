package udp.client

import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress

class CustomClient(private val socket: DatagramSocket,
                   private val serverAddress: String,
                   private val serverPort: Int,
                   private val bufferSize: Int) {
    fun run() {
        while (true) {
            sendRequest(formRequest())
            getResponse();
        }
    }

    private fun formRequest() : String {
        print("Enter parameter A: ")
        val paramA = readLine().toString()
        print("Enter parameter B: ")
        val paramB = readLine().toString()
        print("Enter parameter C: ")
        val paramC = readLine().toString()
        return "A=$paramA B=$paramB C=$paramC";
    }

    private fun sendRequest(request: String) {
        val requestBytes = request.toByteArray()
        val packet = DatagramPacket(requestBytes, requestBytes.size, InetAddress.getByName(serverAddress), serverPort)
        socket.send(packet)
    }

    private fun getResponse() {
        val packet = DatagramPacket(ByteArray(bufferSize), bufferSize, InetAddress.getByName(serverAddress), serverPort)
        socket.receive(packet)
        println("Response received: ${String(packet.data, 0, packet.length)}")
    }
}

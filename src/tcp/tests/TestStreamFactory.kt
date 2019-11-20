package tcp.tests

import tcp.shared.SocketFactory
import tcp.shared.StreamFactory

fun main() {
    createFactory();
}

fun createFactory() {
    var serverSocket = SocketFactory.spawnServerSocket(3000);
    var socket = SocketFactory.spawnClientSocket("localhost", 3000);
    var stream = StreamFactory.ExtractInputStreamFromSocket(socket);
}

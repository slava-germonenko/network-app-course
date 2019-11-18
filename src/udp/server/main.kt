package udp.server

import udp.shared.SocketFactory

fun main() {
    val server = CustomUdpServer(CustomRequestProcessor(), SocketFactory.SpawnSocket("localhost", 3000), 1024)
    server.run();
}

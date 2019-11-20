package udp.client

import udp.shared.SocketFactory

fun main() {
    val client = CustomClient(SocketFactory.SpawnSocket("localhost", 8000), "localhost", 3000, 1024)
    client.run()
}

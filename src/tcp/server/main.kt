package tcp.server

import tcp.shared.SocketFactory

fun main(){
    val server = CustomTcpServer(SocketFactory.spawnServerSocket(3000), CustomRequestProcessor())
    server.run()
}

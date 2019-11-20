package tcp.tests

import tcp.server.CustomRequestProcessor

fun main() {
    val processor = CustomRequestProcessor();
    print(processor.execute(mockString()));
}

fun mockString() : String {
    // 4 + 4
    return "Aa Bb Cc Ee";
}

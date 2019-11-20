package tcp.client

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage
import java.io.IOException

class Client : Application() {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch(Client::class.java)
        }
    }

    override fun start(stage: Stage) {
        val root: Parent?;
        try {
            root = FXMLLoader.load<Parent>(javaClass.getResource("window.fxml"))
        } catch (e: IOException) {
            e.printStackTrace()
            return
        }

        val scene = Scene(root!!, 200.0, 400.0)

        stage.minWidth = 300.0
        stage.minHeight = 400.0
        stage.maxWidth = 500.0
        stage.maxHeight = 525.0

        stage.title = "КР 1"
        stage.scene = scene

        stage.show()
    }
}

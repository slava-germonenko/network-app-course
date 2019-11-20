package collections

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage

class Main : Application() {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch(Main::class.java)
        }
    }

    @Throws(Exception::class)
    override fun start(primaryStage: Stage) {
        val root = FXMLLoader.load<Parent>(javaClass.getResource("window.fxml"))

        val scene = Scene(root, 600.0, 400.0)
        primaryStage.title = "ИПР 1"
        primaryStage.scene = scene
        primaryStage.show()
    }
}


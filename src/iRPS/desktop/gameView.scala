package iRPS.desktop

import javafx.scene.input.{KeyCode, KeyEvent}
import scalafx.application
import scalafx.application.{JFXApp, Platform}
import scalafx.scene.Scene
import scalafx.scene.paint.Color._
import io.socket.client.Socket
import io.socket.client.IO
import io.socket.emitter.Emitter
import play.api.libs.json.{JsValue, Json}
import scalafx.scene.shape.{Circle, Rectangle}
import scalafx.scene.paint.Paint

object gameView extends JFXApp {

  var socket: Socket = IO.socket("http://localhost:8080/")
  socket.on("gamestate", new HandleMessagesFromPython)
  socket.connect()


  stage = new application.JFXApp.PrimaryStage {
    title.value = "Test"
    scene = new Scene(1280, 720) {


      fill = White

      val tileSize = 30

      def placeCircle(x: Double, y: Double, color: Paint, radius: Double): Unit = {
        val circle0 = new Circle
        circle0.radius = 6
        circle0.centerX = x
        circle0.centerY = y
        circle0.fill = color
      }

      def placeSquare(x: Double, y: Double, color: Paint): Unit = {
        val square0 = new Rectangle
        square0.width = tileSize
        square0.height = tileSize
        square0.x = x
        square0.y = y
        square0.fill = color
      }




      def keyPressed(keyCode: KeyCode): Unit = {
        keyCode.getName match {
          case "W" => println("Yup")
          case "A" => println("Yup")
          case "S" => println("Yup")
          case "D" => println("Yup")
          case _ => println(keyCode.getName + " pressed with no action")
        }
      }

      content = List()

      addEventHandler(KeyEvent.KEY_PRESSED, (event: KeyEvent) => keyPressed(event.getCode))
    }
  }
}

class HandleMessagesFromPython() extends Emitter.Listener {
  override def call(objects: Object*): Unit = {
    val message = objects.apply(0).toString
    // do something with message
    Platform.runLater(() => {
      val jsonGameState = objects.apply(0).toString
      println(jsonGameState)
      val gameState: JsValue = Json.parse(jsonGameState)
      val player = (gameState \ "players").as[Map[String, JsValue]]

      for ((k, v) <- player) {

      }
    })
  }
}

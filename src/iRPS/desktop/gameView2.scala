package iRPS

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
import scalafx.scene.Group


object gameView2 extends JFXApp {

  var socket: Socket = IO.socket("http://localhost:8080/")
  socket.on("gamestate", new HandleMessagesFromPython)
  socket.connect()


  stage = new application.JFXApp.PrimaryStage {
    title.value = "Test"
    scene = new Scene(1280, 720) {

      fill = White

      var sceneGraphics: Group = new Group{}
      val tileSize = 30
      def placeSquare(x: Double, y: Double, color: Paint): Rectangle = {
        val square0 = new Rectangle
        square0.width = tileSize
        square0.height = tileSize
        square0.x = x
        square0.y = y
        square0.fill = color
        square0
      }
      def drawGameBoard(x: Double, y: Double): Unit = {
        val gridWidth: Double = x
        val gridHeight: Double = y
        sceneGraphics.children.add(placeSquare(x, y, White))
      }
      def parseGameState(event: String): Unit = {
        val parsed: JsValue = Json.parse(event)
        val gridSize: List[JsValue] = (parsed \ "gridSize").as[List[JsValue]]
        for (j <- gridSize) {
          val gx = (j \ "x").as[Double]
          val gy = (j \ "y").as[Double]

          drawGameBoard(gx, gy)
        }
        val player: List[JsValue] = (parsed \ "players").as[List[JsValue]]
        for (i <- player) {
          val px = (i \ "x").as[Double]
          val py = (i \ "y").as[Double]

          val playerid: Map[String, JsValue] = (parsed \ "id").as[Map[String, JsValue]]
          for (d <- playerid) {
            val id = d._2.as[String]

            sceneGraphics.children.add(placeCircle(px, py, id, 2.0))
          }
        }
      }





      def placeCircle(x: Double, y: Double, color: String, radius: Double): Circle = {
        val circle0 = new Circle
        circle0.radius = 6
        circle0.centerX = x
        circle0.centerY = y
        if (color == socket.id) {
          circle0.fill = Blue
        } else {
          circle0.fill = Orange
        }
        circle0
      }






      var keyStates: JsValue = Json.obj(
        "w" -> false,
        "a" -> false,
        "s" -> false,
        "d" -> false,
      )

      def keyPressed(keyCode: KeyCode): Unit = {
        keyCode.getName match {
          case "W" => checkState(keyCode, true)
          case "A" => checkState(keyCode, true)
          case "S" => checkState(keyCode, true)
          case "D" => checkState(keyCode, true)
          case "ArrowUp" => checkState(keyCode, true)
          case "ArrowLeft" => checkState(keyCode, true)
          case "ArrowDown" => checkState(keyCode, true)
          case "ArrowRight" => checkState(keyCode, true)
          case _ => println(keyCode.getName + " pressed with no action")
        }
      }

      def checkState(keyCode: KeyCode, Boolean: Boolean): Unit = {
        if (keyStates(keyCode.getName) != Boolean) {
          keyStates(keyCode.getName) = Boolean
          socket.emit("keyStates", Json.stringify(keyStates))
        }
      }

      def keyReleased(keyCode: KeyCode): Unit = {
        keyCode.getName match {
          case "W" => checkState(keyCode, false)
          case "A" => checkState(keyCode, false)
          case "S" => checkState(keyCode, false)
          case "D" => checkState(keyCode, false)
          case "ArrowUp" => checkState(keyCode, false)
          case "ArrowLeft" => checkState(keyCode, false)
          case "ArrowDown" => checkState(keyCode, false)
          case "ArrowRight" => checkState(keyCode, false)
          case _ => println(keyCode.getName + " pressed with no action")
        }
      }

      addEventHandler(KeyEvent.KEY_PRESSED, (event: KeyEvent) => keyPressed(event.getCode))

      addEventHandler(KeyEvent.KEY_RELEASED, (event: KeyEvent) => keyReleased(event.getCode))

      content = List(sceneGraphics)


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

    })
  }
}

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

      def parseGameState(event): Unit = {
        val gameState = Json.parse(event)

        drawGameBoard(gameState["gridSize"])

        for (var x <- gameState["players"]) {
          sceneGraphics.children.add(placeCircle(player["x"], player["y"], player["id"], 2.0))
        }
      }

      val tileSize = 30



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

      def placeSquare(x: Double, y: Double, color: Paint): Rectangle = {
        val square0 = new Rectangle
        square0.width = tileSize
        square0.height = tileSize
        square0.x = x
        square0.y = y
        square0.fill = color
        square0
      }

      def drawGameBoard(gridSize): Unit = {
        val gridWidth: Double = gridSize["x"]
        val gridHeight: Double = gridSize["y"]

        var x = 0
        var y = 0
        for (x <- 0 to gridWidth, y <- 0 to gridHeight) {
          sceneGraphics.children.add(placeSquare(x, y, White))
        }
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

      addEventHandler(KeyEvent.KEY_PRESSED, (event: KeyEvent) => keyPressed(event.getCode))

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

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


object gameView extends JFXApp {


  stage = new application.JFXApp.PrimaryStage {
    title.value = "Test"
    scene = new Scene(1280, 720) {
      var socket: Socket = IO.socket("http://localhost:8080/")
      socket.on("gameState", new HandleMessagesFromPython)
      socket.connect()
      val socketid = socket.id
      var gigaGameState: JsValue = Json.obj()


      class HandleMessagesFromPython() extends Emitter.Listener {
        override def call(objects: Object*): Unit = {
          val message = objects.apply(0).toString
          val gameState: JsValue = Json.parse(message)
          Platform.runLater(() => {
          parseGameState(message)
        })
      }
      }

      fill = White

      var sceneGraphics: Group = new Group{}
      val tileSize = 30
      def placeSquare(x: Double, y: Double, color: Paint): Unit = {
        val square0 = new Rectangle
        square0.width = tileSize
        square0.height = tileSize
        square0.x = x
        square0.y = y
        square0.fill = color
        sceneGraphics.children.add(square0)
        content = List(sceneGraphics)
      }
      def placeCircle(x: Double, y: Double, color: String, radius: Double): Unit = {
        val circle0 = new Circle
        circle0.radius = radius
        circle0.centerX = x
        circle0.centerY = y
        if (color == socket.id) {
          circle0.fill = Blue
        } else {
          circle0.fill = Orange
        }
        sceneGraphics.children.add(circle0)
        content = List(sceneGraphics)
      }
      def drawGameBoard(x: Double, y: Double): Unit = {
        placeSquare(x, y, White)
      }
      def parseGameState(event: String): Unit = {
        val parsed: JsValue = Json.parse(event)
        val gridSize: Map[String, JsValue] = (parsed \ "gridSize").as[Map[String, JsValue]]
        val player: List[JsValue] = (parsed \ "players").as[List[JsValue]]


        //for (j <- gridSize) { Faulty code apparently
        //  val gx = gridSize("x").as[Double]
        //  val gy = gridSize("y").as[Double]
        //  drawGameBoard(gx, gy)
        //}

        //for (i <- player) {
        //  val px = (i \ "x").as[Double]
        //  val py = (i \ "y").as[Double]
        //  val playerid: Map[String, JsValue] = (parsed \ "id").as[Map[String, JsValue]]
        //
        //  for (d <- playerid) {
        //    val id = d._2.as[String]

        //    placeCircle(px, py, id, 2.0)
        //  }


        //}
      } // gameparse ends


      var keyStates: JsValue = Json.obj(
        "w" -> false,
        "a" -> false,
        "s" -> false,
        "d" -> false,
      )

      def keyPressed(keyCode: KeyCode): Unit = {
        keyCode.getName match {
          case "W" => //checkState(keyCode, true)
          case "A" => //checkState(keyCode, true)
          case "S" => //checkState(keyCode, true)
          case "D" => //checkState(keyCode, true)
          case "Up" => //checkState(keyCode, true)
          case "Left" => //checkState(keyCode, true)
          case "Down" => //checkState(keyCode, true)
          case "Right" => //checkState(keyCode, true)
          case _ => print()
        }
      }

      //def checkState(keyCode: KeyCode, Boolean: Boolean): Unit = {
      //  if (keyStates(keyCode.getName) != Boolean) {
      //  keyStates(keyCode.getName) = Boolean
      //    socket.emit("keyStates", Json.stringify(keyStates))
      //  }
      //}

      def keyReleased(keyCode: KeyCode): Unit = {
        keyCode.getName match {
          case "W" => //checkState(keyCode, false)
          case "A" => //checkState(keyCode, false)
          case "S" => //checkState(keyCode, false)
          case "D" => //checkState(keyCode, false)
          case "Up" => //checkState(keyCode, false)
          case "Left" => //checkState(keyCode, false)
          case "Down" => //checkState(keyCode, false)
          case "Right" => print("Button up")//checkState(keyCode, false)
          case _ => print()
        }
      }

      addEventHandler(KeyEvent.KEY_PRESSED, (event: KeyEvent) => keyPressed(event.getCode))

      addEventHandler(KeyEvent.KEY_RELEASED, (event: KeyEvent) => keyReleased(event.getCode))

    }
  }





}


package model

import java.sql.{Connection, DriverManager, ResultSet}

import iRPS.model.game_objects.Player

object Database {


  val url = "jdbc:mysql://localhost/mysql?serverTimezone=UTC"
  val username = "root"
  val password = "12345678"
  var connection: Connection = DriverManager.getConnection(url, username, password)

  /*
   * Change the username/password to your own, but do not modify the methods in this file. Your Database.scala
   * file will be deleted and replaced during testing so any changes you make will not be reflected in AutoLab.
   *
   * There are no objectives to complete here. You should read through the methods and determine
   * what they do, then call the methods you need to be able to complete the objectives
  */

  setupTable()

  def setupTable(): Unit = {
    val statement = connection.createStatement()
    statement.execute("CREATE TABLE IF NOT EXISTS players (username TEXT, points DOUBLE, locationX DOUBLE, locationY DOUBLE, velocityX DOUBLE, velocityY DOUBLE, inGame BOOLEAN)")
  }


  def playerExists(username: String): Boolean = {
    val statement = connection.prepareStatement("SELECT * FROM players WHERE username=?")

    statement.setString(1, username)
    val result: ResultSet = statement.executeQuery()

    result.next()
  }


  def createPlayer(username: String, location_x: Double, location_y: Double): Unit = {
    val statement = connection.prepareStatement("INSERT INTO players VALUE (?, ?, ?, ?, ?, ?, ?)")

    statement.setString(1, username)
    statement.setDouble(2, 0.0)
    statement.setDouble(3, location_x)
    statement.setDouble(4, location_y)
    statement.setDouble(5, 0.0)
    statement.setDouble(6, 0.0)
    statement.setBoolean(7, false)

    statement.execute()
  }


  def saveGame(username: String, points: Double, location_x: Double, location_y: Double, velocity_x: Double, velocity_y: Double, ingame: Boolean): Unit = {
    val statement = connection.prepareStatement("UPDATE players SET points = ?, locationX = ?, locationY = ?, velocityX = ?, velocityY = ?, inGame = ? WHERE username = ?")

    statement.setDouble(1, points)
    statement.setDouble(2, location_x)
    statement.setDouble(3, location_y)
    statement.setDouble(4, velocity_x)
    statement.setDouble(5, velocity_y)
    statement.setBoolean(6, ingame)
    statement.setString(7, username)

    statement.execute()
  }


  def loadGame(username: String, player: Player): Unit = {
    val statement = connection.prepareStatement("SELECT * FROM players WHERE username=?")
    statement.setString(1, username)
    val result: ResultSet = statement.executeQuery()

    result.next()
    player.points= result.getDouble("points")
    player.location.x = result.getDouble("locationX")
    player.location.y = result.getDouble("locationY")
    player.velocity.x = result.getDouble("velocityX")
    player.velocity.y = result.getDouble("velovityY")
  }


}

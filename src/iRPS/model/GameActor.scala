package iRPS.model

import akka.actor.{Actor, ActorRef, PoisonPill, Props}
import iRPS.model.game_objects.GameObject3
import iRPS.model.physics.PhysicsVector


class GameActor extends Actor {

  var players: Map[String, ActorRef] = Map()
  var towers: List[ActorRef] = List()

  val game: Game = new Game()
  var levelNumber = 0
  loadLevel(levelNumber)

  def loadLevel(levelNumber: Int): Unit ={
    val level = Level(levelNumber)
    game.loadLevel(level)
    towers.foreach(child => child ! PoisonPill)
    towers = for(t <- level.towerLocations) yield {context.actorOf(Props(classOf[GameActor2], self, t.x, t.y))}
  }

  override def receive: Receive = {
    case message: AddPlayer => game.addPlayer(message.username)
    case message: RemovePlayer => game.removePlayer(message.username)
    case message: MovePlayer => game.players(message.username).move(new PhysicsVector(message.x, message.y))
    case message: StopPlayer => game.players(message.username).stop()
    case message: Rock => {
      if (game.players(message.username).inGame)
        game.players(message.username).choice = 1
    }
    case message: Paper => {
      if (game.players(message.username).inGame)
        game.players(message.username).choice = 2
    }
    case message: Scissors => {
      if (game.players(message.username).inGame)
        game.players(message.username).choice = 3
    }

    case UpdateGame =>
      game.update()
      if (game.baseHealth <= 0) {
        game.baseHealth = 2
        levelNumber = (levelNumber + 1) % 3
        loadLevel(levelNumber)
      }
    case SendGameState => sender() ! GameState(game.gameState())
    case projectile: AddProjectile =>
      val location = new PhysicsVector(projectile.x, projectile.y, projectile.z)
      val velocity = new PhysicsVector(projectile.xVelocity, projectile.yVelocity, projectile.zVelocity)
      game.addProjectile(new GameObject3(location, velocity))
  }
}

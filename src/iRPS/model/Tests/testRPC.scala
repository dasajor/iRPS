package iRPS.model.Tests

import iRPS.model.Game2
import iRPS.model.game_objects.Player
import iRPS.model.physics.PhysicsVector
import org.scalatest.FunSuite

class testRPC extends FunSuite {
    test("see2312") {
      var player1: Player = new Player(new PhysicsVector(0, 0), new PhysicsVector(0, 0))
      var player2: Player = new Player(new PhysicsVector(0, 0), new PhysicsVector(0, 0))
      player1.choice = 1
      player2.choice = 3
      var game: Game2 = new Game2(player1,player2)
      game.RPS()
      assert(player1.points == 1, "yes1")
    }
    test("see1") {
      var player1: Player = new Player(new PhysicsVector(0, 0), new PhysicsVector(0, 0))
      var player2: Player = new Player(new PhysicsVector(0, 0), new PhysicsVector(0, 0))
      player1.choice = 2
      player2.choice = 1
      var game: Game2 = new Game2(player1,player2)
      game.RPS()
      assert(player1.points == 1, "yes3")
    }
    test("see") {
      var player1: Player = new Player(new PhysicsVector(0, 0), new PhysicsVector(0, 0))
      var player2: Player = new Player(new PhysicsVector(0, 0), new PhysicsVector(0, 0))
      player1.choice = 3
      player2.choice = 2
      var game: Game2 = new Game2(player1,player2)
      game.RPS()
      assert(player1.points == 1, "yes2")
    }
    test("see42") {
      var player1: Player = new Player(new PhysicsVector(0, 0), new PhysicsVector(0, 0))
      var player2: Player = new Player(new PhysicsVector(0, 0), new PhysicsVector(0, 0))
      player1.choice = 3
      player2.choice = 1
      var game: Game2 = new Game2(player1,player2)
      game.RPS()
      assert(player2.points == 1, "yes4")
    }
    test("see32") {
      var player1: Player = new Player(new PhysicsVector(0, 0), new PhysicsVector(0, 0))
      var player2: Player = new Player(new PhysicsVector(0, 0), new PhysicsVector(0, 0))
      player1.choice = 1
      player2.choice = 2
      var game: Game2 = new Game2(player1,player2)
      game.RPS()
      assert(player2.points == 1, "yes5")
    }
    test("see2") {
      var player1: Player = new Player(new PhysicsVector(0, 0), new PhysicsVector(0, 0))
      var player2: Player = new Player(new PhysicsVector(0, 0), new PhysicsVector(0, 0))
      player1.choice = 2
      player2.choice = 3
      var game: Game2 = new Game2(player1,player2)
      game.RPS()
      assert(player2.points == 1, "yes6")
    }

}

package iRPS.model.game_objects

import iRPS.model.Game2
import iRPS.model.physics.PhysicsVector

class Player(inputLocation: PhysicsVector,
             inputVelocity: PhysicsVector) extends PhysicalObject(inputLocation, inputVelocity) {

  val speed: Double = 4.0
  var inGame: Boolean = false
  var choice: Int = 0
  var points: Double = 0

  def move(direction: PhysicsVector){
    if(this.inGame == false) {
      val normalDirection = direction.normal2d()
      this.velocity = new PhysicsVector(normalDirection.x * speed, normalDirection.y * speed)
    }
  }

  def stop(): Unit ={
    this.velocity = new PhysicsVector(0, 0)
  }

}

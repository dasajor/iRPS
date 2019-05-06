package iRPS.model.game_objects

import play.api.libs.json.{JsValue, Json}
import iRPS.model.physics.PhysicsVector

class GameObject2(val x: Int, val y: Int) extends GameObject {

  val height = 3.0

  val sightRange = 5.0

  val projectileVelocity = 5.0


  def fire(jsonGameState: String): List[GameObject3] = {
    // TODO: Objective 2
    List()
  }


  def aimFire(jsonGameState: String): List[GameObject3] = {
    // TODO: Bonus Objective
    List()
  }


  // Suggested Genetic Algorithm setup
  def getFitnessFunction(targetPlayer: Player): PhysicsVector => Double = {
    null
  }

  def vectorIncubator(): PhysicsVector = {
    null
  }

}

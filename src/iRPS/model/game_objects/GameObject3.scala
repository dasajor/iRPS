package iRPS.model.game_objects

import iRPS.model.physics.PhysicsVector

class GameObject3(location: PhysicsVector,
                  velocity: PhysicsVector)
  extends PhysicalObject(location, velocity) {


  override def onGround():Unit={
    this.destroy()
  }

  override def collide(): Unit = {
    this.destroy()
  }

}

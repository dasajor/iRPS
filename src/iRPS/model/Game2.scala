package iRPS.model

import iRPS.model.game_objects.Player

class Game2(a: Player,b: Player){
  def RPS(): Unit ={
    Thread.sleep(10000)
    if((a.choice == 1)&&(b.choice==1)){  //rock-rock
      a.choice = 0
      b.choice = 0
      RPS()
    }
    else if((a.choice == 2)&&(b.choice==2)){  //paper-paper
      a.choice = 0
      b.choice = 0
      RPS()
    }
    else if((a.choice == 3)&&(b.choice==3)){  //scissors-scissors
      a.choice = 0
      b.choice = 0
      RPS()
    }
    else if((a.choice == 1)&&(b.choice==2)){  //rock-paper  bW
      a.choice = 0
      a.inGame = false

      b.choice = 0
      b.points = b.points + 1
      b.inGame = false
    }
    else if((a.choice == 1)&&(b.choice==3)){  //rock-scissors  aW
      a.choice = 0
      a.points = a.points + 1
      a.inGame = false

      b.choice = 0
      b.inGame  =false
    }
    else if((a.choice == 2)&&(b.choice==1)){ //paper-rock  aW
      a.choice = 0
      a.points = a.points + 1
      a.inGame = false

      b.choice = 0
      b.inGame = false
    }
    else if((a.choice == 2)&&(b.choice==3)){ //paper-scissors  bW
      a.choice = 0
      a.inGame = false

      b.choice = 0
      b.points = b.points + 1
      b.inGame = false
    }
    else if((a.choice == 3)&&(b.choice==1)){ //scissors-rock  bW
      a.choice = 0
      a.inGame = false

      b.choice = 0
      b.points = b.points + 1
      b.inGame = false
    }
    else if((a.choice == 3)&&(b.choice==2)){ //scissors-paper  aW
      a.choice = 0
      a.points = a.points + 1
      a.inGame = false

      b.choice = 0
      b.inGame = false
    }
  }
}

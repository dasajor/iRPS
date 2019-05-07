package iRPS.model

import iRPS.model.game_objects.Player

class Game2(a: Player,b: Player){
  def RPS(): Unit ={
    println("Game Start!")

    if((a.choice == b.choice) && (a.choice != 0 && b.choice != 0)){  //scissors-scissors
      println(a.choice.toString + "," + b.choice.toString)
      a.choice = 0
      b.choice = 0
      a.inGame = false
      b.inGame = false
    }
    else if((a.choice == 1)&&(b.choice==2)){  //rock-paper  bW
      println(a.choice.toString + "," + b.choice.toString)
      a.choice = 0
      a.inGame = false

      b.choice = 0
      b.points = b.points + 1
      b.inGame = false
    }
    else if((a.choice == 1)&&(b.choice==3)){  //rock-scissors  aW
      println(a.choice.toString + "," + b.choice.toString)
      a.choice = 0
      a.points = a.points + 1
      a.inGame = false

      b.choice = 0
      b.inGame  =false
    }
    else if((a.choice == 2)&&(b.choice==1)){ //paper-rock  aW
      println(a.choice.toString + "," + b.choice.toString)
      a.choice = 0
      a.points = a.points + 1
      a.inGame = false

      b.choice = 0
      b.inGame = false
    }
    else if((a.choice == 2)&&(b.choice==3)){ //paper-scissors  bW
      println(a.choice.toString + "," + b.choice.toString)
      a.choice = 0
      a.inGame = false

      b.choice = 0
      b.points = b.points + 1
      b.inGame = false
    }
    else if((a.choice == 3)&&(b.choice==1)){ //scissors-rock  bW
      println(a.choice.toString + "," + b.choice.toString)
      a.choice = 0
      a.inGame = false

      b.choice = 0
      b.points = b.points + 1
      b.inGame = false
    }
    else if((a.choice == 3)&&(b.choice==2)){ //scissors-paper  aW
      println(a.choice.toString + "," + b.choice.toString)
      a.choice = 0
      a.points = a.points + 1
      a.inGame = false

      b.choice = 0
      b.inGame = false
    }

    println(a.points.toString + "," + b.points.toString)
  }
}

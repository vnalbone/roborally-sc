package mock.com.nalbisoft.roborally.domain

import com.nalbisoft.roborally.domain._
import com.nalbisoft.roborally.domain.game.{ProgramCardSet, Player}

object TestData {
  val SomePos = Position(5, 5)

  val NorthLoc = Location(North, SomePos)
  val  WestLoc = Location(West,  SomePos)
  val  EastLoc = Location(East,  SomePos)
  val SouthLoc = Location(South, SomePos)

  val SomeRobot = new Robot("Twonky", new RegisterSet())
  val SomeOtherRobot = new Robot("Trundle Bot", new RegisterSet())
  val SomeMCard = ProgramCard(100, Move1)
  val SomeLoc = SouthLoc
  val SomePlayer = new Player("Bob", SomeRobot)
  val SomeOtherPlayer = new Player("John", SomeOtherRobot)
  val SomeFloor = new BasicFactoryFloor(8)

  val Move1_Low = new ProgramCard(100, Move1)
  val Move2_Low = new ProgramCard(100, Move2)
  val Move3_Low = new ProgramCard(100, Move3)
  val UTurn_Low = new ProgramCard(100, UTurn)
  val RotateRight_Low = new ProgramCard(100, RotateRight)
  val RotateLeft_Low = new ProgramCard(100, RotateLeft)

  val SomeProgramCardSet = ProgramCardSet(Move1_Low, Move2_Low, Move3_Low, UTurn_Low, RotateLeft_Low)
}

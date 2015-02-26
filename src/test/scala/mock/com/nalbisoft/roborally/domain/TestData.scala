package mock.com.nalbisoft.roborally.domain

import com.nalbisoft.roborally.domain._

object TestData {
  val SomePos = Position(5, 5)

  val NorthLoc = Location(North, SomePos)
  val  WestLoc = Location(West,  SomePos)
  val  EastLoc = Location(East,  SomePos)
  val SouthLoc = Location(South, SomePos)

  val SomeRobot = new Robot("Twonky", new RegisterSet())
  val SomeMCard = ProgramCard(100, Move1)
  val SomeLoc = SouthLoc
}

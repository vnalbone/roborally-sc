package mock.com.nalbisoft.roborally.domain

import com.nalbisoft.roborally.domain._

object TestData {
  val SomePos = Position(5, 5)
  val SomeRobot = new Robot("Twonky")

  val NorthLoc = Location(North, SomePos)
  val  WestLoc = Location(West,  SomePos)
  val  EastLoc = Location(East,  SomePos)
  val SouthLoc = Location(South, SomePos)
}

package mock.com.nalbisoft.roborally.domain

import com.nalbisoft.roborally.domain.{Location, Robot, FactoryFloor}

object MockFactoryFloor {
  def newForLocationOf(locationOfFunc: => Location) = new MockFactoryFloor(canAddMoreFunc = ???, locationOfFunc = locationOfFunc)
  def newForCanAddMore(canAddMoreFunc: => Boolean) = new MockFactoryFloor(canAddMoreFunc = canAddMoreFunc, locationOfFunc = ???)
  def newForOther() = new MockFactoryFloor(canAddMoreFunc = ???, locationOfFunc = ???)
}

class MockFactoryFloor(canAddMoreFunc: => Boolean = false, locationOfFunc: => Location = null) extends FactoryFloor {
  override def canAddMoreRobots: Boolean = canAddMoreFunc

  var spiedRobotForLocationOf: Option[Robot] = None
  override def locationOf(robot: Robot): Location = {
    spiedRobotForLocationOf = Some(robot)
    locationOfFunc
  }

  var    spiedRobotForAdd: Option[Robot]    = None
  var spiedLocationForAdd: Option[Location] = None
  override def addRobot(robot: Robot, startPos: Location) = {
    spiedRobotForAdd = Some(robot)
    spiedLocationForAdd = Some(startPos)
  }


  var    spiedRobotForMove: Option[Robot]    = None
  var spiedLocationForMove: Option[Location] = None
  override def moveRobot(robot: Robot, newLoc: Location) = {
    spiedRobotForMove = Some(robot)
    spiedLocationForMove = Some(newLoc)
  }
}

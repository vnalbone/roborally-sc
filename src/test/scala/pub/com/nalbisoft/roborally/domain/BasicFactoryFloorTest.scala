package pub.com.nalbisoft.roborally.domain

import com.nalbisoft.roborally.domain._

import mock.com.nalbisoft.roborally.domain.TestData._

import org.specs2.mutable.Specification
import org.specs2.specification.Scope

class BasicFactoryFloorTest extends Specification {
  class EmptyFactoryFloorScope extends Scope {
    val floor = new BasicFactoryFloor(0)
  }

  class OneRobotScope extends Scope {
    val startPos = Location(North, Position(0, 0))
    val floor = new BasicFactoryFloor(1)
  }

  class TwoRobotScope extends Scope {
    val  firstStartPos = Location(North, Position(0, 0))
    val secondStartPos = Location(South, Position(10, 10))

    var r1 = new Robot("Bert", new RegisterSet())
    val r2 = new Robot("Ernie", new RegisterSet())

    val floor = new BasicFactoryFloor(2)
  }

  "Creating a FactoryFloor" should {

    "not allow any robots to be added if the max has been hit" in new EmptyFactoryFloorScope {
      floor.canAddMoreRobots must beFalse
    }

    "allow you to add a robot if the max has not been hit" in new OneRobotScope {
      floor.canAddMoreRobots must beTrue
    }
  }

  "Adding a robot to an existing board" should {

    "throw an IllegalStateException if the board has no start positions" in new EmptyFactoryFloorScope() {
      floor.canAddMoreRobots must beFalse

      floor.addRobot(SomeRobot, SomeLoc) must throwAn[IllegalStateException]
    }

    "start that robot off at the proper start position based on the order it is added" in new TwoRobotScope {
      floor.addRobot(r1, firstStartPos)
      floor.locationOf(r1) mustEqual firstStartPos

      floor.addRobot(r2, secondStartPos)
      floor.locationOf(r2) mustEqual secondStartPos

      floor.addRobot(SomeRobot, SomeLoc) must throwAn[IllegalStateException]
    }
  }

  "Calling positionOf" should {

    "throw an IllegalArgumentException if the robot does not exist on the board" in new OneRobotScope {
      floor.locationOf(SomeRobot) must throwAn[IllegalArgumentException]
    }
  }

  "Moving a robot" should {

    "throw an IllegalArgumentException if the robot does not exist on the board" in new OneRobotScope {
      floor.moveRobot(SomeRobot, SomeLoc) must throwAn[IllegalArgumentException]
    }

    "correctly update the position of the robot moved while leaving the others alone" in new TwoRobotScope {

      floor.addRobot(r1, firstStartPos)
      floor.addRobot(r2, secondStartPos)

      val newLoc = Location(East, Position(6, 6))

      floor.moveRobot(r1, newLoc)
      floor.locationOf(r1) mustEqual newLoc
    }
  }
}

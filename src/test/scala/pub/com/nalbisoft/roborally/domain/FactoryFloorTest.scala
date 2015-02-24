package pub.com.nalbisoft.roborally.domain

import com.nalbisoft.roborally.domain.{Robot, Position, FactoryFloor, North}

import mock.com.nalbisoft.roborally.domain.TestData._

import org.specs2.mutable.Specification
import org.specs2.specification.Scope

class FactoryFloorTest extends Specification {
  class EmptyFactoryFloorScope extends Scope {
    val floor = new FactoryFloor(0)
  }

  class OneRobotScope extends Scope {
    val startPos = Position(0, 0)
    val floor = new FactoryFloor(1)
  }

  class TwoRobotScope extends Scope {
    val  firstStartPos = Position(0, 0)
    val secondStartPos = Position(10, 10)

    val floor = new FactoryFloor(2)
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

      floor.addRobot(new Robot("Bob"), SomePos) must throwAn[IllegalStateException]
    }

    "start that robot off at the proper start position based on the order it is added" in new TwoRobotScope {
      var r1 = new Robot("Bert")
      val r2 = new Robot("Ernie")

      floor.addRobot(r1, firstStartPos)
      floor.positionOf(r1) mustEqual firstStartPos

      floor.addRobot(r2, secondStartPos)
      floor.positionOf(r2) mustEqual secondStartPos

      floor.addRobot(new Robot("Darth Vader"), SomePos) must throwAn[IllegalStateException]
    }
  }

  "Calling positionOf" should {

    "throw an IllegalArgumentException if the robot does not exist on the board" in new OneRobotScope {
      floor.positionOf(new Robot("bogus robot")) must throwAn[IllegalArgumentException]
    }
  }

  "Moving a robot" should {

    "throw an IllegalArgumentException if the robot does not exist on the board" in new OneRobotScope {
      floor.moveRobot(new Robot("bogus robot"), 1, North) must throwAn[IllegalArgumentException]
    }

    "correctly update the position of the robot moved while leaving the others alone" in new TwoRobotScope {
      var r1 = new Robot("Bert")
      val r2 = new Robot("Ernie")

      floor.addRobot(r1, firstStartPos)
      floor.addRobot(r2, secondStartPos)

      floor.moveRobot(r1, 1, North)
      floor.positionOf(r1) mustEqual Position(0, 1)
    }
  }
}

package pub.com.nalbisoft.roborally.domain

import com.nalbisoft.roborally.domain._
import org.specs2.mutable.Specification
import mock.com.nalbisoft.roborally.domain.TestData._

class MovementCardTypeTest extends Specification {

  "Move1" should {
    "move robot forward 1, increasing hPos by 1 while leaving the direction unchanged when facing North" in {
      Move1.applyMove(SomeRobot, North, SomePos) mustEqual (North, Position(5, 6))
    }
    "move robot forward 1, decreasing hPos by 1 while leaving the direction unchanged when facing South" in {
      Move1.applyMove(SomeRobot, South, SomePos) mustEqual (South, Position(5, 4))
    }
    "move robot forward 1, decreasing wPos by 1 while leaving the direction unchanged when facing East" in {
      Move1.applyMove(SomeRobot, East, SomePos) mustEqual (East, Position(6, 5))
    }
    "move robot forward 1, decreasing wPos by 1 while leaving the direction unchanged when facing West" in {
      Move1.applyMove(SomeRobot, West, SomePos) mustEqual (West, Position(4, 5))
    }
  }

  "Move2" should {
    "move robot forward 2, increasing hPos by 2 while leaving the direction unchanged when facing North" in {
      Move2.applyMove(SomeRobot, North, SomePos) mustEqual (North, Position(5, 7))
    }
    "move robot forward 2, decreasing hPos by 2 while leaving the direction unchanged when facing South" in {
      Move2.applyMove(SomeRobot, South, SomePos) mustEqual (South, Position(5, 3))
    }
    "move robot forward 2, decreasing wPos by 2 while leaving the direction unchanged when facing East" in {
      Move2.applyMove(SomeRobot, East, SomePos) mustEqual (East, Position(7, 5))
    }
    "move robot forward 2, decreasing wPos by 2 while leaving the direction unchanged when facing West" in {
      Move2.applyMove(SomeRobot, West, SomePos) mustEqual (West, Position(3, 5))
    }
  }

  "Move3" should {
    "move robot forward 3, increasing hPos by 3 while leaving the direction unchanged when facing North" in {
      Move3.applyMove(SomeRobot, North, SomePos) mustEqual (North, Position(5, 8))
    }
    "move robot forward 3, decreasing hPos by 3 while leaving the direction unchanged when facing South" in {
      Move3.applyMove(SomeRobot, South, SomePos) mustEqual (South, Position(5, 2))
    }
    "move robot forward 3, increasing wPos by 3 while leaving the direction unchanged when facing East" in {
      Move3.applyMove(SomeRobot, East, SomePos) mustEqual (East, Position(8, 5))
    }
    "move robot forward 3, decreasing wPos by 3 while leaving the direction unchanged when facing West" in {
      Move3.applyMove(SomeRobot, West, SomePos) mustEqual (West, Position(2, 5))
    }
  }

  "BackUp" should {
    "move robot backwards 1, decreasing hPos by 1 while leaving the direction unchanged when facing North" in {
      BackUp.applyMove(SomeRobot, North, SomePos) mustEqual (North, Position(5, 4))
    }
    "move robot backwards 1, increasing hPos by 1 while leaving the direction unchanged when facing South" in {
      BackUp.applyMove(SomeRobot, South, SomePos) mustEqual (South, Position(5, 6))
    }
    "move robot backwards 1, decreasing wPos by 1 while leaving the direction unchanged when facing East" in {
      BackUp.applyMove(SomeRobot, East, SomePos) mustEqual (East, Position(4, 5))
    }
    "move robot backwards 1, increasing wPos by 1 while leaving the direction unchanged when facing West" in {
      BackUp.applyMove(SomeRobot, West, SomePos) mustEqual (West, Position(6, 5))
    }
  }

  "UTurn" should {
    "turn the robot around, so its facing South when it was facing North" in {
      UTurn.applyMove(SomeRobot, North, SomePos) mustEqual (South, SomePos)
    }
    "turn the robot around, so its facing North when it was facing South" in {
      UTurn.applyMove(SomeRobot, South, SomePos) mustEqual (North, SomePos)
    }
    "turn the robot around, so its facing West when it was facing East" in {
      UTurn.applyMove(SomeRobot, East, SomePos) mustEqual (West, SomePos)
    }
    "turn the robot around, so its facing East when it was facing West" in {
      UTurn.applyMove(SomeRobot, West, SomePos) mustEqual (East, SomePos)
    }
  }

  "RotateRight" should {
    "turn the robot right, so its facing East when it was facing North" in {
      RotateRight.applyMove(SomeRobot, North, SomePos) mustEqual (East, SomePos)
    }
    "turn the robot right, so its facing West when it was facing South" in {
      RotateRight.applyMove(SomeRobot, South, SomePos) mustEqual (West, SomePos)
    }
    "turn the robot right, so its facing South when it was facing East" in {
      RotateRight.applyMove(SomeRobot, East, SomePos) mustEqual (South, SomePos)
    }
    "turn the robot right, so its facing North when it was facing West" in {
      RotateRight.applyMove(SomeRobot, West, SomePos) mustEqual (North, SomePos)
    }
  }

  "RotateLeft" should {
    "turn the robot left, so its facing West when it was facing North" in {
      RotateLeft.applyMove(SomeRobot, North, SomePos) mustEqual (West, SomePos)
    }
    "turn the robot left, so its facing East when it was facing South" in {
      RotateLeft.applyMove(SomeRobot, South, SomePos) mustEqual (East, SomePos)
    }
    "turn the robot left, so its facing North when it was facing East" in {
      RotateLeft.applyMove(SomeRobot, East, SomePos) mustEqual (North, SomePos)
    }
    "turn the robot left, so its facing South when it was facing West" in {
      RotateLeft.applyMove(SomeRobot, West, SomePos) mustEqual (South, SomePos)
    }
  }
}

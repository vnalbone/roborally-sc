package pub.com.nalbisoft.roborally.domain

import com.nalbisoft.roborally.domain._
import org.specs2.mutable.Specification

class DirectionTest extends Specification {
  "Applying move of 1" should {

    "increase hPos by specified amount if North" in {
      val pos = Position(5, 5)
      North.applyMove(pos, 1) mustEqual Position(5, 6)
    }

    "decrease hPos by specified amount if South" in {
      val pos = Position(5, 5)
      South.applyMove(pos, 1) mustEqual Position(5, 4)
    }

    "increase wPos by specified amount if East" in {
      val pos = Position(5, 5)
      East.applyMove(pos, 1) mustEqual Position(6, 5)
    }

    "decrease wPos by specified amount if West" in {
      val pos = Position(5, 5)
      West.applyMove(pos, 1) mustEqual Position(4, 5)
    }
  }

  "Turning around" should {
    "return South when facing North" in {
      North.turnAround mustEqual South
    }
    "return North when facing South" in {
      South.turnAround mustEqual North
    }
    "return West when facing East" in {
      East.turnAround mustEqual West
    }
    "return East when facing West" in {
      West.turnAround mustEqual East
    }
  }

  "Turning right" should {
    "return East when facing North" in {
      North.turnRight mustEqual East
    }
    "return West when facing South" in {
      South.turnRight mustEqual West
    }
    "return South when facing East" in {
      East.turnRight mustEqual South
    }
    "return North when facing West" in {
      West.turnRight mustEqual North
    }
  }

  "Turning left" should {
    "return East when facing North" in {
      North.turnLeft mustEqual West
    }
    "return West when facing South" in {
      South.turnLeft mustEqual East
    }
    "return South when facing East" in {
      East.turnLeft mustEqual North
    }
    "return North when facing West" in {
      West.turnLeft mustEqual South
    }
  }
}

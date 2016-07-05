package pub.com.nalbisoft.roborally.domain

import com.nalbisoft.roborally.domain.RegisterNumbers.One
import com.nalbisoft.roborally.domain._
import com.nalbisoft.roborally.domain.core.card.{Move1, ProgramCard}
import mock.com.nalbisoft.roborally.domain.MockFactoryFloor
import mock.com.nalbisoft.roborally.domain.TestData._
import org.specs2.mutable.Specification
import org.specs2.specification.Scope

class RobotTest extends Specification {
  class RobotScope extends Scope {
    val regSet = new RegisterSet()
    val robot = new Robot("Bob", regSet)
  }

  "Programming a register" should {
    "update the appropriate register in the set" in new RobotScope {
      regSet.isProgrammed(One) must beFalse
      robot.program(One, SomeMCard)

      regSet.isProgrammed(One) must beTrue
      regSet.programmedCard(One) must beSome(SomeMCard)
    }
  }

  "Calling move" should {
    "tell the passed in FactoryFloor to move to the correct location" in new RobotScope {
      val mockFloor = MockFactoryFloor.newForLocationOf(Location(North, Position(5, 5)))

      val card = new ProgramCard(100, Move1)
      robot.program(One, card)
      robot.executeRegister(One, mockFloor)

      mockFloor.spiedRobotForMove must beSome(robot)
      mockFloor.spiedLocationForMove must beSome(Location(North, Position(5, 6)))
    }
  }
}

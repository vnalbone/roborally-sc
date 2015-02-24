package pub.com.nalbisoft.roborally.domain

import com.nalbisoft.roborally.domain.{NotProgrammedException, Move1, Register}
import mock.com.nalbisoft.roborally.domain.TestData._
import mock.com.nalbisoft.roborally.domain.MockMovementCard
import org.specs2.mutable.Specification
import org.specs2.specification.Scope

class RegisterTest extends Specification {
  class RegisterScope extends Scope {
    val register = new Register(1)
  }

  "Programming a register" should {
    "update programed card" in new RegisterScope {
      register.isProgrammed must beFalse
      register.programmedCard must beNone

      register.program(SomeMCard)

      register.isProgrammed must beTrue
      register.programmedCard must beSome(SomeMCard)
    }
  }

  "Applying Register.move" should {
    "throw NotProgrammedException when apply a move to an unprogrammed register" in  new RegisterScope {
      register.applyMove(SomeRobot, SomeLoc) must throwA[NotProgrammedException]
    }

    "delegate the call to the MovementCard" in  new RegisterScope {
      val mockCard = new MockMovementCard(1, Move1, NorthLoc)

      register.program(mockCard)

      register.applyMove(SomeRobot, SouthLoc)

      mockCard.spiedRobot must beSome(SomeRobot)
      mockCard.spiedLocation must beSome(SouthLoc)
    }
  }
}

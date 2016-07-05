package pub.com.nalbisoft.roborally.domain

import com.nalbisoft.roborally.domain.RegisterNumbers.One
import com.nalbisoft.roborally.domain.core.card.Move1
import com.nalbisoft.roborally.domain.{NotProgrammedException, Register}
import mock.com.nalbisoft.roborally.domain.TestData._
import mock.com.nalbisoft.roborally.domain.MockProgramCard
import org.specs2.mutable.Specification
import org.specs2.specification.Scope

class RegisterTest extends Specification {
  class RegisterScope extends Scope {
    val register = new Register(One)
  }

  "Programming a register" should {
    "update programmed card" in new RegisterScope {
      register.isProgrammed must beFalse
      register.programmedCard must beNone

      register.program(SomeMCard)

      register.isProgrammed must beTrue
      register.programmedCard must beSome(SomeMCard)
    }
  }

  "Applying Register.move" should {
    "throw NotProgrammedException when apply a move to an un-programmed register" in  new RegisterScope {
      register.applyMove(SomeRobot, SomeLoc) must throwA[NotProgrammedException]
    }

    "delegate the call to the ProgramCard" in  new RegisterScope {
      val mockCard = new MockProgramCard(1, Move1, NorthLoc)

      register.program(mockCard)

      register.applyMove(SomeRobot, SouthLoc)

      mockCard.spiedRobot must beSome(SomeRobot)
      mockCard.spiedLocation must beSome(SouthLoc)
    }
  }
}

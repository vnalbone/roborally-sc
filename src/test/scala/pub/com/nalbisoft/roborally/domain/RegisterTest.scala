package pub.com.nalbisoft.roborally.domain

import com.nalbisoft.roborally.domain.{Move1, Register}
import mock.com.nalbisoft.roborally.domain.TestData._
import mock.com.nalbisoft.roborally.domain.MockMovementCard
import org.specs2.mutable.Specification

class RegisterTest extends Specification {
  "Applying Register.move" should {
    "delegate the call to the MovementCard" in {
      val mockCard = new MockMovementCard(1, Move1, NorthLoc)
      val register = Register(mockCard)

      register.applyMove(SomeRobot, SouthLoc)

      mockCard.spiedRobot must beSome(SomeRobot)
      mockCard.spiedLocation must beSome(SouthLoc)
    }
  }
}

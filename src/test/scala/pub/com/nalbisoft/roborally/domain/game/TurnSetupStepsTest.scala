package pub.com.nalbisoft.roborally.domain.game

import com.nalbisoft.roborally.domain.game.{Player, TurnSetupSteps}
import mock.com.nalbisoft.roborally.domain.TestData._
import org.specs2.mutable.Specification
import org.specs2.specification.Scope

class TurnSetupStepsTest extends Specification {
  class PlayerScope extends Scope {
    val p1 = new Player("Foo", SomeRobot)
    val p2 = new Player("Bar", SomeOtherRobot)
    val players = Set(p1, p2)
  }

  "Doing turn setup" should {
    "cards should only be marked as dealt when all players have completed it" in new PlayerScope {
      val setup = new TurnSetupSteps(players)

      setup.allCardsDealt must beFalse

      setup.completeDealCards(p1)
      setup.allCardsDealt must beFalse

      setup.completeDealCards(p2)
      setup.allCardsDealt must beTrue
    }

    "registers should only be marked as programmed when all players have completed it" in new PlayerScope {
      val setup = new TurnSetupSteps(players)

      setup.allRegistersProgrammed must beFalse

      setup.completeProgramRegisters(p1)
      setup.allRegistersProgrammed must beFalse

      setup.completeProgramRegisters(p2)
      setup.allRegistersProgrammed must beTrue
    }

    "power downs should only be marked as announced when all players have completed it" in new PlayerScope {
      val setup = new TurnSetupSteps(players)

      setup.allPowerDownsAnnounced must beFalse

      setup.completeAnnouncePowerDown(p1)
      setup.allPowerDownsAnnounced must beFalse

      setup.completeAnnouncePowerDown(p2)
      setup.allPowerDownsAnnounced must beTrue
    }

    "setup should only be marked as complete when all players have completed all steps" in new PlayerScope {
      val setup = new TurnSetupSteps(players)

      setup.complete must beFalse

      setup.completeDealCards(p1)
      setup.completeDealCards(p2)

      setup.complete must beFalse

      setup.completeProgramRegisters(p1)
      setup.completeProgramRegisters(p2)

      setup.complete must beFalse

      setup.completeAnnouncePowerDown(p1)
      setup.completeAnnouncePowerDown(p2)

      setup.complete must beTrue
    }
  }
}

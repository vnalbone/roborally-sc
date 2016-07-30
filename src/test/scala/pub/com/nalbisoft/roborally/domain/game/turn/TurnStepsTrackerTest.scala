package pub.com.nalbisoft.roborally.domain.game.turn

import com.nalbisoft.roborally.domain.game.{Player, PlayerId}
import com.nalbisoft.roborally.domain.game.turn.TurnStepsTracker
import org.specs2.mutable.Specification
import org.specs2.specification.Scope

class TurnStepsTrackerTest extends Specification {
  class PlayerScope extends Scope {
    val p1 = Player(PlayerId("1"), "Foo")
    val p2 = Player(PlayerId("2"), "Bar")
    val players = Set(p1, p2)
  }

  "Doing turn setup" should {
    "cards should only be marked as dealt when all players have completed it" in new PlayerScope {
      val setup = new TurnStepsTracker(players)

      setup.allCardsDealt must beFalse

      setup.completeDealCards(p1)
      setup.allCardsDealt must beFalse

      setup.completeDealCards(p2)
      setup.allCardsDealt must beTrue
    }

    "registers should only be marked as programmed when all players have completed it" in new PlayerScope {
      val setup = new TurnStepsTracker(players)

      setup.allRegistersProgrammed must beFalse

      setup.completeProgramRegisters(p1)
      setup.allRegistersProgrammed must beFalse

      setup.completeProgramRegisters(p2)
      setup.allRegistersProgrammed must beTrue
    }

    "power downs should only be marked as announced when all players have completed it" in new PlayerScope {
      val setup = new TurnStepsTracker(players)

      setup.allPowerDownsAnnounced must beFalse

      setup.completeAnnouncePowerDown(p1)
      setup.allPowerDownsAnnounced must beFalse

      setup.completeAnnouncePowerDown(p2)
      setup.allPowerDownsAnnounced must beTrue
    }

    "setup should only be marked as complete when all players have completed all steps" in new PlayerScope {
      val setup = new TurnStepsTracker(players)

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

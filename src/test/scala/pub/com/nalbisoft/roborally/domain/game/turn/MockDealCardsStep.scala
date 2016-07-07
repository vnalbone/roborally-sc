package pub.com.nalbisoft.roborally.domain.game.turn

import com.nalbisoft.roborally.domain.core.card.{CardDeck, ProgramCard}
import com.nalbisoft.roborally.domain.game.Player
import com.nalbisoft.roborally.domain.game.turn.{DealCardsStep, TurnStepFactory}

import scala.util.Try

class StubTurnStepFactory private(val dcs: DealCardsStep) extends TurnStepFactory {

  def this() {
    this(null)
  }

  def withNewDealCardsStep(newStep: DealCardsStep): StubTurnStepFactory = {
    new StubTurnStepFactory(dcs = newStep)
  }

  override def createDealCardsStep: DealCardsStep = dcs
}

class MockDealCardsStep(res: Try[Seq[ProgramCard]]) extends DealCardsStep {
  override def dealCards(player: Player, deck: CardDeck): Try[Seq[ProgramCard]] = res
}

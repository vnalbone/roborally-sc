package pub.com.nalbisoft.roborally.domain.game.turn

import com.nalbisoft.roborally.domain.game.game.turn.DealCardsStepImpl
import org.specs2.mutable.Specification

class DealCardsStepImplTest extends Specification {

  class DrawCardsStepScope extends BaseStepScope {
    val step = new DealCardsStepImpl()
  }

  "Dealing cards" should {
    "return top 5 cards from deck when robot is undamaged" in new DrawCardsStepScope {
      val dealtCards = step.dealCards(player, deck)
      dealtCards must beSuccessfulTry(cards)
    }
  }
}

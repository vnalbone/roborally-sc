/*
 * Copyright (c) Vincent Nalbone 2016
 */

package pub.com.nalbisoft.roborally.domain.game.turn

import com.nalbisoft.roborally.domain.game.turn.DealCardsStepImpl
import mock.com.nalbisoft.test.BaseSpecs2Test

class DealCardsStepImplTest extends BaseSpecs2Test {

  class DrawCardsStepScope extends BaseStepScope {
    val step = DealCardsStepImpl
  }

  "Dealing cards" should {
    "return top 9 cards from deck when robot is undamaged" in new DrawCardsStepScope {
      step.dealCards(player, deck) must beSuccessfulTry

      player.passedProgramCards must beSome(cards.take(9))
    }
  }
}

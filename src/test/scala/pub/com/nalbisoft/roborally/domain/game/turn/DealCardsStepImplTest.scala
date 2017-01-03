/*
 * Copyright (c) Vincent Nalbone 2017
 */

package pub.com.nalbisoft.roborally.domain.game.turn

import com.nalbisoft.roborally.domain.game.turn.DealCardsStepImpl
import mock.com.nalbisoft.test.BaseSpecs2Test

class DealCardsStepImplTest extends BaseSpecs2Test {

  class DrawCardsStepScope extends BaseStepScope {
    val step = DealCardsStepImpl
  }

  "Dealing cards to an undamaged robot" should {
    "call acceptProgramCards with 9 cards from deck for 1 player" in new DrawCardsStepScope {
      step.dealCards(onePlayerList, deck) must beSuccessfulTry

      onePlayerList(0).passedProgramCards must beSome(cards.take(9))
    }

    "call acceptProgramCards with 9 cards from deck, alternating, for each of 2 players" in new DrawCardsStepScope {

      import com.nalbisoft.util.enrichers.EnrichedSeq

      step.dealCards(twoPlayerList, deck) must beSuccessfulTry

      twoPlayerList(0).passedProgramCards must beSome(cards.take(18).skip(1))
      twoPlayerList(1).passedProgramCards must beSome(cards.take(18).skipOther(1))
    }
  }
}

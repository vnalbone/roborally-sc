/*
 * Copyright (c) Vincent Nalbone 2016
 */

package com.nalbisoft.roborally.domain.game.turn

import com.nalbisoft.roborally.domain.core.card.{CardDeck, ProgramCard}
import com.nalbisoft.roborally.domain.game.Player

import scala.util.{Success, Try}

case object DealCardsStepImpl extends DealCardsStep {
  def dealCards(player: Player, deck: CardDeck): Try[Seq[ProgramCard]] = {
    //TODO check to see how many cards to deal for player based on robot damange, etc.
    val dealtCards = for (i <- 1 to 9) yield {
      deck.draw()
    }

    Success(dealtCards)
  }
}

package com.nalbisoft.roborally.domain.game.turn

import com.nalbisoft.roborally.domain.core.card.{CardDeck, ProgramCard}
import com.nalbisoft.roborally.domain.game.Player

import scala.util.{Success, Try}

class DealCardsStepImpl extends DealCardsStep {
  def dealCards(player: Player, deck: CardDeck): Try[Seq[ProgramCard]] = {
    //TODO check to see how many cards to deal for player based on robot damange, etc.
    val dealtCards = for (i <- 1 to 9) yield {
      deck.next()
    }

    Success(dealtCards)
  }
}

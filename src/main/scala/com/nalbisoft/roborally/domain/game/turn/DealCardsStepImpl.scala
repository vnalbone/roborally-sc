/*
 * Copyright (c) Vincent Nalbone 2017
 */

package com.nalbisoft.roborally.domain.game.turn

import com.nalbisoft.roborally.domain.core.card.{CardDeck, Hand}
import com.nalbisoft.roborally.domain.game.Player

import scala.util.Try

case object DealCardsStepImpl extends DealCardsStep {
  def dealCards(players: Seq[Player], deck: CardDeck): Try[Unit] = {
    deck.deal(players, 9) map { hands =>
      hands foreach { case (p, h) => p.acceptProgramCards(h.cards) }
    }
  }
}

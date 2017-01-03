/*
 * Copyright (c) Vincent Nalbone 2017
 */

package com.nalbisoft.roborally.domain.game.turn

import com.nalbisoft.roborally.domain.core.card.CardDeck
import com.nalbisoft.roborally.domain.game.Player

import scala.util.Try

trait DealCardsStep {
  def dealCards(player: Seq[Player], deck: CardDeck): Try[Unit]
}

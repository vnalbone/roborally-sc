/*
 * Copyright (c) Vincent Nalbone 2016
 */

package com.nalbisoft.roborally.domain.game.turn

import com.nalbisoft.roborally.domain.core.card.{CardDeck, Hand}
import com.nalbisoft.roborally.domain.game.Player

import scala.util.Try

trait DealCardsStep {
  def dealCards(player: Player, deck: CardDeck): Try[Unit]
}

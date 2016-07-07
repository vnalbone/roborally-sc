package com.nalbisoft.roborally.domain.game.turn

import com.nalbisoft.roborally.domain.core.card.{CardDeck, ProgramCard}
import com.nalbisoft.roborally.domain.game.Player

import scala.util.Try

trait DealCardsStep {
  def dealCards(player: Player, deck: CardDeck): Try[Seq[ProgramCard]]
}

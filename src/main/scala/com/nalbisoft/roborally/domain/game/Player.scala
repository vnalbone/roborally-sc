/*
 * Copyright (c) Vincent Nalbone 2016
 */

package com.nalbisoft.roborally.domain.game

import com.nalbisoft.roborally.domain.core.card.ProgramCard

case class PlayerId(id: String)

case class PlayerImpl(id: PlayerId, name: String, var programCards: Option[Seq[ProgramCard]] = None) extends Player {
  override def acceptProgramCards(cards: Seq[ProgramCard]) = {
    programCards = Some(cards)
    //    PlayerImpl(id, name, Some(cards))
  }
}

trait Player {
  val id: PlayerId
  val name: String

  def programCards: Option[Seq[ProgramCard]]

  def acceptProgramCards(cards: Seq[ProgramCard])
}

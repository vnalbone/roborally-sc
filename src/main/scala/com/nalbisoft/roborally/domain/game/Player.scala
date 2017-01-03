/*
 * Copyright (c) Vincent Nalbone 2017
 */

package com.nalbisoft.roborally.domain.game

import com.nalbisoft.roborally.domain.RegisterSet
import com.nalbisoft.roborally.domain.core.card.ProgramCard

case class PlayerId(id: String)

case class PlayerImpl(id: PlayerId, name: String,
                      var programCards: Option[Seq[ProgramCard]] = None,
                      var register: Option[RegisterSet] = None) extends Player {
  override def acceptProgramCards(cards: Seq[ProgramCard]) = {
    programCards = Some(cards)
  }

  override def acceptProgrammedRegister(newRegister: RegisterSet): Unit = {
    register = Some(newRegister)
  }
}

trait Player {
  val id: PlayerId
  val name: String

  def programCards: Option[Seq[ProgramCard]]

  def acceptProgramCards(cards: Seq[ProgramCard])

  def acceptProgrammedRegister(register: RegisterSet)
}

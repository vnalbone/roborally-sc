/*
 * Copyright (c) Vincent Nalbone 2018
 */

package com.nalbisoft.roborally.domain.game

import com.nalbisoft.roborally.domain.{RegisterSet, Robot}
import com.nalbisoft.roborally.domain.core.card.ProgramCard

case class PlayerId(id: String)

case class PlayerImpl(id: PlayerId, name: String, robot: Robot,
                      var programCards: Option[Seq[ProgramCard]] = None,
                      var register: Option[RegisterSet] = None) extends Player {
  override def acceptProgramCards(cards: Seq[ProgramCard]) = {
    programCards = Some(cards)
  }

  override def acceptProgrammedRegister(newRegister: RegisterSet): Unit = {
    register = Some(newRegister)
  }

  override def revealCards(playerCards: Seq[PlayerCard]): Unit = {
    //TODO Implement
  }
}

trait Player {
  val id: PlayerId
  val name: String
  val robot: Robot

  def programCards: Option[Seq[ProgramCard]]

  def register: Option[RegisterSet]

  def acceptProgramCards(cards: Seq[ProgramCard])

  def acceptProgrammedRegister(register: RegisterSet)

  def revealCards(playerCards: Seq[PlayerCard])
}

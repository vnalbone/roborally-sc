/*
 * Copyright (c) Vincent Nalbone 2017
 */

package com.nalbisoft.roborally.domain.game.turn

import com.nalbisoft.roborally.domain.game.{Player, ProgramCardSet}

import scala.util.Try

trait ProgramRegisterStep {
  def programRegisters(player: Player, cards: ProgramCardSet): Try[Unit]
}

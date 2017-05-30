/*
 * Copyright (c) Vincent Nalbone 2017
 */

package com.nalbisoft.roborally.domain.game.turn

import com.nalbisoft.roborally.domain.{FactoryFloor, RegisterNumber}
import com.nalbisoft.roborally.domain.game.Player

import scala.util.Try

/**
  *A. Reveal Program Cards
  *B. Robots Move
  *C. Board Elements Move
  *D. Lasers Fire
  *E. Touch Checkpoints
  */
trait CompleteRegistersStep {
  def completeRegisters(player: Seq[Player], register: RegisterNumber, floor: FactoryFloor): Try[Unit]
}

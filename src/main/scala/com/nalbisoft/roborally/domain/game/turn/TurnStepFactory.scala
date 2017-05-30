/*
 * Copyright (c) Vincent Nalbone 2017
 */

package com.nalbisoft.roborally.domain.game.turn

trait TurnStepFactory {
  def createDealCardsStep: DealCardsStep

  def createProgramRegistersStep: ProgramRegisterStep

  def createCompleteRegistersStep: CompleteRegistersStep
}

case object TurnStepFactoryImpl extends TurnStepFactory {
  override def createDealCardsStep: DealCardsStep = DealCardsStepImpl

  override def createProgramRegistersStep: ProgramRegisterStep = ProgramRegisterStepImpl

  override def createCompleteRegistersStep: CompleteRegistersStep = CompleteRegistersStepImpl
}
package com.nalbisoft.roborally.domain.game.turn

trait TurnStepFactory {
  def createDealCardsStep: DealCardsStep

  def createProgramRegistersStep: ProgramRegisterStep
}

case object TurnStepFactoryImpl extends TurnStepFactory {
  override def createDealCardsStep: DealCardsStep = new DealCardsStepImpl

  override def createProgramRegistersStep: ProgramRegisterStep = new ProgramRegisterStepImpl
}
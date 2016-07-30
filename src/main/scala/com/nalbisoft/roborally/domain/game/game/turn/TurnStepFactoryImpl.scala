package com.nalbisoft.roborally.domain.game.game.turn

case object TurnStepFactoryImpl extends TurnStepFactory {
  override def createDealCardsStep: DealCardsStep = new DealCardsStepImpl

  override def createProgramRegistersStep: ProgramRegisterStep = new ProgramRegisterStepImpl
}

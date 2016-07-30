package com.nalbisoft.roborally.domain.game.game.turn

trait TurnStepFactory {
  def createDealCardsStep: DealCardsStep

  def createProgramRegistersStep: ProgramRegisterStep
}

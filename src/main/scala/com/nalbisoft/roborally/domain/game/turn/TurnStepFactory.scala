package com.nalbisoft.roborally.domain.game.turn

trait TurnStepFactory {
  def createDealCardsStep: DealCardsStep

  def createProgramRegistersStep: ProgramRegisterStep
}

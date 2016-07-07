package com.nalbisoft.roborally.domain.game.turn

case object TurnStepFactoryImpl extends TurnStepFactory {
  override def createDealCardsStep: DealCardsStep = new DealCardsStepImpl
}

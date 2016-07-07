package com.nalbisoft.roborally.domain.game.turn

import com.nalbisoft.roborally.domain.game.Player

class TurnStepsTracker(players: Set[Player]) {

  def complete = allCardsDealt && allRegistersProgrammed && allPowerDownsAnnounced

  private var cardsDealt: Map[Player, Boolean] = (players map { p => (p, false)}).toMap
  def alreadyDealtCards(player: Player) = cardsDealt(player)
  def allCardsDealt = assess(cardsDealt)
  def completeDealCards(player: Player) = cardsDealt += (player -> true)

  private var didProgramCards: Map[Player, Boolean] = (players map { p => (p, false)}).toMap
  def alreadyProgrammedRegisters(player: Player) = didProgramCards(player)
  def allRegistersProgrammed = assess(didProgramCards)
  def completeProgramRegisters(player: Player) =  didProgramCards += (player -> true)

  private var powerDownAnnounced: Map[Player, Boolean] = (players map { p => (p, false)}).toMap
  def alreadyAnnouncedPowerDown(player: Player) = powerDownAnnounced(player)
  def allPowerDownsAnnounced = assess(powerDownAnnounced)
  def completeAnnouncePowerDown(player: Player) =  powerDownAnnounced += (player -> true)

  private def assess(map: Map[Player, Boolean]) = map.values.foldLeft(true)((a, b: Boolean) => a&b)
}

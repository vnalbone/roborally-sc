/*
 * Copyright (c) Vincent Nalbone 2017
 */

package com.nalbisoft.roborally.domain.game.turn

import com.nalbisoft.roborally.domain.game.Player

class TurnStepsTracker(players: Seq[Player]) {

  def complete: Boolean = allCardsDealt && allRegistersProgrammed && allPowerDownsAnnounced

  private var cardsDealt: Map[Player, Boolean] = (players map { p => (p, false)}).toMap

  def alreadyDealtCards(player: Player): Boolean = cardsDealt(player)

  def allCardsDealt: Boolean = assess(cardsDealt)

  def completeDealCards(player: Player): Unit = cardsDealt += (player -> true)

  private var didProgramCards: Map[Player, Boolean] = (players map { p => (p, false)}).toMap

  def alreadyProgrammedRegisters(player: Player): Boolean = didProgramCards(player)

  def allRegistersProgrammed: Boolean = assess(didProgramCards)

  def completeProgramRegisters(player: Player): Unit = didProgramCards += (player -> true)

  private var powerDownAnnounced: Map[Player, Boolean] = (players map { p => (p, false)}).toMap

  def alreadyAnnouncedPowerDown(player: Player): Boolean = powerDownAnnounced(player)

  def allPowerDownsAnnounced: Boolean = assess(powerDownAnnounced)

  def completeAnnouncePowerDown(player: Player): Unit = powerDownAnnounced += (player -> true)

  private def assess(map: Map[Player, Boolean]) = map.values.foldLeft(true)((a, b: Boolean) => a&b)
}

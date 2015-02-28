package com.nalbisoft.roborally.domain.game

import com.nalbisoft.roborally.domain.RegisterNumbers._
import com.nalbisoft.roborally.domain.{FactoryFloor, ProgramCard, RegisterNumbers}

class Turn(players: Set[Player], floor: FactoryFloor) {
  private var setup: TurnSetupSteps = new TurnSetupSteps(players)

  private var turnStarted: Boolean = false
  private var turnEnded: Boolean = false

  private var regNums = RegisterNumbers.asSeq.iterator

  private val registerCompletionRule = new RegisterCompletionRules(players)

  def start() = {
    turnStarted = true
  }

  private def playingGame(player: Player) = players.contains(player)

  def dealCards(player: Player) : Seq[ProgramCard] = {
    assertTurnActive()

    if(!playingGame(player))            throw new IllegalArgumentException(s"Player, ${player.name}, is not part of the game")
    if(setup.alreadyDealtCards(player)) throw new IllegalStateException   (s"Player, ${player.name}, has already been dealt cards!")

    setup.completeDealCards(player)
    Nil
    //TODO check to see how many cards to deal for player based on robot damange, etc.
    //TODO deal from deck
  }

  def programRegisters(player: Player, cards: ProgramCardSet) = {
    assertTurnActive()

    if(!playingGame(player))         throw new IllegalArgumentException(s"Player, ${player.name}, is not part of the game")
    if(!setup.allCardsDealt)         throw new IllegalStateException   ("All cards must be dealt before programming registers!")
    if(setup.alreadyProgrammedRegisters(player)) throw new IllegalStateException   (s"Player, ${player.name}, has already programmed their registers!!")

    //TODO check for locked registers
    player.robot.program(One, cards.card1)
    player.robot.program(Two, cards.card2)
    player.robot.program(Three, cards.card3)
    player.robot.program(Four, cards.card4)
    player.robot.program(Five, cards.card5)

    setup.completeProgramRegisters(player)
  }

  def announcePowerDown(player: Player, intent: Boolean) = {
    assertTurnActive()

    if(!playingGame(player)) throw new IllegalArgumentException(s"Player, ${player.name}, is not part of the game")
    if(!setup.allRegistersProgrammed) throw new IllegalStateException("All registers must be programmed before announcing power down!")
    if(setup.alreadyAnnouncedPowerDown(player)) throw new IllegalStateException(s"Player, ${player.name}, already announced power down!")

    //TODO do something with intent
    setup.completeAnnouncePowerDown(player)
  }

  def completeRegister() = {
    assertTurnActive()
    assertSetupComplete()

    if(registersComplete()) throw new IllegalStateException("All registers are already completed!")
  }

  def end() = {
    if(!registersComplete()) throw new IllegalStateException("Cannot power down because not all registers are completed!")

    turnEnded = true
    //TODO do cleanup
  }

  private def registersComplete() = !regNums.hasNext

  private def assertSetupComplete() = {
    if(!setup.complete) throw new IllegalStateException("Setup is not complete!")
  }

  private def assertTurnActive(): Unit = {
    if(!turnStarted)     throw new IllegalStateException("Turn has not yet started!")
    if(turnEnded)     throw new IllegalStateException("Turn is already over!")
  }
}



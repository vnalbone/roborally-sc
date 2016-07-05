package com.nalbisoft.roborally.domain.game

import com.nalbisoft.roborally.domain.RegisterNumbers._
import com.nalbisoft.roborally.domain.core.card.{CardDeck, ProgramCard}
import com.nalbisoft.roborally.domain.{FactoryFloor, RegisterNumbers}

/**
  * Overall Turn
  * 1. Deal the Program cards.
  * 2. Arrange your Program cards face down among your five registers.
  * 3. Announce intent to power down or continue running NEXT turn.
  * 4. Complete each register in order: execute the Program cards, complete board movements,
  * resolve all interactions, and touch flags and repair sites.
  * 5. Clean up any end-of-turn effects.
  *
  * @param players
  * @param floor
  */
class Turn(players: Set[Player], floor: FactoryFloor) {
  private var setup: TurnStepsTracker = new TurnStepsTracker(players)

  private var turnStarted: Boolean = false
  private var turnEnded: Boolean = false

  private var regNums = RegisterNumbers.asSeq.iterator

  def start() = {
    turnStarted = true
  }

  private def playingGame(player: Player) = players.contains(player)

  def dealCards(player: Player, deck: CardDeck): Seq[ProgramCard] = {
    def assertDealCardsOk(player: Player) = {
      assertTurnActive()
      assertPlayerInGame(player)

      if (setup.alreadyDealtCards(player)) throw new CardsAlreadyDealtException(player)
    }

    assertDealCardsOk(player)

    setup.completeDealCards(player)

    //TODO check to see how many cards to deal for player based on robot damange, etc.
    val dealtCards = for (i <- 1 to 5) yield {
      deck.next()
    }

    dealtCards
  }

  def programRegisters(player: Player, cards: ProgramCardSet) = {
    def assertProgramRegisterOk(player: Player) = {
      assertTurnActive()
      assertPlayerInGame(player)

      if (!setup.allCardsDealt) throw new CardsNotDealtException
      if (setup.alreadyProgrammedRegisters(player)) throw new RegistersAlreadyProgrammedException(player)
    }

    assertProgramRegisterOk(player)

    //TODO check for locked registers
    player.robot.program(One, cards.card1)
    player.robot.program(Two, cards.card2)
    player.robot.program(Three, cards.card3)
    player.robot.program(Four, cards.card4)
    player.robot.program(Five, cards.card5)

    setup.completeProgramRegisters(player)
  }

  //TODO Not implemented yet
  //  def announcePowerDown(player: Player, intent: Boolean) = {
  //    assertTurnActive()
  //    assertPlayerInGame(player)
  //
  //    if(!setup.allRegistersProgrammed) throw new IllegalStateException("All registers must be programmed before announcing power down!")
  //    if(setup.alreadyAnnouncedPowerDown(player)) throw new IllegalStateException(s"Player, ${player.name}, already announced power down!")
  //
  //    //TODO do something with intent
  //    setup.completeAnnouncePowerDown(player)
  //  }

  /*
    A. Reveal Program Cards
    B. Robots Move
    C. Board Elements Move
    D. Lasers Fire
      1. Board Lasers
      2. Robot Lasers
    E. Touch Checkpoints
      1. “touch” flag
      2. Any robot on a flag or repair site updates its archive location by putting its Archive marker on that space.
   */
  def completeRegister() = {
    assertTurnActive()
    assertSetupComplete()

    if(registersComplete()) throw new IllegalStateException("All registers are already completed!")


    //TODO complete register
  }

  /**
    * Repairs & Upgrades
    * Robots on a single-wrench space discard 1 Damage token. Robots on a crossed wrench/hammer space discard 1
    * Damage token AND draw one Option card. When you draw an Option card, read it aloud to the other players
    * and put it in front of you, face up.
    *
    * Wiping Registers
    * Discard all Program cards from registers that aren’t locked.
    */
  def cleanUp() = {
    if(!registersComplete()) throw new IllegalStateException("Cannot power down because not all registers are completed!")

    //TODO do cleanup

    turnEnded = true
  }

  private def registersComplete() = {
    !regNums.hasNext
  }

  private def assertSetupComplete() = {
    if(!setup.complete) throw new IllegalStateException("Setup is not complete!")
  }

  private def assertTurnActive(): Unit = {
    if (!turnStarted) throw new TurnNotStartedException
    if (turnEnded) throw new TurnAlreadyEndedException
  }

  private def assertPlayerInGame(player: Player) = {
    if (!playingGame(player)) throw new InvalidPlayerException(player)
  }
}

class TurnNotStartedException extends GameException("Turn has not yet started!")

class TurnAlreadyEndedException extends GameException("Turn is already over!")

class InvalidPlayerException(player: Player) extends GameException(s"Player, ${player.name}, is not part of the game")

class CardsAlreadyDealtException(player: Player) extends GameException(s"Player, ${player.name}, has already been dealt cards!")

class CardsNotDealtException extends GameException("All cards must be dealt before programming registers!")

class RegistersAlreadyProgrammedException(player: Player) extends GameException(s"Player, ${player.name}, has already programmed their registers!!")


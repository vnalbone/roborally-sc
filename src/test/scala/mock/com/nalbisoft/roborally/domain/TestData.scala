/*
 * Copyright (c) Vincent Nalbone 2017
 */

package mock.com.nalbisoft.roborally.domain

import com.nalbisoft.roborally.domain.RegisterNumbers._
import com.nalbisoft.roborally.domain._
import com.nalbisoft.roborally.domain.core.card._
import com.nalbisoft.roborally.domain.game.{PlayerId, ProgramCardSet}
import mock.com.nalbisoft.roborally.domain.game.PlayerSpy

object TestData {
  val SomePos = Position(5, 5)

  val NorthLoc = Location(North, SomePos)
  val  WestLoc = Location(West,  SomePos)
  val  EastLoc = Location(East,  SomePos)
  val SouthLoc = Location(South, SomePos)

  val SomeRobot = new Robot("Twonky", new RegisterSet())
  val SomeOtherRobot = new Robot("Trundle Bot", new RegisterSet())
  val SomeMCard = ProgramCard(100, Move1)
  val SomeLoc = SouthLoc
  val SomeFloor = new BasicFactoryFloor(8)
  val Move1_Low = ProgramCard(100, Move1)
  val Move2_Low = ProgramCard(100, Move2)
  val Move3_Low = ProgramCard(100, Move3)
  val UTurn_Low = ProgramCard(100, UTurn)
  val RotateRight_Low = ProgramCard(100, RotateRight)
  val RotateLeft_Low = ProgramCard(100, RotateLeft)
  val Move1_Med = ProgramCard(200, Move1)
  val Move2_Med = ProgramCard(200, Move2)
  val Move3_Med = ProgramCard(200, Move3)
  val UTurn_Med = ProgramCard(200, UTurn)
  val RotateRight_Med = ProgramCard(200, RotateRight)
  val RotateLeft_Med = ProgramCard(200, RotateLeft)
  val Move1_High = ProgramCard(300, Move1)
  val Move2_High = ProgramCard(300, Move2)
  val Move3_High = ProgramCard(300, Move3)
  val UTurn_High = ProgramCard(300, UTurn)
  val RotateRight_High = ProgramCard(300, RotateRight)
  val RotateLeft_High = ProgramCard(300, RotateLeft)
  val Move1_Highest = ProgramCard(400, Move1)
  val Move2_Highest = ProgramCard(400, Move2)
  val Move3_Highest = ProgramCard(400, Move3)
  val UTurn_Highest = ProgramCard(400, UTurn)
  val RotateRight_Highest = ProgramCard(400, RotateRight)
  val RotateLeft_Highest = ProgramCard(400, RotateLeft)
  val SomeProgramCardSet = ProgramCardSet(Move1_Low, Move2_Low, Move3_Low, UTurn_Low, RotateLeft_Low)
  val SomeRegSet = new RegisterSet()
    .programRegister(One, Move1_Low)
    .programRegister(Two, Move2_Low)
    .programRegister(Three, Move3_Low)
    .programRegister(Four, UTurn_Low)
    .programRegister(Five, RotateRight_Low)
  val SomeCards = Seq(Move1_Low, Move2_Low, Move3_Low, UTurn_Low, RotateLeft_Low)
  val SomeOtherCards = Seq(Move1_High, Move2_High, Move3_High, UTurn_High, RotateLeft_High)
  val p1Data = new PlayerTestData(SomePlayer, SomeRobot, SomeCards)
  val p2Data = new PlayerTestData(SomeOtherPlayer, SomeOtherRobot, SomeOtherCards)

  def SomePlayer = PlayerSpy(PlayerId("1"), "Bob")

  def SomeOtherPlayer = PlayerSpy(PlayerId("2"), "John")

  def YetAnotherPlayer = PlayerSpy(PlayerId("3"), "Jim")
}

class PlayerTestData(p: PlayerSpy, val robot: Robot, val cards: Seq[ProgramCard]) {

  val firstCard = cards(0)
  val secondCard = cards(1)
  val thirdCard = cards(2)
  val fourthCard = cards(3)
  val fifthCard = cards(4)

  val registerSet = new RegisterSet()
    .programRegister(One, firstCard)
    .programRegister(Two, secondCard)
    .programRegister(Three, thirdCard)
    .programRegister(Four, fourthCard)
    .programRegister(Five, fifthCard)

  val programCardSet = ProgramCardSet(firstCard, secondCard, thirdCard, fourthCard, fifthCard)
  val player = p.withRegister(registerSet)

}


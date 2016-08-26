package mock.com.nalbisoft.roborally.domain

import com.nalbisoft.roborally.domain.RegisterNumbers._
import com.nalbisoft.roborally.domain._
import com.nalbisoft.roborally.domain.core.card._
import com.nalbisoft.roborally.domain.game.{PlayerId, PlayerImpl, ProgramCardSet}

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
  val SomePlayer = PlayerImpl(PlayerId("1"), "Bob")
  val SomeOtherPlayer = PlayerImpl(PlayerId("2"), "John")
  val YetAnotherPlayer = PlayerImpl(PlayerId("3"), "Jim")
  val SomeFloor = new BasicFactoryFloor(8)

  val Move1_Low = ProgramCard(100, Move1)
  val Move2_Low = ProgramCard(100, Move2)
  val Move3_Low = ProgramCard(100, Move3)
  val UTurn_Low = ProgramCard(100, UTurn)
  val RotateRight_Low = ProgramCard(100, RotateRight)
  val RotateLeft_Low = ProgramCard(100, RotateLeft)

  val Move1_High = ProgramCard(200, Move1)
  val Move2_High = ProgramCard(200, Move2)
  val Move3_High = ProgramCard(200, Move3)
  val UTurn_High = ProgramCard(200, UTurn)
  val RotateRight_High = ProgramCard(200, RotateRight)
  val RotateLeft_High = ProgramCard(200, RotateLeft)

  val SomeProgramCardSet = ProgramCardSet(Move1_Low, Move2_Low, Move3_Low, UTurn_Low, RotateLeft_Low)
  val SomeRegSet = new RegisterSet()
    .programRegister(One, Move1_Low)
    .programRegister(Two, Move2_Low)
    .programRegister(Three, Move3_Low)
    .programRegister(Four, UTurn_Low)
    .programRegister(Five, RotateRight_Low)
}

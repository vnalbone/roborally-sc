package com.nalbisoft.roborally.domain

import com.nalbisoft.roborally.domain.RegisterNumbers._

object RegisterNumbers {
  object One extends RegisterNumber(0)
  object Two extends RegisterNumber(1)
  object Three extends RegisterNumber(2)
  object Four extends RegisterNumber(3)
  object Five extends RegisterNumber(4)

  def asSeq = Seq(One, Two, Three, Four, Five)
}

sealed class RegisterNumber(val index: Int)

class Register(val number: RegisterNumber) {
  private var progCard: Option[MovementCard] = None

  def isProgrammed = progCard.isDefined

  def programmedCard = progCard

  def program(card: MovementCard) = {
    progCard = Some(card)
  }

  def applyMove(robot: Robot, loc: Location): Location = {
    if(!isProgrammed) {
      throw new NotProgrammedException(number)
    }

    progCard.get.applyMove(robot, loc)
  }
}

class RegisterSet() {
  val registers = Seq(new Register(One), new Register(Two), new Register(Three), new Register(Four), new Register(Five))

  private def registerAt(regNum: RegisterNumber) = registers(regNum.index)

  def programmedCard(regNum: RegisterNumber) = registerAt(regNum).programmedCard

  def isProgrammed(regNum: RegisterNumber) = registerAt(regNum).isProgrammed

  def programRegister(regNum: RegisterNumber, card: MovementCard) = registerAt(regNum).program(card)

  def applyMove(regNum: RegisterNumber, robot: Robot, loc: Location) = registerAt(regNum).applyMove(robot, loc)
}

case class NotProgrammedException(number: RegisterNumber) extends Exception(s"Register $number not programmed")

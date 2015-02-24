package com.nalbisoft.roborally.domain

class Register(val number: Int) {
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

case class NotProgrammedException(number: Int) extends Exception(s"Register $number not programmed")

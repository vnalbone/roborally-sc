package com.nalbisoft.roborally.domain

object North extends Direction {
  override def applyMove(pos: Position, amount: Int): Position = {
    pos.copy(hPos = pos.hPos + amount)
  }

  override def turnAround: Direction = South

  override def turnRight: Direction = East

  override def turnLeft: Direction = West
}

object South extends Direction {
  override def applyMove(pos: Position, amount: Int): Position = {
    pos.copy(hPos = pos.hPos - amount)
  }

  override def turnAround: Direction = North

  override def turnRight: Direction = West

  override def turnLeft: Direction = East
}

object East  extends Direction {
  override def applyMove(pos: Position, amount: Int): Position = {
    pos.copy(wPos = pos.wPos + amount)
  }

  override def turnAround: Direction = West

  override def turnRight: Direction = South

  override def turnLeft: Direction = North
}

object West  extends Direction {
  override def applyMove(pos: Position, amount: Int): Position = {
    pos.copy(wPos = pos.wPos - amount)
  }

  override def turnAround: Direction = East

  override def turnRight: Direction = North

  override def turnLeft: Direction = South
}

sealed abstract class Direction() {
  def applyMove(pos: Position, amount: Int): Position
  def turnAround: Direction
  def turnRight: Direction
  def turnLeft: Direction
}

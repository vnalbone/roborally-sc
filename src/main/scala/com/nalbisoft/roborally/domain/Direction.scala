package com.nalbisoft.roborally.domain

object North extends Direction {
  override def applyMove(pos: Position, amount: Int): Position = {
    pos.copy(hPos = pos.hPos + amount)
  }
}
object South extends Direction {
  override def applyMove(pos: Position, amount: Int): Position = {
    pos.copy(hPos = pos.hPos - amount)
  }
}
object East  extends Direction {
  override def applyMove(pos: Position, amount: Int): Position = {
    pos.copy(wPos = pos.wPos + amount)
  }
}
object West  extends Direction {
  override def applyMove(pos: Position, amount: Int): Position = {
    pos.copy(wPos = pos.wPos - amount)
  }
}

sealed abstract class Direction() {
  def applyMove(pos: Position, amount: Int): Position
}

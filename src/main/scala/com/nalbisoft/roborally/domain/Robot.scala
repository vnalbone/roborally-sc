package com.nalbisoft.roborally.domain

class Robot(val name: String, regSet: RegisterSet) {
  def program(regNum: RegisterNumber, card: ProgramCard) = regSet.programRegister(regNum, card)

  def executeRegister(regNum: RegisterNumber, floor: FactoryFloor) = {
    val currLoc = floor.locationOf(this)
    val newLoc = regSet.applyMove(regNum, this, currLoc)

    floor.moveRobot(this, newLoc)
  }
}

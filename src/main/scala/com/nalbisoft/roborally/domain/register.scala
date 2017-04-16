/*
 * Copyright (c) Vincent Nalbone 2017
 */

package com.nalbisoft.roborally.domain

import com.nalbisoft.roborally.domain.RegisterNumbers._
import com.nalbisoft.roborally.domain.core.card.ProgramCard

object RegisterNumbers {
  object One extends RegisterNumber(0)
  object Two extends RegisterNumber(1)
  object Three extends RegisterNumber(2)
  object Four extends RegisterNumber(3)
  object Five extends RegisterNumber(4)

  def all = Seq(One, Two, Three, Four, Five)
}

sealed class RegisterNumber(val index: Int) {
  override def toString: String = {
    index.toString
  }

  def canEqual(other: Any): Boolean = other.isInstanceOf[RegisterNumber]

  override def equals(other: Any): Boolean = other match {
    case that: RegisterNumber =>
      (that canEqual this) &&
        index == that.index
    case _ => false
  }

  override def hashCode(): Int = {
    val state = Seq(index)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }
}

class Register(val number: RegisterNumber) {
  private var progCard: Option[ProgramCard] = None

  def isProgrammed = progCard.isDefined

  def programmedCard = progCard

  def program(card: ProgramCard) = {
    progCard = Some(card)
  }

  def applyMove(robot: Robot, loc: Location): Location = {
    if(!isProgrammed) {
      throw new NotProgrammedException(number)
    }

    progCard.get.applyMove(robot, loc)
  }

  override def toString: String = {
    s"""R$number - ${progCard.map(c => c.typ + ":" + c.priority)}"""
  }

  def canEqual(other: Any): Boolean = other.isInstanceOf[Register]

  override def equals(other: Any): Boolean = other match {
    case that: Register =>
      (that canEqual this) &&
        progCard == that.progCard &&
        number == that.number
    case _ => false
  }

  override def hashCode(): Int = {
    val state = Seq(progCard, number)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }
}

class RegisterSet() {
  val registers = Seq(new Register(One), new Register(Two), new Register(Three), new Register(Four), new Register(Five))

  private def registerAt(regNum: RegisterNumber) = {
    registers(regNum.index)
  }

  def programmedCard(regNum: RegisterNumber) = {
    registerAt(regNum).programmedCard
  }

  def isProgrammed(regNum: RegisterNumber) = {
    registerAt(regNum).isProgrammed
  }

  def programRegister(regNum: RegisterNumber, card: ProgramCard): RegisterSet = {
    registerAt(regNum).program(card)
    this
  }

  def applyMove(regNum: RegisterNumber, robot: Robot, loc: Location) = {
    registerAt(regNum).applyMove(robot, loc)
  }

  def canEqual(other: Any): Boolean = other.isInstanceOf[RegisterSet]

  override def equals(other: Any): Boolean = other match {
    case that: RegisterSet =>
      (that canEqual this) &&
        registers == that.registers
    case _ => false
  }

  override def hashCode(): Int = {
    val state = Seq(registers)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }

  override def toString = s"RegisterSet($registers)"
}

case class NotProgrammedException(number: RegisterNumber) extends Exception(s"Register $number not programmed")

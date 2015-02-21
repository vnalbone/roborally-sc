package com.nalbisoft.roborally.domain

import org.specs2.mutable.Specification

class DirectionTest extends Specification {
  "Applying move of 1" should {

    "increase hPos by specified amount if North" in {
      val pos = Position(5, 5)
      North.applyMove(pos, 1) mustEqual Position(5, 6)
    }

    "decrease hPos by specified amount if South" in {
      val pos = Position(5, 5)
      South.applyMove(pos, 1) mustEqual Position(5, 4)
    }

    "increase wPos by specified amount if East" in {
      val pos = Position(5, 5)
      East.applyMove(pos, 1) mustEqual Position(6, 5)
    }

    "decrease wPos by specified amount if West" in {
      val pos = Position(5, 5)
      West.applyMove(pos, 1) mustEqual Position(4, 5)
    }
  }
}

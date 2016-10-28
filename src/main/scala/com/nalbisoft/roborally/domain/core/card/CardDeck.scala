package com.nalbisoft.roborally.domain.core.card

import scala.util.Random

trait CardDeck {
  def next(): ProgramCard

  def shuffle(): Unit

  def count: Int
}

class BasicCardDeck(cards: Seq[ProgramCard]) extends CardDeck {
  var iter = cards.iterator
  var count = cards.size

  override def shuffle(): Unit = {
    iter = Random.shuffle(cards.iterator)
    count = cards.size
  }

  override def next(): ProgramCard = {
    if (!iter.hasNext) {
      throw DeckEmptyException()
    }

    count = count - 1

    iter.next()
  }
}

case class DeckEmptyException() extends Exception
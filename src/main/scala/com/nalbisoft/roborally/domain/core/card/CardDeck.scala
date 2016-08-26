package com.nalbisoft.roborally.domain.core.card

trait CardDeck {
  def next(): ProgramCard

  def count: Int
}

class BasicCardDeck(cards: Seq[ProgramCard]) extends CardDeck {
  val iter = cards.iterator
  var count = cards.size

  override def next(): ProgramCard = {
    if (!iter.hasNext) {
      throw DeckEmptyException()
    }

    count = count - 1

    iter.next()
  }
}

case class DeckEmptyException() extends Exception
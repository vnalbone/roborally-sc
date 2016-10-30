/*
 * Copyright (c) Vincent Nalbone 2016
 */

package com.nalbisoft.roborally.domain.core.card

import scala.util.{Failure, Random, Success, Try}

trait CardDeck {
  def draw(): Try[ProgramCard]

  def shuffle(): Unit

  def count: Int

  def hasMore: Boolean

  def deal(numPlayers: Int, numCards: Int): Try[Seq[Hand]]
}

class BasicCardDeck(cards: Seq[ProgramCard]) extends CardDeck {
  var iter = cards.iterator
  var count = cards.size

  override def shuffle(): Unit = {
    iter = Random.shuffle(cards.iterator)
    count = cards.size
  }

  override def draw(): Try[ProgramCard] = {
    if (!iter.hasNext) {
      return Failure(DeckEmptyException())
    }

    count = count - 1

    Success(iter.next())
  }

  override def hasMore: Boolean = {
    iter.hasNext
  }

  override def deal(numPlayers: Int, numCards: Int): Try[Seq[Hand]] = {

    val hands = List.fill(numPlayers)(new Hand())

    for (_ <- 1 to numCards) {
      hands foreach { h =>
        import com.nalbisoft.util.enrichers.EnrichedBoolean

        if (!hasMore) {
          return Failure(DeckEmptyException())
        }

        for (
          _ <- hasMore.toTry(DeckEmptyException());
          card <- draw()
        ) {
          h.addCard(card)
        }
      }
    }

    Success(hands)
  }
}

case class DeckEmptyException() extends Exception

class Hand(var cards: Seq[ProgramCard]) {
  def this() = {
    this(Nil)
  }

  def addCard(card: ProgramCard) = {
    cards = cards :+ card
  }
}
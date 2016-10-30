/*
 * Copyright (c) Vincent Nalbone 2016
 */

package com.nalbisoft.roborally.domain.core.card

import scala.util.Random

trait CardDeck {
  def draw(): ProgramCard

  def shuffle(): Unit

  def count: Int

  def hasNext: Boolean

  def deal(numPlayers: Int, numCards: Int): Seq[Hand]
}

class BasicCardDeck(cards: Seq[ProgramCard]) extends CardDeck {
  var iter = cards.iterator
  var count = cards.size

  override def shuffle(): Unit = {
    iter = Random.shuffle(cards.iterator)
    count = cards.size
  }

  override def draw(): ProgramCard = {
    if (!iter.hasNext) {
      throw DeckEmptyException()
    }

    count = count - 1

    iter.next()
  }

  override def hasNext: Boolean = {
    iter.hasNext
  }

  override def deal(numPlayers: Int, numCards: Int): Seq[Hand] = {

    val hands = List.fill(numPlayers)(new Hand())

    for (_ <- 1 to numCards) {
      hands foreach { h =>
        if (!hasNext) {
          throw DeckEmptyException()
        }
        h.addCard(draw())
      }
    }

    hands
  }
}

case class DeckEmptyException() extends Exception

class Hand() {
  var cards: Seq[ProgramCard] = Seq()

  def addCard(card: ProgramCard) = {
    cards = cards :+ card
  }
}
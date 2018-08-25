/*
 * Copyright (c) Vincent Nalbone 2018
 */

package pub.com.nalbisoft.roborally.domain.card

import com.nalbisoft.roborally.domain.core.card.{BasicCardDeck, DeckEmptyException}
import com.nalbisoft.roborally.domain.game.{PlayerId, PlayerImpl}
import mock.com.nalbisoft.roborally.domain.TestData._
import mock.com.nalbisoft.test.BaseSpecs2Test

class BasicCardDeckTest extends BaseSpecs2Test {

  class EmptyDeckScope extends Scope {
    val deck = new BasicCardDeck(Nil)
  }

  class CardScope extends Scope {
    val firstCard = Move1_Low
    val secondCard = Move2_Low
    val thirdCard = Move1_Med
    val fourthCard = Move2_Med

    val cards = Seq(firstCard, secondCard, thirdCard, fourthCard)

    val deck = new BasicCardDeck(cards)
  }

  "Calling count" should {
    "return 0 if the deck is created with no cards" in new EmptyDeckScope {
      deck.count mustEqual 0
    }

    "return 4 if the deck is created with 4 cards" in new CardScope {
      deck.count mustEqual 4
    }

    "decrease by 1 for each card drawn" in new CardScope {
      deck.count mustEqual 4
      deck.draw()
      deck.count mustEqual 3
      deck.draw()
      deck.count mustEqual 2
      deck.draw()
      deck.count mustEqual 1
      deck.draw()
      deck.count mustEqual 0
    }
  }

  "Shuffling cards" should {
    "do nothing when deck is empty" in new EmptyDeckScope {
      deck.count mustEqual 0
      deck.shuffle()
      deck.count mustEqual 0
    }

    "reset the count" in new CardScope {

      deck.count mustEqual 4
      deck.draw()
      deck.count mustEqual 3
      deck.draw()
      deck.count mustEqual 2

      deck.shuffle()
      deck.count mustEqual 4

      //TODO How do I test that cards are actually shuffled?
    }
  }

  "Drawing a card without shuffling" should {
    "fail with a DeckEmptyException if there are no more cards" in new EmptyDeckScope {

      deck.draw().assertFail[DeckEmptyException]
    }

    "return cards in their original order" in new CardScope {
      deck.count mustEqual 4
      deck.draw().extractSuccess mustEqual Move1_Low
      deck.count mustEqual 3
      deck.draw().extractSuccess mustEqual Move2_Low
      deck.count mustEqual 2
      deck.draw().extractSuccess mustEqual Move1_Med
      deck.count mustEqual 1
      deck.draw().extractSuccess mustEqual Move2_Med
      deck.count mustEqual 0
    }
  }

  "Calling hasMore" should {
    "return false for an empty deck" in new EmptyDeckScope {
      deck.hasMore must beFalse
    }

    "return true when there are still cards remaining and false after all are drawn" in new CardScope {
      deck.hasMore must beTrue
      deck.draw()
      deck.hasMore must beTrue
      deck.draw()
      deck.hasMore must beTrue
      deck.draw()
      deck.hasMore must beTrue
      deck.draw()
      deck.hasMore must beFalse
    }
  }

  "Dealing a number of cards for a number of people" should {
    "fail with a DeckEmptyException for an empty deck" in {
      val deck = new BasicCardDeck(Nil)
      deck.deal(Seq(SomePlayer), 5).assertFail[DeckEmptyException]
    }

    "fail with a DeckEmptyException if there aren't enough cards for everyone." in {
      val deck = new BasicCardDeck(Nil)
      deck.deal(Seq(SomePlayer), 5).assertFail[DeckEmptyException]
      deck.hasMore must beFalse
    }

    "return no hands when number of people is 0" in new CardScope {
      val result = deck.deal(Seq(), 0).extractSuccess
      result must haveSize(0)
    }

    "return 1 hand containing 1 card" in new CardScope {
      val hands = deck.deal(Seq(SomePlayer), 1).extractSuccess
      hands must haveSize(1)

      val hand = hands(SomePlayer)
      hand.cards must haveSize(1)
      hand.cards(0) mustEqual firstCard
    }

    "return 1 hand containing 2 cards when dealing 2 cards to 1 person" in new CardScope {
      val hands = deck.deal(Seq(SomePlayer), 2).extractSuccess
      hands must haveSize(1)

      val hand = hands(SomePlayer)
      hand.cards must haveSize(2)
      hand.cards(0) mustEqual firstCard
      hand.cards(1) mustEqual secondCard
    }

    "return 2 hands containing 2 cards when dealing 2 cards to 2 people, emptying the deck" in new CardScope {
      val hands = deck.deal(Seq(SomePlayer, SomeOtherPlayer), 2).extractSuccess
      hands must haveSize(2)

      val hand1 = hands(SomePlayer)
      hand1.cards must haveSize(2)
      hand1.cards(0) mustEqual firstCard
      hand1.cards(1) mustEqual thirdCard

      val hand2 = hands(SomeOtherPlayer)
      hand2.cards must haveSize(2)
      hand2.cards(0) mustEqual secondCard
      hand2.cards(1) mustEqual fourthCard

      deck.hasMore must beFalse
    }
  }
}

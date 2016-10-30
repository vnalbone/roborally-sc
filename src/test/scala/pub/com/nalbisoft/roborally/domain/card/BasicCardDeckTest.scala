/*
 * Copyright (c) Vincent Nalbone 2016
 */

package pub.com.nalbisoft.roborally.domain.card

import com.nalbisoft.roborally.domain.core.card.{BasicCardDeck, DeckEmptyException, Move1}
import mock.com.nalbisoft.roborally.domain.TestData._
import mock.com.nalbisoft.test.BaseSpecs2Test

class BasicCardDeckTest extends BaseSpecs2Test {

  class EmptyDeckScope extends Scope {
    val deck = new BasicCardDeck(Nil)
  }

  class CardScope extends Scope {
    val firstCard = Move1_Low
    val secondCard = Move2_Low
    val thirdCard = Move1_High
    val fourthCard = Move2_High

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

  "Calling next without shuffling cards" should {
    "fail with a DeckEmptyException if there are no more cards" in new EmptyDeckScope {

      deck.draw() must throwA[DeckEmptyException]
    }

    "return cards in their original order" in new CardScope {
      deck.count mustEqual 4
      deck.draw() mustEqual Move1_Low
      deck.count mustEqual 3
      deck.draw() mustEqual Move2_Low
      deck.count mustEqual 2
      deck.draw() mustEqual Move1_High
      deck.count mustEqual 1
      deck.draw() mustEqual Move2_High
      deck.count mustEqual 0
    }
  }

  "Calling hasNext" should {
    "return false for an empty deck" in new EmptyDeckScope {
      deck.hasNext must beFalse
    }

    "return true when there are still cards remaining and false after all are drawn" in new CardScope {
      deck.hasNext must beTrue
      deck.draw()
      deck.hasNext must beTrue
      deck.draw()
      deck.hasNext must beTrue
      deck.draw()
      deck.hasNext must beTrue
      deck.draw()
      deck.hasNext must beFalse
    }
  }

  "Dealing a number of cards for a number of people" should {
    "fail with a DeckEmptyException if there are no more cards" in {
      val deck = new BasicCardDeck(Nil)
      deck.deal(1, 5) must throwA[DeckEmptyException]
    }

    "return no hands when number of people is 0" in new CardScope {
      val result = deck.deal(0, 0)
      result must haveSize(0)
    }

    "return 1 hand containing 1 card" in new CardScope {
      val hands = deck.deal(1, 1)
      hands must haveSize(1)

      val hand = hands(0)
      hand.cards must haveSize(1)
      hand.cards(0) mustEqual firstCard
    }

    "return 1 hand containing 2 cards when dealing 2 cards to 1 person" in new CardScope {
      val hands = deck.deal(1, 2)
      hands must haveSize(1)

      val hand = hands(0)
      hand.cards must haveSize(2)
      hand.cards(0) mustEqual firstCard
      hand.cards(1) mustEqual secondCard
    }

    "return 2 hands containing 2 cards when dealing 2 cards to 2 people" in new CardScope {
      val hands = deck.deal(2, 2)
      hands must haveSize(2)

      val hand1 = hands(0)
      hand1.cards must haveSize(2)
      hand1.cards(0) mustEqual firstCard
      hand1.cards(1) mustEqual thirdCard

      val hand2 = hands(1)
      hand2.cards must haveSize(2)
      hand2.cards(0) mustEqual secondCard
      hand2.cards(1) mustEqual fourthCard
    }
  }
}

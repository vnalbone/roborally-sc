package pub.com.nalbisoft.roborally.domain.card

import com.nalbisoft.roborally.domain.core.card.{BasicCardDeck, DeckEmptyException, Move1}
import mock.com.nalbisoft.roborally.domain.TestData._
import mock.com.nalbisoft.test.BaseSpecs2Test

class BasicCardDeckTest extends BaseSpecs2Test {

  class CardScope extends Scope {
    val cards = Seq(Move1_Low, Move2_Low, Move1_High, Move2_High)

    val deck = new BasicCardDeck(cards)
  }

  "Calling count" should {
    "return 0 if the deck is created with no cards" in {
      val deck = new BasicCardDeck(Nil)
      deck.count mustEqual 0
    }

    "return 4 if the deck is created with 4 cards" in new CardScope {
      deck.count mustEqual 4
    }

    "decrease by 1 for each card drawn" in new CardScope {
      deck.count mustEqual 4
      deck.next()
      deck.count mustEqual 3
      deck.next()
      deck.count mustEqual 2
      deck.next()
      deck.count mustEqual 1
      deck.next()
      deck.count mustEqual 0
    }
  }

  "Shuffling cards" should {
    "do nothing when deck is empty" in {
      val deck = new BasicCardDeck(Nil)

      deck.count mustEqual 0
      deck.shuffle()
      deck.count mustEqual 0
    }

    "reset the count" in new CardScope {

      deck.count mustEqual 4
      deck.next()
      deck.count mustEqual 3
      deck.next()
      deck.count mustEqual 2

      deck.shuffle()
      deck.count mustEqual 4

      //TODO How do I test that cards are actually shuffled?
    }
  }

  "Calling next without shuffling cards" should {
    "fail with a DeckEmptyException if there are no more cards" in {
      val deck = new BasicCardDeck(Nil)
      deck.next() must throwA[DeckEmptyException]
    }

    "return cards in their original order" in new CardScope {
      deck.count mustEqual 4
      deck.next() mustEqual Move1_Low
      deck.count mustEqual 3
      deck.next() mustEqual Move2_Low
      deck.count mustEqual 2
      deck.next() mustEqual Move1_High
      deck.count mustEqual 1
      deck.next() mustEqual Move2_High
      deck.count mustEqual 0
    }
  }
}

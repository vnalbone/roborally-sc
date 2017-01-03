/*
 * Copyright (c) Vincent Nalbone 2017
 */

package pub.com.nalbisoft.roborally.domain.game.turn

import com.nalbisoft.roborally.domain.core.card.BasicCardDeck
import com.nalbisoft.roborally.domain.game.{PlayerId, ProgramCardSet}
import mock.com.nalbisoft.roborally.domain.TestData._
import mock.com.nalbisoft.roborally.domain.game.PlayerSpy
import org.specs2.specification.Scope

class BaseStepScope extends Scope {

  val player1 = PlayerSpy(PlayerId("1"), "Bob")
  val player2 = PlayerSpy(PlayerId("2"), "John")
  val onePlayerList: Seq[PlayerSpy] = Seq(player1)
  val twoPlayerList: Seq[PlayerSpy] = Seq(player1, player2)

  val cards = Seq(
    Move1_Low,
    Move2_Low,
    Move3_Low,
    UTurn_Low,
    RotateRight_Low,
    RotateLeft_Low,
    Move1_Med,
    Move2_Med,
    Move3_Med,
    UTurn_Med,
    RotateRight_Med,
    RotateLeft_Med,
    Move1_High,
    Move2_High,
    Move3_High,
    UTurn_High,
    RotateRight_High,
    RotateLeft_High,
    Move1_Highest,
    Move2_Highest,
    Move3_Highest,
    UTurn_Highest,
    RotateRight_Highest,
    RotateLeft_Highest)

  val cardSet = new ProgramCardSet(
    Move1_Low,
    Move2_Low,
    Move3_Low,
    UTurn_Low,
    RotateRight_Low
  )

  val deck = new BasicCardDeck(cards)
}

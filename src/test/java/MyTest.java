import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

class DealerTest {

	@BeforeEach
	void init(){
		BaccaratDealer thedealer = new BaccaratDealer();
		BaccaratGameLogic gameLogic = new BaccaratGameLogic();
	}

	@Test // Card constructor
    void testCardConstructor(){
		Card card1 = new Card("Heart",4);
		Card card2 = new Card("Diamond",2);
		Card card3 = new Card("Clubs",1);
	    assertEquals(4, card1.getvalue(), "Card value didn't initialize correctly");
        assertEquals(1, card3.getvalue(), "Card value didn't initialize correctly");
		assertEquals("DIAMOND", card2.getSuite(), "Incorrect Card Suite");
		assertEquals(2, card.getValue(), "Incorrect Card Value");
    }

	// BaccaratDealer tests

	@Test // BaccaratDealer constructor
	void testBaccaratDealerConstructor(){
		assertNotNull(thedealer.deck);
	}

	@Test // BaccaratDealer -> generateDeck()
	void testGenerateDeck1(){
		thedealer.generateDeck();

		assertEquals(1,thedealer.deck.get(0).getValue(), "wrong card value");
		assertEquals("Spade",thedealer.deck.get(0).getSuite(),"wrong card suite");

		assertEquals(2,thedealer.deck.get(1).getValue(), "wrong card value");
		assertEquals("Spade",thedealer.deck.get(1).getSuite(),"wrong card suite");

		assertEquals(13,thedealer.deck.get(12).getValue(), "wrong card value");
		assertEquals("Spade",thedealer.deck.get(12).getSuite(),"wrong card suite");

		assertEquals(1,thedealer.deck.get(13).getValue(), "wrong card value");
		assertEquals("Clubs",thedealer.deck.get(13).getSuite(),"wrong card suite");

		assertEquals(2,thedealer.deck.get(14).getValue(), "wrong card value");
		assertEquals("Clubs",thedealer.deck.get(14).getSuite(),"wrong card suite");

		assertEquals(13,thedealer.deck.get(25).getValue(), "wrong card value");
		assertEquals("Clubs",thedealer.deck.get(25).getSuite(),"wrong card suite");
	}

	@Test // BaccaratDealer -> generateDeck()
	void testGenerateDeck2(){
		thedealer.generateDeck();

		assertEquals(1,thedealer.deck.get(26).getValue(), "wrong card value");
		assertEquals("Diamond",thedealer.deck.get(26).getSuite(),"wrong card suite");

		assertEquals(2,thedealer.deck.get(27).getValue(), "wrong card value");
		assertEquals("Diamond",thedealer.deck.get(27).getSuite(),"wrong card suite");

		assertEquals(13,thedealer.deck.get(38).getValue(), "wrong card value");
		assertEquals("Diamond",thedealer.deck.get(38).getSuite(),"wrong card suite");

		assertEquals(1,thedealer.deck.get(39).getValue(), "wrong card value");
		assertEquals("Heart",thedealer.deck.get(39).getSuite(),"wrong card suite");

		assertEquals(2,thedealer.deck.get(40).getValue(), "wrong card value");
		assertEquals("Heart",thedealer.deck.get(40).getSuite(),"wrong card suite");
		
		assertEquals(13,thedealer.deck.get(51).getValue(), "wrong card value");
		assertEquals("Heart",thedealer.deck.get(51).getSuite(),"wrong card suite");
	}

	@Test // BaccaratDealer -> dealHand()
	void testDealHand1(){
		thedealer.generateDeck();
		ArrayList<Card> hand = thedealer.dealHand();

		assertEquals(1,hand.get(0).getValue(), "wrong card value");
		assertEquals("Heart",hand.get(0).getSuite(),"wrong card suite");
		assertEquals(2,hand.get(1).getValue(), "wrong card value");
		assertEquals("Heart",hand.get(1).getSuite(),"wrong card suite");
	}

	@Test // BaccaratDealer -> dealHand()
	void testDealHand2(){
		thedealer.generateDeck();
		thedealer.deck.remove(0);
		thedealer.deck.remove(0);
		ArrayList<Card> hand = thedealer.dealHand();


		assertEquals(3,hand.get(0).getValue(), "wrong card value");
		assertEquals("Heart",hand.get(0).getSuite(),"wrong card suite");
		assertEquals(4,hand.get(1).getValue(), "wrong card value");
		assertEquals("Heart",hand.get(1).getSuite(),"wrong card suite");
	}

	@Test
	void testDrawOne1(){
		thedealer.generateDeck();

		Card card = thedealer.drawOne();

		assertEquals(1,card.getValue(), "wrong card value");
		assertEquals("Heart",card.getSuite(),"wrong card suite");
	}

	@Test
	void testDrawOne2(){
		thedealer.generateDeck();
		thedealer.deck.remove(0);

		Card card = thedealer.drawOne();

		assertEquals(2,card.getValue(), "wrong card value");
		assertEquals("Heart",card.getSuite(),"wrong card suite");
	}

	@Test
	void testShuffleDeck1(){
		thedealer.generateDeck();
		Card cardTemp1 = thedealer.deck.get(0);

		assertEquals(1,cardTemp1.getValue(), "wrong card value");
		assertEquals("Heart",cardTemp1.getSuite(),"wrong card suite");

		thedealer.shuffleDeck();
		Card cardTemp2 = thedealer.deck.get(0);

		assertNotSame(cardTemp1,cardTemp2, "cards same, CHANCE THAT CARDS SAME POSSIBLE");
	}

	@Test
	void testShuffleDeck2(){
		thedealer.generateDeck();
		thedealer.deck.remove(0);
		Card cardTemp1 = thedealer.deck.get(0);

		assertEquals(2,cardTemp1.getValue(), "wrong card value");
		assertEquals("Heart",cardTemp1.getSuite(),"wrong card suite");

		thedealer.shuffleDeck();
		Card cardTemp2 = thedealer.deck.get(0);

		assertNotSame(cardTemp1,cardTemp2, "cards same");
	}

	@Test
	void testDeckSize1(){
		thedealer.generateDeck();

		assertEquals(52, thedealer.deckSize(),"wrong deck size");

		thedealer.deck.remove(0);

		assertEquals(51, thedealer.deckSize(),"wrong deck size");
	}

	@Test
	void testDeckSize2(){
		thedealer.generateDeck();

		assertEquals(52, thedealer.deckSize(),"wrong deck size");

		thedealer.deck.remove(0);
		thedealer.deck.remove(0);
		thedealer.deck.remove(0);
		thedealer.deck.remove(0);

		assertEquals(48, thedealer.deckSize(),"wrong deck size");
	}

	// BaccaratGameLogic tests

	@Test
	void testWhoWon1(){
		Card card1 = new Card("Heart",2);
		Card card2 = new Card("Diamond",3);
		Card card3 = new Card("Spade",4);

		Card card4 = new Card("Heart",5);
		Card card5 = new Card("Clubs",5);
		Card card6 = new Card("Diamond",2);

		ArrayList<Card> hand1 = new ArrayList<Card>();
		ArrayList<Card> hand2 = new ArrayList<Card>();
	    hand1.addAll(card1,card2,card3);

		hand2.addAll(card4,card5,card6);
	

		String winner = gameLogic.whoWon(hand1,hand2);

		assertEquals("Banker",winner,"wrong winner");

	}

	@Test
	void testWhoWon2(){
		Card card1 = new Card("Spade",3);
		Card card2 = new Card("Diamond",3);
		Card card3 = new Card("Spade",1);
		Card card4 = new Card("Heart",8);
		Card card5 = new Card("Clubs",9);
		ArrayList<Card> hand1 = new ArrayList<Card>();
		ArrayList<Card> hand2 = new ArrayList<Card>();
		hand1.addALL(card1,card2,card3);

		hand2.add(card4);
		hand2.add(card5);

		String winner = gameLogic.whoWon(hand1,hand2);

		assertEquals("Draw",winner,"wrong winner");
	}

	@Test 
	void testHandTotal1(){
		Card card1 = new Card("Heart",3);
		Card card2 = new Card("Diamond",9);
		Card card3 = new Card("Diamond",1);
		Card card4 = new Card("Clubs",11);
		Card card5 = new Card("Heart",11);
		Card card6 = new Card("Spade",2);
		ArrayList<Card> hand1 = new ArrayList<Card>();
		ArrayList<Card> hand2 = new ArrayList<Card>();
		hand1.add(card1);
		hand1.add(card2);
		hand1.add(card3);
		hand2.add(card4);
		hand2.add(card5);
		hand2.add(card6);

		assertEquals(3,gameLogic.handTotal(hand1), "wrong hand total");
		assertEquals(2,gameLogic.handTotal(hand2), "wrong hand total");
	}

	@Test // BaccaratGameLogic -> handTotal()
	void testHandTotal2(){
		Card card1 = new Card("Diamond",4);
		Card card2 = new Card("Diamond",2); // 7
		Card card3 = new Card("Heart",1);
		Card card4 = new Card("Clubs",9);
		Card card5 = new Card("Diamond",8); // 7
		ArrayList<Card> hand1 = new ArrayList<Card>();
		ArrayList<Card> hand2 = new ArrayList<Card>();
		hand1.add(card1);
		hand1.add(card2);
		hand1.add(card3);
		hand2.add(card4);
		hand2.add(card5);

		assertEquals(7,gameLogic.handTotal(hand1), "wrong hand total");
		assertEquals(7,gameLogic.handTotal(hand2), "wrong hand total");
	}

	@Test // BaccaratGameLogic -> evaluateBankerDraw()
	void testEvaluateBankerDraw1(){
		Card card1 = new Card("Diamond",2);
		Card card3 = new Card("Spade",2);
		Card card4 = new Card("Heart",2);
		Card card5 = new Card("Diamond",8);
		Card card6 = new Card("Clubs",4);
		ArrayList<Card> hand1 = new ArrayList<Card>();
		ArrayList<Card> hand2 = new ArrayList<Card>();
		ArrayList<Card> hand3 = new ArrayList<Card>();
		hand1.add(card1); // hand1: 4
		hand1.add(card3);
		hand2.add(card4); // hand2: 10
		hand2.add(card5);
		hand3.add(card1); // hand3: 6
		hand3.add(card6);

		assertEquals(true,gameLogic.evaluateBankerDraw(hand1,card4),"should not draw");
		assertEquals(true,gameLogic.evaluateBankerDraw(hand2,card5),"should draw");
		assertEquals(false,gameLogic.evaluateBankerDraw(hand3,card5),"should draw");
	}

	@Test // BaccaratGameLogic -> evaluateBankerDraw()
	void testEvaluateBankerDraw2(){
		Card card1 = new Card("Heart",2);
		Card card3 = new Card("Spade",3);
		Card card4 = new Card("Clubs",2);
		Card card5 = new Card("Diamond",6);
		Card card6 = new Card("Heart",1);
		Card card7 = new Card("Heart",4);
		ArrayList<Card> hand1 = new ArrayList<Card>();
		ArrayList<Card> hand2 = new ArrayList<Card>();
		ArrayList<Card> hand3 = new ArrayList<Card>();
		hand1.add(card1); // hand1: 5, card7 = 4
		hand1.add(card3);
		hand2.add(card4); // hand2: 8, card5 = 6
		hand2.add(card5);
		hand3.add(card1); // hand3: 3, card 5 = 6
		hand3.add(card6);

		assertEquals(true,gameLogic.evaluateBankerDraw(hand1,card7),"should draw");
		assertEquals(false,gameLogic.evaluateBankerDraw(hand2,card5),"should not draw");
		assertEquals(true,gameLogic.evaluateBankerDraw(hand3,card5),"should draw");
	}

	@Test // BaccaratGameLogic -> evaluatePlayerDraw()
	void testEvaluatePlayerDraw1(){
		Card card1 = new Card("Heart",4);
		Card card3 = new Card("Clubs",1);
		Card card4 = new Card("Clubs",9);
		Card card5 = new Card("Heart",8);
		ArrayList<Card> hand1 = new ArrayList<Card>();
		ArrayList<Card> hand2 = new ArrayList<Card>();
		hand1.add(card1);
		hand1.add(card3);
		hand2.add(card4);
		hand2.add(card5);

		assertEquals(true,gameLogic.evaluatePlayerDraw(hand1),"should draw another card");
		assertEquals(false,gameLogic.evaluatePlayerDraw(hand2),"should not draw another card");
	}

	@Test // BaccaratGameLogic -> evaluatePlayerDraw()
	void testEvaluatePlayerDraw2(){
		Card card1 = new Card("Spade",8);
		Card card3 = new Card("Spade",6); // 
		Card card4 = new Card("Spade",3);
		Card card5 = new Card("Diamond",1);
		ArrayList<Card> hand1 = new ArrayList<Card>();
		ArrayList<Card> hand2 = new ArrayList<Card>();
		hand1.add(card1);
		hand1.add(card3);
		hand2.add(card4);
		hand2.add(card5);

		assertEquals(true,gameLogic.evaluatePlayerDraw(hand1),"should draw another card");
		assertEquals(true,gameLogic.evaluatePlayerDraw(hand2),"should draw another card");
	}


	@Test // BaccaratGame constructor
	void testBaccaratGameConstructor(){
		BaccaratGame game = new BaccaratGame();
		assertNotNull(game.playerHand);
		assertNotNull(game.bankerHand);
		assertNotNull(game.theDealer);
		assertNotNull(game.gameLogic);
	}
}
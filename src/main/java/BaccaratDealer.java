import java.util.ArrayList;
import java.util.Collections;

public class BaccaratDealer {
    private ArrayList<Card> deck;

    public BaccaratDealer(){
        deck = new ArrayList<Card>();
    }

    public void generateDeck(){
        for (int i = 1; i <= 13; i++){
            Card card = new Card("Spade", i);
            deck.add(card);
        }
        for (int i = 1; i <= 13; i++){
            Card card = new Card("Clubs", i);
            deck.add(card);
        }
        for (int i = 1; i <= 13; i++){
            Card card = new Card("Diamond", i);
            deck.add(card);
        }
        for (int i = 1; i <= 13; i++){
            Card card = new Card("Heart", i);
            deck.add(card);
        }
    }
    //-----------------------------------------------------
    public ArrayList<Card>dealHand(){
        ArrayList<Card> hand = new ArrayList<Card>();
        Card firstCard = deck.get(0);
        Card secondCard = deck.get(1);
        hand.add(firstCard);
        hand.add(secondCard);
        deck.remove(0);
        deck.remove(0);
        return hand;
    }
    //-----------------------------------------------------
    public ArrayList<Card> drawOne(){
        ArrayList<Card> hand = new ArrayList<Card>();
        Card card = deck.get(0);
        hand.add(card);
        deck.remove(0);
        return hand;
    }
    //-----------------------------------------------------
    public void shuffleDeck(){
        Collections.shuffle(deck);
    }
    //-----------------------------------------------------
    public int deckSize(){
        return deck.size();
    }
}

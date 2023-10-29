import java.lang.reflect.Array;
import java.util.ArrayList;

public class BaccaratGameLogic {
    // This class is used to decided the logic behind the game and check for moves that might need to be
    // done or to evaluate the conclusion/winner of the game;
    public String whoWon(ArrayList<Card> hand1, ArrayList<Card> hand2){
        int dealer = handTotal(hand1);
        int player = handTotal(hand2);

        if ((player == 9 || player == 8) && (player != dealer)){
            return "Player";
        }else if ((dealer == 9 || dealer == 8) && (player != dealer)){
            return "Dealer";
        }else if (player == dealer){
            return "Tie";
        }else if (dealer > player){
            return "Dealer";
        }else{
            return "Player";
        }
    }
    //-----------------------------------------
    public int handTotal(ArrayList<Card> hand){
        int total = 0;

        for (Card c: hand){
            int value = c.getValue();
            if (value <= 9 && value >= 1){
                total += value;
            }else if (value >= 10 && value <=13){
                total += 0;
            }
        }
        if (total > 9){
            total = total - 10;
        }
        return total;
    }
    //-----------------------------------------
    public boolean evaluateBankerDraw(ArrayList<Card> hand, Card playerCard){

    }
    //-----------------------------------------
    public boolean evaluatePlayerDraw(ArrayList<Card> hand){

    }
}

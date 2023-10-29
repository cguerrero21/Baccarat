import java.lang.reflect.Array;
import java.util.ArrayList;

public class BaccaratGameLogic {
    public String whoWon(ArrayList<Card> hand1, ArrayList<Card> hand2){

    }
    //-----------------------------------------
    public int handTotal(ArrayList<Card> hand){
        int total = 0;

        for (int i = 0; i <= hand.size(); i++){
            if (hand.get(i).getValue() >= 10){

            }
        }


        if (hand.size() == 3){
            for (int i = 0; i <= (hand.size()-1); i++){
                total = total + hand.get(i).getValue();
            }
            if (total > 10){
                total = total - 10;
            }
            total = total + hand.get(2).getValue();
        }else{

        }
    }
    //-----------------------------------------
    public boolean evaluateBankerDraw(ArrayList<Card> hand, Card playerCard){

    }
    //-----------------------------------------
    public boolean evaluatePlayerDraw(ArrayList<Card> hand){

    }
}

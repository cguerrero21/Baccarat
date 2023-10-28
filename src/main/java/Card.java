
public class Card {
    private String suite; //= {"Spade", "Clove", "Diamond", "Heart"};
    private int value; // = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};

    public Card(String theSuite, int theValue){
        if (theSuite.equals("spade") || theSuite.equals("clove") || theSuite.equals("diamond") || theSuite.equals("heart")){
            this.suite = theSuite;
        }else{
            System.out.println("Not a valid suite!\n");
        }
        if (theValue >= 10){
            this.value = 0;
        }else{
            this.value = theValue;
        }
    }
}

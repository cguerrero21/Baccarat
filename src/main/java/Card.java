
public class Card {
    private final String suite; //= {"Spade", "Clove", "Diamond", "Heart"};
    private final int value; // = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};

    public Card(String theSuite, int theValue){
        suite = theSuite;
        value = theValue;
    }

    public int getValue(){
        return value;
    }

    public String getSuite(){
        return suite;
    }
}

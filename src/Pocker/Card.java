package Pocker;

/**
 * @author Hansi Karunarathna
 */
public class Card {
    private int value;
    private Suit suit;

    public Card(int value, Suit suit) { //parameterized constructor
        this.value = value;
        this.suit = suit;
    }

    public int getValue() { //return the value of the card
        return value;
    }

    public Suit getSuit() { //retun the suit of the card
        return suit;
    }
  
    @Override //return the face value and the suit of the card
    public String toString() {
        String suitString = suit.toString(); //get the suit of the card
        //Get the symbol of the Suit
        if (suitString=="DIAMONDS")
            suitString="♦";
        else if (suitString=="CLUBS")
            suitString="♣";
        else if (suitString=="HEARTS")
            suitString="♥";
        else if (suitString=="SPADES")
            suitString="♠";
        
        //get the face value of the card
        String face = null;
        if (value == 14) { 
            face = "A";
        } else if (value <= 10) { //if the value of the crad is >=2 and <=10 then that is equal to the face value
            face = String.valueOf(value);
        } else if (value == 11) {
            face = "J";
        } else if (value == 12) {
            face = "Q";
        } else if (value == 13) {
            face = "K";
        }
        return face + " " + suitString;
    }


}

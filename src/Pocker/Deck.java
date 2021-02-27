package Pocker;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 *@author Hansi Karunarathna
 */
public class Deck {
    public static final int SIZE = 52; //there 52 cards in one pack

    private Card[] cards;
    int index;

    public Deck() {
        cards = new Card[52]; //Store the entire card pack
        initCards(); //create the card pack
        index = 0;
    }
    
    //create the card pack
    private void initCards() {
        for (int i = 0; i < SIZE; i++) {
            cards[i] = new Card(i / 4 + 2, Suit.values()[i % 4]); //create the cards and store them to the array
        }
    }

    // Takes back all the cards that were dealt and shuffles the deck
    public void shuffle() {
        index = 0;
        shuffleHelper(51);
    }
   
    //shuffle the cards by swapping cards randomly
    private void shuffleHelper(int i) {
        if (i != 0) {
            shuffleHelper(i - 1);
            swap(i, ThreadLocalRandom.current().nextInt(0, i + 1));
        }
    }
    //used to swap cards when shuffling
    private void swap(int i1, int i2) {
        Card temp = cards[i1];
        cards[i1] = cards[i2];
        cards[i2] = temp;
    }
    
    //function to distribute cards to players: Every player is dealt two cards at the begining
    public List<Card> deal(int num) {
        List<Card> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            list.add(cards[index]);
            index++;
        }
        return list;
    }
}

package Pocker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 *  @author Hansi Karunarathna
 */
public class Game {
    public static final int MAX_PLAYERS = 3;
    private List<Player> players;
    private Deck deck;
    private static List<Card> tableCards;

    public Game() {
        players = new ArrayList<>(); //create an arraylist to store players
        initPlayers(); //initialize the players
        deck = new Deck();  //create the deck
    }
    
    //Function which handle all the activities in the Stage
    public int[] handleRound() {
        deck.shuffle(); //shuffle the card pack
        dealHands(); //give two card to each player at the begining
        
       
        //User’s cards are displayed on the screen
        System.out.println("User's Cards : " + players.get(0).getHand()); //User's player id is 0
         
        //Begin the round
         //runRound function will return true only if the user decides to discard the hand before finising the entire round
        if (runRound()==true) 
            players.remove(0); //If the user want to discard the hand before finising the entire round,then the user is removed from playerlist for that round
        
        //The remaining players show their hands in the show Down and their pockerhand is printed to screen
        showDown(players);
        int[] pointRound= points(players); //get the point of each player at end of the current round and stores to an array
        return pointRound; //return that array
    }
    
    //Get the  point of each player at end of the current round 
    private int[] points( List<Player> players){
      int [] points ={0,0,0}; //Intially points are 0
      RankingEnum pokerHand; //Points are calculated according to the ranking of poker hand
      int pid;  
      //User's pid is 0
      for(int i=0;i<players.size();i++){
          pid=i;
          //If the user is not in the current round (If user has discarded his hand at one of the stages),then there are only two players
          if(players.size()==2){
              pokerHand=players.get(pid).getRankingEnum(); //get the poker hand of the player
              pid=i+1; //increment the pid to correcty store the points of remaining players
          }
          else
            pokerHand=players.get(pid).getRankingEnum();
          
          //. Players will receive points accordingto the ranking of the hand.
          //Poker hands are assigned different points
           switch(pokerHand){
               case ROYAL_FLUSH : points[pid]+=30;break;
               case STRAIGHT_FLUSH :points[pid]+=20;break;
               case FOUR_OF_A_KIND :points[pid]+=14;break;
               case FULL_HOUSE:points[pid]+=12;break;
               case FLUSH:points[pid]+=10;break;
               case STRAIGHT:points[pid]+=8;break;
               case THREE_OF_A_KIND:points[pid]+=6;break;
               case TWO_PAIR:points[pid]+=4;break;
               case ONE_PAIR:points[pid]+=2;break;
               case HIGH_CARD:points[pid]+=1;break;
           }
      }
      return points; //return the points of each player at end of the round
    }
    
    
    //Function to reveal the hands of each player in the show Down and to print their pockerhand 
    private static void showDown(List<Player> players){
      
     //All the remaining players show their hands at the showdown 
      System.out.println("\n**********************THE SHOWDOWN**********************");
      for(int a=0;a<players.size();a++){
         System.out.print("\n"+players.get(a).toString()+ " : ");
         System.out.println(players.get(a).getHand());
      
      }
      //Poker hand of each player is printed
      System.out.println("\n**********************POKER HAND OF EACH PLAYER*************************");
      for(int a=0;a<players.size();a++){
         System.out.print ("\n"+players.get(a).toString()+" : ");
         System.out.println(players.get(a).getRankingEnum().name());
        }    ///2 3 4 5 6 7
    }
   
    //Begin the round 
    private Boolean runRound() {
        Boolean fold=false; //fold-user can discard his hand at any stage 
        tableCards = new ArrayList<>(); //Arraylist to store the community cards
        Scanner sc= new Scanner(System.in); 
        char input='n';
        //There are 3 stages in one round (FLOP,TURN ,RIVER)
        for (Stage next : Stage.values()) {
           
            //Stage 01- FOLD
            if (next.equals(Stage.FLOP)) {
                tableCards.addAll(deck.deal(3)); //The first 3 community cards are placed on the table
                System.out.println("\n\n**********************FIRST ROUND : THE FLOP**********************");
                for (int c=0;c<3;c++) //print the first 3 community cards 
                   System.out.println("Card "+0+(c+1)+" : "+tableCards.get(c).toString()+" ");
                do{
               //The user is allowed to select whether he proceed with the received cards or not in each stage 
                System.out.print("\nDo you want to discard the hand (y/n) ? ");
                input=sc.next().charAt(0);
                if (input=='y') //If the user want to discard his hand then fold is change to true
                   fold=true;
                }
                while(input!='y' && input!='n');
            } 
            
            //Stage 02 - TURN
            else if (next.equals(Stage.TURN)) {
                tableCards.addAll(deck.deal(1)); //The 4th community cards is placed on the table
                System.out.println("\n\n**********************SECOND ROUND : THE TURN**********************");
                System.out.println("Card "+0+(3+1)+" : "+tableCards.get(3).toString()); //print the 4th community card
                
                do{
                if(fold==false){ //The user is allowed to select whether he proceed with the received cards or not in each stage 
                System.out.print("\nDo you want to discard the hand (y/n) ? ");
                input=sc.next().charAt(0);
                if (input=='y' )
                   fold=true; //If the user want to discard his hand then fold is change to true
                }
                }while(input!='y' && input!='n');
            }
            
            //Stage 03 - RIVER
            else if (next.equals(Stage.RIVER)) {
                tableCards.addAll(deck.deal(1));  //The 5th community cards is placed on the table
                System.out.println("\n\n**********************THIRD ROUND : THE RIVER**********************");
                System.out.println("Card "+0+(4+1)+" : "+tableCards.get(4).toString()+"\n");  //print the 5th community card
                do{
                if(fold==false){
                        System.out.print("Do you want to discard the hand (y/n) ? "); 
                         input=sc.next().charAt(0);
                        if (input=='y')
                          fold=true; //If the user want to discard his hand then fold is change to true
                }
                }while(input!='y' && input!='n');
            }
        }
       
        //After completing the 3 stages ,Determine the poker hand of each player
       pokerHand(players.get(0));
       pokerHand(players.get(1));
       pokerHand(players.get(2));
    
        return fold; //return the users decision regarding his current hand ;false-user keep the hand ;true-discard the hand
    }

    private static List<Card>  mergeCards(Player pl){
       
       /*merge hole cards (The 2 cards which were given to  player at the begining) 
      with community cards (5 cards on the table) and store them to an arraylist*/
       List<Card> allCards = new ArrayList<Card>();
      allCards.addAll(tableCards);
       allCards.add(pl.getHand().get(0));
      allCards.add(pl.getHand().get(1));   
   
       return allCards;
    }
    //Function to determine the poker hand of each player
    private void pokerHand(Player pl) {
    
        //Get the hight card(highest face value) in the hand
        Card highCard=getHighCard( pl);
          
         //Check whther the player has a ROYAL_FLUSH poker hand
         List<Card> rankingList = getRoyalFlush(pl);
         if (rankingList!=null){ //if the player has   a ROYAL_FLUSH poker hand then set the rank as ROYAL_FLUSH and set the poker hand
           setRankingEnumAndList(pl, RankingEnum.ROYAL_FLUSH, rankingList); 
	   return;
         }
         
         //Check whther the player has a STRAIGHT_FLUSH poker hand
	rankingList = getStraightFlush(pl);
	if (rankingList != null) { //if the player has a STRAIGHT_FLUSH poker hand then set the rank as STRAIGHT_FLUSH and set the poker hand
            setRankingEnumAndList(pl, RankingEnum.STRAIGHT_FLUSH, rankingList);
            return;
	}
	//Check whther the player has a FOUR_OF_A_KIND poker hand
	rankingList = getFourOfAKind(pl);
	if (rankingList != null) {//if the player has a FOUR_OF_A_KIND poker hand then set the rank as FOUR_OF_A_KIND and set the poker hand
            setRankingEnumAndList(pl, RankingEnum.FOUR_OF_A_KIND, rankingList);
	    return;	
	}
	
        //Check whther the player has a FULL_HOUSE poker hand
	rankingList = getFullHouse(pl);
         if (rankingList != null) {//if the player has a FULL_HOUSE poker hand then set the rank as FULL_HOUSE and set the poker hand
            setRankingEnumAndList(pl, RankingEnum.FULL_HOUSE, rankingList);
	  return;
	}
        
       // Check whther the player has a FLUSH poker hand
	rankingList = getFlush(pl);
	if (rankingList != null) {//if the player has a FLUSH poker hand then set the rank as FLUSH and set the poker hand
            setRankingEnumAndList(pl, RankingEnum.FLUSH, rankingList);
	   return;	
	}
	
        //Check whther the player has a STRAIGHT poker hand
	rankingList = getStraight(pl);
	if (rankingList != null) { //if the player has a STRAIGHT poker hand then set the rank as STRAIGHT and set the poker hand
            setRankingEnumAndList(pl, RankingEnum.STRAIGHT,rankingList);
	    return;	
	}
		
        //Check whther the player has a THREE_OF_A_KIND poker hand
	rankingList = getThreeOfAKind(pl);
	if (rankingList != null) { //if the player has a THREE_OF_A_KIND poker hand then set the rank as THREE_OF_A_KIND and set the poker hand
            setRankingEnumAndList(pl, RankingEnum.THREE_OF_A_KIND, rankingList);
	    return;		
	}
		
        //Check whther the player has a TWO_PAIR poker hand
	rankingList = getTwoPair(pl);
	if (rankingList != null) {//if the player has a TWO_PAIR poker hand then set the rank as TWO_PAIR and set the poker hand
            setRankingEnumAndList(pl, RankingEnum.TWO_PAIR, rankingList);
	    return;
	}
		
        //Check whther the player has a ONE_PAIR poker hand
	rankingList = getOnePair(pl);
	if (rankingList != null) { //if the player has a ONE_PAIR poker hand then set the rank as ONE_PAIR and set the poker hand
            setRankingEnumAndList(pl, RankingEnum.ONE_PAIR, rankingList);
	     return;	
	}
        
        //If the player doesn't have any of the above poker hands then player has a HIGH_CARD poker hand
		pl.setRankingEnum(RankingEnum.HIGH_CARD); //set the rank as HIGH_CARD
		List<Card> highCardRankingList = new ArrayList<Card>();
		highCardRankingList.add(highCard);
		pl.setRankingList(highCardRankingList); //set the poker hand
		return;   
    }
    
  
        //Set the  rank of the player's hand and set the poker hand of the player
    private static void setRankingEnumAndList(Player player,RankingEnum rankingEnum, List<Card> rankingList) {
		player.setRankingEnum(rankingEnum);//set the rank of the hand
		player.setRankingList(rankingList); //set the poker hand
	}
    //Two cards of the same rank and two more cards of the same rank    
    private static List<Card> getTwoPair(Player pl) {
                List<Card> allCards = mergeCards(pl);
                List<Card> twoPair1 = checkPair(allCards, 2);  //check whether there are 2 cards which have the same face value
		if (twoPair1 != null) {
			allCards.removeAll(twoPair1); //remove that 2 cards from the card list
			List<Card> twoPair2 = checkPair(allCards, 2); //check for another pair that has  the same face value
			if (twoPair2 != null) {
				twoPair1.addAll(twoPair2);  //add that pait to first pair of cards to create 2 pairs
				return twoPair1;
			}
		}
		return null;
	}

    public static List<Card> getOnePair(Player pl) {
             List<Card> allCards = mergeCards(pl);  
            return checkPair(allCards, 2); //check whether there are 2 cards which have the same face value
	}
        
     // Three cards of the same rank   
    public static List<Card> getThreeOfAKind(Player pl) {
        List<Card> allCards = mergeCards(pl);
        return checkPair(allCards, 3); //check whether there are 3 cards which have the same face value
     }
    
    //Any five cards consecutively ranked
    private static List<Card> getStraight(Player pl) {
           List<Card> allCards = mergeCards(pl);
           return getSequence(allCards, 5, false); //to get 5 consecatively ranked cards 
    }
       
    // Any five cards of the same suit
    	private static List<Card> getFlush(Player pl) {
             List<Card> allCards = mergeCards(pl);
            List<Card> flushList = new ArrayList<Card>(); //store cards of same suit

		for (Card card1 : allCards) {
			for (Card card2 : allCards) {
				if (card1.getSuit().equals(card2.getSuit())) { //if the suits are similiar then add to the list
					if (!flushList.contains(card1)) {
						flushList.add(card1);
					}
					if (!flushList.contains(card2)) {
						flushList.add(card2);
					}
				}
			}
			if (flushList.size() == 5) { //if there are 5 cards of same suit,then return the list
				return flushList;
			}
			flushList.clear(); //else remove all the cards from the list
		}
		return null;
	}
    
     // Three cards of the same rank and two more cards of the same rank
    private static List<Card> getFullHouse(Player pl) {
       List<Card> allCards = mergeCards(pl);
        List<Card> threeList = checkPair(allCards, 3); // return a list of 3 cards which has the same face value or return null
		
                if (threeList != null) { //if there are 3 cards which have same face value
             
			allCards.removeAll(threeList); //remove that three cards from the list
			List<Card> twoList = checkPair(allCards, 2); // return a list of 2 cards which has the same face value or return null
			if (twoList != null) { //if there are 2 cards which have same face value
				threeList.addAll(twoList);//add that 2 cards to the list which contains earlier 3 cards
				return threeList;
			}
		}
		return null;
}
    //Five cards of the same suit and consecutively ranked
    private static List<Card> getStraightFlush(Player pl) {
        
        List<Card> allCards = mergeCards(pl);
	return getSequence(allCards, 5, true); //to get 5 consecatively ranked cards of same suit 
    }
    
    //Return consecutively ranked 'sequenceSize' of cards
    //if Boolean compareSuit ==true ,then five cards must be from the same suit
    private static List<Card> getSequence(List<Card> allCards, Integer sequenceSize, Boolean compareSuit) {
               
                List<Card> orderedList = getOrderedCardList(allCards); //Get the ordered card list  
		List<Card> sequenceList = new ArrayList<Card>();//to store the consecutively ranked cards 
                Card cardPrevious = null;
		for (Card card : orderedList) { 
			if (cardPrevious != null) {
				if ((card.getValue() - cardPrevious.getValue()) == 1 && sequenceList.size() < sequenceSize) { //check for consecatively ranked card
					if (!compareSuit|| cardPrevious.getSuit().equals(card.getSuit())) {
						if (sequenceList.isEmpty()) {
							sequenceList.add(cardPrevious); //add the first card of consecutively ranked  cards
						}
						sequenceList.add(card);//add other cards
					}
				} 
                                else if ((card.getValue() - cardPrevious.getValue()) == 0)
                                    continue;
                                else {
					if (sequenceList.size() == sequenceSize) {
						return sequenceList;
					}
					sequenceList.clear(); //remove all the cards for the ist if the cards are not consecatively ordered
				}
			}
			cardPrevious = card; //2 3 5 6 7 8 9
		}

		return (sequenceList.size() == sequenceSize) ? sequenceList : null;
    }
    
    //Sort all the 7 cards according to thier face value and return that card list 
    private static List<Card> getOrderedCardList(List<Card> allCards) {
         
       //Sort the Card ArrayList according to thier face value
       Collections.sort(allCards, new Comparator<Card>() {
                @Override
		public int compare(Card c1, Card c2) {
				return c1.getValue() < c2.getValue() ? -1 : 1;
		}
	});
       return allCards; //return the sorted card list
   }
    
    //Four cards of the same rank
    private static List<Card> getFourOfAKind(Player pl) {
       List<Card> allCards = mergeCards(pl);
       return checkPair(allCards, 4); //check whether there are 4 cards which have the same face value
    }
    
    //return  the cards which have same face value according to the pairsize(no of cards that need to  have the same face value)
    private static List<Card> checkPair(List<Card> allCards, Integer pairSize) {
		List<Card> checkedPair = new ArrayList<Card>(); //to store cards with same face value
		for (Card card1 : allCards) {
			checkedPair.add(card1);
			for (Card card2 : allCards) {
				if ( !card1.equals(card2) && card1.getValue()==card2.getValue()) {
					checkedPair.add(card2);
				}
			}
			if (checkedPair.size() == pairSize) {
				return checkedPair;
			}
			checkedPair.clear(); //clear the arraylist if the pair size is not matched
		}
		return null;
     }
    
    //Five cards of the same suit, ranked ace through ten
    private static List<Card> getRoyalFlush(Player pl) {
      List<Card> allCards = mergeCards(pl);
      List<Card> royalFlushcards = new ArrayList<Card>(); //to store the cards ,ranked ace through 10

       int royalCount=0;
       for (int j=0;j<allCards.size();j++){
           Card card=allCards.get(j);
          if (card.getValue()==10 || card.getValue()==11|| card.getValue()==12|| card.getValue()==13||card.getValue()==14){
               int val=card.getValue(); //save the value of the card
               royalCount++; //increse the count
               royalFlushcards.add(card); //add the card to the arraylist
               
               //remove all the  cards which has the same value
               //Otherwise we can't say there are exactly 5 cards ranked ace through ten
               for (int i=0;i<allCards.size();i++){
                   if(allCards.get(i).getValue()== val && !allCards.isEmpty())
                       allCards.remove(i);    
               }
            }
       }
      
       //There must be 5 cards ranked ace through ten
       if (royalCount==5){
           if (isSameSuit(royalFlushcards)==true) //All these cards must be from the same suit
                return royalFlushcards; //return the poker hand
           else
               return null;
       }
       
       else
           return null;
    }
  
    //Used to check whether all the cards are from the same suit
    private static  Boolean isSameSuit(List<Card> Cards) {
           
        Suit suit = Cards.get(0).getSuit(); //get the suit of first card from card Array
        for (Card card : Cards) {  
	   if (!(card.getSuit().equals(suit))) { 
		return false;
	   }  
        }
        return true; //return true only if all the cards are from  the same suit
        
    }
          
    
    //return the card which has the highest value in the hand
    private static Card getHighCard(Player pl){
       List<Card> allCards = mergeCards(pl);
       //get the card which has the highest face value
       Card highCard = allCards.get(0);
       for (Card card : allCards) {
	    if (card.getValue() > highCard.getValue()) {
		highCard = card;
	    }
	}
      return highCard; //return the card which has the highest face value
    }
    
    //give 2 cards to each player
    private void dealHands() {
        for (Player next : players) {
            next.setHand(deck.deal(2)); 
        }
    }
    
    //There are only 3 players in the game,add three players to player list
    private void initPlayers() {
            players.add(new Player("User"));
            players.add(new Player("Player01"));
            players.add(new Player("Player02"));
            
    }


}

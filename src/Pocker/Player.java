package Pocker;


import java.util.ArrayList;
import java.util.List;

/**
 *@author Hansi Karunarathna
 */
public class Player {

    private String name;
    private List<Card> hand;
    
    private RankingEnum rankingEnum = null;
    private List<Card> rankingList = null;
    
    public Player(){} //Default constructor
   
    public Player(String name) { //parameterized constructor
        this.name = name;
        hand = new ArrayList<>();
    }
    
    public RankingEnum getRankingEnum() {
		return rankingEnum;
    }

	public void setRankingEnum(RankingEnum rankingEnum) {
		this.rankingEnum = rankingEnum;
	}

	public List<Card> getRankingList() {
		return rankingList;
	}

	public void setRankingList(List<Card> rankingList) {
		this.rankingList = rankingList;
	}

    public void setHand(List<Card> hand) {
        this.hand = hand;
    }
    
    public  List<Card> getHand(){
        return hand;
    }
    


    @Override
    public String toString() { //return name of the player
        return name;
    }

    
}

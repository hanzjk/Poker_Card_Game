/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pocker;

/**
 *
 * @author Hansi Karunarathna
 */

//Poker hands from lowest to highest
//different point are allocated for each hand
public enum RankingEnum {
	HIGH_CARD,  //1
	ONE_PAIR,   //2
	TWO_PAIR,//4
	THREE_OF_A_KIND, //6
	STRAIGHT,//8
	FLUSH,//10
	FULL_HOUSE,//12
	FOUR_OF_A_KIND,//14
	STRAIGHT_FLUSH, //20
	ROYAL_FLUSH //30
}


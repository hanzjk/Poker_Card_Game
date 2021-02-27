/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pocker;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Hansi Karunarathna
 */
public class  Welcome {
    //print ASCII images to screen
    public  void  screen() throws IOException{
         Scanner scanner = new Scanner(getClass().getResourceAsStream("WELCOME.txt"));   
        while( scanner.hasNextLine())
            System.out.println(scanner.nextLine());    
       
    }
    
    public int userSelection(){
        int input;
        do{
        Scanner userIn= new Scanner(System.in); 
        System.out.println("\n\t\t\t\t\t\t(1) Play\n\t\t\t\t\t\t(2) Help");
        System.out.println("\n\t\t\tPress (1) To start the game OR Press (2) To view help");
        System.out.print("\n\t\t\tEnter your selection : ");
         input=userIn.nextInt();
        }while(input!=1 && input!=2);
        return input;
    }
    
    
    public  void help(){
        System.out.println("\n♠   ♦   ♥   ♣   ♠   ♦   ♥   ♣  HOW TO PLAY  ♠   ♦   ♥   ♣   ♠   ♦   ♥   ♣   \n");
        System.out.println("\nEvery player is dealt two cards, for their eyes only.\n"              
                            + "The game has ten rounds. Each round contains three stages. FLOP, TURN and RIVER.\n"
                            + "In these 3 stages,Dealer spreads five cards - three at once, then another, then another - which can be used by all players to make their best possible five-card hand\n"
                            + "In each round When it's your turn,you can make a choice to either dicard your hand or keep it.\n"
                            + "You will get 0 points if you discard the hand.\n"
                            + "All players hands are shown at the show down.\n"
                            + "At the end of each round the winner will receive points according to the ranking of the hand.\n"
                            + "The winner of the game will be the player with the highest score at the end of all 10 rounds.\n\n"
                            + "Here are the \"Ranks of Hands\": \n"
                            + "High Card        -   Five unmatched cards,ranked first by the rank of its highest-ranking card.\n"
                            + "One Pair         -   Two cards of the same rank.\n"
                            + "Two Pair         -   Two cards of the same rank and two more cards of the same rank.\n"
                            + "Three Of A Kind  -   Three cards of the same rank.\n"
                            + "Straight         -   Any five cards consecutively ranked.\n"
                            + "Flush            -   Any Five cards of the same suit.\n"
                            + "Full House       -   Three cards of the same rank and two more cards of the same rank;.\n"
                            + "Four Of A Kind   -   Four cards of the same rank.\n"
                            + "Straight Flush   -   Five cards of the same suit and consecutively ranked.\n"
                            + "Royal Flush      -   Five cards of the same suit, ranked ace through ten\n\n"
                            + "Here's how the points are allocated to each rank:\n"
                            + "High Card        -   1    point\n"
                            + "One Pair         -   2    points\n"
                            + "Two Pair         -   4    points\n"
                            + "Three Of A Kind  -   6    points\n"
                            + "Straight         -   8    points\n"
                            + "Flush            -  10    points\n"
                            + "Full House       -  12    points\n"
                            + "Four Of A Kind   -  14    points\n"
                            + "Straight Flush   -  20    points\n"
                            + "Royal Flush      -  30    points\n\n"
                            + "~~~~~~~~~~        GOOD LUCK!        ~~~~~~~~~~\n");
    }
    
}

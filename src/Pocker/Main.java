package Pocker;

import java.io.IOException;
import static java.lang.System.exit;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *@author Hansi Karunarathna
 */
public class Main {

    public static void main(String[] args) {
        ///welcome screen/////////////////
       Welcome wel =new Welcome();
        try {
            wel.screen(); 
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         int userInput=wel.userSelection();
           switch(userInput){ //At the welcome screen user can view HELP or PLAY the game
                case 1:System.out.printf("\nWelcome to Texas Hold'em Poker.\n");break;
                case 2:{wel.help();pressAnyKeyToContinue();}break;
                default:System.out.println("\t\t\tInvalid Selection.") ;exit(0);
        }
    
       int[] points={0,0,0}; //arry which stores the points throughout the game 

        for(int i=0;i<10;i++){ //there 10 rounds in the game
            int[] roundPoints={0,0,0}; //array to store points in each round
            System.out.printf("\n****************************************WELCOME TO ROUND "+ (i+1)+"***************************************************\n");
            pressAnyKeyToContinue();
           
            Game g = new Game(); //Create a new game for each round
            roundPoints= g.handleRound(); //Play the current round and get the points of that round
           
            //print the points of the current round
            System.out.println("\n************************POINTS FOR ROUND " + (i+1)+"******************************");
            for (int j=0;j<3;j++)
                if (j==0)
                     System.out.println("User : " +roundPoints[j]);
                 else
                    System.out.println("Player"+j+" : " +roundPoints[j]);
            
            //update the total points 
            addPoints(points, roundPoints);
             
             
        }
        //End of the 10 rounds
      //Select the winner and print final points of each player
       winner(points);
    }
   
    
    
  private static void pressAnyKeyToContinue()
 { 
        System.out.println("Press Enter key to continue...");
        try
        {
            System.in.read();
        }  
        catch(Exception e)
        {}  
 }
  
  //update the total points of the players in each round
  private static void addPoints(int[] finalpoints,int points[]){
   
     for(int i=0;i<3;i++)
        finalpoints[i]+=points[i]; //points of the current round are added to previous points 
  }
  
  //Select the winner and print final points of each player
  private static void winner(int[] finalpoints){
     //Player IDs are assigned for players
     //user-0
     //player01-1
     //player02 -2
     
     
    //print the final points at the end of the game  
   System.out.println("\n**********************POINTS AT THE END OF THE GAME***************************");
  
   for(int i=0;i<3;i++){
  if (i==0)
        System.out.println("User : " +finalpoints[i]);
   else
        System.out.println("Player"+0+i+" : " +finalpoints[i]);
   }
   
   //Winner is stored into an arraylist - Beacause there can be multiple winners if there's a tie
    List<String> winner=new ArrayList<>(); 
    if(finalpoints[0]==finalpoints[1] && finalpoints[0]==finalpoints[2]){ //if all three players have same amount of points
       winner.add("User"); //add all the players to winners list
       winner.add("Player1");
       winner.add("Player2");
    }
       
    else if (finalpoints[0]>=finalpoints[1]){
        
        if (finalpoints[0]==finalpoints[1] && finalpoints[0]>finalpoints[2]){ //If two players have same amount of points
           winner.add("User");
            winner.add("Player01"); //add all the players to winners list
        }
        
        else if (finalpoints[0]==finalpoints[1] && finalpoints[0]<finalpoints[2]){
            winner.add("Player02");
        }
        
        else if (finalpoints[0]>finalpoints[2])
            winner.add("User");
    
        else if (finalpoints[0]<finalpoints[2])
            winner.add("Player01");
    }
    
    else{ 
     
         if (finalpoints[1]==finalpoints[2]){
           winner.add("Player01");
           winner.add("Player02");
        }
        else if (finalpoints[1]>finalpoints[2])
            winner.add("Player01");
         
        else if (finalpoints[1]<finalpoints[2])
            winner.add("Player02"); 
      
   }
    
    //Finally print the winner of the game
    System.out.println("\n**********************WINNER OF THE GAME ********************************");
    for (int k=0;k<winner.size();k++)
        System.out.println("\t\t\t\t"+winner.get(k));
        
  }
}

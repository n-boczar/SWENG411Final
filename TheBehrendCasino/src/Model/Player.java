/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import View.CAssignFrameNew;
import Model.Card;

/**
 *
 * @author PKC5102
 */
public class Player {
    
    //boolean statement to know if a player lost all their money and is eliminate
    public static boolean active = true;
    
    //boolean statement to know if a player folded
    public static boolean fold = false;
    
<<<<<<< HEAD

    //Did the player already bet once?
    public static boolean didBet = false;

    public static int totalAmountOwed = 0; 

=======
    //Did the player already bet once?
    public static boolean didBet = false;
    public static int totalAmountOwed = 0; 
>>>>>>> 83110ee684472f0bef5f56097c02a25ac58e9889
    
    //player's bet 
    private static int bet;

    public static int currency;
    
    public static void setTotalAmountOwed(int c){
        totalAmountOwed = c; 
    }
    
    public static int getCurrency(){
        return currency;
    }
    
    public static void setCurrency(int c){
        currency = c;
    }
    
    //get the players Bet
    public static int getBet(){
        return bet;
    }
    
    //set the players Bet
    public static void setBet(int b){
        bet = b;
    }
}

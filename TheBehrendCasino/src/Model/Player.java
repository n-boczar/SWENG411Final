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
    
    //Hand if they play Texas Hold'em
    public static Card[] texasHand = new Card[2];
    
    //boolean statement to know if a player folded and is still in a round of poker
    public static boolean active = false;
    
    //player's bet 
    private static int bet;

    public static int currency;
    
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
    public static void setB(int b){
        bet = b;
    }
}

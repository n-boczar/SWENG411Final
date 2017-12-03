/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author fernandocarrillo
 */
public class AIPlayer{
    
    //Hand if they play Texas Hold'em
    public static Card[] texasHand = new Card[2];
    
    //Original hand combined with cards dealt on table
    public static Card[] evalHand = new Card[7];
    
    //boolean statement to know if a player folded and is still in a round of poker
    public boolean active = true;
    
    //player's bet 
    private int bet;

    public int currency;
    
    public int getCurrency(){
        return currency;
    }
    
    public void setCurrency(int c){
        currency = c;
    }
    
    //get the players Bet
    public int getBet(){
        return bet;
    }
    
    //set the players Bet
    public void setBet(int b){
        bet = b;
    }
    
}

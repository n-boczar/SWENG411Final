/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import Model.*;
/**
 *
 * @author Perry
 */
public abstract class GameEngine {
    private static int deckAmt = 52;
    private boolean placedBet;
    private int betAmount;
    
    
    
    // Methods without implementation
    public void deal(Deck deck) {};
    public void determineWinner(Deck deck) {};
    public void compareHands() {};
    public void shuffle() {};
    public void payBuyInAmount() {};
    public void placeBet(int bet) {};
    public void createDeck() {};
    public void terminateGameSession() {};

    public static int getDeckAmt() {
        
        // This is a test to see if I can see changes on Github
        
        return deckAmt;
    }
    
    
   
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Card;
import Model.PokerDeck;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

/**
 *
 * @author fernandocarrillo
 */

/*

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
        
        return deckAmt;
    }
*/
public class FiveCardPokerEngine extends GameEngine{
    
    int initialDeal; 
    double buyInAmount; 
    boolean playerTurn; 
    boolean playerWon; 
    
    
    public void showCardValues(){
        
    }
    
    public void deal(int initialDeal){
        this.initialDeal = initialDeal; 
    }
    
    public void discard(int cards){
        
    }
    
     public void payBuyInAmount(double buyInAmount) {
         this.buyInAmount = buyInAmount; 
     }
 
     
     
     // MY IMPLEMENTED FUNCTIONS
     
     
     
    public void compareHands(Vector<Card> playerHand, Vector<Card> AI_1Hand, Vector<Card> AI_2Hand, Vector<Card> AI_3Hand) {
    
        // Declare winning hand values
        int royalFlush = 10; 
        int straightFlush = 9; 
        int fourOfAKind = 8; 
        int fullHouse = 7; 
        int flush = 6; 
        int straight = 5; 
        int threeOfAKind = 4; 
        int twoPair = 3; 
        int pair = 2; 
        int highCard = 1; 
        
        // ANALYSE PLAYER HAND
        
        // 1) Check for Royal FLush
        int card1;
        int card2;
        int card3; 
        int card4; 
        int card5; 
            
        if(){
            
        }
        
        
        // 2) Check for Straight Flush
        // 3) Check for Four of a Kind
        // 4) Check for Full House
        // 5) Check for Flush
        // 6) Check for Straight
        // 7) Check for Three of a Kind
        // 8) Check for Two Pair
        // 9) Check for Pair
        // 10) Check for High Card
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        // ANALYSE AI_1 HAND
        
        
        // ANALYSE AI_1 HAND
        
        
        
        // ANALYSE AI_1 HAND
        
        
        
        
    }
    
    public void terminateGameSession() {
    
    
    }
     
     
     
     public static void main(String[] args) throws IOException{
         
         PokerDeck pokerDeck = new PokerDeck(); 
         Vector<Card> playerHand = new Vector<Card>(); 
         Vector<Card> AI_1Hand = new Vector<Card>();
         Vector<Card> AI_2Hand = new Vector<Card>(); 
         Vector<Card> AI_3Hand = new Vector<Card>(); 
         
         Scanner scnr = new Scanner(System.in);
         FiveCardPokerEngine FCPokerEngine = new FiveCardPokerEngine(); 
         boolean replaceCardChoice; 
         int gameChoice = 1; 
         int replaceCardsChoice; 
         int playerMoveChoice; 
         int roundNumber; 
         pokerDeck.shuffle(); 
         
         
         do{
            
             /*
                Start by giving the player his first 5 cards
             */
             // Give player first card
             playerHand.add(pokerDeck.deal()); 
             // Give player second card
             playerHand.add(pokerDeck.deal()); 
             // Give player third card 
             playerHand.add(pokerDeck.deal()); 
             // Give player fourth card
             playerHand.add(pokerDeck.deal()); 
             // Give player fifth card
             playerHand.add(pokerDeck.deal()); 
             
             roundNumber = 1; 
             System.out.println("Player current hand: ");
             for(int i=0; i < playerHand.size(); i++){
                 System.out.println(playerHand.elementAt(i) + ",");
             }
             
             
             /*
                PLAYERS CHOICES IN A STARTING ROUND, EITHER 1 or 2
                Can be CALL(Call) = where player needs to meet other player's bet to continue
                Can be FOLD = where the player terminates the game session
                Can be BET(Raise) = where the player sets a new bet for all other to call or raise 
             */
             
             // Player places the first bet (PLACEHOLDER)
             System.out.println("Player places bet amount");
             
             
             // Player is able to replace three cards 
             System.out.println("Do you wish to replace any of up to 3 cards in your hand? Yes 0r No (1 or 2)");
             // Get input (choice) from player
             replaceCardsChoice = scnr.nextInt();
             
             if(replaceCardsChoice == 1){
                 // Start replace card process for every player/AIs that would like to replace cards
             }
             
             if(replaceCardsChoice == 2){
                 
                 // User/AIs places bet, calls, check, or folds
                 
             }
             
             
             
             
             
             
             
             
             
             
             
             
             
             
             
             
             
             
         }while(gameChoice != 1); 
         
     }
     
     
    
}

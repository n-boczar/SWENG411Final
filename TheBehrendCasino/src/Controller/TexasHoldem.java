/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import Model.PokerDeck;
import Model.Card;
import Model.Player;
import java.awt.List;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Ian
 */
public class TexasHoldem extends GameEngine {
    public static void main(String[] args) {
        
        int totalPot;
        Card[] boardCards = new Card[5];
        Card[] burnCards = new Card[3];
        
        //Create the deck for the poker game
        PokerDeck deck = null;
        try {
            deck = new PokerDeck();
        } catch (IOException ex) {
            Logger.getLogger(PokerDeck.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Shuffle the deck
        deck.shuffle();
        
        //Initialize user and computer players
        Player user = new Player();
        Player computer1 = new Player();
        Player computer2 = new Player();
        Player computer3 = new Player();
        Player computer4 = new Player();
        Player computer5 = new Player();
        
        // create array of players to make bet checking easier
        Player[] playerArray = {user, computer1, computer2, computer3, computer4, computer5};
        // MAY NOT NEED create list of players to make bet checking easier
	//List<Player> playerList = new ArrayList<Player>();
 
		// add 4 different values to list
		//crunchifyList.add("eBay");
		//crunchifyList.add("Paypal");
		//crunchifyList.add("Google");
		//crunchifyList.add("Yahoo");
        
        /*
        for loop to check whic players bet. If the player bet, set active to 
        true and deal them cards.
        */
        
       //--------------------------------
       // Deal each player a hand
       //--------------------------------
        for(int i=0; i<6; i++){
            //check to see if a player has enough to play
            //if(playerArray[i].getCurrency() < 25)
                //playerArray[i].active = false;
            //if the player has enough money to play deal them cards
            //if(playerArray[i].active != false){
                //playerArray[i].setBet(25);
            for(int x = 0; x < 2; x++){
                playerArray[i].texasHand[x] = deck.deal();
            }
        }
         
       //--------------------------------
       // Deal the table cards 
       //--------------------------------
        //Burn one card
        burnCards[0] = deck.deal();
        //Get the flop
        for (int i = 0; i < 3; i++){
            boardCards[i] = deck.deal();
        }
        //Burn another card
        burnCards[1] = deck.deal();
        //Deal the turn 
        boardCards[3] = deck.deal();
        //Burn one last card
        burnCards[2] = deck.deal();
        //Deal the river card
        boardCards[4] = deck.deal();
        
        //-------------------------
        // Add the board cards to each players hand for evaluaiton
        //------------------------- 
        for(int i = 0; i < playerArray.length; i++){
            //for loop to go throug each boardCard
            for(int x = 0; i < boardCards.length; x++){
                System.arraycopy(playerArray[i].texasHand,0,playerArray[i].evalHand,0,playerArray[i].texasHand.length);
                System.arraycopy(boardCards,0,playerArray[i].evalHand, playerArray[i].texasHand.length + 1, boardCards.length);
            }
        }
        
        for(int i = 0; i < playerArray.length; i++){
            System.out.print("Player " + i + ": "  );
            for(int x = 0; x < 7; x++){
                
            }
        }
        
        
        
        //Card[] comp1Hand = new Card[2];
        //Card[] comp2Hand = new Card[2];
        //Card[] comp3Hand = new Card[2];
        //Card[] comp4Hand = new Card[2];
        //Card[] comp5Hand = new Card[2];
        /*
        for(int i = 0; i < 2; i++){
            playerHand[i] = deck.deal();
        }
        for(int i = 0; i < 2; i++){
            comp1Hand[i] = deck.deal();
        }
        for(int i = 0; i < 2; i++){
            comp2Hand[i] = deck.deal();
        }
        for(int i = 0; i < 2; i++){
            comp3Hand[i] = deck.deal();
        }
        for(int i = 0; i < 2; i++){
            comp4Hand[i] = deck.deal();
        }
        for(int i = 0; i < 2; i++){
            comp5Hand[i] = deck.deal();
        }
        */
        
        
        
    
        
        
        
        
    }
    
}

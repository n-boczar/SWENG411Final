/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
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
public class TexasHoldem {
    public static void main(String[] args) {
        
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
        for(int i=0; i<6; i++){
            if(playerArray[i].getBet() != 0){
                playerArray[i].active = true;
                for(int x = 0; x < 2; x++){
                    playerArray[i].texasHand[x] = deck.deal();
                }
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

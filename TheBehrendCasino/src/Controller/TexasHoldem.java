/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import Model.PokerDeck;
import Model.Card;
import java.awt.image.BufferedImage;
import java.io.IOException;
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
        
        //Initialize and Deal the hands
        Card[] playerHand = new Card[5];
        Card[] comp1Hand = new Card[5];
        Card[] comp2Hand = new Card[5];
        Card[] comp3Hand = new Card[5];
        Card[] comp4Hand = new Card[5];
        Card[] comp5Hand = new Card[5];
        
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
        
        
    
        
        
        
        
    }
    
}

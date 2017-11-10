/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import Model.Deck;
import Model.Card;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;
/**
 *
 * @author Perry
 */
public class BlackJackGameEngine extends GameEngine {
    private int initialDeal;
    private double buyInAmount;
    private boolean playerTurn;
    private boolean playerWon;
    

    // Setters and getters
    public int getInitialDeal() {
        return initialDeal;
    }

    public void setInitialDeal(int initialDeal) {
        this.initialDeal = initialDeal;
    }

    public double getBuyInAmount() {
        return buyInAmount;
    }

    public void setBuyInAmount(double buyInAmount) {
        this.buyInAmount = buyInAmount;
    }

    public boolean isPlayerTurn() {
        return playerTurn;
    }

    public void setPlayerTurn(boolean playerTurn) {
        this.playerTurn = playerTurn;
    }

    public boolean isPlayerWon() {
        return playerWon;
    }

    public void setPlayerWon(boolean playerWon) {
        this.playerWon = playerWon;
    }
    
    // Blackjack specific methods
   
    
    @Override
    public void determineWinner(Deck deck) {
    
    }
    
    public static void main(String[] args) throws IOException{
        
        Deck d = new Deck();
        Vector<Card> playerHand = new Vector<Card>();
        Vector<Card> dealerHand = new Vector<Card>();
        BlackJackGameEngine ge = new BlackJackGameEngine();
        Scanner scnr = new Scanner(System.in);
        int choice;
        int playerAmount = 0;
        int dealerAmount = 0;
        d.shuffle();
        
        
        // Add in dealt cards to hand vector
        playerHand.add(d.deal());
        playerHand.add(d.deal());
        
        
        dealerHand.add(d.deal());
        dealerHand.add(d.deal());
        
        
        System.out.print("Player Hand:  ");
        
        for (int i = 0; i < playerHand.size(); i++) {
           System.out.print(playerHand.elementAt(i) + " , ");
           playerAmount += playerHand.elementAt(i).getCardValue();  
        }
        System.out.println("\n" + "Player's hand value: " + playerAmount);
        
        
        System.out.print("\n" + "Dealer Hand:  ");
        for (int j = 0; j < dealerHand.size() - 1; j++) {
           System.out.print(dealerHand.elementAt(j) + " Flipped Card ");
           dealerAmount += dealerHand.elementAt(j).getCardValue();
        }
        
        // continued loop for player's hitting/staying
        while (playerAmount < 21  || playerHand.size() <=5 ) {
            // Each deck has first deal 
            System.out.println("\n" + "Would you like to Hit or Stay? (1) , (2) ");
            choice = scnr.nextInt();
 
    
            if (choice == 1) {
             //hit and add 1 more cardd to deck [3]
            playerHand.add(d.deal());
            
           
            // Display updated hand to user
            for (int i = 0; i < playerHand.size(); i++) {
               System.out.print(playerHand.elementAt(i) + " , ");
              playerAmount += playerHand.elementAt(i).getCardValue();
          }
            System.out.println("\n" + "Player's hand value: " + playerAmount);
           
            
          } else if (choice == 2){
            // stay
            ge.determineWinner(d);
            //output winner, assign currency and ask to play again  
            break;
        }
     }
     
        
 }
    

}

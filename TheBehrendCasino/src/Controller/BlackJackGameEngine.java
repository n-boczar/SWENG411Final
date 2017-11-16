/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
// hi
package Controller;

import Model.*;
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
    public void determineWinner(int playerAmount, int dealerAmount) {
        if (playerAmount > dealerAmount) {
            System.out.println("Player wins.");
            playerWon = true;
        } else {
            System.out.println("Dealer wins.");
            playerWon = false;
        }
    }

    public static void main(String[] args) throws IOException {

        BlackJackDeck d = new BlackJackDeck();
        Vector<Card> playerHand = new Vector<Card>();
        Vector<Card> dealerHand = new Vector<Card>();
        BlackJackGameEngine ge = new BlackJackGameEngine();
        Scanner scnr = new Scanner(System.in);
        int choice;
        int gameChoice = 1;
        int playerAmount = 0;
        int dealerAmount = 0;
        d.shuffle();

        do {
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
            System.out.println("\n" + "Dealer's hand value: " + dealerAmount);

            // continued loop for player's hitting/staying
            while (playerAmount < 21) {
                // Each deck has first deal 
                System.out.println("\n" + "Would you like to Hit or Stay? (1) , (2) ");
                choice = scnr.nextInt();

                if (choice == 1) {
                    //hit and add 1 more card to deck [3]
                    playerHand.add(d.deal());

                    System.out.println("Updated player hand: ");
                    // Display updated hand
                    for (int i = 0; i < playerHand.size(); i++) {
                        System.out.print(playerHand.elementAt(i) + " ");
                        playerAmount += playerHand.elementAt(i).getCardValue();
                    }
                    System.out.println("\n");

                    // print both of the dealers cards
                    System.out.println("Updated Dealer hand");
                    for (int j = 0; j < dealerHand.size(); j++) {
                        System.out.print(dealerHand.elementAt(j) + " , ");
                        dealerAmount += dealerHand.elementAt(j).getCardValue();
                    }
                    System.out.println("\n" + "Dealer's hand value: " + dealerAmount);
                    System.out.println("\n" + "Player's hand value: " + playerAmount);

                } else if (choice == 2) {
                    // stay
                    ge.determineWinner(playerAmount, dealerAmount);

                    //output winner, assign currency and ask to play again  
                    break;
                }

            } // end betting loop
            
            if (playerAmount > 21) {
                System.out.println("Hand value over 21. Dealer wins.");
            } else if (playerAmount < 21 && playerHand.size() <= 5) {
                // call compare hand function
                ge.determineWinner(playerAmount, dealerAmount);
            }

            System.out.println("Play again? Yes(1) No(2) ");
            gameChoice = scnr.nextInt();
            
            if (gameChoice == 0) {
                playerHand.clear();
                dealerHand.clear();
                playerAmount = 0;
                dealerAmount = 0;
            }
            
        } while (gameChoice != 0);
    }

}

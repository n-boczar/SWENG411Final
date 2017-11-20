/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
// hi
package Controller;

import Model.*;
import View.BlackJackFrame;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;
import javax.swing.JOptionPane;

/**
 *
 * @author Perry
 */
public class BlackJackGameEngine extends GameEngine {

    private int initialDeal;
    private int buyInAmount;
    private boolean playerTurn;
    private boolean playerWon;
    private boolean anteUp = false;
    BlackJackDeck d;
    
    public BlackJackGameEngine() throws IOException{
         d = new BlackJackDeck();
         d.shuffle();
    }
    
    public void didAnteUp(boolean choice){
        anteUp = true;
    }
    
    public Card[][] beginPlay() throws IOException{
        Card[][] hands = new Card[2][5];
        if (anteUp == true){
            
            //d.shuffle();
            
            Vector<Card> playerHand = new Vector<Card>();
            Vector<Card> dealerHand = new Vector<Card>();
            
            playerHand.add(d.deal());
            playerHand.add(d.deal());

            dealerHand.add(d.deal());
            dealerHand.add(d.deal());

            hands[0][0] = dealerHand.elementAt(0);
            hands[0][1] = dealerHand.elementAt(1);
            hands[1][0] = playerHand.elementAt(0);
            hands[1][1] = playerHand.elementAt(1);
            
        }
        return hands;
    }
    
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

    public void setBuyInAmount(int buyInAmount) {
        this.buyInAmount = buyInAmount;
    }

    public boolean isPlayerTurn() {
        return playerTurn;
    }

    public void setPlayerTurn(boolean playerTurn) {
        this.playerTurn = playerTurn;
    }

//    BlackJackDeck d;
    Vector<Card> playerHand;
    Vector<Card> dealerHand;
    private int hitChoice;
    private int gameChoice;
    private int playerAmount;
    private int dealerAmount;
    //private boolean playerWon;
 
    // Setters and getters
    public boolean isPlayerWon() {
        return playerWon;
    }

    public void setPlayerWon(boolean playerWon) {
        this.playerWon = playerWon;
    }

    // Blackjack specific methods
    int choice;
    String[] options = {"Yes", "No"};
    public void determineWinner(int playerAmount, int dealerAmount, int pot) throws IOException {
        Player p = new Player();
        if(playerAmount == 0 && dealerAmount == 500){
            Player.currency -= pot;
            //JOptionPane.showMessageDialog(null, "DEALER BLACKJACK! You lose. Play again?", "BLACKJACK", JOptionPane.YES_NO_OPTION);
            choice = JOptionPane.showOptionDialog(null, //Component parentComponent
                               "DEALER BLACKJACK! You lose. Play again?",
                               "BLACKJACK",
                               JOptionPane.YES_NO_OPTION,
                               JOptionPane.INFORMATION_MESSAGE,
                               null,
                               options,
                               null);
            if(choice == 0){
               BlackJackFrame.startIt(p);
            }           
            else{
                System.exit(0);
                }
            }
        else if(playerAmount == 1000 && dealerAmount == 0){
            Player.currency += pot * 2;
            //JOptionPane.showMessageDialog(null, "5 Cards Under 21! YOU WON! Play again?", "BLACKJACK", JOptionPane.YES_NO_OPTION);
            choice = JOptionPane.showOptionDialog(null, //Component parentComponent
                               "5 Cards Under 21! YOU WON! Play again?",
                               "BLACKJACK",
                               JOptionPane.YES_NO_OPTION,
                               JOptionPane.INFORMATION_MESSAGE,
                               null,
                               options,
                               null);
            if(choice == 0){
               BlackJackFrame.startIt(p);
            }           
            else{
                System.exit(0);
                }
        }
        else if(playerAmount == 0 && dealerAmount == 1000){
            Player.currency -= pot;
            //JOptionPane.showMessageDialog(null, "5 Cards Under 21! DEALER WON! Play again?", "BLACKJACK", JOptionPane.YES_NO_OPTION);
            choice = JOptionPane.showOptionDialog(null, //Component parentComponent
                               "5 Cards Under 21! DEALER WON! Play again?",
                               "BLACKJACK",
                               JOptionPane.YES_NO_OPTION,
                               JOptionPane.INFORMATION_MESSAGE,
                               null,
                               options,
                               null);
            if(choice == 0){
               BlackJackFrame.startIt(p);
            }           
            else{
                System.exit(0);
                }
        }
        else if(playerAmount == 100 && dealerAmount == 0){
            Player.currency -= pot;
            //JOptionPane.showMessageDialog(null, "YOU BUSTED! You lose. Play again?", "BLACKJACK", JOptionPane.YES_NO_OPTION);
            choice = JOptionPane.showOptionDialog(null, //Component parentComponent
                               "YOU BUSTED! You lose. Play again?",
                               "BLACKJACK",
                               JOptionPane.YES_NO_OPTION,
                               JOptionPane.INFORMATION_MESSAGE,
                               null,
                               options,
                               null);
            if(choice == 0){
               BlackJackFrame.startIt(p);
            }           
            else{
                System.exit(0);
                }
        }
        else if(playerAmount == 0 && dealerAmount == 100){
            Player.currency += pot * 2;
             //JOptionPane.showMessageDialog(null, "DEALER BUSTED! You win. Play again?", "BLACKJACK", JOptionPane.YES_NO_OPTION);
             choice = JOptionPane.showOptionDialog(null, //Component parentComponent
                               "DEALER BUSTED! You win! Play again?",
                               "BLACKJACK",
                               JOptionPane.YES_NO_OPTION,
                               JOptionPane.INFORMATION_MESSAGE,
                               null,
                               options,
                               null);
            if(choice == 0){
               BlackJackFrame.startIt(p);
            }           
            else{
                System.exit(0);
                }
        }
        else if (playerAmount > dealerAmount && playerAmount != 100 && playerAmount != 1000) {
            //System.out.println("Player wins.");
            Player.currency += pot * 2;
            //JOptionPane.showMessageDialog(null, "YOU WIN! Play again?", "BLACKJACK", JOptionPane.YES_NO_OPTION);
            playerWon = true;
            choice = JOptionPane.showOptionDialog(null, //Component parentComponent
                               "YOU WIN! Play again?",
                               "BLACKJACK",
                               JOptionPane.YES_NO_OPTION,
                               JOptionPane.INFORMATION_MESSAGE,
                               null,
                               options,
                               null);
            if(choice == 0){
               BlackJackFrame.startIt(p);
            }           
            else{
                System.exit(0);
                }
        }
        else {
            //System.out.println("Dealer wins.");
            Player.currency -= pot;
            //JOptionPane.showMessageDialog(null, "YOU LOSE! Play again?", "BLACKJACK", JOptionPane.YES_NO_OPTION);
            playerWon = false;
            choice = JOptionPane.showOptionDialog(null, //Component parentComponent
                               "YOU LOSE! Play again?",
                               "BLACKJACK",
                               JOptionPane.YES_NO_OPTION,
                               JOptionPane.INFORMATION_MESSAGE,
                               null,
                               options,
                               null);
            if(choice == 0){
               BlackJackFrame.startIt(p);
            }           
            else{
                System.exit(0);
                }
        }
    }
    public void setPlayerAmount(int amount) {
        playerAmount += amount;
    }
    public void setDealerAmount(int amount) {
        dealerAmount += amount;
    }
    public int getPlayerAmount() {
        return playerAmount;
    }
    public int getDealerAmount() {
        return dealerAmount;
    }
    public Vector getPlayerHand() {
        return playerHand;
    }
    public Vector getDealerHand() {
        return dealerHand;
    }
    
    public void dealInitialCards() { 
        d.shuffle();
        playerHand.add(d.deal());
        playerHand.add(d.deal());

        dealerHand.add(d.deal());
        dealerHand.add(d.deal());
    }
    
    public Card hit(){
        Card c = d.deal();
        return c;
    }
//    public Card playerHit() {
//        Card c = d.deal();
//        return c;
//    }
//    public Card dealerHit() {
//        Card c = d.deal();
//        return c;
//    }
  
    

    public static void main(String[] args) throws IOException {
        
        BlackJackGameEngine ge = new BlackJackGameEngine();
        Scanner scnr = new Scanner(System.in);
        int choice = 1;
        int gameChoice = 1;
        int playerAmount = 0;
        int dealerAmount = 0;

        do {
            // Add in dealt cards to hand vector
            ge.dealInitialCards();

            System.out.println("Player hand: ");
            //for (int i = 0; i < getPlayerHand.size(); i++) {
                //System.out.println(playerHand.elementAt(i));
           // }

            /*
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
                    }
                    playerAmount += playerHand.elementAt(2).getCardValue();
                    System.out.println("\n");

                    // Add a 3rd card for dealer
                    if (dealerAmount < 15) {
                        dealerHand.add(d.deal());
                    }

                    // print both of the dealers cards
                    System.out.println("Updated Dealer hand");
                    for (int j = 0; j < dealerHand.size(); j++) {
                        System.out.print(dealerHand.elementAt(j) + " , ");
                    }
                    dealerAmount += dealerHand.elementAt(2).getCardValue();

                    System.out.println("\n" + "Dealer's hand value: " + dealerAmount);
                    System.out.println("\n" + "Player's hand value: " + playerAmount);

                } else if (choice == 2) {
                    // stay
                    ge.determineWinner(playerAmount, dealerAmount);
                    break;
                }

            } // end betting loop

            if (playerAmount > 21) {
                System.out.println("Hand value over 21. Dealer wins.");
            } else if (playerAmount < 21 && playerHand.size() <= 5) {
                ge.determineWinner(playerAmount, dealerAmount);
            } else if (playerHand.size() == 5) {
                System.out.println("Player wins. 5 cards.");
            } else if (dealerAmount > 21) {
                System.out.println("Dealer hand value over 21. Player wins.");
            } else if (dealerAmount < 21 && dealerHand.size() <= 5) {
                ge.determineWinner(playerAmount, dealerAmount);
            } else if (dealerHand.size() == 5) {
                System.out.println("Dealer wins. 5 cards.");
            }

            System.out.println("Play again? Yes(1) No(2) ");
            gameChoice = scnr.nextInt();

            if (gameChoice == 1) {
                playerHand.clear();
                dealerHand.clear();
                playerAmount = 0;
                dealerAmount = 0;
            } else {
                break;
            }*/
        } while (gameChoice != 0);
        
        }
    }


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.AIPlayer;
import Model.Card;
import Model.Player;
import Model.PokerDeck;
import java.io.IOException;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;
import static javafx.application.Platform.exit;
import javax.swing.JOptionPane;

/**
 *
 * @author IanTaylor
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
public class TexasHoldem extends GameEngine {

    int initialDeal;
    double buyInAmount;
    double totalPot;
    boolean playerTurn;
    boolean playerWon;

    public Random rand;
    public PokerDeck pokerDeck;
    public Vector<Card> playerHand;
    public Vector<Card> AI_1Hand;
    public Vector<Card> AI_2Hand;
    public Vector<Card> AI_3Hand;
    public Vector<Card> boardCards;
    public Vector<Card> burnCards;
    public Player p;
    public AIPlayer ai1;
    public AIPlayer ai2;
    public AIPlayer ai3;
    //boolean replaceCardChoice;
    int gameChoice = 1;
    //int replaceCardsChoice;
    int playerMoveChoice;
    int AI_MoveChoice;
    int roundNumber;
    int lastBet = 0;
    int pot;
    int totalBetOwedByPlayer = 0;
    int totalBetOwedByAI1 = 0;
    int totalBetOwedByAI2 = 0;
    int totalBetOwedByAI3 = 0;
    int universalBetAmountOwed = 0;
    int tempCurr1;
    int tempCurr2;
    int tempCurr3;
    int tempCurr4;
    int pOwed = 0;
    int ai1Owed = 0;
    int ai2Owed = 0;
    int ai3Owed = 0;

    public TexasHoldem(AIPlayer ai1, AIPlayer ai2, AIPlayer ai3) throws IOException {

        this.rand = new Random();
        this.pokerDeck = new PokerDeck();
        this.playerHand = new Vector<Card>();
        this.AI_1Hand = new Vector<Card>();
        this.AI_2Hand = new Vector<Card>();
        this.AI_3Hand = new Vector<Card>();
        this.boardCards = new Vector<Card>();
        this.burnCards = new Vector<Card>();
        this.p = new Player();
        this.ai1 = ai1;
        this.ai2 = ai2;
        this.ai3 = ai3;

        deal();
        flop();
        turnAndRiver();
        turnAndRiver();

        // Set currency for ai's to whatever the user has
        this.ai1.setCurrency(Player.getCurrency());
        this.ai2.setCurrency(Player.getCurrency());
        this.ai3.setCurrency(Player.getCurrency());

        // Set the pot to 100, 25 placed from the player and 75 from the AIs
        this.pot = 100;

        this.tempCurr1 = p.getCurrency();
        this.tempCurr2 = ai1.getCurrency();
        this.tempCurr3 = ai2.getCurrency();
        this.tempCurr4 = ai3.getCurrency();
    }

    /**
     * Start each round by giving each player 2 cards
     */
    public void deal() {
        pokerDeck.shuffle();

        // Give player first card
        playerHand.add(pokerDeck.deal());
        // Give player second card
        playerHand.add(pokerDeck.deal());

        // Give player first card
        AI_1Hand.add(pokerDeck.deal());
        // Give player second card
        AI_1Hand.add(pokerDeck.deal());

        // Give player first card
        AI_2Hand.add(pokerDeck.deal());
        // Give player second card
        AI_2Hand.add(pokerDeck.deal());

        // Give player first card
        AI_3Hand.add(pokerDeck.deal());
        // Give player second card
        AI_3Hand.add(pokerDeck.deal());
    }

    /**
     * Function to Call/Check add players owed amount to the pot subtract the
     * owed amount from their currency return 0 which will be set to their new
     * owed amount
     *
     * @param player
     * @param owedAmt
     * @return
     */
    public int call(Player player, int owedAmt) {
        pot += owedAmt;
        player.setCurrency(player.getCurrency() - owedAmt);
        return 0;
    }

    public int call(AIPlayer player, int owedAmt) {
        pot += owedAmt;
        player.setCurrency(player.getCurrency() - owedAmt);
        return 0;
    }

    /**
     * Set a new bet and add that amount to the pot before calling this function
     * call() should be called after this function all owed amounts should be
     * added to
     *
     * @param player
     * @param betAmt
     * @return
     */
    public int newBet(AIPlayer player, int betAmt) {
        do {
            // Get bet amount input
            betAmt = rand.nextInt(player.getCurrency());
        } while ((betAmt > p.getCurrency() && p.active) || (betAmt > ai1.getCurrency() && ai1.active) || (betAmt > ai2.getCurrency() && ai2.active) || (betAmt > ai3.getCurrency() && ai3.active));
        pot += betAmt;
        player.setCurrency(player.getCurrency() - betAmt);
        return betAmt;
    }

    /**
     * Player folds set them to inactive for the round use fold value to
     * reactivate folded players at the end of the hand
     *
     * @param player
     */
    public void fold(Player player) {
        player.active = false;
        player.fold = true;
    }

    public void fold(AIPlayer player) {
        player.active = false;
        player.fold = true;
    }

    public int startRound(int playerMoveChoice, int betAmt) {
        //do {
        tempCurr1 = p.getCurrency();
        tempCurr2 = ai1.getCurrency();
        tempCurr3 = ai2.getCurrency();
        tempCurr4 = ai3.getCurrency();

        System.out.println("AI 2 Active: " + ai2.active);
        System.out.println("Player Owes: " + pOwed);
        System.out.println("AI1 Curr: " + ai1.getCurrency());
        //if the player is in the hand
        if (p.active) {
            System.out.println("Players choice: " + playerMoveChoice);
            //while (playerMoveChoice == 0) {
            //This endless polling loop might break everything
            //startRound(playerMoveChoice, betAmt);
            //}
            switch (playerMoveChoice) {
                //BET: Don't call bet function because that generate a random number
                case 1:
                    //Only add on to amount owed if the player has not folded
                    if (!ai1.fold) {
                        ai1Owed += betAmt;
                    }
                    if (!ai2.fold) {
                        ai2Owed += betAmt;
                    }
                    if (!ai3.fold) {
                        ai3Owed += betAmt;
                    }
                    pot += betAmt;
                    p.setCurrency(p.getCurrency() - betAmt);
                    break;

                case 2:
                    pOwed = call(p, pOwed);
                    break;

                case 3:
                    fold(p);
                    pOwed = 0;
                    break;
            }

        }

        //if the ai1 is in the hand
        if (ai1.active) {
            AI_MoveChoice = rand.nextInt(3) + 1;
            switch (AI_MoveChoice) {

                //BET: Don't call bet function because that generate a random number
                case 1:
                    ai1Owed = call(ai1, ai1Owed);
                    betAmt = newBet(ai1, betAmt);
                    System.out.println("AI1 Bet Amount: " + betAmt);

                    //Only add on to amount owed if the player has not folded
                    if (p.active) {
                        pOwed += betAmt;
                    }
                    if (ai2.active) {
                        ai2Owed += betAmt;
                    }
                    if (ai3.active) {
                        ai3Owed += betAmt;
                    }
                    pot += betAmt;
                    ai1.setCurrency(ai1.getCurrency() - betAmt);
                    break;

                case 2:
                    System.out.println("AI1 Calling: " + ai1Owed);

                    ai1Owed = call(ai1, ai1Owed);
                    break;

                case 3:
                    System.out.println("AI1 Folding");
                    if (ai2.fold) {
                        System.out.println("AI2 Folding");
                    }
                    if (ai3.fold) {
                        System.out.println("AI3 Folding");
                    }

                    fold(ai1);
                    ai1Owed = 0;
                    break;
            }
            if (ai2.active) {
                System.out.println("AI2 active");
            }
            if (ai3.active) {
                System.out.println("AI3 active");
            }
        }

        //if the ai2 is in the hand
        if (ai2.active) {
            AI_MoveChoice = rand.nextInt(3) + 1;
            switch (AI_MoveChoice) {

                //BET: Don't call bet function because that generate a random number
                case 1:
                    ai2Owed = call(ai2, ai2Owed);
                    betAmt = newBet(ai2, betAmt);
                    System.out.println("AI2 Bet Amount: " + betAmt);
                    //Only add on to amount owed if the player has not folded
                    if (p.active) {
                        pOwed += betAmt;
                    }
                    if (ai1.active) {
                        ai1Owed += betAmt;
                    }
                    if (ai3.active) {
                        ai3Owed += betAmt;
                    }
                    pot += betAmt;
                    ai2.setCurrency(ai2.getCurrency() - betAmt);
                    break;

                case 2:
                    ai2Owed = call(ai2, ai2Owed);
                    System.out.println("AI2 Calling: " + ai2Owed);
                    break;

                case 3:
                    fold(ai2);
                    System.out.println("AI2 Folding");
                    ai2Owed = 0;
                    break;
            }
        }

        //if the ai3 is in the hand
        if (ai3.active) {
            AI_MoveChoice = rand.nextInt(3) + 1;
            switch (AI_MoveChoice) {

                //BET: Don't call bet function because that generate a random number
                case 1:
                    ai3Owed = call(ai3, ai3Owed);
                    betAmt = newBet(ai3, betAmt);
                    System.out.println("AI3 Bet Amount: " + betAmt);
                    //Only add on to amount owed if the player has not folded
                    if (p.active) {
                        pOwed += betAmt;
                    }
                    if (ai1.active) {
                        ai1Owed += betAmt;
                    }
                    if (ai2.active) {
                        ai2Owed += betAmt;
                    }
                    pot += betAmt;
                    ai3.setCurrency(ai3.getCurrency() - betAmt);
                    break;

                case 2:
                    ai3Owed = call(ai3, ai3Owed);
                    System.out.println("AI3 Bet Amount: " + ai3Owed);
                    break;

                case 3:
                    fold(ai3);
                    System.out.println("AI3 Folding");
                    ai3Owed = 0;
                    break;
            }
        }
        universalBetAmountOwed = pOwed + ai1Owed + ai2Owed + ai3Owed;
        return universalBetAmountOwed;
        //} while (pOwed != 0 && ai1Owed != 0 && ai2Owed != 0 && ai3Owed != 0);
    }

    /**
     * ------ROUND 1------- -------------------- Players options include
     * BET/RAISE (1), CALL/CHECK (2), FOLD (3) If(player.active) checks to make
     * sure that player is still in the game
     *
     * @param playerMoveChoice
     * @param betAmount
     * @return
     */
    public int startRound1(int playerMoveChoice, int betAmount) {

        System.out.println("Player 1 TempCurr: " + tempCurr1);
        System.out.println("AI 1 TempCurr: " + tempCurr2);
        System.out.println("AI 2 TempCurr: " + tempCurr3);
        System.out.println("AI 3 TempCurr: " + tempCurr4);

        roundNumber = 1;

        System.out.println("Player Currency: " + p.getCurrency());
        // User places bet, calls, or folds
        System.out.println("ROUND 1 STARTING");
        System.out.println("Do you wish to bet, call, or fold? : 1 or 2 or 3");

        //IF statement to ensure the player is still in the game
        //Do this for each player
        if (p.active) {
            switch (playerMoveChoice) {

                case 1: // BET

                    // Check if player needs to clear bets from any other player first
                    if (totalBetOwedByPlayer != 0) {
                        // Updates total bet by substracting amount needed to call (totalBetOwed), resulting in the real bet amount
                        betAmount -= totalBetOwedByPlayer;
                        // Clears bet owed by player
                        p.setBet(totalBetOwedByPlayer); // Update player bet variable 
                        p.setCurrency(p.getCurrency() - totalBetOwedByPlayer); // Update player currency 
                        pot += totalBetOwedByPlayer; // Update pot
                        totalBetOwedByPlayer -= totalBetOwedByPlayer; // Update player total bet owed varaible
                    }

                    p.setBet(betAmount); // Update player bet amount
                    p.setCurrency(p.getCurrency() - betAmount); // Update player currency 
                    pot += betAmount; // Add bet amount to pot
                    // Broadcast bet amount to all other players
                    totalBetOwedByAI1 += betAmount;
                    totalBetOwedByAI2 += betAmount;
                    totalBetOwedByAI3 += betAmount;
                    break;

                case 2: // CALL
                    betAmount = totalBetOwedByPlayer;
                    // Updates total bet by substracting amount needed to call (totalBetOwed), resulting in the real bet amount
                    betAmount -= totalBetOwedByPlayer;
                    // Clears bet owed by player
                    p.setBet(totalBetOwedByPlayer); // Update player bet variable 
                    p.setCurrency(p.getCurrency() - totalBetOwedByPlayer); // Update player currency 
                    pot += totalBetOwedByPlayer; // Update pot
                    totalBetOwedByPlayer -= totalBetOwedByPlayer; // Update player total bet owed varaible
                    break;

                case 3: // FOLD
                    p.active = false;
                    p.fold = true;
                    break;
            }
        }

        // AI_1 turn
        // Get random input (choice) from AI player
        AI_MoveChoice = rand.nextInt(3) + 1;

        System.out.println("AI1 Move #:" + AI_MoveChoice);
        System.out.println("AI1 Currency: " + ai1.getCurrency());

        if (ai1.active) {
            switch (AI_MoveChoice) {

                case 1: // BET
                    // Get bet amount input
                    betAmount = rand.nextInt(ai1.getCurrency() - totalBetOwedByAI1) + totalBetOwedByAI1;

                    while ((betAmount > tempCurr1 && p.active) || (betAmount > tempCurr2 && ai1.active) || (betAmount > tempCurr3 && ai1.active) || (betAmount > tempCurr4 && ai3.active)) {
                        betAmount = rand.nextInt(ai1.getCurrency() - totalBetOwedByAI1) + totalBetOwedByAI1;
                    }

//                    // Force CALL action if less than 50 in currency 
//                    if (ai1.getCurrency() < 50) {
//                        betAmount = totalBetOwedByAI1;
//                        // Updates total bet by substracting amount needed to call (totalBetOwed), resulting in the real bet amount
//                        betAmount -= totalBetOwedByAI1;
//                        // Clears bet owed by player
//                        ai1.setBet(totalBetOwedByAI1); // Update player bet variable 
//                        ai1.setCurrency(ai1.getCurrency() - totalBetOwedByAI1); // Update player currency 
//                        pot += totalBetOwedByAI1; // Update pot
//                        totalBetOwedByAI1 -= totalBetOwedByAI1; // Update player total bet owed varaible
//                        break;
//                    }
                    // Check if player needs to clear bets from any other player first
                    if (totalBetOwedByAI1 != 0) {
                        // Updates total bet by substracting amount needed to call (totalBetOwed), resulting in the real bet amount
                        betAmount -= totalBetOwedByAI1;
                        // Clears bet owed by player
                        ai1.setBet(totalBetOwedByAI1); // Update player bet variable 
                        ai1.setCurrency(ai1.getCurrency() - totalBetOwedByAI1); // Update player currency 
                        pot += totalBetOwedByAI1; // Update pot
                        totalBetOwedByAI1 -= totalBetOwedByAI1; // Update player total bet owed varaible
                    }

                    ai1.setBet(betAmount); // Update player bet amount
                    ai1.setCurrency(ai1.getCurrency() - betAmount); // Update player currency 
                    pot += betAmount; // Add bet amount to pot
                    System.out.println("AI1 Bet Amount: " + betAmount);
                    // Broadcast bet amount to all other players
                    totalBetOwedByPlayer += betAmount;
                    totalBetOwedByAI2 += betAmount;
                    totalBetOwedByAI3 += betAmount;
                    break;

                case 2: // CALL
                    betAmount = totalBetOwedByAI1;
                    // Updates total bet by substracting amount needed to call (totalBetOwed), resulting in the real bet amount
                    betAmount -= totalBetOwedByAI1;
                    // Clears bet owed by player
                    ai1.setBet(totalBetOwedByPlayer); // Update player bet variable 
                    ai1.setCurrency(ai1.getCurrency() - totalBetOwedByAI1); // Update player currency 
                    pot += totalBetOwedByAI1; // Update pot
                    totalBetOwedByAI1 -= totalBetOwedByAI1; // Update player total bet owed varaible
                    break;

                case 3: // FOLD
                    ai1.active = false;
                    ai1.fold = true;
                    break;
            }
        }

        // AI_2 player bet, or calls 
        // Get random input (choice) from AI player
        AI_MoveChoice = rand.nextInt(3) + 1;

        System.out.println("AI2 Move #:" + AI_MoveChoice);
        System.out.println("AI2 Currency: " + ai2.getCurrency());

        if (ai2.active) {
            switch (AI_MoveChoice) {

                case 1: // BET  

                    // Get bet amount input
                    betAmount = rand.nextInt(ai2.getCurrency() - totalBetOwedByAI2) + totalBetOwedByAI2;

                    //While loop ensures bets don't exceed the minimum currency
                    while ((betAmount > tempCurr1 && p.active) || (betAmount > tempCurr2 && ai1.active) || (betAmount > tempCurr3 && ai1.active) || (betAmount > tempCurr4 && ai3.active)) {
                        betAmount = rand.nextInt(ai2.getCurrency() - totalBetOwedByAI2) + totalBetOwedByAI2;
                    }

                    // Check if player needs to clear bets from any other player first
                    if (totalBetOwedByAI2 != 0) {
                        // Updates total bet by substracting amount needed to call (totalBetOwed), resulting in the real bet amount
                        betAmount -= totalBetOwedByAI2;
                        // Clears bet owed by player
                        ai2.setBet(totalBetOwedByAI2); // Update player bet variable 
                        ai2.setCurrency(ai2.getCurrency() - totalBetOwedByAI2); // Update player currency 
                        pot += totalBetOwedByAI2; // Update pot
                        totalBetOwedByAI2 -= totalBetOwedByAI2; // Update player total bet owed varaible
                    }

                    ai2.setBet(betAmount); // Update player bet amount
                    ai2.setCurrency(ai2.getCurrency() - betAmount); // Update player currency 
                    pot += betAmount; // Add bet amount to pot
                    System.out.println("AI2 Bet Amount: " + betAmount);
                    // Broadcast bet amount to all other players
                    totalBetOwedByPlayer += betAmount;
                    totalBetOwedByAI1 += betAmount;
                    totalBetOwedByAI3 += betAmount;
                    break;

                case 2: // CALL
                    betAmount = totalBetOwedByAI2;
                    // Updates total bet by substracting amount needed to call (totalBetOwed), resulting in the real bet amount
                    betAmount -= totalBetOwedByAI2;
                    // Clears bet owed by player
                    ai2.setBet(totalBetOwedByAI2); // Update player bet variable 
                    ai2.setCurrency(ai2.getCurrency() - totalBetOwedByAI2); // Update player currency 
                    pot += totalBetOwedByAI2; // Update pot
                    totalBetOwedByAI2 -= totalBetOwedByAI2; // Update player total bet owed varaible
                    break;

                case 3: // FOLD
                    ai2.active = false;
                    ai2.fold = true;
                    break;
            }
        }

        /// AI_3 player bet, or calls 
        // Get random input (choice) from AI player
        AI_MoveChoice = rand.nextInt(3) + 1;

        System.out.println("AI3 Move #:" + AI_MoveChoice);
        System.out.println("AI3 Currency: " + ai3.getCurrency());

        if (ai3.active) {

            switch (AI_MoveChoice) {

                case 1: // BET

                    // Get bet amount input
                    betAmount = rand.nextInt(ai3.getCurrency() - totalBetOwedByAI3) + totalBetOwedByAI3;
                    System.out.println("AI3 Bet: " + betAmount);

                    //While loop ensures bets don't exceed the minimum currency
                    while ((betAmount > tempCurr1 && p.active) || (betAmount > tempCurr2 && ai1.active) || (betAmount > tempCurr3 && ai1.active) || (betAmount > tempCurr4 && ai3.active)) {
                        betAmount = rand.nextInt(ai3.getCurrency() - totalBetOwedByAI3) + totalBetOwedByAI3;
                    }

                    // Check if player needs to clear bets from any other player first
                    if (totalBetOwedByAI3 != 0) {
                        // Updates total bet by substracting amount needed to call (totalBetOwed), resulting in the real bet amount
                        betAmount -= totalBetOwedByAI3;
                        // Clears bet owed by player
                        ai3.setBet(totalBetOwedByAI3); // Update player bet variable 
                        ai3.setCurrency(ai3.getCurrency() - totalBetOwedByAI3); // Update player currency 
                        pot += totalBetOwedByAI3; // Update pot
                        totalBetOwedByAI3 -= totalBetOwedByAI3; // Update player total bet owed varaible
                    }

                    ai3.setBet(betAmount); // Update player bet amount
                    ai3.setCurrency(ai3.getCurrency() - betAmount); // Update player currency 
                    pot += betAmount; // Add bet amount to pot
                    System.out.println("AI3 Bet Amount: " + betAmount);
                    // Broadcast bet amount to all other players
                    totalBetOwedByPlayer += betAmount;
                    totalBetOwedByAI1 += betAmount;
                    totalBetOwedByAI2 += betAmount;
                    break;

                case 2: // CALL
                    betAmount = totalBetOwedByAI3;
                    // Updates total bet by substracting amount needed to call (totalBetOwed), resulting in the real bet amount
                    betAmount -= totalBetOwedByAI3;
                    // Clears bet owed by player
                    ai3.setBet(totalBetOwedByAI3); // Update player bet variable 
                    ai3.setCurrency(ai3.getCurrency() - totalBetOwedByAI3); // Update player currency 
                    pot += totalBetOwedByAI3; // Update pot
                    totalBetOwedByAI3 -= totalBetOwedByAI3; // Update player total bet owed varaible
                    break;

                case 3: // FOLD
                    ai3.active = false;
                    ai3.fold = true;
                    break;
            }
        }

        System.out.println("Total Owed: ");
        System.out.println(totalBetOwedByPlayer);
        System.out.println(totalBetOwedByAI1);
        System.out.println(totalBetOwedByAI2);
        System.out.println(totalBetOwedByAI3);

        universalBetAmountOwed = totalBetOwedByPlayer + totalBetOwedByAI1 + totalBetOwedByAI2 + totalBetOwedByAI3;

        return universalBetAmountOwed;
    }

    /**
     * ------ROUND 2------- -------------------- Player Choices include BET (1),
     * CALL/CHECK (2), FOLD (3)
     *
     * @param playerMoveChoice
     * @param betAmount
     * @return
     */
    public int startRound2(int playerMoveChoice, int betAmount) {

        // User places bet, calls, or folds
        System.out.println("ROUND 2 STARTING");
        System.out.println("Do you wish to bet, call, or fold? : 1 or 2 or 3");

        //IF statement to ensure players are still in the game
        //Do this for each player
        if (p.active) {
            switch (playerMoveChoice) {

                case 1: // BET

                    // Check if player needs to clear bets from any other player first
                    if (totalBetOwedByPlayer != 0) {

                        // Updates total bet by substracting amount needed to call (totalBetOwed), resulting in the real bet amount
                        betAmount -= totalBetOwedByPlayer;
                        // Clears bet owed by player
                        p.setBet(totalBetOwedByPlayer); // Update player bet variable 
                        p.setCurrency(p.getCurrency() - totalBetOwedByPlayer); // Update player currency 
                        pot += totalBetOwedByPlayer; // Update pot
                        totalBetOwedByPlayer -= totalBetOwedByPlayer; // Update player total bet owed varaible
                    }

                    p.setBet(betAmount); // Update player bet amount
                    p.setCurrency(p.getCurrency() - betAmount); // Update player currency 
                    pot += betAmount; // Add bet amount to pot
                    // Broadcast bet amount to all other players
                    totalBetOwedByAI1 += betAmount;
                    totalBetOwedByAI2 += betAmount;
                    totalBetOwedByAI3 += betAmount;
                    break;

                case 2: // CALL
                    betAmount = totalBetOwedByPlayer;
                    // Updates total bet by substracting amount needed to call (totalBetOwed), resulting in the real bet amount
                    betAmount -= totalBetOwedByPlayer;
                    // Clears bet owed by player
                    p.setBet(totalBetOwedByPlayer); // Update player bet variable 
                    p.setCurrency(p.getCurrency() - totalBetOwedByPlayer); // Update player currency 
                    pot += totalBetOwedByPlayer; // Update pot
                    totalBetOwedByPlayer -= totalBetOwedByPlayer; // Update player total bet owed varaible
                    break;

                case 3: // FOLD
                    p.active = false;
                    p.fold = true;
                    break;
            }
        }

        //------------------------
        //------AI_1 Turn---------
        //------------------------
        // Get random input (choice) from AI player
        AI_MoveChoice = rand.nextInt(3) + 1;

        System.out.println("AI Move #:" + AI_MoveChoice);
        System.out.println("AI1 Currency: " + ai1.getCurrency());

        if (ai1.active) {
            switch (AI_MoveChoice) {

                case 1: // BET

                    // Get bet amount input
                    betAmount = rand.nextInt(ai1.getCurrency() - totalBetOwedByAI1) + totalBetOwedByAI1;
                    while ((betAmount > tempCurr1 && p.active) || (betAmount > tempCurr2 && ai1.active) || (betAmount > tempCurr3 && ai1.active) || (betAmount > tempCurr4 && ai3.active)) {
                        betAmount = rand.nextInt(ai1.getCurrency() - totalBetOwedByAI1) + totalBetOwedByAI1;
                    }

                    // Check if player needs to clear bets from any other player first
                    if (totalBetOwedByAI1 != 0) {
                        // Updates total bet by substracting amount needed to call (totalBetOwed), resulting in the real bet amount
                        betAmount -= totalBetOwedByAI1;
                        // Clears bet owed by player
                        ai1.setBet(totalBetOwedByAI1); // Update player bet variable 
                        ai1.setCurrency(ai1.getCurrency() - totalBetOwedByAI1); // Update player currency 
                        pot += totalBetOwedByAI1; // Update pot
                        totalBetOwedByAI1 -= totalBetOwedByAI1; // Update player total bet owed varaible
                    }

                    ai1.setBet(betAmount); // Update player bet amount
                    ai1.setCurrency(ai1.getCurrency() - betAmount); // Update player currency 
                    pot += betAmount; // Add bet amount to pot
                    System.out.println("AI1 Bet Amount: " + betAmount);
                    // Broadcast bet amount to all other players
                    totalBetOwedByPlayer += betAmount;
                    totalBetOwedByAI2 += betAmount;
                    totalBetOwedByAI3 += betAmount;
                    break;

                case 2: // CALL
                    betAmount = totalBetOwedByAI1;
                    // Updates total bet by substracting amount needed to call (totalBetOwed), resulting in the real bet amount
                    betAmount -= totalBetOwedByAI1;
                    // Clears bet owed by player
                    ai1.setBet(totalBetOwedByPlayer); // Update player bet variable 
                    ai1.setCurrency(ai1.getCurrency() - totalBetOwedByAI1); // Update player currency 
                    pot += totalBetOwedByAI1; // Update pot
                    totalBetOwedByAI1 -= totalBetOwedByAI1; // Update player total bet owed varaible
                    break;

                case 3: // FOLD
                    ai1.active = false;
                    ai1.fold = true;
                    break;
            }
        }

        //------------------------
        //------AI_2 Turn---------
        //------------------------
        // Get random input (choice) from AI player
        AI_MoveChoice = rand.nextInt(3) + 1;

        System.out.println("AI Move #:" + AI_MoveChoice);
        System.out.println("AI2 Currency: " + ai2.getCurrency());
        if (ai2.active) {
            switch (AI_MoveChoice) {

                case 1: // BET  
                    // Get bet amount input
                    betAmount = rand.nextInt(ai2.getCurrency() - totalBetOwedByAI2) + totalBetOwedByAI2;
                    while ((betAmount > tempCurr1 && p.active) || (betAmount > tempCurr2 && ai1.active) || (betAmount > tempCurr3 && ai1.active) || (betAmount > tempCurr4 && ai3.active)) {
                        betAmount = rand.nextInt(ai2.getCurrency() - totalBetOwedByAI2) + totalBetOwedByAI2;
                    }

                    // Check if player needs to clear bets from any other player first
                    if (totalBetOwedByAI2 != 0) {
                        // Updates total bet by substracting amount needed to call (totalBetOwed), resulting in the real bet amount
                        betAmount -= totalBetOwedByAI2;
                        // Clears bet owed by player
                        ai2.setBet(totalBetOwedByAI2); // Update player bet variable 
                        ai2.setCurrency(ai2.getCurrency() - totalBetOwedByAI2); // Update player currency 
                        pot += totalBetOwedByAI2; // Update pot
                        totalBetOwedByAI2 -= totalBetOwedByAI2; // Update player total bet owed varaible
                    }

                    ai2.setBet(betAmount); // Update player bet amount
                    ai2.setCurrency(ai2.getCurrency() - betAmount); // Update player currency 
                    pot += betAmount; // Add bet amount to pot
                    System.out.println("AI2 Bet Amount: " + betAmount);
                    // Broadcast bet amount to all other players
                    totalBetOwedByPlayer += betAmount;
                    totalBetOwedByAI1 += betAmount;
                    totalBetOwedByAI3 += betAmount;
                    break;

                case 2: // CALL
                    betAmount = totalBetOwedByAI2;
                    // Updates total bet by substracting amount needed to call (totalBetOwed), resulting in the real bet amount
                    betAmount -= totalBetOwedByAI2;
                    // Clears bet owed by player
                    ai2.setBet(totalBetOwedByAI2); // Update player bet variable 
                    ai2.setCurrency(ai2.getCurrency() - totalBetOwedByAI2); // Update player currency 
                    pot += totalBetOwedByAI2; // Update pot
                    totalBetOwedByAI2 -= totalBetOwedByAI2; // Update player total bet owed varaible
                    break;

                case 3: // FOLD
                    ai2.active = false;
                    ai2.fold = true;
                    break;
            }
        }

        //------------------------
        //------AI_3 Turn---------
        //------------------------
        // Get random input (choice) from AI player
        AI_MoveChoice = rand.nextInt(3) + 1;

        System.out.println("AI Move #:" + AI_MoveChoice);
        System.out.println("AI3 Currency: " + ai3.getCurrency());
        if (ai3.active) {
            switch (AI_MoveChoice) {

                case 1: // BET

                    // Get bet amount input
                    betAmount = rand.nextInt(ai3.getCurrency() - totalBetOwedByAI3) + totalBetOwedByAI3;
                    while ((betAmount > tempCurr1 && p.active) || (betAmount > tempCurr2 && ai1.active) || (betAmount > tempCurr3 && ai1.active) || (betAmount > tempCurr4 && ai3.active)) {
                        betAmount = rand.nextInt(ai3.getCurrency() - totalBetOwedByAI3) + totalBetOwedByAI3;
                    }

                    // Check if player needs to clear bets from any other player first
                    if (totalBetOwedByAI3 != 0) {
                        // Updates total bet by substracting amount needed to call (totalBetOwed), resulting in the real bet amount
                        betAmount -= totalBetOwedByAI3;
                        // Clears bet owed by player
                        ai3.setBet(totalBetOwedByAI3); // Update player bet variable 
                        ai3.setCurrency(ai3.getCurrency() - totalBetOwedByAI3); // Update player currency 
                        pot += totalBetOwedByAI3; // Update pot
                        totalBetOwedByAI3 -= totalBetOwedByAI3; // Update player total bet owed varaible
                    }

                    ai3.setBet(betAmount); // Update player bet amount
                    ai3.setCurrency(ai3.getCurrency() - betAmount); // Update player currency 
                    pot += betAmount; // Add bet amount to pot
                    System.out.println("AI3 Bet Amount: " + betAmount);
                    // Broadcast bet amount to all other players
                    totalBetOwedByPlayer += betAmount;
                    totalBetOwedByAI1 += betAmount;
                    totalBetOwedByAI2 += betAmount;
                    break;

                case 2: // CALL
                    betAmount = totalBetOwedByAI3;
                    // Updates total bet by substracting amount needed to call (totalBetOwed), resulting in the real bet amount
                    betAmount -= totalBetOwedByAI3;
                    // Clears bet owed by player
                    ai3.setBet(totalBetOwedByAI3); // Update player bet variable 
                    ai3.setCurrency(ai3.getCurrency() - totalBetOwedByAI3); // Update player currency 
                    pot += totalBetOwedByAI3; // Update pot
                    totalBetOwedByAI3 -= totalBetOwedByAI3; // Update player total bet owed varaible
                    break;

                case 3: // FOLD
                    ai3.active = false;
                    ai3.fold = true;
                    break;
            }
        }

        System.out.println("Total Owed: ");
        System.out.println(totalBetOwedByPlayer);
        System.out.println(totalBetOwedByAI1);
        System.out.println(totalBetOwedByAI2);
        System.out.println(totalBetOwedByAI3);

        universalBetAmountOwed = totalBetOwedByPlayer + totalBetOwedByAI1 + totalBetOwedByAI2 + totalBetOwedByAI3;

        return universalBetAmountOwed;

    }

    /**
     * ------ROUND 3------- -------------------- Player Choices include BET (1),
     * CALL/CHECK (2), FOLD (3)
     *
     * @param playerMoveChoice
     * @param betAmount
     * @return
     */
    public int startRound3(int playerMoveChoice, int betAmount) {

        // User places bet, calls, or folds
        System.out.println("ROUND 3 STARTING");
        System.out.println("Do you wish to bet, call, or fold? : 1 or 2 or 3");

        //IF statement to ensure players are still in the game
        //Do this for each player
        if (p.active) {
            switch (playerMoveChoice) {

                case 1: // BET

                    // Check if player needs to clear bets from any other player first
                    if (totalBetOwedByPlayer != 0) {

                        // Updates total bet by substracting amount needed to call (totalBetOwed), resulting in the real bet amount
                        betAmount -= totalBetOwedByPlayer;
                        // Clears bet owed by player
                        p.setBet(totalBetOwedByPlayer); // Update player bet variable 
                        p.setCurrency(p.getCurrency() - totalBetOwedByPlayer); // Update player currency 
                        pot += totalBetOwedByPlayer; // Update pot
                        totalBetOwedByPlayer -= totalBetOwedByPlayer; // Update player total bet owed varaible
                    }

                    p.setBet(betAmount); // Update player bet amount
                    p.setCurrency(p.getCurrency() - betAmount); // Update player currency 
                    pot += betAmount; // Add bet amount to pot
                    // Broadcast bet amount to all other players
                    totalBetOwedByAI1 += betAmount;
                    totalBetOwedByAI2 += betAmount;
                    totalBetOwedByAI3 += betAmount;
                    break;

                case 2: // CALL
                    betAmount = totalBetOwedByPlayer;
                    // Updates total bet by substracting amount needed to call (totalBetOwed), resulting in the real bet amount
                    betAmount -= totalBetOwedByPlayer;
                    // Clears bet owed by player
                    p.setBet(totalBetOwedByPlayer); // Update player bet variable 
                    p.setCurrency(p.getCurrency() - totalBetOwedByPlayer); // Update player currency 
                    pot += totalBetOwedByPlayer; // Update pot
                    totalBetOwedByPlayer -= totalBetOwedByPlayer; // Update player total bet owed varaible
                    break;

                case 3: // FOLD
                    p.active = false;
                    p.fold = true;
                    break;
            }
        }

        //------------------------
        //------AI_1 Turn---------
        //------------------------
        // Get random input (choice) from AI player
        AI_MoveChoice = rand.nextInt(3) + 1;

        System.out.println("AI Move #:" + AI_MoveChoice);
        System.out.println("AI1 Currency: " + ai1.getCurrency());

        if (ai1.active) {
            switch (AI_MoveChoice) {

                case 1: // BET

                    // Get bet amount input
                    betAmount = rand.nextInt(ai1.getCurrency() - totalBetOwedByAI1) + totalBetOwedByAI1;
                    while ((betAmount > tempCurr1 && p.active) || (betAmount > tempCurr2 && ai1.active) || (betAmount > tempCurr3 && ai1.active) || (betAmount > tempCurr4 && ai3.active)) {
                        betAmount = rand.nextInt(ai1.getCurrency() - totalBetOwedByAI1) + totalBetOwedByAI1;
                    }

                    // Check if player needs to clear bets from any other player first
                    if (totalBetOwedByAI1 != 0) {
                        // Updates total bet by substracting amount needed to call (totalBetOwed), resulting in the real bet amount
                        betAmount -= totalBetOwedByAI1;
                        // Clears bet owed by player
                        ai1.setBet(totalBetOwedByAI1); // Update player bet variable 
                        ai1.setCurrency(ai1.getCurrency() - totalBetOwedByAI1); // Update player currency 
                        pot += totalBetOwedByAI1; // Update pot
                        totalBetOwedByAI1 -= totalBetOwedByAI1; // Update player total bet owed varaible
                    }

                    ai1.setBet(betAmount); // Update player bet amount
                    ai1.setCurrency(ai1.getCurrency() - betAmount); // Update player currency 
                    pot += betAmount; // Add bet amount to pot
                    System.out.println("AI1 Bet Amount: " + betAmount);
                    // Broadcast bet amount to all other players
                    totalBetOwedByPlayer += betAmount;
                    totalBetOwedByAI2 += betAmount;
                    totalBetOwedByAI3 += betAmount;
                    break;

                case 2: // CALL
                    betAmount = totalBetOwedByAI1;
                    // Updates total bet by substracting amount needed to call (totalBetOwed), resulting in the real bet amount
                    betAmount -= totalBetOwedByAI1;
                    // Clears bet owed by player
                    ai1.setBet(totalBetOwedByPlayer); // Update player bet variable 
                    ai1.setCurrency(ai1.getCurrency() - totalBetOwedByAI1); // Update player currency 
                    pot += totalBetOwedByAI1; // Update pot
                    totalBetOwedByAI1 -= totalBetOwedByAI1; // Update player total bet owed varaible
                    break;

                case 3: // FOLD
                    ai1.active = false;
                    ai1.fold = true;
                    break;
            }
        }

        //------------------------
        //------AI_2 Turn---------
        //------------------------
        // Get random input (choice) from AI player
        AI_MoveChoice = rand.nextInt(3) + 1;

        System.out.println("AI Move #:" + AI_MoveChoice);
        System.out.println("AI2 Currency: " + ai2.getCurrency());
        if (ai2.active) {
            switch (AI_MoveChoice) {

                case 1: // BET  
                    // Get bet amount input
                    betAmount = rand.nextInt(ai2.getCurrency() - totalBetOwedByAI2) + totalBetOwedByAI2;
                    while ((betAmount > tempCurr1 && p.active) || (betAmount > tempCurr2 && ai1.active) || (betAmount > tempCurr3 && ai1.active) || (betAmount > tempCurr4 && ai3.active)) {
                        betAmount = rand.nextInt(ai2.getCurrency() - totalBetOwedByAI2) + totalBetOwedByAI2;
                    }

                    // Check if player needs to clear bets from any other player first
                    if (totalBetOwedByAI2 != 0) {
                        // Updates total bet by substracting amount needed to call (totalBetOwed), resulting in the real bet amount
                        betAmount -= totalBetOwedByAI2;
                        // Clears bet owed by player
                        ai2.setBet(totalBetOwedByAI2); // Update player bet variable 
                        ai2.setCurrency(ai2.getCurrency() - totalBetOwedByAI2); // Update player currency 
                        pot += totalBetOwedByAI2; // Update pot
                        totalBetOwedByAI2 -= totalBetOwedByAI2; // Update player total bet owed varaible
                    }

                    ai2.setBet(betAmount); // Update player bet amount
                    ai2.setCurrency(ai2.getCurrency() - betAmount); // Update player currency 
                    pot += betAmount; // Add bet amount to pot
                    System.out.println("AI2 Bet Amount: " + betAmount);
                    // Broadcast bet amount to all other players
                    totalBetOwedByPlayer += betAmount;
                    totalBetOwedByAI1 += betAmount;
                    totalBetOwedByAI3 += betAmount;
                    break;

                case 2: // CALL
                    betAmount = totalBetOwedByAI2;
                    // Updates total bet by substracting amount needed to call (totalBetOwed), resulting in the real bet amount
                    betAmount -= totalBetOwedByAI2;
                    // Clears bet owed by player
                    ai2.setBet(totalBetOwedByAI2); // Update player bet variable 
                    ai2.setCurrency(ai2.getCurrency() - totalBetOwedByAI2); // Update player currency 
                    pot += totalBetOwedByAI2; // Update pot
                    totalBetOwedByAI2 -= totalBetOwedByAI2; // Update player total bet owed varaible
                    break;

                case 3: // FOLD
                    ai2.active = false;
                    ai2.fold = true;
                    break;
            }
        }

        //------------------------
        //------AI_3 Turn---------
        //------------------------
        // Get random input (choice) from AI player
        AI_MoveChoice = rand.nextInt(3) + 1;

        System.out.println("AI Move #:" + AI_MoveChoice);
        System.out.println("AI3 Currency: " + ai3.getCurrency());
        if (ai3.active) {
            switch (AI_MoveChoice) {

                case 1: // BET

                    // Get bet amount input
                    betAmount = rand.nextInt(ai3.getCurrency() - totalBetOwedByAI3) + totalBetOwedByAI3;
                    while ((betAmount > tempCurr1 && p.active) || (betAmount > tempCurr2 && ai1.active) || (betAmount > tempCurr3 && ai1.active) || (betAmount > tempCurr4 && ai3.active)) {
                        betAmount = rand.nextInt(ai3.getCurrency() - totalBetOwedByAI3) + totalBetOwedByAI3;
                    }

                    // Check if player needs to clear bets from any other player first
                    if (totalBetOwedByAI3 != 0) {
                        // Updates total bet by substracting amount needed to call (totalBetOwed), resulting in the real bet amount
                        betAmount -= totalBetOwedByAI3;
                        // Clears bet owed by player
                        ai3.setBet(totalBetOwedByAI3); // Update player bet variable 
                        ai3.setCurrency(ai3.getCurrency() - totalBetOwedByAI3); // Update player currency 
                        pot += totalBetOwedByAI3; // Update pot
                        totalBetOwedByAI3 -= totalBetOwedByAI3; // Update player total bet owed varaible
                    }

                    ai3.setBet(betAmount); // Update player bet amount
                    ai3.setCurrency(ai3.getCurrency() - betAmount); // Update player currency 
                    pot += betAmount; // Add bet amount to pot
                    System.out.println("AI3 Bet Amount: " + betAmount);
                    // Broadcast bet amount to all other players
                    totalBetOwedByPlayer += betAmount;
                    totalBetOwedByAI1 += betAmount;
                    totalBetOwedByAI2 += betAmount;
                    break;

                case 2: // CALL
                    betAmount = totalBetOwedByAI3;
                    // Updates total bet by substracting amount needed to call (totalBetOwed), resulting in the real bet amount
                    betAmount -= totalBetOwedByAI3;
                    // Clears bet owed by player
                    ai3.setBet(totalBetOwedByAI3); // Update player bet variable 
                    ai3.setCurrency(ai3.getCurrency() - totalBetOwedByAI3); // Update player currency 
                    pot += totalBetOwedByAI3; // Update pot
                    totalBetOwedByAI3 -= totalBetOwedByAI3; // Update player total bet owed varaible
                    break;

                case 3: // FOLD
                    ai3.active = false;
                    ai3.fold = true;
                    break;
            }
        }

        System.out.println("Total Owed: ");
        System.out.println(totalBetOwedByPlayer);
        System.out.println(totalBetOwedByAI1);
        System.out.println(totalBetOwedByAI2);
        System.out.println(totalBetOwedByAI3);

        universalBetAmountOwed = totalBetOwedByPlayer + totalBetOwedByAI1 + totalBetOwedByAI2 + totalBetOwedByAI3;

        return universalBetAmountOwed;

    }

    /**
     * See if a player folded that round because if they did they should be
     * active for the next round. Reset fold because if you don't it could cause
     * problems
     */
    public void reset() {
        if (p.fold) {
            p.active = true;
            p.fold = false;
        }

        if (ai1.fold) {
            ai1.active = true;
            ai1.fold = false;
        }

        if (ai2.fold) {
            ai2.active = true;
            ai2.fold = false;
        }

        if (ai3.fold) {
            ai3.active = true;
            ai3.fold = false;
        }
    }

    /**
     * Deal the flop to the table Burn one card and turn 3 on the table
     *
     * @param boardCards
     * @param burnCards
     */
    public void flop() {
        // Burn one card
        burnCards.add(pokerDeck.deal());
        // Turn three cards
        boardCards.add(pokerDeck.deal());
        boardCards.add(pokerDeck.deal());
        boardCards.add(pokerDeck.deal());
    }

    /**
     * Deal the turn and the river are the same function because they do the
     * same thing Burn one card and turn 1 on the table
     *
     * @param boardCards
     * @param burnCards
     */
    public void turnAndRiver() {
        // Burn one card
        burnCards.add(pokerDeck.deal());
        // Turn one card
        boardCards.add(pokerDeck.deal());
    }

    /**
     * Combine each players hand with the board cards for comparison
     *
     * @param playerCards
     * @param boardCards
     * @return
     */
    static Vector<Card> mergeHand(Vector<Card> playerCards, Vector<Card> boardCards) {
        Vector<Card> merge = new Vector<Card>();
        merge.addAll(playerCards);
        merge.addAll(boardCards);
        return merge;
    }

    //--------------------------------------------
    //-------MY IMPLEMENTED FUNCTIONS-------------
    //--------------------------------------------
    /**
     * Compare each players hand and determine the winner of that round
     *
     * @param OrigPlayerHand
     * @param OrigAI_1Hand
     * @param OrigAI_2Hand
     * @param OrigAI_3Hand
     * @param boardCards
     * @return
     */
    public int compareHands(Vector<Card> OrigPlayerHand, Vector<Card> OrigAI_1Hand, Vector<Card> OrigAI_2Hand, Vector<Card> OrigAI_3Hand, Vector<Card> boardCards) {

        // Add the board cards to each players hands
        Vector<Card> tempPlayerHand = mergeHand(OrigPlayerHand, boardCards);
        Vector<Card> tempAI_1Hand = mergeHand(OrigAI_1Hand, boardCards);
        Vector<Card> tempAI_2Hand = mergeHand(OrigAI_2Hand, boardCards);
        Vector<Card> tempAI_3Hand = mergeHand(OrigAI_3Hand, boardCards);

        // Declare variables for each player to hold scores
        int playerScore = 0;
        int AI_1Score = 0;
        int AI_2Score = 0;
        int AI_3Score = 0;

        // In case player/AI only has high card, save the value of the card in these variables
        int playerHC = 0;
        int AI_1HC = 0;
        int AI_2HC = 0;
        int AI_3HC = 0;

        // If player = 0, if AI 1 = 1, if AI 2 = 2, if AI 3 = 3
        int gamePlayerThatWon = 0;

        if (p.active) {
            // ANALYSE PLAYER HAND
            if (isARoyalFlush(tempPlayerHand)) {
                playerScore = 10;
            } else if (isAStraightFlush(tempPlayerHand)) {
                playerScore = 9;
            } else if (isAFourOfAKind(tempPlayerHand)) {
                playerScore = 8;
            } else if (isAFullHouse(tempPlayerHand)) {
                playerScore = 7;
            } else if (isAFlush(tempPlayerHand)) {
                playerScore = 6;
            } else if (isAStraight(tempPlayerHand)) {
                playerScore = 5;
            } else if (isAThreeOfAKind(tempPlayerHand)) {
                playerScore = 4;
            } else if (isATwoPair(tempPlayerHand)) {
                playerScore = 3;
            } else if (isAPair(tempPlayerHand)) {
                playerScore = 2;
            } else {
                playerHC = isAHighCard(tempPlayerHand);
                playerScore = 1;
            }
        } else {
            playerHC = 0;
            playerScore = 0;
        }

        if (ai1.active) {
            // ANALYSE AI_1 HAND
            if (isARoyalFlush(tempAI_1Hand)) {
                AI_1Score = 10;
            } else if (isAStraightFlush(tempAI_1Hand)) {
                AI_1Score = 9;
            } else if (isAFourOfAKind(tempAI_1Hand)) {
                AI_1Score = 8;
            } else if (isAFullHouse(tempAI_1Hand)) {
                AI_1Score = 7;
            } else if (isAFlush(tempAI_1Hand)) {
                AI_1Score = 6;
            } else if (isAStraight(tempAI_1Hand)) {
                AI_1Score = 5;
            } else if (isAThreeOfAKind(tempAI_1Hand)) {
                AI_1Score = 4;
            } else if (isATwoPair(tempAI_1Hand)) {
                AI_1Score = 3;
            } else if (isAPair(tempAI_1Hand)) {
                AI_1Score = 2;
            } else {
                AI_1HC = isAHighCard(tempAI_1Hand);
                AI_1Score = 1;
            }
        } else {
            AI_1HC = 0;
            AI_1Score = 0;
        }

        if (ai2.active) {
            // ANALYSE AI_2 HAND
            if (isARoyalFlush(tempAI_2Hand)) {
                AI_2Score = 10;
            } else if (isAStraightFlush(tempAI_2Hand)) {
                AI_2Score = 9;
            } else if (isAFourOfAKind(tempAI_2Hand)) {
                AI_2Score = 8;
            } else if (isAFullHouse(tempAI_2Hand)) {
                AI_2Score = 7;
            } else if (isAFlush(tempAI_2Hand)) {
                AI_2Score = 6;
            } else if (isAStraight(tempAI_2Hand)) {
                AI_2Score = 5;
            } else if (isAThreeOfAKind(tempAI_2Hand)) {
                AI_2Score = 4;
            } else if (isATwoPair(tempAI_2Hand)) {
                AI_2Score = 3;
            } else if (isAPair(tempAI_2Hand)) {
                AI_2Score = 2;
            } else {
                AI_2HC = isAHighCard(tempAI_2Hand);
                AI_2Score = 1;
            }
        } else {
            AI_2HC = 0;
            AI_2Score = 0;
        }

        if (ai3.active) {
            // ANALYSE AI_3 HAND
            if (isARoyalFlush(tempAI_3Hand)) {
                AI_3Score = 10;
            } else if (isAStraightFlush(tempAI_3Hand)) {
                AI_3Score = 9;
            } else if (isAFourOfAKind(tempAI_3Hand)) {
                AI_3Score = 8;
            } else if (isAFullHouse(tempAI_3Hand)) {
                AI_3Score = 7;
            } else if (isAFlush(tempAI_3Hand)) {
                AI_3Score = 6;
            } else if (isAStraight(tempAI_3Hand)) {
                AI_3Score = 5;
            } else if (isAThreeOfAKind(tempAI_3Hand)) {
                AI_3Score = 4;
            } else if (isATwoPair(tempAI_3Hand)) {
                AI_3Score = 3;
            } else if (isAPair(tempAI_3Hand)) {
                AI_3Score = 2;
            } else {
                AI_3HC = isAHighCard(tempAI_3Hand);
                AI_3Score = 1;
            }
        } else {
            AI_3HC = 0;
            AI_3Score = 0;
        }

        // Determine winner based on hand score
        //Set pot = 0 in each case so if it doesn't add high card winner too (changing to else if may have fixed this)
        if ((playerScore > AI_1Score) && (playerScore > AI_2Score) && (playerScore > AI_3Score)) {
            gamePlayerThatWon = 0;
            p.setCurrency(p.getCurrency() + pot);
            pot = 0;
        } else if ((AI_1Score > playerScore) && (AI_1Score > AI_2Score) && (AI_1Score > AI_3Score)) {
            gamePlayerThatWon = 1;
            ai1.setCurrency(ai1.getCurrency() + pot);
            pot = 0;
        } else if ((AI_2Score > playerScore) && (AI_2Score > AI_1Score) && (AI_2Score > AI_3Score)) {
            gamePlayerThatWon = 2;
            ai2.setCurrency(ai2.getCurrency() + pot);
            pot = 0;
        } else if ((AI_3Score > playerScore) && (AI_3Score > AI_2Score) && (AI_3Score > AI_1Score)) {
            gamePlayerThatWon = 3;
            ai3.setCurrency(ai3.getCurrency() + pot);
            pot = 0;
        } // Special case where every player/AI just has a high card, determine winner based on high card
        else if ((playerHC > AI_1HC) && (playerHC > AI_2HC) && (playerHC > AI_3HC)) {
            gamePlayerThatWon = 0;
            p.setCurrency(p.getCurrency() + pot);
            pot = 0;
        } else if ((AI_1HC > playerHC) && (AI_1HC > AI_2HC) && (AI_1HC > AI_3HC)) {
            gamePlayerThatWon = 1;
            ai1.setCurrency(ai1.getCurrency() + pot);
            pot = 0;
        } else if ((AI_2HC > playerHC) && (AI_2HC > AI_1HC) && (AI_2HC > AI_3HC)) {
            gamePlayerThatWon = 2;
            ai2.setCurrency(ai2.getCurrency() + pot);
            pot = 0;
        } else if ((AI_3HC > playerHC) && (AI_3HC > AI_2HC) && (AI_3HC > AI_1HC)) {
            gamePlayerThatWon = 3;
            ai3.setCurrency(ai3.getCurrency() + pot);
            pot = 0;
        }

        return gamePlayerThatWon;

    }

    /**
     * Check for a royal flush
     *
     * @param checkedHand
     * @return
     */
    public boolean isARoyalFlush(Vector<Card> checkedHand) {

        if (isAStraight(checkedHand) && isAFlush(checkedHand)) {
            boolean aceExists = false, kingExists = false, queenExists = false, jackExists = false, tenExists = false;

            for (Card c : checkedHand) {
                String face = c.toString();
                int val = 0;
                val = c.getCardValue();

                switch (val) {
                    case 1:
                        aceExists = true;
                        break;
                    case 13:
                        kingExists = true;
                        break;
                    case 12:
                        queenExists = true;
                        break;
                    case 11:
                        jackExists = true;
                        break;
                    case 10:
                        tenExists = true;
                        break;

                }
            }
            return (aceExists && kingExists && queenExists && jackExists && tenExists);
        } else {
            return false;
        }

    }

    /**
     * Check for a straight flush
     *
     * @param checkedHand
     * @return
     */
    public boolean isAStraightFlush(Vector<Card> checkedHand) {

        if (isAFlush(checkedHand) && isAStraight(checkedHand)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Check for a 4 of a kind
     *
     * @param checkedHand
     * @return
     */
    public boolean isAFourOfAKind(Vector<Card> checkedHand) {

        int cardRepeats = 1;
        boolean isFourOfAKind = false;
        int i = 0;
        int k = i + 1;
        while (i < checkedHand.size() && !isFourOfAKind) {
            cardRepeats = 1;
            while (k < checkedHand.size() && !isFourOfAKind) {
                if (checkedHand.elementAt(i).getCardValue() == checkedHand.elementAt(k).getCardValue()) {
                    cardRepeats++;
                    if (cardRepeats == 4) {
                        isFourOfAKind = true;
                    }
                }
                k++;
            }
            i++;
        }
        return isFourOfAKind;
    }

    /**
     * Check for a full house
     *
     * @param checkedHand
     * @return
     */
    public boolean isAFullHouse(Vector<Card> checkedHand) {

        // Sorts current hand by implementing comparable
        Collections.sort(checkedHand);

        int noOfRepeats = 1;
        boolean isThreeOfAKind = false;
        boolean isTwoOfAKind = false;
        for (int i = 0; i < checkedHand.size() - 1; i++) {
            if (checkedHand.elementAt(i).getCardValue() == checkedHand.elementAt(i + 1).getCardValue()) {
                noOfRepeats++;
                if (noOfRepeats == 3) {
                    isThreeOfAKind = true;
                    noOfRepeats = 1;
                } else if (noOfRepeats == 2) {
                    isTwoOfAKind = true;
                    noOfRepeats = 1;
                }
            } else {
                noOfRepeats = 1;
            }
        }
        return (isTwoOfAKind && isThreeOfAKind);

    }

    /**
     * Check for a flush
     *
     * @param checkedHand
     * @return
     */
    public boolean isAFlush(Vector<Card> checkedHand) {

        int noOfClubs = 0;
        int noOfSpades = 0;
        int noOfHearts = 0;
        int noOfDiamonds = 0;

        // Check every card in the hand and count the suits
        for (Card c : checkedHand) {
            switch (c.getCardSuit()) {
                case "Heart":
                    noOfHearts++;
                    break;
                case "Spades":
                    noOfSpades++;
                    break;
                case "Clubs":
                    noOfClubs++;
                    break;
                case "Diamonds":
                    noOfDiamonds++;
                    break;
            }
        }
        // Return true for any suit category that added up to 5 signaling a flush hand
        return (noOfClubs >= 5 || noOfSpades >= 5 || noOfHearts >= 5 || noOfDiamonds >= 5);
    }

    /**
     * Check for a straight
     *
     * @param checkedHand
     * @return
     */
    public boolean isAStraight(Vector<Card> checkedHand) {

        // Sorts current hand by implementing comparable
        Collections.sort(checkedHand);

        // Checks if the cards are in row by comparing elements nex to eachother, if the difference is 1 then it means they're in order
        int noOfCardsInARow = 0;
        int pos = 0;
        boolean isAStraight = false;
        while (pos < checkedHand.size() - 1 && !isAStraight) {
            if (checkedHand.elementAt(pos + 1).getCardValue() - checkedHand.elementAt(pos).getCardValue() == 1) {
                noOfCardsInARow++;
                if (noOfCardsInARow == 4) {
                    isAStraight = true;
                } else {
                    pos++;
                }
            } else {
                noOfCardsInARow = 0;
                pos++;
            }
        }
        return isAStraight;

    }

    /**
     * Check for 3 of a kind
     *
     * @param checkedHand
     * @return
     */
    public boolean isAThreeOfAKind(Vector<Card> checkedHand) {

        int cardRepeats = 1;
        boolean isThreeOfAKind = false;
        int i = 0;
        int k = i + 1;
        while (i < checkedHand.size() && !isThreeOfAKind) {
            cardRepeats = 1;
            while (k < checkedHand.size() && !isThreeOfAKind) {
                if (checkedHand.elementAt(i).getCardValue() == checkedHand.elementAt(k).getCardValue()) {
                    cardRepeats++;
                    if (cardRepeats == 3) {
                        isThreeOfAKind = true;
                    }
                }
                k++;
            }
            i++;
        }
        return isThreeOfAKind;
    }

    /**
     * Check for two pair
     *
     * @param checkedHand
     * @return
     */
    public boolean isATwoPair(Vector<Card> checkedHand) {

        int cardRepeats = 1;
        int noOfCardRepeats = 0;
        boolean isTwoPair = false;
        int i = 0;
        int k = i + 1;
        while (i < checkedHand.size() && !isTwoPair) {
            cardRepeats = 1;
            while (k < checkedHand.size() && !isTwoPair) {
                if (checkedHand.elementAt(i).getCardValue() == checkedHand.elementAt(k).getCardValue()) {
                    cardRepeats++;
                    if (cardRepeats == 2) {
                        cardRepeats = 1;
                        noOfCardRepeats++;
                        if (noOfCardRepeats == 2) {
                            isTwoPair = true;

                        }
                    }

                }
                k++;
            }
            i++;
        }
        return isTwoPair;
    }

    /**
     * Check for one pair
     *
     * @param checkedHand
     * @return
     */
    public boolean isAPair(Vector<Card> checkedHand) {

        int cardRepeats = 1;
        boolean isPair = false;
        int i = 0;
        int k = i + 1;
        while (i < checkedHand.size() && !isPair) {
            cardRepeats = 1;
            while (k < checkedHand.size() && !isPair) {
                if (checkedHand.elementAt(i).getCardValue() == checkedHand.elementAt(k).getCardValue()) {
                    cardRepeats++;
                    if (cardRepeats == 2) {
                        isPair = true;
                    }
                }
                k++;
            }
            i++;
        }
        return isPair;
    }

    /**
     * Check for a highest card in the hand
     *
     * @param checkedHand
     * @return
     */
    public int isAHighCard(Vector<Card> checkedHand) {

        // Sorts current hand by implementing comparable
        Collections.sort(checkedHand);
        return checkedHand.elementAt(4).getCardValue();

    }

    public void terminateGameSession(Vector<Card> checkedHand) {

    }

    public static void main(String[] args) throws IOException {

//        // Set the pot to 200, 50 placed from the player and 150 from the AIs
//        pot = 200;
        System.out.println("Player placed buy in amount");
//        /*
//                Start by giving the player and AI the first 5 cards
//         */
//        // Give player first card
//        playerHand.add(pokerDeck.deal());
//        // Give player second card
//        playerHand.add(pokerDeck.deal());
//        // Give player third card 
//        playerHand.add(pokerDeck.deal());
//        // Give player fourth card
//        playerHand.add(pokerDeck.deal());
//        // Give player fifth card
//        playerHand.add(pokerDeck.deal());
//
//        pokerDeck.shuffle();
//
//        // Give player first card
//        AI_1Hand.add(pokerDeck.deal());
//        // Give player second card
//        AI_1Hand.add(pokerDeck.deal());
//        // Give player third card 
//        AI_1Hand.add(pokerDeck.deal());
//        // Give player fourth card
//        AI_1Hand.add(pokerDeck.deal());
//        // Give player fifth card
//        AI_1Hand.add(pokerDeck.deal());
//
//        pokerDeck.shuffle();
//
//        // Give player first card
//        AI_2Hand.add(pokerDeck.deal());
//        // Give player second card
//        AI_2Hand.add(pokerDeck.deal());
//        // Give player third card 
//        AI_2Hand.add(pokerDeck.deal());
//        // Give player fourth card
//        AI_2Hand.add(pokerDeck.deal());
//        // Give player fifth card
//        AI_2Hand.add(pokerDeck.deal());
//
//        pokerDeck.shuffle();
//
//        // Give player first card
//        AI_3Hand.add(pokerDeck.deal());
//        // Give player second card
//        AI_3Hand.add(pokerDeck.deal());
//        // Give player third card 
//        AI_3Hand.add(pokerDeck.deal());
//        // Give player fourth card
//        AI_3Hand.add(pokerDeck.deal());
//        // Give player fifth card
//        AI_3Hand.add(pokerDeck.deal());

//            System.out.println("Player current hand: ");
//            for (int i = 0; i < playerHand.size(); i++) {
//                System.out.println(playerHand.elementAt(i) + ",");
//            }
//
//            System.out.println("AI_1 current hand: ");
//            for (int i = 0; i < AI_1Hand.size(); i++) {
//                System.out.println(AI_1Hand.elementAt(i) + ",");
//            }
//
//            System.out.println("AI_2 current hand: ");
//            for (int i = 0; i < AI_2Hand.size(); i++) {
//                System.out.println(AI_2Hand.elementAt(i) + ",");
//            }
//
//            System.out.println("AI_3 current hand: ");
//            for (int i = 0; i < AI_3Hand.size(); i++) {
//                System.out.println(AI_3Hand.elementAt(i) + ",");
//            }
//            /*
//            FIRST ROUND BETTING STARTS
//                PLAYERS CHOICES IN ROUND
//                Can be CALL(Check) = where player needs to meet other player's bet to continue
//                Can be FOLD = where the player terminates the game session
//                Can be BET(Raise) = where the player sets a new bet for all other to call or raise 
//             */
//            do {
//                roundNumber = 1;
//
//                // User places bet, calls, or folds
//                System.out.println("First round of betting starting");
//                System.out.println("Do you wish to bet, call, or fold? : 1 or 2 or 3");
//                // Get input (choice) from player
//                playerMoveChoice = scnr.nextInt();
//
//                switch (playerMoveChoice) {
//
//                    case 1: // BET
//                        // Get bet amount input
//                        int betAmount = scnr.nextInt();
//
//                        // Check if player needs to clear bets from any other player first
//                        if (totalBetOwedByPlayer != 0) {
//                            // Updates total bet by substracting amount needed to call (totalBetOwed), resulting in the real bet amount
//                            betAmount -= totalBetOwedByPlayer;
//                            // Clears bet owed by player
//                            p.setBet(totalBetOwedByPlayer); // Update player bet variable 
//                            p.setCurrency(p.getCurrency() - totalBetOwedByPlayer); // Update player currency 
//                            pot += totalBetOwedByPlayer; // Update pot
//                            totalBetOwedByPlayer -= totalBetOwedByPlayer; // Update player total bet owed varaible
//                        }
//
//                        p.setBet(betAmount); // Update player bet amount
//                        p.setCurrency(p.getCurrency() - betAmount); // Update player currency 
//                        pot += betAmount; // Add bet amount to pot
//                        // Broadcast bet amount to all other players
//                        totalBetOwedByAI1 += betAmount;
//                        totalBetOwedByAI2 += betAmount;
//                        totalBetOwedByAI3 += betAmount;
//                        break;
//
//                    case 2: // CALL
//                        betAmount = totalBetOwedByPlayer;
//                        // Updates total bet by substracting amount needed to call (totalBetOwed), resulting in the real bet amount
//                        betAmount -= totalBetOwedByPlayer;
//                        // Clears bet owed by player
//                        p.setBet(totalBetOwedByPlayer); // Update player bet variable 
//                        p.setCurrency(p.getCurrency() - totalBetOwedByPlayer); // Update player currency 
//                        pot += totalBetOwedByPlayer; // Update pot
//                        totalBetOwedByPlayer -= totalBetOwedByPlayer; // Update player total bet owed varaible
//                        break;
//
//                    case 3: // FOLD
//                        System.exit(0);
//                        break;
//                }
//
//                // int betAmount = rand.nextInt(900) + lastBet;
//                // AI_1 player bet, or calls 
//                // Get random input (choice) from AI player
//                AI_MoveChoice = rand.nextInt(2) + 1;
//
//                System.out.println("AI Move #:" + AI_MoveChoice);
//
//                switch (AI_MoveChoice) {
//
//                    case 1: // BET
//                       // Get bet amount input
//                        int betAmount = rand.nextInt(900) + totalBetOwedByAI2;
//                        
//                        // Force CALL action if less than 50 in currency 
//                        if (ai1.getCurrency() < 50) {
//                        betAmount = totalBetOwedByAI1;
//                        // Updates total bet by substracting amount needed to call (totalBetOwed), resulting in the real bet amount
//                        betAmount -= totalBetOwedByAI1;
//                        // Clears bet owed by player
//                        ai1.setBet(totalBetOwedByAI1); // Update player bet variable 
//                        ai1.setCurrency(ai1.getCurrency() - totalBetOwedByAI1); // Update player currency 
//                        pot += totalBetOwedByAI1; // Update pot
//                        totalBetOwedByAI1 -= totalBetOwedByAI1; // Update player total bet owed varaible
//                        break;
//                        }
//
//                        // Check if player needs to clear bets from any other player first
//                        if (totalBetOwedByAI1 != 0) {
//                            // Updates total bet by substracting amount needed to call (totalBetOwed), resulting in the real bet amount
//                            betAmount -= totalBetOwedByAI1;
//                            // Clears bet owed by player
//                            ai1.setBet(totalBetOwedByAI1); // Update player bet variable 
//                            ai1.setCurrency(ai1.getCurrency() - totalBetOwedByAI1); // Update player currency 
//                            pot += totalBetOwedByAI1; // Update pot
//                            totalBetOwedByAI1 -= totalBetOwedByAI1; // Update player total bet owed varaible
//                        }
//
//                        ai1.setBet(betAmount); // Update player bet amount
//                        ai1.setCurrency(ai1.getCurrency() - betAmount); // Update player currency 
//                        pot += betAmount; // Add bet amount to pot
//                        System.out.println("AI1 Bet Amount: " + betAmount);
//                        // Broadcast bet amount to all other players
//                        totalBetOwedByPlayer += betAmount;
//                        totalBetOwedByAI2 += betAmount;
//                        totalBetOwedByAI3 += betAmount;
//                        break;
//
//                    case 2: // CALL
//                        betAmount = totalBetOwedByAI1;
//                        // Updates total bet by substracting amount needed to call (totalBetOwed), resulting in the real bet amount
//                        betAmount -= totalBetOwedByAI1;
//                        // Clears bet owed by player
//                        ai1.setBet(totalBetOwedByPlayer); // Update player bet variable 
//                        ai1.setCurrency(ai1.getCurrency() - totalBetOwedByAI1); // Update player currency 
//                        pot += totalBetOwedByAI1; // Update pot
//                        totalBetOwedByAI1 -= totalBetOwedByAI1; // Update player total bet owed varaible
//                        break;
//                }
//
//                // AI_2 player bet, or calls 
//                // Get random input (choice) from AI player
//                AI_MoveChoice = rand.nextInt(2) + 1;
//
//                System.out.println("AI Move #:" + AI_MoveChoice);
//
//                switch (AI_MoveChoice) {
//
//                    case 1: // BET  
//                        // Get bet amount input
//                        int betAmount = rand.nextInt(900) + totalBetOwedByAI2;
//                        
//                        // Force CALL action if less than 50 in currency 
//                        if (ai2.getCurrency() < 50) {
//                        betAmount = totalBetOwedByAI2;
//                        // Updates total bet by substracting amount needed to call (totalBetOwed), resulting in the real bet amount
//                        betAmount -= totalBetOwedByAI2;
//                        // Clears bet owed by player
//                        ai2.setBet(totalBetOwedByAI2); // Update player bet variable 
//                        ai2.setCurrency(ai2.getCurrency() - totalBetOwedByAI2); // Update player currency 
//                        pot += totalBetOwedByAI2; // Update pot
//                        totalBetOwedByAI2 -= totalBetOwedByAI2; // Update player total bet owed varaible
//                        break;
//                        }
//                        
//                        
//                        // Check if player needs to clear bets from any other player first
//                        if (totalBetOwedByAI2 != 0) {
//                            // Updates total bet by substracting amount needed to call (totalBetOwed), resulting in the real bet amount
//                            betAmount -= totalBetOwedByAI2;
//                            // Clears bet owed by player
//                            ai2.setBet(totalBetOwedByAI2); // Update player bet variable 
//                            ai2.setCurrency(ai2.getCurrency() - totalBetOwedByAI2); // Update player currency 
//                            pot += totalBetOwedByAI2; // Update pot
//                            totalBetOwedByAI2 -= totalBetOwedByAI2; // Update player total bet owed varaible
//                        }
//
//                        ai2.setBet(betAmount); // Update player bet amount
//                        ai2.setCurrency(ai2.getCurrency() - betAmount); // Update player currency 
//                        pot += betAmount; // Add bet amount to pot
//                        System.out.println("AI2 Bet Amount: " + betAmount);
//                        // Broadcast bet amount to all other players
//                        totalBetOwedByPlayer += betAmount;
//                        totalBetOwedByAI1 += betAmount;
//                        totalBetOwedByAI3 += betAmount;
//                        break;
//
//                    case 2: // CALL
//                        betAmount = totalBetOwedByAI2;
//                        // Updates total bet by substracting amount needed to call (totalBetOwed), resulting in the real bet amount
//                        betAmount -= totalBetOwedByAI2;
//                        // Clears bet owed by player
//                        ai2.setBet(totalBetOwedByAI2); // Update player bet variable 
//                        ai2.setCurrency(ai2.getCurrency() - totalBetOwedByAI2); // Update player currency 
//                        pot += totalBetOwedByAI2; // Update pot
//                        totalBetOwedByAI2 -= totalBetOwedByAI2; // Update player total bet owed varaible
//                        break;
//                }
//
//                /// AI_3 player bet, or calls 
//                // Get random input (choice) from AI player
//                AI_MoveChoice = rand.nextInt(2) + 1;
//
//                System.out.println("AI Move #:" + AI_MoveChoice);
//
//                switch (AI_MoveChoice) {
//
//                    case 1: // BET
//                        
//                        // Get bet amount input
//                        int betAmount = rand.nextInt(900) + totalBetOwedByAI3;
//                        
//                        // Force CALL action if less than 50 in currency 
//                        if (ai3.getCurrency() < 50) {
//                        betAmount = totalBetOwedByAI3;
//                        // Updates total bet by substracting amount needed to call (totalBetOwed), resulting in the real bet amount
//                        betAmount -= totalBetOwedByAI3;
//                        // Clears bet owed by player
//                        ai3.setBet(totalBetOwedByAI3); // Update player bet variable 
//                        ai3.setCurrency(ai3.getCurrency() - totalBetOwedByAI3); // Update player currency 
//                        pot += totalBetOwedByAI3; // Update pot
//                        totalBetOwedByAI3 -= totalBetOwedByAI3; // Update player total bet owed varaible
//                        break;
//                        }
//                        
//
//                        // Check if player needs to clear bets from any other player first
//                        if (totalBetOwedByAI3 != 0) {
//                            // Updates total bet by substracting amount needed to call (totalBetOwed), resulting in the real bet amount
//                            betAmount -= totalBetOwedByAI3;
//                            // Clears bet owed by player
//                            ai3.setBet(totalBetOwedByAI3); // Update player bet variable 
//                            ai3.setCurrency(ai3.getCurrency() - totalBetOwedByAI3); // Update player currency 
//                            pot += totalBetOwedByAI3; // Update pot
//                            totalBetOwedByAI3 -= totalBetOwedByAI3; // Update player total bet owed varaible
//                        }
//
//                        ai3.setBet(betAmount); // Update player bet amount
//                        ai3.setCurrency(ai3.getCurrency() - betAmount); // Update player currency 
//                        pot += betAmount; // Add bet amount to pot
//                        System.out.println("AI3 Bet Amount: " + betAmount);
//                        // Broadcast bet amount to all other players
//                        totalBetOwedByPlayer += betAmount;
//                        totalBetOwedByAI1 += betAmount;
//                        totalBetOwedByAI2 += betAmount;
//                        break;
//
//                    case 2: // CALL
//                        betAmount = totalBetOwedByAI3;
//                        // Updates total bet by substracting amount needed to call (totalBetOwed), resulting in the real bet amount
//                        betAmount -= totalBetOwedByAI3;
//                        // Clears bet owed by player
//                        ai3.setBet(totalBetOwedByAI3); // Update player bet variable 
//                        ai3.setCurrency(ai3.getCurrency() - totalBetOwedByAI3); // Update player currency 
//                        pot += totalBetOwedByAI3; // Update pot
//                        totalBetOwedByAI3 -= totalBetOwedByAI3; // Update player total bet owed varaible
//                        break;
//                }
//
//                System.out.println(totalBetOwedByPlayer);
//                System.out.println(totalBetOwedByAI1);
//                System.out.println(totalBetOwedByAI2);
//                System.out.println(totalBetOwedByAI3);
//
//                //***** If player bet, then ask for call or bet from AI, if they bet, call user, and so on until no one else raises
//            } while (totalBetOwedByPlayer != 0 || totalBetOwedByAI1 != 0 || totalBetOwedByAI2 != 0 || totalBetOwedByAI3 != 0);
//            // When betting round 1 is finished, ask players if they want to replace their cards
//            // Player is able to replace three cards 
//            System.out.println("Do you wish to replace the first 3 cards in your hand? Yes 0r No (1 or 2)");
//            // Get input (choice) from player
//            replaceCardsChoice = scnr.nextInt();
//
//            if (replaceCardsChoice == 1) {
//                // Start replace card process for the player
//
//                // Give player first card
//                playerHand.set(0, pokerDeck.deal());
//                // Give player second card
//                playerHand.set(1, pokerDeck.deal());
//                // Give player third card 
//                playerHand.set(2, pokerDeck.deal());
//            }
//
//            System.out.println("Player current hand: ");
//            for (int i = 0; i < playerHand.size(); i++) {
//                System.out.println(playerHand.elementAt(i) + ",");
//            }
//            /*
//            SECOND ROUND BETTING STARTS
//                PLAYERS CHOICES IN ROUND
//                Can be CALL(Check) = where player needs to meet other player's bet to continue
//                Can be FOLD = where the player terminates the game session
//                Can be BET(Raise) = where the player sets a new bet for all other to call or raise 
//             */
//            do {
//
//                // User places bet, calls, or folds
//                System.out.println("Second round of betting starting");
//                System.out.println("Do you wish to bet, call, or fold? : 1 or 2 or 3");
//                // Get input (choice) from player
//                playerMoveChoice = scnr.nextInt();
//
//                switch (playerMoveChoice) {
//
//                    case 1: // BET
//                        // Get bet amount input
//                        int betAmount = scnr.nextInt();
//
//                        // Check if player needs to clear bets from any other player first
//                        if (totalBetOwedByPlayer != 0) {
//                            // Updates total bet by substracting amount needed to call (totalBetOwed), resulting in the real bet amount
//                            betAmount -= totalBetOwedByPlayer;
//                            // Clears bet owed by player
//                            p.setBet(totalBetOwedByPlayer); // Update player bet variable 
//                            p.setCurrency(p.getCurrency() - totalBetOwedByPlayer); // Update player currency 
//                            pot += totalBetOwedByPlayer; // Update pot
//                            totalBetOwedByPlayer -= totalBetOwedByPlayer; // Update player total bet owed varaible
//                        }
//
//                        p.setBet(betAmount); // Update player bet amount
//                        p.setCurrency(p.getCurrency() - betAmount); // Update player currency 
//                        pot += betAmount; // Add bet amount to pot
//                        // Broadcast bet amount to all other players
//                        totalBetOwedByAI1 += betAmount;
//                        totalBetOwedByAI2 += betAmount;
//                        totalBetOwedByAI3 += betAmount;
//                        break;
//
//                    case 2: // CALL
//                        betAmount = totalBetOwedByPlayer;
//                        // Updates total bet by substracting amount needed to call (totalBetOwed), resulting in the real bet amount
//                        betAmount -= totalBetOwedByPlayer;
//                        // Clears bet owed by player
//                        p.setBet(totalBetOwedByPlayer); // Update player bet variable 
//                        p.setCurrency(p.getCurrency() - totalBetOwedByPlayer); // Update player currency 
//                        pot += totalBetOwedByPlayer; // Update pot
//                        totalBetOwedByPlayer -= totalBetOwedByPlayer; // Update player total bet owed varaible
//                        break;
//
//                    case 3: // FOLD
//                        System.exit(0);
//                        break;
//                }
//
//                // int betAmount = rand.nextInt(900) + lastBet;
//                // AI_1 player bet, or calls 
//                // Get random input (choice) from AI player
//                AI_MoveChoice = rand.nextInt(2) + 1;
//
//                System.out.println("AI Move #:" + AI_MoveChoice);
//
//                switch (AI_MoveChoice) {
//
//                    case 1: // BET
//                       // Get bet amount input
//                        int betAmount = rand.nextInt(900) + totalBetOwedByAI2;
//                        
//                        // Force CALL action if less than 50 in currency 
//                        if (ai1.getCurrency() < 50) {
//                        betAmount = totalBetOwedByAI1;
//                        // Updates total bet by substracting amount needed to call (totalBetOwed), resulting in the real bet amount
//                        betAmount -= totalBetOwedByAI1;
//                        // Clears bet owed by player
//                        ai1.setBet(totalBetOwedByAI1); // Update player bet variable 
//                        ai1.setCurrency(ai1.getCurrency() - totalBetOwedByAI1); // Update player currency 
//                        pot += totalBetOwedByAI1; // Update pot
//                        totalBetOwedByAI1 -= totalBetOwedByAI1; // Update player total bet owed varaible
//                        break;
//                        }
//
//                        // Check if player needs to clear bets from any other player first
//                        if (totalBetOwedByAI1 != 0) {
//                            // Updates total bet by substracting amount needed to call (totalBetOwed), resulting in the real bet amount
//                            betAmount -= totalBetOwedByAI1;
//                            // Clears bet owed by player
//                            ai1.setBet(totalBetOwedByAI1); // Update player bet variable 
//                            ai1.setCurrency(ai1.getCurrency() - totalBetOwedByAI1); // Update player currency 
//                            pot += totalBetOwedByAI1; // Update pot
//                            totalBetOwedByAI1 -= totalBetOwedByAI1; // Update player total bet owed varaible
//                        }
//
//                        ai1.setBet(betAmount); // Update player bet amount
//                        ai1.setCurrency(ai1.getCurrency() - betAmount); // Update player currency 
//                        pot += betAmount; // Add bet amount to pot
//                        System.out.println("AI1 Bet Amount: " + betAmount);
//                        // Broadcast bet amount to all other players
//                        totalBetOwedByPlayer += betAmount;
//                        totalBetOwedByAI2 += betAmount;
//                        totalBetOwedByAI3 += betAmount;
//                        break;
//
//                    case 2: // CALL
//                        betAmount = totalBetOwedByAI1;
//                        // Updates total bet by substracting amount needed to call (totalBetOwed), resulting in the real bet amount
//                        betAmount -= totalBetOwedByAI1;
//                        // Clears bet owed by player
//                        ai1.setBet(totalBetOwedByPlayer); // Update player bet variable 
//                        ai1.setCurrency(ai1.getCurrency() - totalBetOwedByAI1); // Update player currency 
//                        pot += totalBetOwedByAI1; // Update pot
//                        totalBetOwedByAI1 -= totalBetOwedByAI1; // Update player total bet owed varaible
//                        break;
//                }
//
//                // AI_2 player bet, or calls 
//                // Get random input (choice) from AI player
//                AI_MoveChoice = rand.nextInt(2) + 1;
//
//                System.out.println("AI Move #:" + AI_MoveChoice);
//
//                switch (AI_MoveChoice) {
//
//                    case 1: // BET  
//                        // Get bet amount input
//                        int betAmount = rand.nextInt(900) + totalBetOwedByAI2;
//                        
//                        // Force CALL action if less than 50 in currency 
//                        if (ai2.getCurrency() < 50) {
//                        betAmount = totalBetOwedByAI2;
//                        // Updates total bet by substracting amount needed to call (totalBetOwed), resulting in the real bet amount
//                        betAmount -= totalBetOwedByAI2;
//                        // Clears bet owed by player
//                        ai2.setBet(totalBetOwedByAI2); // Update player bet variable 
//                        ai2.setCurrency(ai2.getCurrency() - totalBetOwedByAI2); // Update player currency 
//                        pot += totalBetOwedByAI2; // Update pot
//                        totalBetOwedByAI2 -= totalBetOwedByAI2; // Update player total bet owed varaible
//                        break;
//                        }
//                        
//                        
//                        // Check if player needs to clear bets from any other player first
//                        if (totalBetOwedByAI2 != 0) {
//                            // Updates total bet by substracting amount needed to call (totalBetOwed), resulting in the real bet amount
//                            betAmount -= totalBetOwedByAI2;
//                            // Clears bet owed by player
//                            ai2.setBet(totalBetOwedByAI2); // Update player bet variable 
//                            ai2.setCurrency(ai2.getCurrency() - totalBetOwedByAI2); // Update player currency 
//                            pot += totalBetOwedByAI2; // Update pot
//                            totalBetOwedByAI2 -= totalBetOwedByAI2; // Update player total bet owed varaible
//                        }
//
//                        ai2.setBet(betAmount); // Update player bet amount
//                        ai2.setCurrency(ai2.getCurrency() - betAmount); // Update player currency 
//                        pot += betAmount; // Add bet amount to pot
//                        System.out.println("AI2 Bet Amount: " + betAmount);
//                        // Broadcast bet amount to all other players
//                        totalBetOwedByPlayer += betAmount;
//                        totalBetOwedByAI1 += betAmount;
//                        totalBetOwedByAI3 += betAmount;
//                        break;
//
//                    case 2: // CALL
//                        betAmount = totalBetOwedByAI2;
//                        // Updates total bet by substracting amount needed to call (totalBetOwed), resulting in the real bet amount
//                        betAmount -= totalBetOwedByAI2;
//                        // Clears bet owed by player
//                        ai2.setBet(totalBetOwedByAI2); // Update player bet variable 
//                        ai2.setCurrency(ai2.getCurrency() - totalBetOwedByAI2); // Update player currency 
//                        pot += totalBetOwedByAI2; // Update pot
//                        totalBetOwedByAI2 -= totalBetOwedByAI2; // Update player total bet owed varaible
//                        break;
//                }
//
//                /// AI_3 player bet, or calls 
//                // Get random input (choice) from AI player
//                AI_MoveChoice = rand.nextInt(2) + 1;
//
//                System.out.println("AI Move #:" + AI_MoveChoice);
//
//                switch (AI_MoveChoice) {
//
//                    case 1: // BET
//                        
//                        // Get bet amount input
//                        int betAmount = rand.nextInt(900) + totalBetOwedByAI3;
//                        
//                        // Force CALL action if less than 50 in currency 
//                        if (ai3.getCurrency() < 50) {
//                        betAmount = totalBetOwedByAI3;
//                        // Updates total bet by substracting amount needed to call (totalBetOwed), resulting in the real bet amount
//                        betAmount -= totalBetOwedByAI3;
//                        // Clears bet owed by player
//                        ai3.setBet(totalBetOwedByAI3); // Update player bet variable 
//                        ai3.setCurrency(ai3.getCurrency() - totalBetOwedByAI3); // Update player currency 
//                        pot += totalBetOwedByAI3; // Update pot
//                        totalBetOwedByAI3 -= totalBetOwedByAI3; // Update player total bet owed varaible
//                        break;
//                        }
//                        
//
//                        // Check if player needs to clear bets from any other player first
//                        if (totalBetOwedByAI3 != 0) {
//                            // Updates total bet by substracting amount needed to call (totalBetOwed), resulting in the real bet amount
//                            betAmount -= totalBetOwedByAI3;
//                            // Clears bet owed by player
//                            ai3.setBet(totalBetOwedByAI3); // Update player bet variable 
//                            ai3.setCurrency(ai3.getCurrency() - totalBetOwedByAI3); // Update player currency 
//                            pot += totalBetOwedByAI3; // Update pot
//                            totalBetOwedByAI3 -= totalBetOwedByAI3; // Update player total bet owed varaible
//                        }
//
//                        ai3.setBet(betAmount); // Update player bet amount
//                        ai3.setCurrency(ai3.getCurrency() - betAmount); // Update player currency 
//                        pot += betAmount; // Add bet amount to pot
//                        System.out.println("AI3 Bet Amount: " + betAmount);
//                        // Broadcast bet amount to all other players
//                        totalBetOwedByPlayer += betAmount;
//                        totalBetOwedByAI1 += betAmount;
//                        totalBetOwedByAI2 += betAmount;
//                        break;
//
//                    case 2: // CALL
//                        betAmount = totalBetOwedByAI3;
//                        // Updates total bet by substracting amount needed to call (totalBetOwed), resulting in the real bet amount
//                        betAmount -= totalBetOwedByAI3;
//                        // Clears bet owed by player
//                        ai3.setBet(totalBetOwedByAI3); // Update player bet variable 
//                        ai3.setCurrency(ai3.getCurrency() - totalBetOwedByAI3); // Update player currency 
//                        pot += totalBetOwedByAI3; // Update pot
//                        totalBetOwedByAI3 -= totalBetOwedByAI3; // Update player total bet owed varaible
//                        break;
//                }
//
//                //***** If player bet, then ask for call or bet from AI, if they bet, call user, and so on until no one else raises
//            } while (totalBetOwedByPlayer != 0 || totalBetOwedByAI1 != 0 || totalBetOwedByAI2 != 0 || totalBetOwedByAI3 != 0);
//            // Call compareHands function and determine the winner
//            int playerWon;
//            playerWon = FCPokerEngine.compareHands(playerHand, AI_1Hand, AI_2Hand, AI_3Hand);
//            if (playerWon == 0) {
//                System.out.println("User player won the game.");
//                System.out.println("Player current hand: ");
//                for (int i = 0; i < playerHand.size(); i++) {
//                    System.out.println(playerHand.elementAt(i) + ",");
//                }
//
//            } else if (playerWon == 1) {
//                System.out.println("AI 1 player won the game.");
//                System.out.println("Player current hand: ");
//                for (int i = 0; i < AI_1Hand.size(); i++) {
//                    System.out.println(playerHand.elementAt(i) + ",");
//                }
//
//            } else if (playerWon == 2) {
//                System.out.println("AI 2 player won the game.");
//                System.out.println("Player current hand: ");
//                for (int i = 0; i < AI_2Hand.size(); i++) {
//                    System.out.println(playerHand.elementAt(i) + ",");
//                }
//
//            } else if (playerWon == 3) {
//                System.out.println("AI 3 player won the game.");
//                System.out.println("Player current hand: ");
//                for (int i = 0; i < AI_3Hand.size(); i++) {
//                    System.out.println(playerHand.elementAt(i) + ",");
//                }
//
//            }
    }

}

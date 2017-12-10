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
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
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
    public PokerDeck tempDeck;
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
    //int lastBet = 0;
    public int pot;
    int totalBetOwedByPlayer = 0;
    int totalBetOwedByAI1 = 0;
    int totalBetOwedByAI2 = 0;
    int totalBetOwedByAI3 = 0;
    int universalBetAmountOwed = 0;
    int tempCurr1;
    int tempCurr2;
    int tempCurr3;
    int tempCurr4;
    public int pOwed = 0;
    int ai1Owed = 0;
    int ai2Owed = 0;
    int ai3Owed = 0;
    public String playerMove;
    public String ai1Move;
    public String ai2Move;
    public String ai3Move;
    public String winningHand;

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
        tempDeck = pokerDeck;
        //deal();

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
        //Create a new pokerDeck and shuffle it
        //Create the new pokerDeck in here because the game needs a new one each round
        pokerDeck = tempDeck;
        pokerDeck.shuffle();

        //Clear Hand 
        playerHand.clear();
        // Give player first card
        playerHand.add(pokerDeck.deal());
        // Give player second card
        playerHand.add(pokerDeck.deal());

        //Clear Hand 
        AI_1Hand.clear();
        // Give player first card
        AI_1Hand.add(pokerDeck.deal());
        // Give player second card
        AI_1Hand.add(pokerDeck.deal());

        //Clear Hand 
        AI_2Hand.clear();
        // Give player first card
        AI_2Hand.add(pokerDeck.deal());
        // Give player second card
        AI_2Hand.add(pokerDeck.deal());

        //Clear Hand 
        AI_3Hand.clear();
        // Give player first card
        AI_3Hand.add(pokerDeck.deal());
        // Give player second card
        AI_3Hand.add(pokerDeck.deal());

        //Clear the boardCards and burnCards
        boardCards.clear();
        burnCards.clear();
        //Deal the table cards 
        flop();
        turnAndRiver();
        turnAndRiver();

        System.out.println("AI 1 HAND: ");
        for (int i = 0; i < AI_1Hand.size(); i++) {
            System.out.println(AI_1Hand.elementAt(i).getCardSuit() + " of " + AI_1Hand.elementAt(i).getCardFace());
        }
        System.out.println("AI 2 HAND: ");
        for (int i = 0; i < AI_2Hand.size(); i++) {
            System.out.println(AI_2Hand.elementAt(i).getCardSuit() + " of " + AI_2Hand.elementAt(i).getCardFace());
        }
        System.out.println("AI 3 HAND: ");
        for (int i = 0; i < AI_3Hand.size(); i++) {
            System.out.println(AI_3Hand.elementAt(i).getCardSuit() + " of " + AI_3Hand.elementAt(i).getCardFace());
        }
    }

    /**
     * Function: CALL/CHECK add players owed amount to the pot subtract the owed
     * amount from their currency return 0 which will be set to their new owed
     * amount
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
     * Function: NEW BET Set a new bet and add that amount to the pot before
     * calling this function call() should be called after this function all
     * owed amounts should be added to
     *
     * @param player
     * @param betAmt
     * @return
     */
    public int newBet(AIPlayer player, int betAmt) {
        do {
            // Get bet amount input 
            // If they have a lot of currency left divide by 4 to slow game down
            if (player.getCurrency() > 100) {
                betAmt = rand.nextInt(player.getCurrency() / 4);
            } else {
                betAmt = rand.nextInt(player.getCurrency());
            }
        } while ((betAmt > p.getCurrency() && p.active) || (betAmt > ai1.getCurrency() && ai1.active) || (betAmt > ai2.getCurrency() && ai2.active) || (betAmt > ai3.getCurrency() && ai3.active));
        pot += betAmt;
        player.setCurrency(player.getCurrency() - betAmt);
        player.didBet = true;
        return betAmt;
    }

    /**
     * Function: FOLD Player folds set them to inactive for the round use fold
     * value to reactivate folded players at the end of the hand
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

    /**
     * After the user has selected a move start the betting until it gets back
     * to the user. Then they will pick another move in the frame and the round
     * will continue.
     *
     * @param playerMoveChoice
     * @param betAmt
     * @return universalAmountOwed because if it is 0 then everyone is satisfied
     * and squared away with their bets so the round of betting is over
     */
    public int startRound(int playerMoveChoice, int betAmt) {
        //do {
        //tempCurr1 = p.getCurrency();
        //tempCurr2 = ai1.getCurrency();
        //tempCurr3 = ai2.getCurrency();
        //tempCurr4 = ai3.getCurrency();

        //System.out.println("AI 2 Active: " + ai2.active);
        //System.out.println("Player Owes: " + pOwed);
        //System.out.println("AI1 Curr: " + ai1.getCurrency());
        //if the player is in the hand
        if (p.active) {
            //System.out.println("Players choice: " + playerMoveChoice);
            //while (playerMoveChoice == 0) {
            //This endless polling loop might break everything
            //startRound(playerMoveChoice, betAmt);
            //}
            switch (playerMoveChoice) {
                //BET: Don't call bet function because that generate a random number
                case 1:
                    //Determine if ai is betting or raising someone elses bet
                    if (pOwed == 0) {
                        playerMove = "User: Bets " + betAmt;
                    } else {
                        playerMove = "User: Raises " + (betAmt - pOwed);
                    }
                    pOwed = call(p, pOwed);
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
                //CALL
                case 2:
                    if (pOwed == 0) {
                        playerMove = "User: Checks";
                    } else {
                        playerMove = "User: Calls " + pOwed;
                    }
                    pOwed = call(p, pOwed);
                    break;
                //FOLD
                case 3:
                    playerMove = "User: Folds";
                    fold(p);
                    pOwed = 0;
                    break;
            }

        }

        //if the ai1 is in the hand
        if (ai1.active) {
            AI_MoveChoice = rand.nextInt(3) + 1;
            //If the player already bet that round but they want to still play force them to call
            if (AI_MoveChoice == 1 && ai1.didBet == true) {
                AI_MoveChoice = 2;
            }
            //If there is no bet on the table don't let the ai fold make it so they at least check
            if (AI_MoveChoice == 3 && ai1Owed == 0) {
                AI_MoveChoice = 2;
            }
            //If everyone else folds don't let this player fold
            if (p.fold && ai2.fold && ai3.fold) {
                AI_MoveChoice = 2;
            }
            switch (AI_MoveChoice) {

                //BET: Don't call bet function because that generate a random number
                case 1:
                    //Determine if ai is betting or raising someone elses bet
                    if (ai1Owed == 0) {
                        ai1Move = "AI1: Bets ";
                    } else {
                        ai1Move = "AI1: Raises ";
                    }
                    //Clear what the AI owes first
                    ai1Owed = call(ai1, ai1Owed);
                    betAmt = newBet(ai1, betAmt);
                    //System.out.println("AI1 Bet Amount: " + betAmt);
                    ai1Move += betAmt;
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
                    break;
                //CALL
                case 2:
                    //System.out.println("AI1 Calling: " + ai1Owed);
                    if (ai1Owed == 0) {
                        ai1Move = "AI1: Checks";
                    } else {
                        ai1Move = "AI1: Calls " + ai1Owed;
                    }
                    ai1Owed = call(ai1, ai1Owed);
                    break;
                //FOLD
                case 3:
                    //System.out.println("AI1 Folding");
                    ai1Move = "AI1: Folds";

                    fold(ai1);
                    ai1Owed = 0;
                    break;
            }
        }

        //if the ai2 is in the hand
        if (ai2.active) {
            AI_MoveChoice = rand.nextInt(3) + 1;
            //If the player already bet that round but they want to still play force them to call
            if (AI_MoveChoice == 1 && ai2.didBet == true) {
                AI_MoveChoice = 2;
            }
            //If there is no bet on the table don't let the ai fold make it so they at least check
            if (AI_MoveChoice == 3 && ai2Owed == 0) {
                AI_MoveChoice = 2;
            }
            //If everyone else folds don't let this player fold
            if (p.fold && ai1.fold && ai3.fold) {
                AI_MoveChoice = 2;
            }
            switch (AI_MoveChoice) {

                //BET: Don't call bet function because that generate a random number
                case 1:
                    //Determine if ai is betting or raising someone elses bet
                    if (ai2Owed == 0) {
                        ai2Move = "AI2: Bets ";
                    } else {
                        ai2Move = "AI2: Raises ";
                    }
                    //Clear what the AI owes first
                    ai2Owed = call(ai2, ai2Owed);
                    betAmt = newBet(ai2, betAmt);
                    ai2Move += betAmt;
                    //System.out.println("AI2 Bet Amount: " + betAmt);
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
                    break;
                ///CALL
                case 2:
                    if (ai2Owed == 0) {
                        ai2Move = "AI2: Checks";
                    } else {
                        ai2Move = "AI2: Calls " + ai2Owed;
                    }
                    ai2Owed = call(ai2, ai2Owed);
                    //System.out.println("AI2 Calling: " + ai2Owed);
                    break;
                //FOLD
                case 3:
                    ai2Move = "AI2: Folds";
                    fold(ai2);
                    //System.out.println("AI2 Folding");
                    ai2Owed = 0;
                    break;
            }
        }

        //if the ai3 is in the hand
        if (ai3.active) {
            AI_MoveChoice = rand.nextInt(3) + 1;
            //If the player already bet that round but they want to still play force them to call
            if (AI_MoveChoice == 1 && ai3.didBet == true) {
                AI_MoveChoice = 2;
            }
            //If there is no bet on the table don't let the ai fold make it so they at least check
            if (AI_MoveChoice == 3 && ai3Owed == 0) {
                AI_MoveChoice = 2;
            }
            //If everyone else folds don't let this player fold
            if (p.fold && ai2.fold && ai1.fold) {
                AI_MoveChoice = 2;
            }
            switch (AI_MoveChoice) {

                //BET: Don't call bet function because that generate a random number
                case 1:
                    //Clear what the AI owes first
                    //Determine if ai is betting or raising someone elses bet
                    if (ai3Owed == 0) {
                        ai3Move = "AI3: Bets ";
                    } else {
                        ai3Move = "AI3: Raises ";
                    }
                    ai3Owed = call(ai3, ai3Owed);
                    betAmt = newBet(ai3, betAmt);
                    ai3Move += betAmt;
                    //System.out.println("AI3 Bet Amount: " + betAmt);
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
                    break;
                //CALL
                case 2:
                    if (ai3Owed == 0) {
                        ai3Move = "AI3: Checks";
                    } else {
                        ai3Move = "AI3: Calls " + ai3Owed;
                    }
                    ai3Owed = call(ai3, ai3Owed);
                    //System.out.println("AI3 Bet Amount: " + ai3Owed);
                    break;
                //FOLD
                case 3:
                    ai3Move = "AI3: Folds";
                    fold(ai3);
                    //System.out.println("AI3 Folding");
                    ai3Owed = 0;
                    break;
            }
        }
        universalBetAmountOwed = pOwed + ai1Owed + ai2Owed + ai3Owed;
        System.out.println("Player Owes: " + pOwed);

        return universalBetAmountOwed;
        //} while (pOwed != 0 && ai1Owed != 0 && ai2Owed != 0 && ai3Owed != 0);
    }

    /**
     * Function: RESET See if a player folded that round because if they did
     * they should be active for the next round. Reset fold because if you don't
     * it could cause problems
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
     * Function: FLOP Deal the flop to the table Burn one card and turn 3 on the
     * table
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
     * Function:TURN AND RIVER Deal the turn and the river are the same function
     * because they do the same thing Burn one card and turn 1 on the table
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
     * Function: MERGE HAND Combine each players hand with the board cards for
     * comparison
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
     * Function: COMPARE HANDS Compare each players hand and determine the
     * winner of that round
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

        System.out.println("Players Compare Hand: ");
        for (int i = 0; i < tempPlayerHand.size(); i++) {
            System.out.println(tempPlayerHand.elementAt(i).getCardSuit() + " of " + tempPlayerHand.elementAt(i).getCardFace());
        }
        
        System.out.println("AI1 Compare Hand: ");
        for (int i = 0; i < tempAI_1Hand.size(); i++) {
            System.out.println(tempAI_1Hand.elementAt(i).getCardSuit() + " of " + tempAI_1Hand.elementAt(i).getCardFace());
        }
        
        System.out.println("AI1 Compare Hand: ");
        for (int i = 0; i < tempAI_2Hand.size(); i++) {
            System.out.println(tempAI_2Hand.elementAt(i).getCardSuit() + " of " + tempAI_2Hand.elementAt(i).getCardFace());
        }
        
        System.out.println("AI1 Compare Hand: ");
        for (int i = 0; i < tempAI_3Hand.size(); i++) {
            System.out.println(tempAI_3Hand.elementAt(i).getCardSuit() + " of " + tempAI_3Hand.elementAt(i).getCardFace());
        }
        
        

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
            //Always generate high card score in case more than one person has the same score
            playerHC = isAHighCard(OrigPlayerHand);
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
                AI_1Score = 1;
                AI_1HC = isAHighCard(OrigAI_1Hand);
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
                AI_2Score = 1;
                AI_2HC = isAHighCard(OrigAI_2Hand);
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
                AI_3Score = 1;
                AI_3HC = isAHighCard(OrigAI_3Hand);
            }
        } else {
            AI_3HC = 0;
            AI_3Score = 0;
        }

        // Safety to handle when all players have high card and the user and some other ai have the same one, favor goes to player
        //if ((playerScore == AI_1Score) && (playerScore == AI_2Score) && (playerScore == AI_3Score)) {
        if (playerScore == 1 && AI_1Score == 1 && AI_2Score == 1 && AI_3Score == 1) {
            if (playerHC >= AI_1HC && playerHC >= AI_2HC && playerHC >= AI_3HC) {
                gamePlayerThatWon = 0;
            }
            if (AI_1HC >= playerHC && AI_1HC >= AI_2HC && AI_1HC >= AI_3HC) {
                gamePlayerThatWon = 1;
            }
            if (AI_2HC >= playerHC && AI_2HC >= AI_1HC && AI_2HC >= AI_3HC) {
                gamePlayerThatWon = 2;
            }
            if (AI_3HC >= playerHC && AI_3HC >= AI_1HC && AI_3HC >= AI_2HC) {
                gamePlayerThatWon = 3;
            }
        }

        if ((AI_1Score >= playerScore) && (AI_1Score >= AI_2Score) && (AI_1Score >= AI_3Score)) {
            gamePlayerThatWon = 1;
            ai1.setCurrency(ai1.getCurrency() + pot);
            pot = 0;
            if (AI_1Score == 1) {
                winningHand = "High Card!";
            } else if (AI_1Score == 2) {
                winningHand = "Pair!";
            } else if (AI_1Score == 3) {
                winningHand = "Two Pair!";
            } else if (AI_1Score == 4) {
                winningHand = "Three of a Kind!";
            } else if (AI_1Score == 5) {
                winningHand = "Straight!";
            } else if (AI_1Score == 6) {
                winningHand = "Flush!";
            } else if (AI_1Score == 7) {
                winningHand = "Full House!";
            } else if (AI_1Score == 8) {
                winningHand = "Four of a Kind!";
            } else if (AI_1Score == 9) {
                winningHand = "Straight Flush!";
            } else if (AI_1Score == 10) {
                winningHand = "Royal Flush!";
            }

        }

        if ((AI_2Score >= playerScore) && (AI_2Score >= AI_1Score) && (AI_2Score >= AI_3Score)) {
            gamePlayerThatWon = 2;
            ai2.setCurrency(ai2.getCurrency() + pot);
            pot = 0;
            if (AI_2Score == 1) {
                winningHand = "High Card!";
            } else if (AI_2Score == 2) {
                winningHand = "Pair!";
            } else if (AI_2Score == 3) {
                winningHand = "Two Pair!";
            } else if (AI_2Score == 4) {
                winningHand = "Three of a Kind!";
            } else if (AI_2Score == 5) {
                winningHand = "Straight!";
            } else if (AI_2Score == 6) {
                winningHand = "Flush!";
            } else if (AI_2Score == 7) {
                winningHand = "Full House!";
            } else if (AI_2Score == 8) {
                winningHand = "Four of a Kind!";
            } else if (AI_2Score == 9) {
                winningHand = "Straight Flush!";
            } else if (AI_2Score == 10) {
                winningHand = "Royal Flush!";
            }

        }

        if ((AI_3Score >= playerScore) && (AI_3Score >= AI_2Score) && (AI_3Score >= AI_1Score)) {
            gamePlayerThatWon = 3;
            ai3.setCurrency(ai3.getCurrency() + pot);
            pot = 0;
            if (AI_3Score == 1) {
                winningHand = "High Card!";
            } else if (AI_3Score == 2) {
                winningHand = "Pair!";
            } else if (AI_3Score == 3) {
                winningHand = "Two Pair!";
            } else if (AI_3Score == 4) {
                winningHand = "Three of a Kind!";
            } else if (AI_3Score == 5) {
                winningHand = "Straight!";
            } else if (AI_3Score == 6) {
                winningHand = "Flush!";
            } else if (AI_3Score == 7) {
                winningHand = "Full House!";
            } else if (AI_3Score == 8) {
                winningHand = "Four of a Kind!";
            } else if (AI_3Score == 9) {
                winningHand = "Straight Flush!";
            } else if (AI_3Score == 10) {
                winningHand = "Royal Flush!";
            }

        }
        if ((playerScore >= AI_1Score) && (playerScore >= AI_2Score) && (playerScore >= AI_3Score)) {
            gamePlayerThatWon = 0;
            Player.currency = (Player.currency + pot);
            pot = 0;
            if (playerScore == 1) {
                winningHand = "High Card!";
            } else if (playerScore == 2) {
                winningHand = "Pair!";
            } else if (playerScore == 3) {
                winningHand = "Two Pair!";
            } else if (playerScore == 4) {
                winningHand = "Three of a Kind!";
            } else if (playerScore == 5) {
                winningHand = "Straight!";
            } else if (playerScore == 6) {
                winningHand = "Flush!";
            } else if (playerScore == 7) {
                winningHand = "Full House!";
            } else if (playerScore == 8) {
                winningHand = "Four of a Kind!";
            } else if (playerScore == 9) {
                winningHand = "Straight Flush!";
            } else if (playerScore == 10) {
                winningHand = "Royal Flush!";
            }
        }
        return gamePlayerThatWon;
    }

    /**
     * Function: COMPARATOR Compare two cards
     */
    public Comparator<Card> byValue = (Card left, Card right) -> {
        if (left.getCardValue() < right.getCardValue()) {
            return -1;
        } else {
            return 1;
        }
    };

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

        Card[] objArray = new Card[checkedHand.size()];

        for (int i = 0; i < checkedHand.size(); i++) {
            objArray[i] = checkedHand.get(i);
        }

        // Sorts current hand by implementing comparable
        Arrays.sort(objArray, byValue);

        int noOfRepeats = 0;
        boolean isThreeOfAKind = false;
        boolean isTwoOfAKind = false;
        for (int i = 0; i < objArray.length - 1; i++) {
            if (objArray[i].getCardValue() == objArray[i + 1].getCardValue()) {
                noOfRepeats++;
                if (noOfRepeats == 2) {
                    isThreeOfAKind = true;
                    noOfRepeats = 0;
                    for (int x = i; x < objArray.length - 1; x++) {
                        if (objArray[x].getCardValue() == objArray[x + 1].getCardValue()) {
                            noOfRepeats++;
                            if (noOfRepeats == 2) {
                                isTwoOfAKind = true;
                                noOfRepeats = 0;
                            }
                        }

                    }
                }
            } else {
                noOfRepeats = 0;
            }            
        }
        for (int i = objArray.length-1; objArray.length != 0; i++) {
            if (objArray[i].getCardValue() == objArray[i + 1].getCardValue()) {
                noOfRepeats++;
                if (noOfRepeats == 2) {
                    isThreeOfAKind = true;
                    noOfRepeats = 0;
                    for (int x = i; x < objArray.length - 1; x++) {
                        if (objArray[x].getCardValue() == objArray[x + 1].getCardValue()) {
                            noOfRepeats++;
                            if (noOfRepeats == 2) {
                                isTwoOfAKind = true;
                                noOfRepeats = 0;
                            }
                        }

                    }
                }
            } else {
                noOfRepeats = 0;
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

        Card[] objArray = new Card[checkedHand.size()];

        for (int i = 0; i < checkedHand.size(); i++) {
            objArray[i] = checkedHand.get(i);
        }

        // Sorts current hand by implementing comparable
        Arrays.sort(objArray, byValue);

        // Checks if the cards are in row by comparing elements nex to eachother, if the difference is 1 then it means they're in order
        int noOfCardsInARow = 0;
        int pos = 0;
        boolean isAStraight = false;
        while (pos < objArray.length - 1 && !isAStraight) {
            if (objArray[pos + 1].getCardValue() - objArray[pos].getCardValue() == 1) {
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
            k=0;
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

        Card[] objArray = new Card[checkedHand.size()];

        for (int i = 0; i < checkedHand.size(); i++) {
            objArray[i] = checkedHand.get(i);
        }

        // Sorts current hand by implementing comparable
        Arrays.sort(objArray, byValue);

        return objArray[1].getCardValue();
    }

    public void terminateGameSession(Vector<Card> checkedHand) {

    }

    public static void main(String[] args) throws IOException {

//        // Set the pot to 200, 50 placed from the player and 150 from the AIs
//        pot = 200;
        System.out.println("Player placed buy in amount");

    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Card;
import Model.Player;
import Model.PokerDeck;
import java.io.IOException;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;
import static javafx.application.Platform.exit;

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
public class FiveCardPokerEngine extends GameEngine {
 
    int initialDeal;
    double buyInAmount;
    double totalPot;
    boolean playerTurn;
    boolean playerWon;
    
//        public Random rand = new Random();
//        public PokerDeck pokerDeck;
//        public Vector<Card> playerHand;
//        public Vector<Card> AI_1Hand;
//        public Vector<Card> AI_2Hand;
//        public Vector<Card> AI_3Hand;
//        public Player p;
//        public Player ai1;
//        public Player ai2;
//        public Player ai3;
//        
        
    public void showCardValues() {

    }

    public void deal(int initialDeal) {
        this.initialDeal = initialDeal;
    }

    public void discard(int cards) {

    }

    public void payBuyInAmount(double buyInAmount) {
        this.buyInAmount = buyInAmount;
    }

    // MY IMPLEMENTED FUNCTIONS
    // Determine Winner method
    public int compareHands(Vector<Card> playerHand, Vector<Card> AI_1Hand, Vector<Card> AI_2Hand, Vector<Card> AI_3Hand) {

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

        // ANALYSE PLAYER HAND
        if (isARoyalFlush(playerHand)) {
            playerScore = 10;
        } else if (isAStraightFlush(playerHand)) {
            playerScore = 9;
        } else if (isAFourOfAKind(playerHand)) {
            playerScore = 8;
        } else if (isAFullHouse(playerHand)) {
            playerScore = 7;
        } else if (isAFlush(playerHand)) {
            playerScore = 6;
        } else if (isAStraight(playerHand)) {
            playerScore = 5;
        } else if (isAThreeOfAKind(playerHand)) {
            playerScore = 4;
        } else if (isATwoPair(playerHand)) {
            playerScore = 3;
        } else if (isAPair(playerHand)) {
            playerScore = 2;
        } else {
            playerHC = isAHighCard(playerHand);
            playerScore = 1;
        }

        // ANALYSE AI_1 HAND
        if (isARoyalFlush(AI_1Hand)) {
            AI_1Score = 10;
        } else if (isAStraightFlush(AI_1Hand)) {
            AI_1Score = 9;
        } else if (isAFourOfAKind(AI_1Hand)) {
            AI_1Score = 8;
        } else if (isAFullHouse(AI_1Hand)) {
            AI_1Score = 7;
        } else if (isAFlush(AI_1Hand)) {
            AI_1Score = 6;
        } else if (isAStraight(AI_1Hand)) {
            AI_1Score = 5;
        } else if (isAThreeOfAKind(AI_1Hand)) {
            AI_1Score = 4;
        } else if (isATwoPair(AI_1Hand)) {
            AI_1Score = 3;
        } else if (isAPair(AI_1Hand)) {
            AI_1Score = 2;
        } else {
            AI_1HC = isAHighCard(AI_1Hand);
            AI_1Score = 1;
        }

        // ANALYSE AI_2 HAND
        if (isARoyalFlush(AI_2Hand)) {
            AI_2Score = 10;
        } else if (isAStraightFlush(AI_2Hand)) {
            AI_2Score = 9;
        } else if (isAFourOfAKind(AI_2Hand)) {
            AI_2Score = 8;
        } else if (isAFullHouse(AI_2Hand)) {
            AI_2Score = 7;
        } else if (isAFlush(AI_2Hand)) {
            AI_2Score = 6;
        } else if (isAStraight(AI_2Hand)) {
            AI_2Score = 5;
        } else if (isAThreeOfAKind(AI_2Hand)) {
            AI_2Score = 4;
        } else if (isATwoPair(AI_2Hand)) {
            AI_2Score = 3;
        } else if (isAPair(AI_2Hand)) {
            AI_2Score = 2;
        } else {
            AI_2HC = isAHighCard(AI_2Hand);
            AI_2Score = 1;
        }

        // ANALYSE AI_3 HAND
        if (isARoyalFlush(AI_3Hand)) {
            AI_3Score = 10;
        } else if (isAStraightFlush(AI_3Hand)) {
            AI_3Score = 9;
        } else if (isAFourOfAKind(AI_3Hand)) {
            AI_3Score = 8;
        } else if (isAFullHouse(AI_3Hand)) {
            AI_3Score = 7;
        } else if (isAFlush(AI_3Hand)) {
            AI_3Score = 6;
        } else if (isAStraight(AI_3Hand)) {
            AI_3Score = 5;
        } else if (isAThreeOfAKind(AI_3Hand)) {
            AI_3Score = 4;
        } else if (isATwoPair(AI_3Hand)) {
            AI_3Score = 3;
        } else if (isAPair(AI_3Hand)) {
            AI_3Score = 2;
        } else {
            AI_3HC = isAHighCard(AI_3Hand);
            AI_3Score = 1;
        }

        // Determine winner based on hand score
        if ((playerScore > AI_1Score) && (playerScore > AI_2Score) && (playerScore > AI_3Score)) {
            gamePlayerThatWon = 0;
        }

        if ((AI_1Score > playerScore) && (AI_1Score > AI_2Score) && (AI_1Score > AI_3Score)) {
            gamePlayerThatWon = 1;
        }

        if ((AI_2Score > playerScore) && (AI_2Score > AI_1Score) && (AI_2Score > AI_3Score)) {
            gamePlayerThatWon = 2;
        }

        if ((AI_3Score > playerScore) && (AI_3Score > AI_2Score) && (AI_3Score > AI_1Score)) {
            gamePlayerThatWon = 3;
        }

        // Special case where every player/AI just has a high card, determine winner based on high card
        if ((playerHC > AI_1HC) && (playerHC > AI_2HC) && (playerHC > AI_3HC)) {
            gamePlayerThatWon = 0;
        }

        if ((AI_1HC > playerHC) && (AI_1HC > AI_2HC) && (AI_1HC > AI_3HC)) {
            gamePlayerThatWon = 1;
        }

        if ((AI_2HC > playerHC) && (AI_2HC > AI_1HC) && (AI_2HC > AI_3HC)) {
            gamePlayerThatWon = 2;
        }

        if ((AI_3HC > playerHC) && (AI_3HC > AI_2HC) && (AI_3HC > AI_1HC)) {
            gamePlayerThatWon = 3;
        }

        return gamePlayerThatWon;

    }

    // 1) Check for Royal FLush
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

    // 2) Check for Straight Flush
    public boolean isAStraightFlush(Vector<Card> checkedHand) {

        if (isAFlush(checkedHand) && isAStraight(checkedHand)) {
            return true;
        } else {
            return false;
        }
    }

    // 3) Check for Four of a Kind
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

    // 4) Check for Full House
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

    // 5) Check for Flush
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

    // 6) Check for Straight
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

    // 7) Check for Three of a Kind
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

    // 8) Check for Two Pair
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

    // 9) Check for Pair
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

    // 10) Check for High Card
    public int isAHighCard(Vector<Card> checkedHand) {

        // Sorts current hand by implementing comparable
        Collections.sort(checkedHand);
        return checkedHand.elementAt(4).getCardValue();

    }

    public void terminateGameSession(Vector<Card> checkedHand) {

    }

    public static void main(String[] args) throws IOException {

        Random rand = new Random();
        PokerDeck pokerDeck = new PokerDeck();
        Vector<Card> playerHand = new Vector<Card>();
        Vector<Card> AI_1Hand = new Vector<Card>();
        Vector<Card> AI_2Hand = new Vector<Card>();
        Vector<Card> AI_3Hand = new Vector<Card>();
        Player p = new Player();
        Player ai1 = new Player();
        Player ai2 = new Player();
        Player ai3 = new Player();
        Scanner scnr = new Scanner(System.in);
        FiveCardPokerEngine FCPokerEngine = new FiveCardPokerEngine();
        boolean replaceCardChoice;
        int gameChoice = 1;
        int replaceCardsChoice;
        int playerMoveChoice;
        int AI_MoveChoice;
        int roundNumber;
        pokerDeck.shuffle();
        int lastBet = 0;
        int pot;
        int totalBetOwedByPlayer = 0;
        int totalBetOwedByAI1 = 0;
        int totalBetOwedByAI2 = 0;
        int totalBetOwedByAI3 = 0;

        // Set currency for ai's, initial is 1000 - 50 for buy in amount
        p.setCurrency(1000);
        ai1.setCurrency(950);
        ai2.setCurrency(950);
        ai3.setCurrency(950);

        // Player places the entry bet amount
        FCPokerEngine.payBuyInAmount(50);

        p.setCurrency((p.getCurrency() - 50));

        // Set the pot to 200, 50 placed from the player and 150 from the AIs
        pot = 200;

        System.out.println("Player placed buy in amount");
        /*
                Start by giving the player and AI the first 5 cards
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

        pokerDeck.shuffle();

        // Give player first card
        AI_1Hand.add(pokerDeck.deal());
        // Give player second card
        AI_1Hand.add(pokerDeck.deal());
        // Give player third card 
        AI_1Hand.add(pokerDeck.deal());
        // Give player fourth card
        AI_1Hand.add(pokerDeck.deal());
        // Give player fifth card
        AI_1Hand.add(pokerDeck.deal());

        pokerDeck.shuffle();

        // Give player first card
        AI_2Hand.add(pokerDeck.deal());
        // Give player second card
        AI_2Hand.add(pokerDeck.deal());
        // Give player third card 
        AI_2Hand.add(pokerDeck.deal());
        // Give player fourth card
        AI_2Hand.add(pokerDeck.deal());
        // Give player fifth card
        AI_2Hand.add(pokerDeck.deal());

        pokerDeck.shuffle();

        // Give player first card
        AI_3Hand.add(pokerDeck.deal());
        // Give player second card
        AI_3Hand.add(pokerDeck.deal());
        // Give player third card 
        AI_3Hand.add(pokerDeck.deal());
        // Give player fourth card
        AI_3Hand.add(pokerDeck.deal());
        // Give player fifth card
        AI_3Hand.add(pokerDeck.deal());

        do {

            System.out.println("Player current hand: ");
            for (int i = 0; i < playerHand.size(); i++) {
                System.out.println(playerHand.elementAt(i) + ",");
            }

            System.out.println("AI_1 current hand: ");
            for (int i = 0; i < AI_1Hand.size(); i++) {
                System.out.println(AI_1Hand.elementAt(i) + ",");
            }

            System.out.println("AI_2 current hand: ");
            for (int i = 0; i < AI_2Hand.size(); i++) {
                System.out.println(AI_2Hand.elementAt(i) + ",");
            }

            System.out.println("AI_3 current hand: ");
            for (int i = 0; i < AI_3Hand.size(); i++) {
                System.out.println(AI_3Hand.elementAt(i) + ",");
            }

            /*
            FIRST ROUND BETTING STARTS
                PLAYERS CHOICES IN ROUND
                Can be CALL(Check) = where player needs to meet other player's bet to continue
                Can be FOLD = where the player terminates the game session
                Can be BET(Raise) = where the player sets a new bet for all other to call or raise 
             */
            do {
                roundNumber = 1;

                // User places bet, calls, or folds
                System.out.println("First round of betting starting");
                System.out.println("Do you wish to bet, call, or fold? : 1 or 2 or 3");
                // Get input (choice) from player
                playerMoveChoice = scnr.nextInt();

                switch (playerMoveChoice) {

                    case 1: // BET
                        // Get bet amount input
                        int betAmount = scnr.nextInt();

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
                        System.exit(0);
                        break;
                }

                // int betAmount = rand.nextInt(900) + lastBet;
                // AI_1 player bet, or calls 
                // Get random input (choice) from AI player
                AI_MoveChoice = rand.nextInt(2) + 1;

                System.out.println("AI Move #:" + AI_MoveChoice);

                switch (AI_MoveChoice) {

                    case 1: // BET
                       // Get bet amount input
                        int betAmount = rand.nextInt(900) + totalBetOwedByAI2;
                        
                        // Force CALL action if less than 50 in currency 
                        if (ai1.getCurrency() < 50) {
                        betAmount = totalBetOwedByAI1;
                        // Updates total bet by substracting amount needed to call (totalBetOwed), resulting in the real bet amount
                        betAmount -= totalBetOwedByAI1;
                        // Clears bet owed by player
                        ai1.setBet(totalBetOwedByAI1); // Update player bet variable 
                        ai1.setCurrency(ai1.getCurrency() - totalBetOwedByAI1); // Update player currency 
                        pot += totalBetOwedByAI1; // Update pot
                        totalBetOwedByAI1 -= totalBetOwedByAI1; // Update player total bet owed varaible
                        break;
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
                }

                // AI_2 player bet, or calls 
                // Get random input (choice) from AI player
                AI_MoveChoice = rand.nextInt(2) + 1;

                System.out.println("AI Move #:" + AI_MoveChoice);

                switch (AI_MoveChoice) {

                    case 1: // BET  
                        // Get bet amount input
                        int betAmount = rand.nextInt(900) + totalBetOwedByAI2;
                        
                        // Force CALL action if less than 50 in currency 
                        if (ai2.getCurrency() < 50) {
                        betAmount = totalBetOwedByAI2;
                        // Updates total bet by substracting amount needed to call (totalBetOwed), resulting in the real bet amount
                        betAmount -= totalBetOwedByAI2;
                        // Clears bet owed by player
                        ai2.setBet(totalBetOwedByAI2); // Update player bet variable 
                        ai2.setCurrency(ai2.getCurrency() - totalBetOwedByAI2); // Update player currency 
                        pot += totalBetOwedByAI2; // Update pot
                        totalBetOwedByAI2 -= totalBetOwedByAI2; // Update player total bet owed varaible
                        break;
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
                }

                /// AI_3 player bet, or calls 
                // Get random input (choice) from AI player
                AI_MoveChoice = rand.nextInt(2) + 1;

                System.out.println("AI Move #:" + AI_MoveChoice);

                switch (AI_MoveChoice) {

                    case 1: // BET
                        
                        // Get bet amount input
                        int betAmount = rand.nextInt(900) + totalBetOwedByAI3;
                        
                        // Force CALL action if less than 50 in currency 
                        if (ai3.getCurrency() < 50) {
                        betAmount = totalBetOwedByAI3;
                        // Updates total bet by substracting amount needed to call (totalBetOwed), resulting in the real bet amount
                        betAmount -= totalBetOwedByAI3;
                        // Clears bet owed by player
                        ai3.setBet(totalBetOwedByAI3); // Update player bet variable 
                        ai3.setCurrency(ai3.getCurrency() - totalBetOwedByAI3); // Update player currency 
                        pot += totalBetOwedByAI3; // Update pot
                        totalBetOwedByAI3 -= totalBetOwedByAI3; // Update player total bet owed varaible
                        break;
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
                }

                System.out.println(totalBetOwedByPlayer);
                System.out.println(totalBetOwedByAI1);
                System.out.println(totalBetOwedByAI2);
                System.out.println(totalBetOwedByAI3);

                //***** If player bet, then ask for call or bet from AI, if they bet, call user, and so on until no one else raises
            } while (totalBetOwedByPlayer != 0 || totalBetOwedByAI1 != 0 || totalBetOwedByAI2 != 0 || totalBetOwedByAI3 != 0);

            // When betting round 1 is finished, ask players if they want to replace their cards
            // Player is able to replace three cards 
            System.out.println("Do you wish to replace the first 3 cards in your hand? Yes 0r No (1 or 2)");
            // Get input (choice) from player
            replaceCardsChoice = scnr.nextInt();

            if (replaceCardsChoice == 1) {
                // Start replace card process for the player

                // Give player first card
                playerHand.set(0, pokerDeck.deal());
                // Give player second card
                playerHand.set(1, pokerDeck.deal());
                // Give player third card 
                playerHand.set(2, pokerDeck.deal());
            }

            System.out.println("Player current hand: ");
            for (int i = 0; i < playerHand.size(); i++) {
                System.out.println(playerHand.elementAt(i) + ",");
            }

            /*
            SECOND ROUND BETTING STARTS
                PLAYERS CHOICES IN ROUND
                Can be CALL(Check) = where player needs to meet other player's bet to continue
                Can be FOLD = where the player terminates the game session
                Can be BET(Raise) = where the player sets a new bet for all other to call or raise 
             */
            do {

                // User places bet, calls, or folds
                System.out.println("Second round of betting starting");
                System.out.println("Do you wish to bet, call, or fold? : 1 or 2 or 3");
                // Get input (choice) from player
                playerMoveChoice = scnr.nextInt();

                switch (playerMoveChoice) {

                    case 1: // BET
                        // Get bet amount input
                        int betAmount = scnr.nextInt();

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
                        System.exit(0);
                        break;
                }

                // int betAmount = rand.nextInt(900) + lastBet;
                // AI_1 player bet, or calls 
                // Get random input (choice) from AI player
                AI_MoveChoice = rand.nextInt(2) + 1;

                System.out.println("AI Move #:" + AI_MoveChoice);

                switch (AI_MoveChoice) {

                    case 1: // BET
                       // Get bet amount input
                        int betAmount = rand.nextInt(900) + totalBetOwedByAI2;
                        
                        // Force CALL action if less than 50 in currency 
                        if (ai1.getCurrency() < 50) {
                        betAmount = totalBetOwedByAI1;
                        // Updates total bet by substracting amount needed to call (totalBetOwed), resulting in the real bet amount
                        betAmount -= totalBetOwedByAI1;
                        // Clears bet owed by player
                        ai1.setBet(totalBetOwedByAI1); // Update player bet variable 
                        ai1.setCurrency(ai1.getCurrency() - totalBetOwedByAI1); // Update player currency 
                        pot += totalBetOwedByAI1; // Update pot
                        totalBetOwedByAI1 -= totalBetOwedByAI1; // Update player total bet owed varaible
                        break;
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
                }

                // AI_2 player bet, or calls 
                // Get random input (choice) from AI player
                AI_MoveChoice = rand.nextInt(2) + 1;

                System.out.println("AI Move #:" + AI_MoveChoice);

                switch (AI_MoveChoice) {

                    case 1: // BET  
                        // Get bet amount input
                        int betAmount = rand.nextInt(900) + totalBetOwedByAI2;
                        
                        // Force CALL action if less than 50 in currency 
                        if (ai2.getCurrency() < 50) {
                        betAmount = totalBetOwedByAI2;
                        // Updates total bet by substracting amount needed to call (totalBetOwed), resulting in the real bet amount
                        betAmount -= totalBetOwedByAI2;
                        // Clears bet owed by player
                        ai2.setBet(totalBetOwedByAI2); // Update player bet variable 
                        ai2.setCurrency(ai2.getCurrency() - totalBetOwedByAI2); // Update player currency 
                        pot += totalBetOwedByAI2; // Update pot
                        totalBetOwedByAI2 -= totalBetOwedByAI2; // Update player total bet owed varaible
                        break;
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
                }

                /// AI_3 player bet, or calls 
                // Get random input (choice) from AI player
                AI_MoveChoice = rand.nextInt(2) + 1;

                System.out.println("AI Move #:" + AI_MoveChoice);

                switch (AI_MoveChoice) {

                    case 1: // BET
                        
                        // Get bet amount input
                        int betAmount = rand.nextInt(900) + totalBetOwedByAI3;
                        
                        // Force CALL action if less than 50 in currency 
                        if (ai3.getCurrency() < 50) {
                        betAmount = totalBetOwedByAI3;
                        // Updates total bet by substracting amount needed to call (totalBetOwed), resulting in the real bet amount
                        betAmount -= totalBetOwedByAI3;
                        // Clears bet owed by player
                        ai3.setBet(totalBetOwedByAI3); // Update player bet variable 
                        ai3.setCurrency(ai3.getCurrency() - totalBetOwedByAI3); // Update player currency 
                        pot += totalBetOwedByAI3; // Update pot
                        totalBetOwedByAI3 -= totalBetOwedByAI3; // Update player total bet owed varaible
                        break;
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
                }

                //***** If player bet, then ask for call or bet from AI, if they bet, call user, and so on until no one else raises
            } while (totalBetOwedByPlayer != 0 || totalBetOwedByAI1 != 0 || totalBetOwedByAI2 != 0 || totalBetOwedByAI3 != 0);

            // Call compareHands function and determine the winner
            int playerWon;
            playerWon = FCPokerEngine.compareHands(playerHand, AI_1Hand, AI_2Hand, AI_3Hand);
            if (playerWon == 0) {
                System.out.println("User player won the game.");
                System.out.println("Player current hand: ");
                for (int i = 0; i < playerHand.size(); i++) {
                    System.out.println(playerHand.elementAt(i) + ",");
                }

            } else if (playerWon == 1) {
                System.out.println("AI 1 player won the game.");
                System.out.println("Player current hand: ");
                for (int i = 0; i < AI_1Hand.size(); i++) {
                    System.out.println(playerHand.elementAt(i) + ",");
                }

            } else if (playerWon == 2) {
                System.out.println("AI 2 player won the game.");
                System.out.println("Player current hand: ");
                for (int i = 0; i < AI_2Hand.size(); i++) {
                    System.out.println(playerHand.elementAt(i) + ",");
                }

            } else if (playerWon == 3) {
                System.out.println("AI 3 player won the game.");
                System.out.println("Player current hand: ");
                for (int i = 0; i < AI_3Hand.size(); i++) {
                    System.out.println(playerHand.elementAt(i) + ",");
                }

            }
        } while (gameChoice != 1);
    }

}

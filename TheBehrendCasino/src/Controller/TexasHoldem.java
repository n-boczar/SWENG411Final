/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package texas_hold_.em;

/**
 *
 * @author Ian
 */
public class Texas_Hold_Em {

    //MAKE THE DECK
    public static void makeDeck(Card[] deck){ 
        
        //Initial Value and Suit
        int initNum = 2;
        String initSuit = "Diamonds";
        
        //Give values to all cards in the deck
        for(int i = 0; i < deck.length; i++){
            deck[i]= new Card(initNum, initSuit);
            //increment initNum
            initNum++;
            //check to see if the next suit should be made
            if(initNum == 15){
                //set the initial number back to 2
                initNum = 2;
                //change suit before rolling over the process
                if(initSuit == "Diamonds")
                    initSuit = "Clubs";
                
                else if(initSuit == "Clubs")
                    initSuit = "Hearts";
                
                else if(initSuit == "Hearts")
                    initSuit = "Spades";
            }      
        }  
    }
    
    //SHUFFLE THE DECK
    public static void Shuffle(Card[] deck){
        for(int i = 0; i < 200; i++){
            //generate 2 random numbers to switch in the deck
            int rand1 = (int)(Math.random() * 51);
            int rand2 = (int)(Math.random() * 51);
            //placeholder for first card
            Card temp = deck[rand1];
            //swap cards
            deck[rand1] = deck[rand2];
            deck[rand2] = temp;
        }
    }
    
    
    public static void main(String[] args) {
        
        //Card example = new Card(4, "Spades");
        //Initialize 52 card deck with array
        Card deck[] = new Card[52];
        
        //Give the 52 cards their values and suits
        makeDeck(deck);
        
        //Display the deck
        for(int i = 0; i < deck.length; i++){
            System.out.println(deck[i].toString()); 
        }
        //Shuffle the deck
        Shuffle(deck);
        
        //Seperate the displays
        System.out.println("################################");
        
        //Display the deck again
        for(int i = 0; i < deck.length; i++){
            System.out.println(deck[i].toString()); 
        }
        
        Player player1 = new Player(deck[1], deck[2]);
        player1.printHand(player1);
        Player player2 = new Player(deck[3], deck[4]);
        player2.printHand(player2);
    }
    
}

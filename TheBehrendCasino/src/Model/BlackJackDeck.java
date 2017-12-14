 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.security.SecureRandom;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Natalie
 */
public class BlackJackDeck extends Deck {
    private Card[] deck;
    private int cardNow;    //upcoming card in deck
    
    //Constructor
    public BlackJackDeck() throws IOException{
        String[] cards = {"Ace","2","3","4","5","6","7","8","9","10","Jack","Queen","King"};    //All card ranks
        String[] suits = {"Clubs","Diamonds","Hearts","Spades"};    //All card suits
        
        deck = new Card[52];    //The deck is an array of 52 cards
        cardNow = 0;    //Start on the zero'th card
        
        //WIDTH and HEIGHT below are concrete/final dimensions of the actual pixel image
        final int width = 79;
        final int height = 123;
        //In the master image, CardSet.png, there are 4 rows and 13 columns
        final int rows = 4;
        final int cols = 13;
        
        //Read in master image (This image has all cards -- we take subimage from this to get each card face).
        BufferedImage master = ImageIO.read(new File("CardSet.png"));
        BufferedImage temp;
        
        //Extract card image by row and column
        for(int i = 0; i < 4; i++){ 
            for(int j = 0; j < 13; j++){
                //extract
                temp = master.getSubimage(j*width, i*height, width, height);
                deck[j + i*13] = new Card(suits[i], cards[j], j+1, temp);
                //The statement below is checking for if the card is a face card
                //Since in BlackJack, face cards are worth 10, we must be sure to set this
                if(deck[j+ i*13].toString().contains("Jack") || deck[j+ i*13].toString().contains("Queen") || deck[j+ i*13].toString().contains("King"))
                {
                    deck[j + i*13] = new Card(cards[j], suits[i], 10, temp);
                }
                else{
                    deck[j + i*13] = new Card(cards[j], suits[i], j+1, temp);
                }
            }
        }
    }
    
    //This method prints the entire deck
    //This is mainly used for testing
    public void showDeck(){
        for(Card card : deck){
            System.out.println(card);
            if(card.toString().contains("Jack") || card.toString().contains("Queen") || card.toString().contains("King")){
                card.setCardValue(10);
            }
            System.out.println(card.getCardValue());
        }
        
        JFrame jf = new JFrame();
        jf.setSize(400, 600);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
        JPanel jp = new JPanel(new BorderLayout());
        jp.setVisible(true);
       
        
        //***********************************************************************************
        //Put a number 0 - 51 in the deck[ ] array  V  here. It will show that card in popup.  Just have it to test for now.
        JLabel card = new JLabel(new ImageIcon(deck[39].getCardImage()));
                
        jp.add(card);
        jf.add(jp);
    }
    
    public void shuffle(){
        cardNow = 0;
        SecureRandom r = new SecureRandom();
        for(int i = 0; i < deck.length; i++){
            int next = r.nextInt(52);
            
            //SWAP cards to perform shuffle
            Card temp = deck[i];
            deck[i] = deck[next];
            deck[next] = temp;
        }
    }
    
    //Returns a single card (ideally, this method should be called after shuffling)
    public Card deal(){
        if(cardNow < deck.length){
            return deck[cardNow++];
        }
        else{
            return null;
        }
    }
    
    public static void main(String[] args) throws IOException{
        BlackJackDeck d = new BlackJackDeck();
        d.shuffle();
        System.out.println("THE SHUFFLED DECK");
        d.showDeck();
        
    }
}

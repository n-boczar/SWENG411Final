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
    
    public BlackJackDeck() throws IOException{
        String[] cards = {"Ace","2","3","4","5","6","7","8","9","10","Jack","Queen","King"};
        String[] suits = {"Clubs","Diamonds","Hearts","Spades"};
        
        deck = new Card[52];
        cardNow = 0;
        
        final int width = 79;
        final int height = 123;
        final int rows = 4;
        final int cols = 13;
        
        BufferedImage master = ImageIO.read(new File("CardSet.png"));
        BufferedImage temp;
        
        for(int i = 0; i < 4; i++){ 
            for(int j = 0; j < 13; j++){
                //extract
                temp = master.getSubimage(j*width, i*height, width, height);
                //deck[j + i*13] = new Card(suits[i], cards[j], j+1, temp);
                deck[j + i*13] = new Card(cards[j], suits[i], j+1, temp);
            }
        }
    }
    
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

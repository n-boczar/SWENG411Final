/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Natalie
 */
public class Card implements Comparable<Card>{
    private String face;
    private String suit;
    private int value;
    private BufferedImage img;
    
    //Card constructor, to take in all basic card identity features, including IMAGE
    public Card(String face, String suit, int value, BufferedImage img){
        this.face = face;
        this.suit = suit;
        this.value = value;
        this.img = img;
    }
    
    public void setCardValue(int v){
        value = v;
    }
    
    public int getCardValue(){
        return value;
    }
    
    public String getCardSuit(){
        return suit; 
    }
    
    public String getCardFace(){
        return face;
    }
    
    public BufferedImage getCardImage()
    {
        return img;
    }
    
    public String toString(){
        return face + " of " + suit;
    }
    
    
    public int compareTo(Card other) {
        return value - other.getCardValue();
   } 
    
    //This returns a "face-down" card image, to use when you want to conceal card identity
    public static BufferedImage getFlippedCardImage() throws IOException{
        final int width = 79;
        final int height = 123;
        
        BufferedImage master = ImageIO.read(new File("CardSet.png"));
        BufferedImage temp;

        temp = master.getSubimage(2*width, 4*height, width, height);
        
        return temp;
    }
}

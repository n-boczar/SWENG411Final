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
public class Card {
    private String face;
    private String suit;
    private int value;
    private BufferedImage img;
    
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
    
    public BufferedImage getCardImage()
    {
        return img;
    }
    
    public String toString(){
        return face + " of " + suit;
    }
}

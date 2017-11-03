/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import View.GameSelectionFrame;
import java.awt.Image;

/**
 *
 * @author Natalie
 */
public class Card {
    
    String rank;
    String suit;
    int value;
    Image img;
    
    public Card(String rank, String suit, int value, Image img){
        //creates card obj
        this.rank = rank;
        this.suit = suit;
        this.value = value;
        this.img = img;
        
        System.out.println(this.rank + ", " + this.suit + ", " + this.value);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Natalie
 */
public class Deck {

    public void createDeck() throws IOException{
        //public Card(String rank, String suit, String value, Image img){
        Card aceOfSpades = new Card("Ace", "Spades", 11, ImageIO.read(new File("C:\\TEMP\\CARDS\\ace_of_spades.png")));
        Card aceOfHearts = new Card("Ace", "Hearts", 11, ImageIO.read(new File("C:\\TEMP\\CARDS\\ace_of_hearts.png")));
        Card aceOfDiamonds = new Card("Ace", "Diamonds", 11, ImageIO.read(new File("C:\\TEMP\\CARDS\\ace_of_diamonds.png")));
        Card aceOfClubs = new Card("Ace", "Clubs", 11, ImageIO.read(new File("C:\\TEMP\\CARDS\\ace_of_clubs.png")));
        Card twoOfSpades = new Card("2", "Spades", 2, ImageIO.read(new File("C:\\TEMP\\CARDS\\2_of_spades.png")));
        Card twoOfHearts = new Card("2", "Hearts", 2, ImageIO.read(new File("C:\\TEMP\\CARDS\\2_of_hearts.png")));
        Card twoOfDiamonds = new Card("2", "Diamonds", 2, ImageIO.read(new File("C:\\TEMP\\CARDS\\2_of_diamonds.png")));
        Card twoOfClubs = new Card("2", "Clubs", 2, ImageIO.read(new File("C:\\TEMP\\CARDS\\2_of_clubs.png")));
        Card threeOfSpades = new Card("3", "Spades", 3, ImageIO.read(new File("C:\\TEMP\\CARDS\\3_of_spades.png")));
        Card threeOfHearts = new Card("3", "Hearts", 3, ImageIO.read(new File("C:\\TEMP\\CARDS\\3_of_hearts.png")));
        Card threeOfDiamonds = new Card("3", "Diamonds", 3, ImageIO.read(new File("C:\\TEMP\\CARDS\\3_of_diamonds.png")));
        Card threeOfClubs = new Card("3", "Clubs", 3, ImageIO.read(new File("C:\\TEMP\\CARDS\\3_of_clubs.png")));
        Card fourOfSpades = new Card("4", "Spades", 4, ImageIO.read(new File("C:\\TEMP\\CARDS\\4_of_spades.png")));
        Card fourOfHearts = new Card("4", "Hearts", 4, ImageIO.read(new File("C:\\TEMP\\CARDS\\4_of_hearts.png")));
        Card fourOfDiamonds = new Card("4", "Diamonds", 4, ImageIO.read(new File("C:\\TEMP\\CARDS\\4_of_diamonds.png")));
        Card fourOfClubs = new Card("4", "Clubs", 4, ImageIO.read(new File("C:\\TEMP\\CARDS\\4_of_clubs.png")));
        Card fiveOfSpades = new Card("5", "Spades", 5, ImageIO.read(new File("C:\\TEMP\\CARDS\\5_of_spades.png")));
        Card fiveOfHearts = new Card("5", "Hearts", 5, ImageIO.read(new File("C:\\TEMP\\CARDS\\5_of_hearts.png")));
        Card fiveOfDiamonds = new Card("5", "Diamonds", 5, ImageIO.read(new File("C:\\TEMP\\CARDS\\5_of_diamonds.png")));
        Card fiveOfClubs = new Card("5", "Clubs", 5, ImageIO.read(new File("C:\\TEMP\\CARDS\\5_of_clubs.png")));
        Card sixOfSpades = new Card("6", "Spades", 6, ImageIO.read(new File("C:\\TEMP\\CARDS\\6_of_spades.png")));
        Card sixOfHearts = new Card("6", "Hearts", 6, ImageIO.read(new File("C:\\TEMP\\CARDS\\6_of_hearts.png")));
        Card sixOfDiamonds = new Card("6", "Diamonds", 6, ImageIO.read(new File("C:\\TEMP\\CARDS\\6_of_diamonds.png")));
        Card sixOfClubs = new Card("6", "Clubs", 6, ImageIO.read(new File("C:\\TEMP\\CARDS\\6_of_clubs.png")));
        Card sevenOfSpades = new Card("7", "Spades", 7, ImageIO.read(new File("C:\\TEMP\\CARDS\\7_of_spades.png")));
        Card sevenOfHearts = new Card("7", "Hearts", 7, ImageIO.read(new File("C:\\TEMP\\CARDS\\7_of_hearts.png")));
        Card sevenOfDiamonds = new Card("7", "Diamonds", 7, ImageIO.read(new File("C:\\TEMP\\CARDS\\7_of_diamonds.png")));
        Card sevenOfClubs = new Card("7", "Clubs", 7, ImageIO.read(new File("C:\\TEMP\\CARDS\\7_of_clubs.png")));
        Card eightOfSpades = new Card("8", "Spades", 8, ImageIO.read(new File("C:\\TEMP\\CARDS\\8_of_spades.png")));
        Card eightOfHearts = new Card("8", "Hearts", 8, ImageIO.read(new File("C:\\TEMP\\CARDS\\8_of_hearts.png")));
        Card eightOfDiamonds = new Card("8", "Diamonds", 8, ImageIO.read(new File("C:\\TEMP\\CARDS\\8_of_diamonds.png")));
        Card eightOfClubs = new Card("8", "Clubs", 8, ImageIO.read(new File("C:\\TEMP\\CARDS\\8_of_clubs.png")));
        Card nineOfSpades = new Card("9", "Spades", 9, ImageIO.read(new File("C:\\TEMP\\CARDS\\9_of_spades.png")));
        Card nineOfHearts = new Card("9", "Hearts", 9, ImageIO.read(new File("C:\\TEMP\\CARDS\\9_of_hearts.png")));
        Card nineOfDiamonds = new Card("9", "Diamonds", 9, ImageIO.read(new File("C:\\TEMP\\CARDS\\9_of_diamonds.png")));
        Card nineOfClubs = new Card("9", "Clubs", 9, ImageIO.read(new File("C:\\TEMP\\CARDS\\9_of_clubs.png")));
        Card tenOfSpades = new Card("10", "Spades", 10, ImageIO.read(new File("C:\\TEMP\\CARDS\\10_of_spades.png")));
        Card tenOfHearts = new Card("10", "Hearts", 10, ImageIO.read(new File("C:\\TEMP\\CARDS\\10_of_hearts.png")));
        Card tenOfDiamonds = new Card("10", "Diamonds", 10, ImageIO.read(new File("C:\\TEMP\\CARDS\\10_of_diamonds.png")));
        Card tenOfClubs = new Card("10", "Clubs", 10, ImageIO.read(new File("C:\\TEMP\\CARDS\\10_of_clubs.png")));
        Card jackOfSpades = new Card("Jack", "Spades", 10, ImageIO.read(new File("C:\\TEMP\\CARDS\\jack_of_spades.png")));
        Card jackOfHearts = new Card("Jack", "Hearts", 10, ImageIO.read(new File("C:\\TEMP\\CARDS\\jack_of_hearts.png")));
        Card jackOfDiamonds = new Card("Jack", "Diamonds", 10, ImageIO.read(new File("C:\\TEMP\\CARDS\\jack_of_diamonds.png")));
        Card jackOfClubs = new Card("Jack", "Clubs", 10, ImageIO.read(new File("C:\\TEMP\\CARDS\\jack_of_clubs.png")));
        Card queenOfSpades = new Card("Queen", "Spades", 10, ImageIO.read(new File("C:\\TEMP\\CARDS\\queen_of_spades.png")));
        Card queenOfHearts = new Card("Queen", "Hearts", 10, ImageIO.read(new File("C:\\TEMP\\CARDS\\queen_of_hearts.png")));
        Card queenOfDiamonds = new Card("Queen", "Diamonds", 10, ImageIO.read(new File("C:\\TEMP\\CARDS\\queen_of_diamonds.png")));
        Card queenOfClubs = new Card("Queen", "Clubs", 10, ImageIO.read(new File("C:\\TEMP\\CARDS\\queen_of_clubs.png")));
        Card kingOfSpades = new Card("King", "Spades", 10, ImageIO.read(new File("C:\\TEMP\\CARDS\\king_of_spades.png")));
        Card kingOfHearts = new Card("King", "Hearts", 10, ImageIO.read(new File("C:\\TEMP\\CARDS\\king_of_hearts.png")));
        Card kingOfDiamonds = new Card("King", "Diamonds", 10, ImageIO.read(new File("C:\\TEMP\\CARDS\\king_of_diamonds.png")));
        Card kingOfClubs = new Card("King", "Clubs", 10, ImageIO.read(new File("C:\\TEMP\\CARDS\\king_of_clubs.png")));
    }
}
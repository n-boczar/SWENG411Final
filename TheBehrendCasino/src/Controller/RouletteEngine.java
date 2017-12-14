package Controller;

import java.util.Vector;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Natalie
 */
public class RouletteEngine extends GameEngine {

    int userWinnings = 0;   //Initialize the user's winnings to be zero
    
    //The method below checks every userPlacements element.  If it finds that the userPlacements element matches the winning pocket,
    //then the userWinnings integer var will increase by the amount bet on that placement
    public int getWinner(Vector<Integer> userPlacements, Vector<Integer> userBets, int winningPocket){
        for(int i = 0; i < userPlacements.size(); i++){
            if(userPlacements.elementAt(i).equals((Integer) winningPocket)){
                userWinnings += userBets.elementAt(i);
            }
        }
        
        return userWinnings;    //Return to be displayed in frame
    }
}

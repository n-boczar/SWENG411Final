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

    int userWinnings = 0;
    
    public int getWinner(Vector<Integer> userPlacements, Vector<Integer> userBets, int winningPocket){
        for(int i = 0; i < userPlacements.size(); i++){
            if(userPlacements.elementAt(i).equals((Integer) winningPocket)){
                userWinnings += userBets.elementAt(i);
            }
        }
        
        return userWinnings;
    }
}

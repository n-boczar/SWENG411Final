/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author PKC5102
 */
public class Currency {
    int amount; 
    int balance;
    
    public int checkBalance() {
        balance = balance - amount;
        return balance;
    }
}

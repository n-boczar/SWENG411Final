/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.TexasHoldem;
import Model.AIPlayer;
import Model.Player;
import static View.TexasHoldemFrame.ante;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Ian
 */
public class TexasHoldemFrame extends javax.swing.JFrame {

    public static boolean ante;
    public static int currency;
    public TexasHoldem e;
    public int c = currency;
    public int roundNumber = 0;
    public int playerMoveChoice = 0;
    public int betAmount = 0;
    public int universalBetAmountOwed = 0;
    public int playerWon;
    public int round = 0;
    public AIPlayer ai1 = new AIPlayer();
    public AIPlayer ai2 = new AIPlayer();
    public AIPlayer ai3 = new AIPlayer();

    public static void startIt(Player player, boolean x) {

        ante = x;
        TexasHoldemFrame.main(null);

    }

    /**
     * Creates new form TexasHoldemFrame
     */
    public TexasHoldemFrame() throws IOException {
        initComponents();

        if (ante == true) {
            JOptionPane.showMessageDialog(null, "25 Chips Deducted for Ante.", "OK", JOptionPane.OK_OPTION);
        }

        // Take out the ante from the players currency before showing it
        Player.setCurrency(Player.getCurrency() - 25);

        // Show the current currency of the player
        jTextField1.setText(String.valueOf(Player.currency));

        // Load table image into frame
        BufferedImage table = null;
        try {
            table = ImageIO.read(new File("PokerTableFinished.png"));
            Icon i = new ImageIcon(table);
            JLabel pokerTable = new JLabel(i);
            jPanel3.add(pokerTable);
        } catch (IOException ex) {
            Logger.getLogger(GameSelectionFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Load game engine
        e = new TexasHoldem(ai1, ai2, ai3);

        // Show the card in their respective panels for the player
        JLabel card_1 = new JLabel(new ImageIcon(e.playerHand.elementAt(0).getCardImage()));
        jPanel6.add(card_1);

        JLabel card_2 = new JLabel(new ImageIcon(e.playerHand.elementAt(1).getCardImage()));
        jPanel6.add(card_2);

        jPanel6.doLayout();

        // Show the flop
        JLabel tableCard1 = new JLabel(new ImageIcon(e.boardCards.elementAt(0).getCardImage()));
        jPanel4.add(tableCard1);

        JLabel tableCard2 = new JLabel(new ImageIcon(e.boardCards.elementAt(1).getCardImage()));
        jPanel4.add(tableCard2);

        JLabel tableCard3 = new JLabel(new ImageIcon(e.boardCards.elementAt(2).getCardImage()));
        jPanel4.add(tableCard3);

        jPanel4.doLayout();

        roundNumber = 1;

    }

    public void paint(Graphics g) {
        super.paint(g);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jSlider1 = new javax.swing.JSlider();
        jTextField2 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMaximumSize(new java.awt.Dimension(655, 628));
        setMinimumSize(new java.awt.Dimension(655, 628));
        setPreferredSize(new java.awt.Dimension(655, 730));
        setResizable(false);
        setSize(new java.awt.Dimension(655, 730));

        jPanel3.setBackground(new java.awt.Color(51, 51, 51));
        jPanel3.setMaximumSize(new java.awt.Dimension(634, 376));
        jPanel3.setMinimumSize(new java.awt.Dimension(634, 376));
        jPanel3.setPreferredSize(new java.awt.Dimension(634, 376));
        jPanel3.setLayout(new java.awt.GridLayout());
        getContentPane().add(jPanel3, java.awt.BorderLayout.PAGE_START);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(634, 50));

        jButton1.setBackground(new java.awt.Color(0, 0, 51));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Call / Check");
        jButton1.setPreferredSize(new java.awt.Dimension(95, 40));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1);

        jButton2.setBackground(new java.awt.Color(0, 0, 51));
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Bet / Raise");
        jButton2.setPreferredSize(new java.awt.Dimension(90, 40));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2);

        jSlider1.setBackground(new java.awt.Color(0, 0, 51));
        jSlider1.setForeground(new java.awt.Color(255, 255, 255));
        jSlider1.setMajorTickSpacing(100);
        jSlider1.setMaximum(1000);
        jSlider1.setMinimum(10);
        jSlider1.setPaintTicks(true);
        jSlider1.setValue(0);
        jSlider1.setPreferredSize(new java.awt.Dimension(185, 31));
        jSlider1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSlider1StateChanged(evt);
            }
        });
        jPanel2.add(jSlider1);

        jTextField2.setEditable(false);
        jTextField2.setPreferredSize(new java.awt.Dimension(50, 25));
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        jPanel2.add(jTextField2);

        jButton3.setBackground(new java.awt.Color(0, 0, 51));
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Fold");
        jButton3.setPreferredSize(new java.awt.Dimension(73, 40));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton3);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Currency:");
        jPanel2.add(jLabel1);

        jTextField1.setEditable(false);
        jTextField1.setPreferredSize(new java.awt.Dimension(50, 25));
        jPanel2.add(jTextField1);

        getContentPane().add(jPanel2, java.awt.BorderLayout.PAGE_END);

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setMaximumSize(new java.awt.Dimension(634, 143));
        jPanel1.setMinimumSize(new java.awt.Dimension(634, 143));
        jPanel1.setPreferredSize(new java.awt.Dimension(634, 143));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel6.setBackground(new java.awt.Color(51, 51, 51));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Player Hand:");
        jPanel6.add(jLabel2);

        jPanel1.add(jPanel6, java.awt.BorderLayout.PAGE_END);

        jPanel4.setBackground(new java.awt.Color(0, 0, 51));
        jPanel4.setForeground(new java.awt.Color(255, 255, 255));
        jPanel4.setPreferredSize(new java.awt.Dimension(634, 272));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Community Cards:");
        jPanel4.add(jLabel3);

        jPanel1.add(jPanel4, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //Update player currency
        //Player.setCurrency(Player.getCurrency());
        // Set the bet amount from the text field
        round++;
        playerMoveChoice = 2;
        // Show the current currency of the player
        //COULDN'T FIND A BETTER WAY TO SHOW UPDATED CURRENCY AFTER CALLING
        //SO I'M JUST UPDATING AFTER THE ROUND OF BETTING
        jTextField1.setText(String.valueOf(Player.getCurrency() - betAmount));
        if (round == 1) {
            do {
                universalBetAmountOwed = e.startRound(playerMoveChoice, betAmount);
            } while (universalBetAmountOwed != 0);
            jTextField1.setText(String.valueOf(Player.getCurrency()));
            //Show another board card (turn)
            JLabel tableCard4 = new JLabel(new ImageIcon(e.boardCards.elementAt(3).getCardImage()));
            jPanel4.add(tableCard4);
            jPanel4.doLayout();
        } else if (round == 2) {
            do {
                universalBetAmountOwed = e.startRound(playerMoveChoice, betAmount);
            } while (universalBetAmountOwed != 0);
            jTextField1.setText(String.valueOf(Player.getCurrency()));
            //Show another board card (river)
            JLabel tableCard5 = new JLabel(new ImageIcon(e.boardCards.elementAt(4).getCardImage()));
            jPanel4.add(tableCard5);
            jPanel4.doLayout();
        } else if (round == 3) {
            do {
                universalBetAmountOwed = e.startRound(playerMoveChoice, betAmount);
            } while (universalBetAmountOwed != 0);
            check();
        }

        System.out.println("AI1 Curr: " + ai1.getCurrency());
        System.out.println("AI2 Curr: " + ai2.getCurrency());
        System.out.println("AI3 Curr: " + ai3.getCurrency());

    }//GEN-LAST:event_jButton1ActionPerformed
//BET
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        //Set your bet amount 
        // Set the bet amount from the text field
        betAmount = Integer.parseInt(jTextField1.getText());
        playerMoveChoice = 1;

        //make sure the user isn't betting more than what anyone has
        if (betAmount > Player.getCurrency() || betAmount > ai1.getCurrency() || betAmount > ai2.getCurrency() || betAmount > ai3.getCurrency()) {

            System.out.println("INSIDE FIRST USER BET MESSAGE.");
            JOptionPane.showMessageDialog(null, "You're betting more than you or another player has, retry!", "Warning", JOptionPane.OK_OPTION);

        } else {
            round++;
            // Show the current currency of the player
            jTextField1.setText(String.valueOf(Player.getCurrency() - betAmount));
            if (round == 1) {
                do {
                    universalBetAmountOwed = e.startRound(playerMoveChoice, betAmount);
                } while (universalBetAmountOwed != 0);
                //Show another board card (turn)
                JLabel tableCard4 = new JLabel(new ImageIcon(e.boardCards.elementAt(3).getCardImage()));
                jPanel4.add(tableCard4);
                jPanel4.doLayout();
            } else if (round == 2) {
                do {
                    universalBetAmountOwed = e.startRound(playerMoveChoice, betAmount);
                } while (universalBetAmountOwed != 0);
                //Show another board card (river)
                JLabel tableCard5 = new JLabel(new ImageIcon(e.boardCards.elementAt(4).getCardImage()));
                jPanel4.add(tableCard5);
                jPanel4.doLayout();
            } else if (round == 3) {
                do {
                    universalBetAmountOwed = e.startRound(playerMoveChoice, betAmount);
                } while (universalBetAmountOwed != 0);
                check();
            }

            System.out.println("AI1 Curr: " + ai1.getCurrency());
            System.out.println("AI2 Curr: " + ai2.getCurrency());
            System.out.println("AI3 Curr: " + ai3.getCurrency());

            /*
            if (roundNumber == 1) {

                do {
                    universalBetAmountOwed = e.startRound1(playerMoveChoice, betAmount);
                    System.out.println("Universal Amount Owed: " + universalBetAmountOwed);

                } while (universalBetAmountOwed != 0);

                roundNumber = 2;

                

            } else if (roundNumber == 2) {
                do {
                    universalBetAmountOwed = e.startRound2(playerMoveChoice, betAmount);
                    System.out.println("Universal Amount Owed: " + universalBetAmountOwed);

                } while (universalBetAmountOwed != 0);

                roundNumber = 3;

                //Show another board card (river)
                JLabel tableCard5 = new JLabel(new ImageIcon(e.boardCards.elementAt(4).getCardImage()));
                jPanel4.add(tableCard5);
                jPanel4.doLayout();

            } else if (roundNumber == 3) {
                do {
                    universalBetAmountOwed = e.startRound2(playerMoveChoice, betAmount);
                    System.out.println("Universal Amount Owed: " + universalBetAmountOwed);

                } while (universalBetAmountOwed != 0);

                // Call compareHands function and determine the winner
                playerWon = e.compareHands(e.playerHand, e.AI_1Hand, e.AI_2Hand, e.AI_3Hand, e.boardCards);

                if (playerWon == 1) {
                    JOptionPane.showMessageDialog(null, "User player won the game!", "Winner", JOptionPane.OK_OPTION);
                }
                if (playerWon == 2) {
                    JOptionPane.showMessageDialog(null, "AI player 1 won the game!", "Winner", JOptionPane.OK_OPTION);
                }
                if (playerWon == 3) {
                    JOptionPane.showMessageDialog(null, "AI player 2 won the game!", "Winner", JOptionPane.OK_OPTION);
                }
                if (playerWon == 4) {
                    JOptionPane.showMessageDialog(null, "AI player 3 won the game!", "Winner", JOptionPane.OK_OPTION);
                }

                //Check if player or AI have enough currencies to go to next round
                if (Player.getCurrency() == 0) {
                    Player.active = false;
                }
                if (ai1.getCurrency() == 0) {
                    ai1.active = false;
                }
                if (ai2.getCurrency() == 0) {
                    ai2.active = false;
                }
                if (ai3.getCurrency() == 0) {
                    ai3.active = false;
                }
            }
        }
             */
        }
    }//GEN-LAST:event_jButton2ActionPerformed
//FOLD
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // Set player choice to fold
        playerMoveChoice = 3;
        betAmount = 0;

        do {
            universalBetAmountOwed = e.startRound(playerMoveChoice, betAmount);
        } while (universalBetAmountOwed != 0);
        if (round < 2) {
            //Show another board card (turn)
            JLabel tableCard4 = new JLabel(new ImageIcon(e.boardCards.elementAt(3).getCardImage()));
            jPanel4.add(tableCard4);
            jPanel4.doLayout();
        }

        do {
            universalBetAmountOwed = e.startRound(playerMoveChoice, betAmount);
        } while (universalBetAmountOwed != 0);

        if (round < 3) {
            //Show another board card (river)
            JLabel tableCard5 = new JLabel(new ImageIcon(e.boardCards.elementAt(4).getCardImage()));
            jPanel4.add(tableCard5);
            jPanel4.doLayout();
        }
        do {
            universalBetAmountOwed = e.startRound(playerMoveChoice, betAmount);
        } while (universalBetAmountOwed != 0);
        System.out.println("AI1 Curr: " + ai1.getCurrency());
        System.out.println("AI2 Curr: " + ai2.getCurrency());
        System.out.println("AI3 Curr: " + ai3.getCurrency());
        check();
    }

    /**
     * Check to see if the players are still in the game
     */
    public void check() {
        // Call compareHands function and determine the winner
        playerWon = e.compareHands(e.playerHand, e.AI_1Hand, e.AI_2Hand, e.AI_3Hand, e.boardCards);

        if (playerWon == 1) {
            JOptionPane.showMessageDialog(null, "User player won the game!", "Winner", JOptionPane.OK_OPTION);
        }
        if (playerWon == 2) {
            JOptionPane.showMessageDialog(null, "AI player 1 won the game!", "Winner", JOptionPane.OK_OPTION);
        }
        if (playerWon == 3) {
            JOptionPane.showMessageDialog(null, "AI player 2 won the game!", "Winner", JOptionPane.OK_OPTION);
        }
        if (playerWon == 4) {
            JOptionPane.showMessageDialog(null, "AI player 3 won the game!", "Winner", JOptionPane.OK_OPTION);
        }

        //Check if player or AI have enough currencies to go to next round
        if (Player.getCurrency() < 25) {
            Player.active = false;
        }
        if (ai1.getCurrency() < 25) {
            ai1.active = false;
        }
        if (ai2.getCurrency() < 25) {
            ai2.active = false;
        }
        if (ai3.getCurrency() < 25) {
            ai3.active = false;
        }

        //reset the folded players at the end of each game
        e.reset();

        /*
    if (roundNumber

    
        == 1) {

            do {
            universalBetAmountOwed = e.startRound1(playerMoveChoice, betAmount);
            System.out.println("Universal Amount Owed: " + universalBetAmountOwed);

        } while (universalBetAmountOwed != 0);

        roundNumber = 2;

        //Show another board card (turn)
        //JLabel tableCard4 = new JLabel(new ImageIcon(e.boardCards.elementAt(3).getCardImage()));
        jPanel4.add(tableCard4);
        jPanel4.doLayout();

    }
    else if (roundNumber

    
        == 2) {
            do {
            universalBetAmountOwed = e.startRound2(playerMoveChoice, betAmount);
            System.out.println("Universal Amount Owed: " + universalBetAmountOwed);

        } while (universalBetAmountOwed != 0);

        roundNumber = 3;

        //Show another board card (river)
        //JLabel tableCard5 = new JLabel(new ImageIcon(e.boardCards.elementAt(4).getCardImage()));
        jPanel4.add(tableCard5);
        jPanel4.doLayout();

    }
    else if (roundNumber

    
        == 3) {
            do {
            universalBetAmountOwed = e.startRound2(playerMoveChoice, betAmount);
            System.out.println("Universal Amount Owed: " + universalBetAmountOwed);

        } while (universalBetAmountOwed != 0);

        // Call compareHands function and determine the winner
        playerWon = e.compareHands(e.playerHand, e.AI_1Hand, e.AI_2Hand, e.AI_3Hand, e.boardCards);

        if (playerWon == 1) {
            JOptionPane.showMessageDialog(null, "User player won the game!", "Winner", JOptionPane.OK_OPTION);
        }
        if (playerWon == 2) {
            JOptionPane.showMessageDialog(null, "AI player 1 won the game!", "Winner", JOptionPane.OK_OPTION);
        }
        if (playerWon == 3) {
            JOptionPane.showMessageDialog(null, "AI player 2 won the game!", "Winner", JOptionPane.OK_OPTION);
        }
        if (playerWon == 4) {
            JOptionPane.showMessageDialog(null, "AI player 3 won the game!", "Winner", JOptionPane.OK_OPTION);
        }

        //Check if player or AI have enough currencies to go to next round
        if (Player.getCurrency() == 0) {
            Player.active = false;
        }
        if (ai1.getCurrency() == 0) {
            ai1.active = false;
        }
        if (ai2.getCurrency() == 0) {
            ai2.active = false;
        }
        if (ai3.getCurrency() == 0) {
            ai3.active = false;
        }
    }
         */

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jSlider1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSlider1StateChanged
        jTextField2.setText(Integer.toString(jSlider1.getValue()));
    }//GEN-LAST:event_jSlider1StateChanged

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TexasHoldemFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TexasHoldemFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TexasHoldemFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TexasHoldemFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new TexasHoldemFrame().setVisible(true);

                } catch (IOException ex) {
                    Logger.getLogger(TexasHoldemFrame.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JSlider jSlider1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}

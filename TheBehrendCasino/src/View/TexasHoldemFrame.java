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
import static javax.swing.JOptionPane.YES_NO_OPTION;

/**
 *
 * @author Ian
 */
public class TexasHoldemFrame extends javax.swing.JFrame {

    public static boolean ante;
    public static int currency;
    public TexasHoldem e;
    public int c = currency;
    public int roundNumber = 1;
    public int playerMoveChoice = 0;
    public int betAmount = 0;
    public int universalBetAmountOwed = 0;
    public int playerWon;
    //public int round = 0;
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
            JOptionPane.showMessageDialog(null, "25 Chips Deducted for the Ante.", "OK", JOptionPane.OK_OPTION);
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

        //Start a new hand of TexasHoldem
        newHand();
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
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMaximumSize(new java.awt.Dimension(655, 628));
        setMinimumSize(new java.awt.Dimension(655, 628));
        setPreferredSize(new java.awt.Dimension(655, 730));
        setResizable(false);
        setSize(new java.awt.Dimension(655, 730));

        jPanel3.setBackground(new java.awt.Color(51, 51, 51));
        jPanel3.setMaximumSize(new java.awt.Dimension(634, 360));
        jPanel3.setMinimumSize(new java.awt.Dimension(634, 360));
        jPanel3.setPreferredSize(new java.awt.Dimension(634, 360));
        jPanel3.setLayout(new java.awt.GridLayout(1, 0));
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

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Player Hand:");
        jPanel6.add(jLabel2);

        jPanel1.add(jPanel6, java.awt.BorderLayout.PAGE_END);

        jPanel4.setBackground(new java.awt.Color(0, 0, 51));
        jPanel4.setForeground(new java.awt.Color(255, 255, 255));
        jPanel4.setMaximumSize(new java.awt.Dimension(140, 35));
        jPanel4.setMinimumSize(new java.awt.Dimension(140, 35));
        jPanel4.setPreferredSize(new java.awt.Dimension(140, 35));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Community Cards:");
        jPanel4.add(jLabel3);

        jPanel1.add(jPanel4, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel5.setEnabled(false);
        jPanel5.setMaximumSize(new java.awt.Dimension(99, 50));
        jPanel5.setMinimumSize(new java.awt.Dimension(99, 50));
        jPanel5.setPreferredSize(new java.awt.Dimension(99, 50));
        jPanel5.setLayout(new java.awt.BorderLayout());

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Mongolian Baiti", 1, 11)); // NOI18N
        jTextArea1.setRows(5);
        jTextArea1.setMaximumSize(new java.awt.Dimension(60, 60));
        jTextArea1.setMinimumSize(new java.awt.Dimension(60, 60));
        jScrollPane1.setViewportView(jTextArea1);

        jPanel5.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel5, java.awt.BorderLayout.LINE_END);

        jMenu1.setText("Exit");

        jMenuItem1.setText("Main Menu");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Desktop");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Help");

        jMenuItem3.setText("Game Rules");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Function: CALL Set bet amount to 0 and start betting round with player
     * choice as 2 The Engine should do the rest such as subtracting from
     * player's currency
     *
     * @param evt
     */
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        betAmount = 0;
        playerMoveChoice = 2;
        //Start betting round with user calling previous bet
        universalBetAmountOwed = e.startRound(playerMoveChoice, betAmount);
        //Update currency
        jTextField1.setText(String.valueOf(Player.getCurrency()));

        /*
            If the betting clears and no one else owes anything check to see what round it is
            so the right table card can be displayed and then round number increments 
         */
        if (universalBetAmountOwed == 0) {
            checkRound();
        }

        System.out.println("AI1 Curr: " + ai1.getCurrency());
        System.out.println("AI2 Curr: " + ai2.getCurrency());
        System.out.println("AI3 Curr: " + ai3.getCurrency());

    }//GEN-LAST:event_jButton1ActionPerformed
    /**
     * Function: BET Set the bet amount for the user according to the JSlider
     * Make sure the user isn't betting more than any player's currency around
     * the table Make sure the user didn't already bet this round
     *
     * @param evt
     */
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        //Set your bet amount 
        // Set the bet amount from the text field
        betAmount = Integer.parseInt(jTextField1.getText());
        playerMoveChoice = 1;
        //make sure the user isn't betting more than what anyone has
        if (betAmount > Player.getCurrency() || betAmount > ai1.getCurrency() || betAmount > ai2.getCurrency() || betAmount > ai3.getCurrency()) {
            JOptionPane.showMessageDialog(null, "You're betting more than you or another player has, retry!", "Warning", JOptionPane.OK_OPTION);

        } //players should only be able to bet once per round
        else if (Player.didBet) {
            JOptionPane.showMessageDialog(null, "You already bet once this round.", "Warning", JOptionPane.OK_OPTION);
        } else {
            Player.didBet = true;
            //Change the current currency of the player
            jTextField1.setText(String.valueOf(Player.getCurrency() - betAmount));
            //Start betting
            universalBetAmountOwed = e.startRound(playerMoveChoice, betAmount);
            /*
            If the betting clears and no one else owes anything check to see what round it is
            so the right table card can be displayed and then round number increments 
             */
            if (universalBetAmountOwed == 0) {
                checkRound();
            }

            //System.out.println("AI1 Curr: " + ai1.getCurrency());
            //System.out.println("AI2 Curr: " + ai2.getCurrency());
            //System.out.println("AI3 Curr: " + ai3.getCurrency());
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * Function: FOLD Determines which round it is when the player folds From
     * this it will finish the AI betting accordingly and display the rest of
     * the board cards. Once complete it will check to see which AI player one
     * NOTE: It should always be an AI player that wins from this function.
     *
     * @param evt
     */
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // Set player choice to fold
        playerMoveChoice = 3;
        betAmount = 0;

        //First finish the round of betting that you are in
        do {
            universalBetAmountOwed = e.startRound(playerMoveChoice, betAmount);
        } while (universalBetAmountOwed != 0);

        //If it is the first round it should display 2 cards, run until there is no univsalBetAmountOwed and then check for the winner
        if (roundNumber == 1) {
            //Show another board card (turn)
            JLabel tableCard4 = new JLabel(new ImageIcon(e.boardCards.elementAt(3).getCardImage()));
            jPanel4.add(tableCard4);
            jPanel4.doLayout();
            //run through the betting until none of the players owe anything
            do {
                universalBetAmountOwed = e.startRound(playerMoveChoice, betAmount);
            } while (universalBetAmountOwed != 0);
            //Show another board card (river)
            JLabel tableCard5 = new JLabel(new ImageIcon(e.boardCards.elementAt(4).getCardImage()));
            jPanel4.add(tableCard5);
            jPanel4.doLayout();
            //run through the betting until none of the players owe anything
            do {
                universalBetAmountOwed = e.startRound(playerMoveChoice, betAmount);
            } while (universalBetAmountOwed != 0);
            //check for a winner
            check();
        }

        //If it is the second round display 1 card, run through the betting, and then check for the winner
        if (roundNumber == 2) {
            //Show another board card (river)
            JLabel tableCard5 = new JLabel(new ImageIcon(e.boardCards.elementAt(4).getCardImage()));
            jPanel4.add(tableCard5);
            jPanel4.doLayout();
            //run through the betting until none of the players owe anything
            do {
                universalBetAmountOwed = e.startRound(playerMoveChoice, betAmount);
            } while (universalBetAmountOwed != 0);
            //check for a winner
            check();
        }

        //If it's round 3 DON'T display another card, run through the betting and check for a winner
        if (roundNumber == 3) {
            //check for a winner
            check();
        }

    }

    /**
     * Function: CHECK Check to see who won the game and if all players are
     * still in the game if they have less than 25 they should be set to
     * inactive
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
            //Show the user lost and exit game
            JOptionPane.showMessageDialog(null, "You ran out of currency! Please reenter the Casino to play again.", "Warning", JOptionPane.OK_OPTION);
            System.exit(0);
        }
        if (ai1.getCurrency() < 25) {
            ai1.active = false;
            ai1.fold = false;
        }
        if (ai2.getCurrency() < 25) {
            ai2.active = false;
            ai2.fold = false;
        }
        if (ai3.getCurrency() < 25) {
            ai3.active = false;
            ai3.fold = false;
        }

        //reset the folded players at the end of each game
        e.reset();
        
        //If all the ai's are inactive then the user wins! Exit back to game selection screen
        if (!ai1.active && !ai2.active && !ai3.active) {
            JOptionPane.showMessageDialog(null, "You ran out of currency! Please reenter the Casino to play again.", "Warning", JOptionPane.OK_OPTION);
            GameSelectionFrame.startIt(e.p);
            this.dispose();
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * Function: CHECKROUND Check to see what round it is This will decide which
     * card to put on the table and when to increment the round number. If the
     * round number is 3 then the hand is over and a winner should be
     * determined.
     */
    public void checkRound() {
        //Reset the didBet variable so players can bet in the next round
        Player.didBet = false;
        e.ai1.didBet = false;
        e.ai2.didBet = false;
        e.ai3.didBet = false;
        //Begin to check which round it is
        if (roundNumber == 1) {
            //Show board card (turn)
            JLabel tableCard4 = new JLabel(new ImageIcon(e.boardCards.elementAt(3).getCardImage()));
            jPanel4.add(tableCard4);
            jPanel4.doLayout();
            roundNumber++;
        } else if (roundNumber
                == 2) {
            //Show board card (river)
            JLabel tableCard5 = new JLabel(new ImageIcon(e.boardCards.elementAt(4).getCardImage()));
            jPanel4.add(tableCard5);
            jPanel4.doLayout();
            roundNumber++;
        } else if (roundNumber == 3) {
            check();
        }

    }

    /**
     * Start a new round of Texas Hold 'em Shuffle cards Deal new hands
     */
    public void newHand() {
        e.deal();
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
    }

    private void jSlider1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSlider1StateChanged
        jTextField2.setText(Integer.toString(jSlider1.getValue()));
    }//GEN-LAST:event_jSlider1StateChanged

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit? ", "Warning", YES_NO_OPTION);
        if (dialogResult == JOptionPane.YES_OPTION) {
            GameSelectionFrame.startIt(e.p);
            this.dispose();
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit to desktop? ", "Warning", YES_NO_OPTION);
        if (dialogResult == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        new HelpScreen().setVisible(true);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

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
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSlider jSlider1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables

}

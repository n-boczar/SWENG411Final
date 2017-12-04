/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.FiveCardPokerEngine;
import Model.AIPlayer;
import Model.Player;
import static View.GameSelectionFrame.player;
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
 * @author fernandocarrillo
 */
public class FCPokerFrame extends javax.swing.JFrame {

    public static boolean ante;
    public static int currency;
    public FiveCardPokerEngine e;
    public int c = currency;
    public int roundNumber = 0;
    public int playerMoveChoice = 0;
    public int betAmount = 0;
    public int universalBetAmountOwedR1 = 0;
    public int universalBetAmountOwedR2 = 0;
    public int playerWon;
    public AIPlayer ai1 = new AIPlayer();
    public AIPlayer ai2 = new AIPlayer();
    public AIPlayer ai3 = new AIPlayer();

    public static void startIt(Player player, boolean x) {

        ante = x;
        FCPokerFrame.main(null);

    }

    /**
     * Creates new form FCPokerFrame
     */
    public FCPokerFrame() throws IOException {
        initComponents();

        if (ante == true) {
            JOptionPane.showMessageDialog(null, "Please ANTE UP 25 chips to start playing.", "Ante Up", JOptionPane.OK_OPTION);
        }

        // Take out the ante from the players currency before showing it
        Player.setCurrency(Player.getCurrency() - 25);

        // Show the current currency of the player
        jTextField2.setText(String.valueOf(Player.currency));

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
        e = new FiveCardPokerEngine(ai1, ai2, ai3);

        // Show the card in their respective panels for the player
        JLabel card_1 = new JLabel(new ImageIcon(e.playerHand.elementAt(0).getCardImage()));
        jPanel4.add(card_1);

        JLabel card_2 = new JLabel(new ImageIcon(e.playerHand.elementAt(1).getCardImage()));
        jPanel4.add(card_2);

        JLabel card_3 = new JLabel(new ImageIcon(e.playerHand.elementAt(2).getCardImage()));
        jPanel4.add(card_3);

        JLabel card_4 = new JLabel(new ImageIcon(e.playerHand.elementAt(3).getCardImage()));
        jPanel4.add(card_4);

        JLabel card_5 = new JLabel(new ImageIcon(e.playerHand.elementAt(4).getCardImage()));
        jPanel4.add(card_5);

        jPanel4.doLayout();

        roundNumber = 1;

    }

    public void paint(Graphics g) {
        super.paint(g);
    }

    public void runCallChoiceRound1() {

        System.out.println("Universal Amount Owed: " + universalBetAmountOwedR1);

        if (roundNumber == 1) {
            universalBetAmountOwedR1 = e.startRound1(playerMoveChoice, betAmount);
            System.out.println("Universal Amount Owed: " + universalBetAmountOwedR1);
        }

        if (universalBetAmountOwedR1 != 0) {
            roundNumber = 1;
        } else {
            roundNumber = 2;
        }

    }

    public void runBetChoiceRound1() {

        //Update player currency
        Player.setCurrency(Player.getCurrency());

        // Set the bet amount from the text field
        betAmount = Integer.parseInt(jTextField1.getText());

        System.out.println("BET AMOUNT: " + betAmount);

        if (betAmount > Player.getCurrency()) {

            System.out.println("INSIDE FIRST USER BET MESSAGE.");
            JOptionPane.showMessageDialog(null, "You're betting more than you actually have, retry!", "Warning", JOptionPane.OK_OPTION);

        } else {

            // Show the current currency of the player
            jTextField2.setText(String.valueOf(Player.getCurrency() - betAmount));

            System.out.println("Universal Amount Owed: " + universalBetAmountOwedR1);

            if (roundNumber == 1) {
                universalBetAmountOwedR1 = e.startRound1(playerMoveChoice, betAmount);
                System.out.println("Universal Amount Owed: " + universalBetAmountOwedR1);
            }

            if (universalBetAmountOwedR1 != 0) {
                roundNumber = 1;
            } else {
                roundNumber = 2;
            }
        }
    }

    public void runCallChoiceRound2() {
        if (roundNumber == 2) {
            universalBetAmountOwedR2 = e.startRound2(playerMoveChoice, betAmount);
            System.out.println("Universal Amount Owed: " + universalBetAmountOwedR2);
        }

        if (universalBetAmountOwedR2 == 0) {

            // Call compareHands function and determine the winner
            playerWon = e.compareHands(e.playerHand, e.AI_1Hand, e.AI_2Hand, e.AI_3Hand);
            System.out.println("PLAYER WON # : " + playerWon);
            if (playerWon == 0) {
                JOptionPane.showMessageDialog(null, "User player won the game!", "Winner", JOptionPane.OK_OPTION);
                if(!(player.getCurrency() < 25)){
                startIt(player, true);
                this.dispose();
                }else{
                    System.exit(0); 
                }
            }
            if (playerWon == 1) {
                JOptionPane.showMessageDialog(null, "AI player 1 won the game!", "Winner", JOptionPane.OK_OPTION);
                if(!(player.getCurrency() < 25)){
                startIt(player, true);
                this.dispose();
                }else{
                    System.exit(0); 
                }
            }
            if (playerWon == 2) {
                JOptionPane.showMessageDialog(null, "AI player 2 won the game!", "Winner", JOptionPane.OK_OPTION);
                if(!(player.getCurrency() < 25)){
                startIt(player, true);
                this.dispose();
                }else{
                    System.exit(0); 
                }
            }
            if (playerWon == 3) {
                JOptionPane.showMessageDialog(null, "AI player 3 won the game!", "Winner", JOptionPane.OK_OPTION);
                if(!(player.getCurrency() < 25)){
                startIt(player, true);
                this.dispose();
                }else{
                    System.exit(0); 
                }
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

    public void runBetChoiceRound2() {

        if (roundNumber == 2) {
            universalBetAmountOwedR2 = e.startRound2(playerMoveChoice, betAmount);
            System.out.println("Universal Amount Owed: " + universalBetAmountOwedR2);
        }

        if (universalBetAmountOwedR2 == 0) {
            // Call compareHands function and determine the winner
            
            playerWon = e.compareHands(e.playerHand, e.AI_1Hand, e.AI_2Hand, e.AI_3Hand);
            System.out.println("PLAYER WON # : " + playerWon);
            
            if (playerWon == 0) {
                JOptionPane.showMessageDialog(null, "User player won the game!", "Winner", JOptionPane.OK_OPTION);
                if(!(player.getCurrency() < 25)){
                startIt(player, true);
                this.dispose();
                }else{
                    System.exit(0); 
                }
            }
            if (playerWon == 1) {
                JOptionPane.showMessageDialog(null, "AI player 1 won the game!", "Winner", JOptionPane.OK_OPTION);
                if(!(player.getCurrency() < 25)){
                startIt(player, true);
                this.dispose();
                }else{
                    System.exit(0); 
                }
            }
            if (playerWon == 2) {
                JOptionPane.showMessageDialog(null, "AI player 2 won the game!", "Winner", JOptionPane.OK_OPTION);
                if(!(player.getCurrency() < 25)){
                startIt(player, true);
                this.dispose();
                }else{
                    System.exit(0); 
                }
            }
            if (playerWon == 3) {
                JOptionPane.showMessageDialog(null, "AI player 3 won the game!", "Winner", JOptionPane.OK_OPTION);
                if(!(player.getCurrency() < 25)){
                startIt(player, true);
                this.dispose();
                }else{
                    System.exit(0); 
                }
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jRadioButtonMenuItem1 = new javax.swing.JRadioButtonMenuItem();
        jFrame1 = new javax.swing.JFrame();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jSlider1 = new javax.swing.JSlider();
        jButton4 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();

        jRadioButtonMenuItem1.setSelected(true);
        jRadioButtonMenuItem1.setText("jRadioButtonMenuItem1");

        jFrame1.setMinimumSize(new java.awt.Dimension(576, 584));
        jFrame1.setSize(new java.awt.Dimension(576, 584));

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(655, 628));
        setMinimumSize(new java.awt.Dimension(655, 628));
        setResizable(false);
        setSize(new java.awt.Dimension(655, 628));

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));

        jButton2.setText("Call");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Bet");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jSlider1.setMajorTickSpacing(100);
        jSlider1.setMaximum(500);
        jSlider1.setMinorTickSpacing(50);
        jSlider1.setPaintTicks(true);
        jSlider1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSlider1StateChanged(evt);
            }
        });

        jButton4.setText("Fold");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jTextField1.setEditable(false);
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jTextField2.setEditable(false);

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Currency");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addGap(18, 18, 18)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(40, 40, 40))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton4)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton2))
                    .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3))
                .addContainerGap(22, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        jPanel3.setBackground(new java.awt.Color(51, 51, 51));
        jPanel3.setMaximumSize(new java.awt.Dimension(576, 584));
        jPanel3.setMinimumSize(new java.awt.Dimension(576, 584));
        jPanel3.setPreferredSize(new java.awt.Dimension(576, 584));
        jPanel3.setLayout(new java.awt.GridLayout(1, 0));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 655, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed

    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        // Set player choice to call
        playerMoveChoice = 2;
        betAmount = 0;

        if (roundNumber == 1) {
            runCallChoiceRound1();
        }

        if (roundNumber == 2) {
            runCallChoiceRound2();
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        // Set player move to bet
        playerMoveChoice = 1;

        if (roundNumber == 1) {
            runBetChoiceRound1();
        }

        if (roundNumber == 2) {
            runBetChoiceRound2();
        }

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        // Just quit the game
        this.dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jSlider1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSlider1StateChanged
        jTextField1.setText(Integer.toString(jSlider1.getValue()));
    }//GEN-LAST:event_jSlider1StateChanged

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
            java.util.logging.Logger.getLogger(FCPokerFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FCPokerFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FCPokerFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FCPokerFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new FCPokerFrame().setVisible(true);

                } catch (IOException ex) {
                    Logger.getLogger(FCPokerFrame.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem1;
    private javax.swing.JSlider jSlider1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}

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
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
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
    public JLabel tableCard1;
    public JLabel tableCard2;
    public JLabel tableCard3;
    public JLabel tableCard4;
    public JLabel tableCard5;
    public JLabel card_1;
    public JLabel card_2;
    //public int round = 0;
    public AIPlayer ai1 = new AIPlayer();
    public AIPlayer ai2 = new AIPlayer();
    public AIPlayer ai3 = new AIPlayer();
    Player p;

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

        //Update the amount you owe 
        jTextField3.setText(String.valueOf(e.pOwed));

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
        jTextField3 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jSlider1 = new javax.swing.JSlider();
        jTextField2 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jTextField4 = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
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

        jTextField3.setEditable(false);
        jTextField3.setPreferredSize(new java.awt.Dimension(50, 25));
        jPanel2.add(jTextField3);

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
        jSlider1.setPreferredSize(new java.awt.Dimension(130, 31));
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

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 51));
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

        jPanel7.setMaximumSize(new java.awt.Dimension(100, 110));
        jPanel7.setMinimumSize(new java.awt.Dimension(100, 110));
        jPanel7.setPreferredSize(new java.awt.Dimension(100, 110));
        jPanel7.setLayout(new java.awt.GridLayout(3, 2));

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 51));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("AI1:");
        jPanel7.add(jLabel4);

        jTextField5.setEditable(false);
        jTextField5.setBackground(new java.awt.Color(0, 0, 51));
        jTextField5.setForeground(new java.awt.Color(255, 255, 255));
        jTextField5.setMaximumSize(new java.awt.Dimension(59, 20));
        jTextField5.setMinimumSize(new java.awt.Dimension(59, 20));
        jPanel7.add(jTextField5);

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 51));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("AI2:");
        jPanel7.add(jLabel5);

        jTextField6.setEditable(false);
        jTextField6.setBackground(new java.awt.Color(0, 0, 51));
        jTextField6.setForeground(new java.awt.Color(255, 255, 255));
        jTextField6.setMaximumSize(new java.awt.Dimension(59, 20));
        jTextField6.setMinimumSize(new java.awt.Dimension(59, 20));
        jPanel7.add(jTextField6);

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 51));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("AI3:");
        jPanel7.add(jLabel6);

        jTextField7.setEditable(false);
        jTextField7.setBackground(new java.awt.Color(0, 0, 51));
        jTextField7.setForeground(new java.awt.Color(255, 255, 255));
        jTextField7.setMaximumSize(new java.awt.Dimension(59, 20));
        jTextField7.setMinimumSize(new java.awt.Dimension(59, 20));
        jPanel7.add(jTextField7);

        jPanel6.add(jPanel7);

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

        jTextField4.setEditable(false);
        jTextField4.setBackground(new java.awt.Color(0, 0, 51));
        jTextField4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextField4.setForeground(new java.awt.Color(255, 255, 255));
        jTextField4.setText("Pot = ");
        jTextField4.setMaximumSize(new java.awt.Dimension(49, 23));
        jTextField4.setMinimumSize(new java.awt.Dimension(49, 23));
        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });
        jPanel5.add(jTextField4, java.awt.BorderLayout.PAGE_END);

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

        jMenu3.setText("Change Game");

        jMenuItem4.setText("BlackJack");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem4);

        jMenuItem5.setText("Five Card Poker");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem5);

        jMenuItem6.setText("Roulette");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem6);

        jMenu1.add(jMenu3);

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

        //If the user calls then they aren't betting any more
        betAmount = 0;

        //set playerMoveChoice to 2 which means CALL
        playerMoveChoice = 2;

        //Start betting round with user calling previous bet
        universalBetAmountOwed = e.startRound(playerMoveChoice, betAmount);

        //Display all the players moves
        display();

        //Update the amount you owe 
        jTextField3.setText(String.valueOf(e.pOwed));

        //Update currency
        jTextField1.setText(String.valueOf(Player.getCurrency()));

        //If all the other players folded then the user should win 
        if (ai1.fold && ai2.fold && ai3.fold) {
            check();
            return;
        }

        /*
            If the betting clears and no one else owes anything check to see what round it is
            so the right table card can be displayed and then round number increments 
         */
        if (universalBetAmountOwed == 0) {
            checkRound();
            //roundNumber++;
            return;
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
        // Set the bet amount from the text field
        betAmount = Integer.parseInt(jTextField2.getText());
        playerMoveChoice = 1;
        //make sure the user isn't betting more than what anyone has
        if (betAmount > Player.getCurrency() || (betAmount > ai1.getCurrency() && ai1.active) || (betAmount > ai2.getCurrency() && ai2.active) || (betAmount > ai3.getCurrency() && ai3.active)) {
            JOptionPane.showMessageDialog(null, "You're betting more than you or another player has, retry!", "Warning", JOptionPane.OK_OPTION);
        } else if (Player.didBet) {
            //players should only be able to bet once per round
            JOptionPane.showMessageDialog(null, "You already bet once this round.", "Warning", JOptionPane.OK_OPTION);
        } else if (betAmount < e.pOwed){
            JOptionPane.showMessageDialog(null, "Can't bet less than you owe.", "Warning", JOptionPane.OK_OPTION);
        } 
        else {
            //set didBet to true so player can only bet once and start betting
            Player.didBet = true;
            universalBetAmountOwed = e.startRound(playerMoveChoice, betAmount);

            //Display all the players moves
            display();

            //Update the amount you owe 
            jTextField3.setText(String.valueOf(e.pOwed));

            //update the users displayed currency
            jTextField1.setText(String.valueOf(Player.getCurrency()));

            /*
            If all the other players folded then the user should win 
             */
            if (ai1.fold && ai2.fold && ai3.fold) {
                check();
                return;
            }
            /*
            If the betting clears and no one else owes anything check to see what round it is
            so the right table card can be displayed and then round number increments 
             */
            if (universalBetAmountOwed == 0) {
                checkRound();
                //roundNumber++;
                return;
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
            //Display all the players moves
            display();
        } while (universalBetAmountOwed != 0);

        //If it is the first round it should display 2 cards, run until there is no univsalBetAmountOwed and then check for the winner
        if (roundNumber == 1) {
            jTextArea1.append("ROUND 2\n");
            //Show another board card (turn)
            tableCard4 = new JLabel(new ImageIcon(e.boardCards.elementAt(3).getCardImage()));
            jPanel4.add(tableCard4);
            jPanel4.doLayout();
            //run through the betting until none of the players owe anything
            do {
                universalBetAmountOwed = e.startRound(playerMoveChoice, betAmount);
                //Display all the players moves
                display();
            } while (universalBetAmountOwed != 0);
            jTextArea1.append("ROUND 3\n");
            //Show another board card (river)
            tableCard5 = new JLabel(new ImageIcon(e.boardCards.elementAt(4).getCardImage()));
            jPanel4.add(tableCard5);
            jPanel4.doLayout();
            //run through the betting until none of the players owe anything
            do {
                universalBetAmountOwed = e.startRound(playerMoveChoice, betAmount);
                //Display all the players moves
                display();
            } while (universalBetAmountOwed != 0);
            //check for a winner
            check();
        }

        //If it is the second round display 1 card, run through the betting, and then check for the winner
        if (roundNumber == 2) {
            jTextArea1.append("ROUND 3\n");
            //Show another board card (river)
            tableCard5 = new JLabel(new ImageIcon(e.boardCards.elementAt(4).getCardImage()));
            jPanel4.add(tableCard5);
            jPanel4.doLayout();
            //run through the betting until none of the players owe anything
            do {
                universalBetAmountOwed = e.startRound(playerMoveChoice, betAmount);
                //Display all the players moves
                display();

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
     * Function: CHECK Check to see who won the game and if fall players are
     * still in the game if they have less than 25 they should be set to
     * inactive
     */
    public void check() {
        // Call compareHands function and determine the winner
        playerWon = e.compareHands(e.playerHand, e.AI_1Hand, e.AI_2Hand, e.AI_3Hand, e.boardCards);
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException ex) {
            Logger.getLogger(TexasHoldemFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        //}
        System.out.println("Player won: " + playerWon);
        if (playerWon == 0) {
            JOptionPane.showMessageDialog(null, "User player won the game with a " + e.winningHand, "Winner", JOptionPane.OK_OPTION);
        }
        if (playerWon == 1) {
            JOptionPane.showMessageDialog(null, "AI player 1 won the game with a " + e.winningHand, "Winner", JOptionPane.OK_OPTION);
        }
        if (playerWon == 2) {
            JOptionPane.showMessageDialog(null, "AI player 2 won the game with a " + e.winningHand, "Winner", JOptionPane.OK_OPTION);
        }
        if (playerWon == 3) {
            JOptionPane.showMessageDialog(null, "AI player 3 won the game with a " + e.winningHand, "Winner", JOptionPane.OK_OPTION);
        }

        //update the users displayed currency
        //jTextField1.setText(String.valueOf(Player.getCurrency()));

        //Check if player or AI have enough currencies to go to next round
        if (Player.getCurrency() < 25) {
            Player.active = false;
            //Show the user lost and exit game
            JOptionPane.showMessageDialog(null, "You ran out of currency! Please reenter the Casino to play again.", "Game Over", JOptionPane.OK_OPTION);
            System.exit(0);
        }
        if (ai1.getCurrency() < 25) {
            ai1.active = false;
            ai1.fold = false;
            //black out their displayed currency
            jTextField5.setBackground(Color.white);
        }
        if (ai2.getCurrency() < 25) {
            ai2.active = false;
            ai2.fold = false;
            //black out their displayed currency
            jTextField6.setBackground(Color.white);
        }
        if (ai3.getCurrency() < 25) {
            ai3.active = false;
            ai3.fold = false;
            //black out their displayed currency
            jTextField7.setBackground(Color.white);
        }

        //reset the folded players at the end of each game
        e.reset();

        //If all the ai's are inactive then the user wins! Exit back to game selection screen
        if (!ai1.active && !ai2.active && !ai3.active) {
            //JOptionPane.showConfirmDialog(null, "YOU WIN!! Press OK to ", "Congrats!", JOptionPane.OK_OPTION);   
            int response = JOptionPane.showConfirmDialog(null, "YOU WIN! Press OK to exit back to the game selection screen.",
                    "CONGRATS!!", JOptionPane.OK_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (response == JOptionPane.CLOSED_OPTION || response == JOptionPane.OK_OPTION) {
                GameSelectionFrame.startIt(e.p);
                this.dispose();
            }
        }

        clearTable();
        newHand();
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * Function: CLEAR TABLE gets rid of all existing JLabel Cards Images so the
     * new hand and table can be displayed
     */
    public void clearTable() {
        //If a card has been created destroy it
        if (tableCard1 != null) {
            jPanel4.remove(tableCard1);
        }
        if (tableCard2 != null) {
            jPanel4.remove(tableCard2);
        }
        if (tableCard3 != null) {
            jPanel4.remove(tableCard3);
        }
        //if (tableCard5 != null) {
            //jPanel4.remove(tableCard5);
        //}
        if (tableCard4 != null) {
            jPanel4.remove(tableCard4);
        }
        if (tableCard5 != null) {
        jPanel4.remove(tableCard5);
        }
        if (card_1 != null) {
            jPanel6.remove(card_1);
        }
        if (card_2 != null) {
            jPanel6.remove(card_2);
        }

        repaint();
    }

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
            //Add to display
            jTextArea1.append("ROUND 2\n");
            //Show board card (turn)
            tableCard4 = new JLabel(new ImageIcon(e.boardCards.elementAt(3).getCardImage()));
            jPanel4.add(tableCard4);
            jPanel4.doLayout();
            roundNumber++;
            return;
        } else if (roundNumber == 2) {
            //Add to display
            jTextArea1.append("ROUND 3\n");
            //Show board card (river)
            tableCard5 = new JLabel(new ImageIcon(e.boardCards.elementAt(4).getCardImage()));
            jPanel4.add(tableCard5);
            jPanel4.doLayout();
            roundNumber++;
            return;
        } else if (roundNumber == 3) {
            check();
            return;
        }

    }

    /**
     * Function: New Hand Start a new round of Texas Hold 'em Shuffle cards Deal
     * new hands
     */
    public void newHand() {
        
        //Clear the display messages and start them over
        jTextArea1.setText(null);
        jTextArea1.append("ROUND 1\n");
        //jTextField4.setText("Pot = " + e.pot);
        //jTextField3.setText("" + e.pOwed);
        //jTextField1.setText(String.valueOf(e.p.getCurrency()));
        //jTextField5.setText(String.valueOf(e.ai1.getCurrency()));
        //jTextField6.setText(String.valueOf(e.ai2.getCurrency()));
        //jTextField7.setText(String.valueOf(e.ai3.getCurrency()));

        e.deal();
        // Show the card in their respective panels for the player
        card_1 = new JLabel(new ImageIcon(e.playerHand.elementAt(0).getCardImage()));
        jPanel6.add(card_1);

        card_2 = new JLabel(new ImageIcon(e.playerHand.elementAt(1).getCardImage()));
        jPanel6.add(card_2);

        jPanel6.doLayout();

        // Show the flop
        tableCard1 = new JLabel(new ImageIcon(e.boardCards.elementAt(0).getCardImage()));
        jPanel4.add(tableCard1);

        tableCard2 = new JLabel(new ImageIcon(e.boardCards.elementAt(1).getCardImage()));
        jPanel4.add(tableCard2);

        tableCard3 = new JLabel(new ImageIcon(e.boardCards.elementAt(2).getCardImage()));
        jPanel4.add(tableCard3);

        jPanel4.doLayout();

        repaint();
        
        //update the display numbers 
        display();

        roundNumber = 1;
        return;
    }

    /**
     * Function: DISPLAY display what each player did in the text area if they
     * folded then no more moves will be displayed
     */
    public void display() {
        //Reset jslider 
        jSlider1.setValue(10);
        jTextField2.setText(String.valueOf(jSlider1.getValue()));
        
        //Update the ai's currency
        jTextField5.setText(String.valueOf(e.ai1.getCurrency()));
        jTextField6.setText(String.valueOf(e.ai2.getCurrency()));
        jTextField7.setText(String.valueOf(e.ai3.getCurrency()));
        
        //Update the user's owed amount and currency
        jTextField3.setText(String.valueOf(e.pOwed));
        jTextField1.setText(String.valueOf(e.p.getCurrency()));
        
        //Update the pot 
        jTextField4.setText("Pot = " + e.pot);

        //If the players made a move (they aren't out yet) then display what they did
        if (e.playerMove != null) {
            jTextArea1.append(e.playerMove + "\n");
        }
        if (e.ai1Move != null) {
            jTextArea1.append(e.ai1Move + "\n");
        }
        if (e.ai2Move != null) {
            jTextArea1.append(e.ai2Move + "\n");
        }
        if (e.ai3Move != null) {
            jTextArea1.append(e.ai3Move + "\n");
        }
        e.playerMove = null;
        e.ai1Move = null;
        e.ai2Move = null;
        e.ai3Move = null;
        return;
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

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

    // blackjack
    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
         try {
            // TODO add your handling code here:
            BlackJackFrame.startIt(p,true);
        } catch (IOException ex) {
            Logger.getLogger(FCPokerFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.dispose();
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    // five card
    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    // roulette
    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        // TODO add your handling code here:
        RoulletteWheel.startIt(p, false);
        this.dispose();
    }//GEN-LAST:event_jMenuItem6ActionPerformed

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
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSlider jSlider1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    // End of variables declaration//GEN-END:variables

}

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
import static javax.swing.JOptionPane.YES_NO_OPTION;

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
    public int counter = 0;
    public JLabel card_1;
    public JLabel card_2;
    public JLabel card_3;
    public JLabel card_4;
    public JLabel card_5;
    public Player p;
    public boolean submitButton = false;
    public boolean firstBetPlaced = false;
    public int replacementLimit;

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
        Player.setCurrency(Player.currency - 25);

        // Show the current currency of the player
        jTextField2.setText(Integer.toString(Player.currency));

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
        card_1 = new JLabel(new ImageIcon(e.playerHand.elementAt(0).getCardImage()));
        jPanel4.add(card_1);

        card_2 = new JLabel(new ImageIcon(e.playerHand.elementAt(1).getCardImage()));
        jPanel4.add(card_2);

        card_3 = new JLabel(new ImageIcon(e.playerHand.elementAt(2).getCardImage()));
        jPanel4.add(card_3);

        card_4 = new JLabel(new ImageIcon(e.playerHand.elementAt(3).getCardImage()));
        jPanel4.add(card_4);

        card_5 = new JLabel(new ImageIcon(e.playerHand.elementAt(4).getCardImage()));
        jPanel4.add(card_5);

        jPanel4.doLayout();

        roundNumber = 1;

        // Set replacement card panel not visible until the end of round 1
        jPanel5.setVisible(true);

    }

    public void paint(Graphics g) {
        super.paint(g);
    }

    public void runCallChoiceRound1() {

        firstBetPlaced = true;

        System.out.println("Universal Amount Owed: " + universalBetAmountOwedR1);

        if (roundNumber == 1) {
            universalBetAmountOwedR1 = e.startRound1(playerMoveChoice, betAmount);
            System.out.println("Universal Amount Owed: " + universalBetAmountOwedR1);
            jTextField3.setText(Integer.toString(e.ai1.getBet()));
            jTextField4.setText(Integer.toString(e.ai2.getBet()));
            jTextField5.setText(Integer.toString(e.ai3.getBet()));
        }

        if (universalBetAmountOwedR1 != 0) {
            roundNumber = 1;
        } else {
            roundNumber = 2;
            counter++;
            if (counter == 1) {

            }
        }
        // Update total amount owed by player text field
        jTextField6.setText(Integer.toString(e.totalBetOwedByPlayer));
        // Show the current currency of the player
        jTextField2.setText(String.valueOf(Player.getCurrency()));

    }

    public void runBetChoiceRound1() {

        firstBetPlaced = true;

        //Update player currency
        Player.setCurrency(Player.getCurrency());

        // Set the bet amount from the text field
        betAmount = Integer.parseInt(jTextField1.getText());

        System.out.println("BET AMOUNT: " + betAmount);

        if (betAmount > Player.getCurrency()) {

            System.out.println("INSIDE FIRST USER BET MESSAGE.");
            JOptionPane.showMessageDialog(null, "You're betting more than you actually have, retry!", "Warning", JOptionPane.OK_OPTION);
        } else if (betAmount < e.totalBetOwedByPlayer) {
            System.out.println("INSIDE SECOND USER BET MESSAGE.");
            JOptionPane.showMessageDialog(null, "You need to bet more than you owe to the table, retry!", "Warning", JOptionPane.OK_OPTION);
        } else {

            // Show the current currency of the player
            jTextField2.setText(Integer.toString((Player.currency - betAmount)));

            System.out.println("Universal Amount Owed: " + universalBetAmountOwedR1);

            if (roundNumber == 1) {
                universalBetAmountOwedR1 = e.startRound1(playerMoveChoice, betAmount);
                System.out.println("Universal Amount Owed: " + universalBetAmountOwedR1);
                jTextField3.setText(Integer.toString(e.ai1.getBet()));
                jTextField4.setText(Integer.toString(e.ai2.getBet()));
                jTextField5.setText(Integer.toString(e.ai3.getBet()));
                // Reset Bet
                jTextField1.setText(Integer.toString(0));
            }

            if (universalBetAmountOwedR1 != 0) {
                roundNumber = 1;
            } else {
                roundNumber = 2;
                counter++;
                if (counter == 1) {
                    JOptionPane.showMessageDialog(null, "Replacing your cards!", "Betting Round 1 Over!", JOptionPane.OK_OPTION);
                    replaceCards();
                    jPanel5.setVisible(false);
                }
            }
        }
        // Update total amount owed by player text field
        jTextField6.setText(Integer.toString(e.totalBetOwedByPlayer));
        // Show the current currency of the player
        jTextField2.setText(String.valueOf(Player.getCurrency()));
    }

    public void runCallChoiceRound2() {

        if (roundNumber == 2) {
            universalBetAmountOwedR2 = e.startRound2(playerMoveChoice, betAmount);
            System.out.println("Universal Amount Owed: " + universalBetAmountOwedR2);
            jTextField3.setText(Integer.toString(e.ai1.getBet()));
            jTextField4.setText(Integer.toString(e.ai2.getBet()));
            jTextField5.setText(Integer.toString(e.ai3.getBet()));
        }

        // Update total amount owed by player text field
        jTextField6.setText(Integer.toString(e.totalBetOwedByPlayer));
        // Show the current currency of the player
        jTextField2.setText(String.valueOf(Player.getCurrency()));

        if (universalBetAmountOwedR2 == 0) {

            // Call compareHands function and determine the winner
            playerWon = e.compareHands(e.playerHand, e.AI_1Hand, e.AI_2Hand, e.AI_3Hand);
            System.out.println("PLAYER WON # : " + playerWon);
            p.totalAmountOwed = 0;
            if (playerWon == 0) {
                JOptionPane.showMessageDialog(null, "User player has a: " + e.winningHand, "Winner", JOptionPane.OK_OPTION);
                Player.currency = Player.getCurrency() + 25;
                if (!(Player.currency < 25)) {
                    startIt(player, true);
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Restart the game to get more money!", "No money!", JOptionPane.OK_OPTION);
                    this.dispose();
                    CAssignFrameNew.main(null);
                }
            }
            if (playerWon == 1) {
                JOptionPane.showMessageDialog(null, "AI player 1 has a: " + e.winningHand, "Winner", JOptionPane.OK_OPTION);
                if (!(Player.currency < 25)) {
                    startIt(player, true);
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Restart the game to get more money!", "No money!", JOptionPane.OK_OPTION);
                    this.dispose();
                    CAssignFrameNew.main(null);
                }
            }
            if (playerWon == 2) {
                JOptionPane.showMessageDialog(null, "AI player 2 has a: " + e.winningHand, "Winner", JOptionPane.OK_OPTION);
                if (!(Player.currency < 25)) {
                    startIt(player, true);
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Restart the game to get more money!", "No money!", JOptionPane.OK_OPTION);
                    this.dispose();
                    CAssignFrameNew.main(null);
                }
            }
            if (playerWon == 3) {
                JOptionPane.showMessageDialog(null, "AI player 3 has a: " + e.winningHand, "Winner", JOptionPane.OK_OPTION);
                if (!(Player.currency < 25)) {
                    startIt(player, true);
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Restart the game to get more money!", "No money!", JOptionPane.OK_OPTION);
                    this.dispose();
                    CAssignFrameNew.main(null);
                }
            }

//            //Check if player or AI have enough currencies to go to next round
//            if (Player.getCurrency() == 0) {
//                Player.active = false;
//            }
//            if (ai1.getCurrency() == 0) {
//                ai1.active = false;
//            }
//            if (ai2.getCurrency() == 0) {
//                ai2.active = false;
//            }
//            if (ai3.getCurrency() == 0) {
//                ai3.active = false;
//            }
        }

    }

    public void runBetChoiceRound2() {

        //Update player currency
        Player.setCurrency(Player.getCurrency());

        // Set the bet amount from the text field
        betAmount = Integer.parseInt(jTextField1.getText());

        System.out.println("BET AMOUNT: " + betAmount);

        if (betAmount > Player.getCurrency()) {

            System.out.println("INSIDE FIRST USER BET MESSAGE.");
            JOptionPane.showMessageDialog(null, "You're betting more than you actually have, retry!", "Warning", JOptionPane.OK_OPTION);
        } else if (betAmount < e.totalBetOwedByPlayer) {
            System.out.println("INSIDE SECOND USER BET MESSAGE.");
            JOptionPane.showMessageDialog(null, "You need to bet more than you owe to the table, retry!", "Warning", JOptionPane.OK_OPTION);
        } else {

            // Show the current currency of the player
            jTextField2.setText(Integer.toString((Player.currency - betAmount)));

            System.out.println("Universal Amount Owed: " + universalBetAmountOwedR1);

            if (roundNumber == 2) {
                universalBetAmountOwedR2 = e.startRound2(playerMoveChoice, betAmount);
                System.out.println("Universal Amount Owed: " + universalBetAmountOwedR2);
                jTextField3.setText(Integer.toString(e.ai1.getBet()));
                jTextField4.setText(Integer.toString(e.ai2.getBet()));
                jTextField5.setText(Integer.toString(e.ai3.getBet()));
                // Reset Bet
                jTextField1.setText(Integer.toString(0));
            }

            // Update total amount owed by player text field
            jTextField6.setText(Integer.toString(e.totalBetOwedByPlayer));
            // Show the current currency of the player
            jTextField2.setText(String.valueOf(Player.getCurrency()));

            if (universalBetAmountOwedR2 == 0) {
                // Call compareHands function and determine the winner

                playerWon = e.compareHands(e.playerHand, e.AI_1Hand, e.AI_2Hand, e.AI_3Hand);
                System.out.println("PLAYER WON # : " + playerWon);
                p.totalAmountOwed = 0;
                if (playerWon == 0) {
                    JOptionPane.showMessageDialog(null, "User player has a: " + e.winningHand, "Winner", JOptionPane.OK_OPTION);
                    Player.currency = Player.getCurrency() + 25;
                    if (!(Player.currency < 25)) {
                        startIt(player, true);
                        this.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Restart the game to get more money!", "No money!", JOptionPane.OK_OPTION);
                        this.dispose();
                        CAssignFrameNew.main(null);
                    }
                }
                if (playerWon == 1) {
                    JOptionPane.showMessageDialog(null, "AI player 1 has a: " + e.winningHand, "Winner", JOptionPane.OK_OPTION);
                    if (!(Player.currency < 25)) {
                        startIt(player, true);
                        this.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Restart the game to get more money!", "No money!", JOptionPane.OK_OPTION);
                        this.dispose();
                        CAssignFrameNew.main(null);
                    }
                }
                if (playerWon == 2) {
                    JOptionPane.showMessageDialog(null, "AI player 2 has a: " + e.winningHand, "Winner", JOptionPane.OK_OPTION);
                    if (!(Player.currency < 25)) {
                        startIt(player, true);
                        this.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Restart the game to get more money!", "No money!", JOptionPane.OK_OPTION);
                        this.dispose();
                        CAssignFrameNew.main(null);
                    }
                }
                if (playerWon == 3) {
                    JOptionPane.showMessageDialog(null, "AI player 3 has a: " + e.winningHand, "Winner", JOptionPane.OK_OPTION);
                    if (!(Player.currency < 25)) {
                        startIt(player, true);
                        this.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Restart the game to get more money!", "No money!", JOptionPane.OK_OPTION);
                        this.dispose();
                        CAssignFrameNew.main(null);
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
    }

    public void replaceCards() {

        jPanel5.setVisible(true);

        repaint();

        if (jCheckBox1.isSelected()) {
            jPanel4.remove(card_1);
            e.playerHand.setElementAt(e.dealNew(), 0);
            // Show the card in their respective panels for the player
            card_1 = new JLabel(new ImageIcon(e.playerHand.elementAt(0).getCardImage()));
            jPanel4.add(card_1);
            jPanel4.doLayout();
        }
        if (jCheckBox2.isSelected()) {
            jPanel4.remove(card_2);
            e.playerHand.setElementAt(e.dealNew(), 1);
            // Show the card in their respective panels for the player
            card_2 = new JLabel(new ImageIcon(e.playerHand.elementAt(1).getCardImage()));
            jPanel4.add(card_2);
            jPanel4.doLayout();
        }
        if (jCheckBox3.isSelected()) {
            jPanel4.remove(card_3);
            e.playerHand.setElementAt(e.dealNew(), 2);
            // Show the card in their respective panels for the player
            card_3 = new JLabel(new ImageIcon(e.playerHand.elementAt(2).getCardImage()));
            jPanel4.add(card_3);
            jPanel4.doLayout();
        }
        if (jCheckBox4.isSelected()) {
            jPanel4.remove(card_4);
            e.playerHand.setElementAt(e.dealNew(), 3);
            // Show the card in their respective panels for the player
            card_4 = new JLabel(new ImageIcon(e.playerHand.elementAt(3).getCardImage()));
            jPanel4.add(card_4);
            jPanel4.doLayout();
        }
        if (jCheckBox5.isSelected()) {
            jPanel4.remove(card_5);
            e.playerHand.setElementAt(e.dealNew(), 4);
            // Show the card in their respective panels for the player
            card_5 = new JLabel(new ImageIcon(e.playerHand.elementAt(4).getCardImage()));
            jPanel4.add(card_5);
            jPanel4.doLayout();
        }

        repaint();

        jPanel5.setVisible(false);

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
        jLabel2 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jCheckBox4 = new javax.swing.JCheckBox();
        jCheckBox5 = new javax.swing.JCheckBox();
        jButton1 = new javax.swing.JButton();
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

        jLabel2.setText("AI 1 Bet:");

        jTextField3.setEditable(false);
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        jLabel3.setText("AI 2 Bet:");

        jTextField4.setEditable(false);

        jLabel4.setText("AI 3 Bet:");

        jTextField5.setEditable(false);

        jLabel5.setText("Amount You Owe Table:");

        jTextField6.setEditable(false);
        jTextField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField6ActionPerformed(evt);
            }
        });

        jLabel6.setText("Choose cards to replace:");

        jCheckBox1.setText("Card #1");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        jCheckBox2.setText("Card #2");

        jCheckBox3.setText("Card #3");

        jCheckBox4.setText("Card #4");

        jCheckBox5.setText("Card #5");

        jButton1.setText("Submit");
        jButton1.setToolTipText("");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jCheckBox3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBox1)
                            .addComponent(jCheckBox2)
                            .addComponent(jCheckBox4)
                            .addComponent(jCheckBox5))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox3)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 525, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

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

        jMenuItem5.setText("Texas Hold'Em");
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

        Player.totalAmountOwed = 0;
        // Just quit the game
        GameSelectionFrame.startIt(p);
        this.dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jSlider1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSlider1StateChanged
        jTextField1.setText(Integer.toString(jSlider1.getValue()));
    }//GEN-LAST:event_jSlider1StateChanged

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField6ActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if (jCheckBox1.isSelected()) {
            replacementLimit++;
        }
        if (jCheckBox2.isSelected()) {
            replacementLimit++;
        }
        if (jCheckBox3.isSelected()) {
            replacementLimit++;
        }
        if (jCheckBox4.isSelected()) {
            replacementLimit++;
        }
        if (jCheckBox5.isSelected()) {
            replacementLimit++;
        }

        if (replacementLimit > 3) {
            JOptionPane.showMessageDialog(null, "You can only replace up to three cards!", "Warning", JOptionPane.OK_OPTION);
        } else {
            if (firstBetPlaced == true) {
                submitButton = true;
                replaceCards();
                if (roundNumber == 2) {

                    runBetChoiceRound2();
                }
            } else {
                JOptionPane.showMessageDialog(null, "You need to call or bet before your card replacement!", "Warning", JOptionPane.OK_OPTION);
            }
        }
        replacementLimit = 0;
    }//GEN-LAST:event_jButton1ActionPerformed

    // main menu
    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit? ", "Warning", YES_NO_OPTION);
        if (dialogResult == JOptionPane.YES_OPTION) {
            GameSelectionFrame.startIt(p);
            this.dispose();
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    // dekstop
    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit to desktop? ", "Warning", YES_NO_OPTION);
        if (dialogResult == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    // blackjack
    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        try {
            // TODO add your handling code here:
            BlackJackFrame.startIt(p,true);
        } catch (IOException ex) {
            Logger.getLogger(FCPokerFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.dispose();
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    // hold em
    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
        TexasHoldemFrame.startIt(p,true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    // roulette 
    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        // TODO add your handling code here:
        RoulletteWheel.startIt(p, false);
        this.dispose();
    }//GEN-LAST:event_jMenuItem6ActionPerformed

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
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JCheckBox jCheckBox5;
    private javax.swing.JFrame jFrame1;
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
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem1;
    private javax.swing.JSlider jSlider1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    // End of variables declaration//GEN-END:variables
}

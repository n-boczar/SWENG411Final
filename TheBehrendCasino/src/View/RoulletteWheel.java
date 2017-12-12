/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */





package View;
import Model.Player;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Toolkit;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

/**
 *
 * @author mtw52
 */
public class RoulletteWheel extends javax.swing.JFrame {

    /**
     * Creates new form RoulletteWheel
     */
    
    public RoulletteWheel() {
        initComponents();
    }
    
    public void paint(Graphics g){
        super.paint(g);
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        jOptionPane1 = new javax.swing.JOptionPane();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jButton9 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(102, 102, 102));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);

        jButton10.setFont(new java.awt.Font("Tahoma", 1, 21)); // NOI18N
        jButton10.setIcon(new ImageIcon("FinalChipSmall2.png"));
        jButton10.setText("10");
        jButton10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jButton10MouseReleased(evt);
            }
        });
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.setFont(new java.awt.Font("Tahoma", 1, 21)); // NOI18N
        jButton11.setIcon(new ImageIcon("OrangeChipSmall2.png"));
        jButton11.setText("50");
        jButton11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jButton11MouseReleased(evt);
            }
        });
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton12.setFont(new java.awt.Font("Tahoma", 1, 21)); // NOI18N
        jButton12.setIcon(new ImageIcon("RedChipSmall2.png"));
        jButton12.setText("20");
        jButton12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jButton12MouseReleased(evt);
            }
        });
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jButton14.setFont(new java.awt.Font("Tahoma", 1, 21)); // NOI18N
        jButton14.setIcon(new ImageIcon("BlackChipSmall.png"));
        jButton14.setText("100");
        jButton14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jButton14MouseReleased(evt);
            }
        });
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(15, 89, 0));
        jPanel1.setForeground(new java.awt.Color(51, 255, 0));

        jLabel2.setIcon(new ImageIcon("roulette-table3.png"));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel2))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel2))
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("Balance");

        jPanel2.setBackground(new java.awt.Color(2, 137, 32));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton9.setFont(new java.awt.Font("Tahoma", 1, 21)); // NOI18N
        jButton9.setIcon(new ImageIcon("LBchipSmall.png"));
        jButton9.setText("5");
        jButton9.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jButton9MouseDragged(evt);
            }
        });
        jButton9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton9MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton9MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jButton9MouseReleased(evt);
            }
        });
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jButton1.setText("SPIN");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextField2.setFont(new java.awt.Font("Tahoma", 1, 128)); // NOI18N
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jTextField1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel3.setText("Pay Out");

        jTextField3.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)))
                .addContainerGap(38, Short.MAX_VALUE))
        );

        setBounds(0, 0, 1634, 883);
    }// </editor-fold>//GEN-END:initComponents

    //5 Value Chip
    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton9ActionPerformed
    //10 Value Chip
    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton10ActionPerformed
    //50 Value Chip
    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton11ActionPerformed
    //20 Value Chip
    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton12ActionPerformed
    //100 Value Chip
    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton9MouseClicked
        // TODO add your handling code here:
     
    }//GEN-LAST:event_jButton9MouseClicked

    
    private void jButton9MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton9MousePressed
        // TODO add your handling code here:
        
        
    }//GEN-LAST:event_jButton9MousePressed
//5 Value Chip
    private void jButton9MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton9MouseReleased
        Graphics g = this.getGraphics();
        int x = evt.getXOnScreen(); //Chip X Coordinate
        int y = evt.getYOnScreen(); //Chip Y Coordinate
        int x2 = x + 5; //Inner Circle Coordinate
        int y2 = y + 5; //Inner Circle Coordinate
        int x3 = x + 18;//Precise Chip Location
        int y3 = y + 18;

        
        
     if ( Player.currency >5)
     {
        g.setColor(Color.CYAN);
        g.fillOval(x,y,40,40);
        g.setColor(Color.WHITE);
        g.fillOval(x2,y2,30,30);
     
        int userBet = 5;
        Player.currency = Player.currency - userBet;
        String displayCurrency = Integer.toString(Player.currency);
        jTextField1.setText(displayCurrency);
        
       
        int chip5Location[][] = {{x3},{y3}};
        compare5(chip5Location);
     }  
     else if( Player.currency < 5) 
             {
                 JOptionPane.showMessageDialog(null, "Insufficient Funds", " ", JOptionPane.OK_OPTION);
             }
        
    }//GEN-LAST:event_jButton9MouseReleased
public static void compare5(int a[][])
{
    for(int i = 0;i<a.length;i++)
        {
            for(int j = 0;j<a.length;j++)
                System.out.println(a[i][j]);
        }
    System.out.println();
}
    private void jButton9MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton9MouseDragged
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jButton9MouseDragged
//10 Value Chip
    private void jButton10MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton10MouseReleased
        // TODO add your handling code here:
        Graphics g = this.getGraphics();
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        int x2 = x + 5; //Inner Circle Coordinate
        int y2 = y + 5; //Inner Circle Coordinate
        int x3 = x + 18;
        int y3 = y + 18;
        
        
       if ( Player.currency > 10)
       {
        g.setColor(Color.BLUE);
        g.fillOval(x,y,40,40);
        g.setColor(Color.WHITE);
        g.fillOval(x2,y2,30,30);
        
        
        int userBet = 10;
        Player.currency = Player.currency - userBet;
        String displayCurrency = Integer.toString(Player.currency);
        jTextField1.setText(displayCurrency);
        
        int chip10Location[][] = {{x3},{y3}};
        compare10(chip10Location);
       }
       
       else if( Player.currency < 10) 
             {
                 JOptionPane.showMessageDialog(null, "Insufficient Funds", " ", JOptionPane.OK_OPTION);
             }
    }//GEN-LAST:event_jButton10MouseReleased
public static void compare10(int a[][])
{
    for(int i = 0;i<a.length;i++)
        {
            for(int j = 0;j<a.length;j++)
                System.out.println(a[i][j]);
        }
    System.out.println();
}
//20 value chip
    private void jButton12MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton12MouseReleased
        // TODO add your handling code here:
        Graphics g = this.getGraphics();
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        int x2 = x + 5; //Inner Circle Coordinate
        int y2 = y + 5; //Inner Circle Coordinate
        int x3 = x + 18;
        int y3 = y + 18;
        
        if (Player.currency > 20)
        {
        g.setColor(Color.RED);
        g.fillOval(x,y,40,40);
        g.setColor(Color.WHITE);
        g.fillOval(x2,y2,30,30);
        g.setColor(Color.WHITE); //Chip location 
        
        
        int userBet = 20;
        Player.currency = Player.currency - userBet;
        String displayCurrency = Integer.toString(Player.currency);
        jTextField1.setText(displayCurrency);
        
        int chip20Location[][] = {{x3},{y3}};
        compare20(chip20Location);
        }
        
        else if( Player.currency < 20) 
             {
                 JOptionPane.showMessageDialog(null, "Insufficient Funds", " ", JOptionPane.OK_OPTION);
             }
    }//GEN-LAST:event_jButton12MouseReleased
public static void compare20(int a[][])
{
    for(int i = 0;i<a.length;i++)
        {
            for(int j = 0;j<a.length;j++)
                System.out.println(a[i][j]);
        }
    System.out.println();
}

//50 Value Chip
    private void jButton11MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton11MouseReleased
        // TODO add your handling code here:
        Graphics g = this.getGraphics();
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        int x2 = x + 5; //Inner Circle Coordinate
        int y2 = y + 5; //Inner Circle Coordinate
        int x3 = x + 18;
        int y3 = y + 18;
        
        if (Player.currency > 50)
        {
        g.setColor(Color.ORANGE);
        g.fillOval(x,y,40,40);
        g.setColor(Color.WHITE);
        g.fillOval(x2,y2,30,30);
        
        int userBet = 50;
        Player.currency = Player.currency - userBet;
        String displayCurrency = Integer.toString(Player.currency);
        jTextField1.setText(displayCurrency);
        
        int chip50Location[][] = {{x3},{y3}};
        compare50(chip50Location);
    }//GEN-LAST:event_jButton11MouseReleased
 else if( Player.currency < 50) 
             {
                 JOptionPane.showMessageDialog(null, "Insufficient Funds", " ", JOptionPane.OK_OPTION);
             }
    }
    

    public static void compare50(int a[][])
      {
    for(int i = 0;i<a.length;i++)
        {
            for(int j = 0;j<a.length;j++)
                System.out.println(a[i][j]);
        }
    System.out.println();
       }
    private void jButton14MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton14MouseReleased
        // TODO add your handling code here:
        Graphics g = this.getGraphics();
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        int x2 = x + 5; //Inner Circle Coordinate
        int y2 = y + 5; //Inner Circle Coordinate
        int x3 = x + 18;
        int y3 = y + 18;
        
       if (Player.currency > 100)
       {
        g.setColor(Color.BLACK);
        g.fillOval(x,y,40,40);
        g.setColor(Color.WHITE);
        g.fillOval(x2,y2,30,30);
       
        int userBet = 100;
        Player.currency = Player.currency - userBet;
        String displayCurrency = Integer.toString(Player.currency);
        jTextField1.setText(displayCurrency);
        
        int chip100Location[][] = {{x3},{y3}};
        compare100(chip100Location);
       }
     
       else if( Player.currency < 100) 
             {
                 JOptionPane.showMessageDialog(null, "Insufficient Funds", " ", JOptionPane.OK_OPTION);
             } 
     
    }//GEN-LAST:event_jButton14MouseReleased
public static void compare100(int a[][])
      {
    for(int i = 0;i<a.length;i++)
        {
            for(int j = 0;j<a.length;j++)
                System.out.println(a[i][j]);
        }
    System.out.println();
       }


//Wheel Spin
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Random wheelSpin = new Random();
        int winNum = wheelSpin.nextInt(37); //Winning Number
        System.out.println(winNum);
        String winNum2 = Integer.toString(winNum); //String sent to text field
        jTextField2.setText(winNum2);
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(RoulletteWheel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RoulletteWheel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RoulletteWheel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RoulletteWheel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
       
         int Balance = 10000;
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RoulletteWheel().setVisible(true);
            }
        });
    }
public static void startIt(Player player, boolean x) 
{
         
         RoulletteWheel.main(null);
            
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton9;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JOptionPane jOptionPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables

    
}

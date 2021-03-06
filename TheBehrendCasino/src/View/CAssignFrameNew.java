/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.Player;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Natalie
 */
public class CAssignFrameNew extends JPanel{
    //The new assignment frame
    private static int currency;
    static boolean s = false;
    //static int clickCount = 0;
    static JFrame f;
    
    Player p = new Player();
    
    public static void main(String[] args){

        f = new JFrame();
        //f.setBackground(Color.white);
        f.setResizable(false);
        f.setSize(600,500);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        CAssignFrameNew spinWheel = new CAssignFrameNew();
        //spinWheel.setBackground(Color.white);
        spinWheel.setLocation(0, 0);
        spinWheel.setSize(f.getWidth(), f.getHeight() - 80);
        
        JPanel buttonBar = new JPanel();
        buttonBar.setBackground(new Color(255, 127, 39));
        buttonBar.setLocation(0, spinWheel.getHeight());
        buttonBar.setSize(f.getWidth(), 100);
        
        JButton jb = new JButton("SPIN!");
        jb.setLocation(buttonBar.getWidth()/2, buttonBar.getHeight()/2);
        
        buttonBar.add(jb);
        f.add(buttonBar);
        f.add(spinWheel);
        
        f.setSize(f.getWidth()+1, f.getHeight()+1);
   
        jb.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
                s = true;
                f.setSize(f.getWidth()-1, f.getHeight()-1);
                jb.setEnabled(false);
            } 
        });
    }
    
   int i = 0;
   Random r = new Random();
   
   int spinUntil = 300 + r.nextInt(600);
   boolean startSpin = false;
    public void paint(Graphics g){
        BufferedImage wheel = loadImg("GrayWheel.png");
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform atNorm = AffineTransform.getTranslateInstance(300 - wheel.getWidth()/2, 100);
        g2d.drawImage(wheel, atNorm, null);
        
        if(s == true){
        AffineTransform at = AffineTransform.getTranslateInstance(300 - wheel.getWidth()/2, 100);
        at.rotate(Math.toRadians(i++), wheel.getWidth()/2, wheel.getHeight()/2);

        g2d.drawImage(wheel, at, null);
        
        if(i<spinUntil)
            repaint();
        }
        if(i == spinUntil){
            setCurrency(i);
        }
    }

    public void setCurrency(int c){
        currency = c;
        
        Object[] options = {"Accept"};
        int n = JOptionPane.showOptionDialog(null,
        "You have " + currency + " chips to start",
        "Currency Assigned",
        JOptionPane.OK_OPTION,
        JOptionPane.INFORMATION_MESSAGE,
        null,
        options,
        options[0]);
            f.dispose();
            Player.setCurrency(currency);
            GameSelectionFrame.startIt(p);
            //GameSelectionFrame.main(s);
    }
    
    public static int getCurrency(){
        return currency;
    }
    
    public BufferedImage loadImg(String fileName){
        BufferedImage img = null;
        
        try {
            img = ImageIO.read(new File(fileName));
        } catch (IOException ex) {
            Logger.getLogger(CAssignFrameNew.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return img;
    }
    
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.howest.ti.breakout.swing;

import java.awt.EventQueue;
import javax.swing.JFrame;

/**
 *
 * @author micha
 */
public class ProjectBreakoutSwing extends JFrame{

    public ProjectBreakoutSwing() {
        initUI();
    }
    
     private void initUI() {
        
        add(new Board());
        setTitle("Breakout");
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 1000);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
          EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {                
                ProjectBreakoutSwing game = new ProjectBreakoutSwing();
                game.setVisible(true);                
            }
        });
    }
    
}

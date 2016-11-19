/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper;

import java.awt.Color;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;
/**
 *
 * @author Andy
 */
public class MineJFrame extends javax.swing.JFrame {

    /**
     * Creates new form MineJFrame
     */
    public MineJFrame() {
        //initComponents();
        initBoard();
    }
    
    class MineButton extends javax.swing.JButton
    {
        public MineButton(int r, int c)
        {
            row=r;
            col=c;
        }
        public int getRow() {
            return row;
        }
        public int getCol() {
            return col;
        }
        
          // Paint the round background and label.
        protected void paintComponent(Graphics g) {
          if (getModel().isArmed()) {
            // You might want to make the highlight color 
            // a property of the RoundButton class.
            g.setColor(Color.lightGray);
          } else {
            g.setColor(getBackground());
          }

          int x4Points[] = {0, getSize().width, getSize().width, 0};
          int y4Points[] = {0, 0, getSize().height, getSize().height};
          g.fillPolygon(x4Points, y4Points, x4Points.length); 

          // This call will paint the label and the 
          // focus rectangle.
          super.paintComponent(g);
        }
        private int row;
        private int col;
    };
    
    class ButtonAction implements java.awt.event.ActionListener
    {
        public ButtonAction(MineButton btn)
        {
            button=btn;
        }
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            ActionPerformed(button, evt);
        }

        private MineButton button;
    }
    
    private void initBoard()
    {
    
        board = new Board(10);
        board.setMines(10);
        jButtons = new MineButton[10][10];
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        java.awt.GridLayout grid=new java.awt.GridLayout(10, 10, 0,0);
        grid.setHgap(0);
        grid.setVgap(0);
        getContentPane().setLayout(grid);
        for (int i=0; i<10; i++) {
            for (int j=0; j<10; j++)   {             
                jButtons[i][j] = new MineButton(i+1, j+1);
                jButtons[i][j].setText("");
                jButtons[i][j].setBorder(javax.swing.BorderFactory.createLineBorder(Color.darkGray));
                jButtons[i][j].setContentAreaFilled(false);
                jButtons[i][j].setMargin(new java.awt.Insets(0, 0, 0, 0));
                
                jButtons[i][j].setBackground(Color.GRAY);
                getContentPane().add(jButtons[i][j]);
                
                jButtons[i][j].addActionListener(new ButtonAction(jButtons[i][j]));
            }
        }
        /*
        B11 = new javax.swing.JButton();      
        B11.setText("N");
        B11.setToolTipText("");
        B11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NewGameActionPerformed(evt);
            }
        });
        getContentPane().add(B11);
        */
        getContentPane().setPreferredSize(new Dimension(250,250));

        pack();
        
        JMenuBar menuBar1 = new JMenuBar();
        JMenu menu1 = new JMenu("File");
        menuBar1.add(menu1);
        JMenuItem menuItem = new JMenuItem("New Game", KeyEvent.VK_N);
        menuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NewGameActionPerformed(evt);
            }
        });
        menu1.add(menuItem);
        setJMenuBar(menuBar1);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuBar1 = new java.awt.MenuBar();
        menu1 = new java.awt.Menu();
        menu2 = new java.awt.Menu();
        B11 = new javax.swing.JButton();
        javax.swing.JButton B12 = new javax.swing.JButton();

        menu1.setLabel("File");
        menuBar1.add(menu1);

        menu2.setLabel("Edit");
        menuBar1.add(menu2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridLayout(0, 2));

        B11.setBackground(new java.awt.Color(204, 51, 0));
        B11.setText("11");
        B11.setToolTipText("");
        B11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        B11.setMargin(new java.awt.Insets(0, 0, 0, 0));
        B11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B11ActionPerformed(evt);
            }
        });
        getContentPane().add(B11);

        B12.setBackground(new java.awt.Color(0, 0, 153));
        B12.setText("12");
        B12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        B12.setContentAreaFilled(false);
        B12.setMargin(new java.awt.Insets(0, 0, 0, 0));
        B12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B12ActionPerformed(evt);
            }
        });
        getContentPane().add(B12);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void B11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_B11ActionPerformed

    private void B12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_B12ActionPerformed

    private void ActionPerformed(MineButton button, java.awt.event.ActionEvent evt) {                                    
        // TODO add your handling code here:
        if ( !board.open(button.getRow(), button.getCol() ))
            this.setTitle("You Lost");
        for (int i=0; i<10; i++) {
            for (int j=0; j<10; j++)   {
                if (board.isOpened(i, j)) {
                    if (board.getMines(i, j)<0)
                        jButtons[i][j].setText("!");
                    else if (board.getMines(i, j)>0)
                        jButtons[i][j].setText(""+board.getMines(i, j)); 
                    jButtons[i][j].setBackground(Color.lightGray);
                }
                else
                    jButtons[i][j].setBackground(Color.GRAY);
            }
        }
        if (board.won())
            this.setTitle("You Won");
    }
        
    private void NewGameActionPerformed(java.awt.event.ActionEvent evt) {                                    
        // TODO add your handling code here:
        board = new Board(10);
        board.setMines(10);
        for (int i=0; i<10; i++) {
            for (int j=0; j<10; j++)   {
                jButtons[i][j].setText(""); 
                jButtons[i][j].setBackground(Color.GRAY);
            }
        }
        this.setTitle("New Game");
    }
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
            java.util.logging.Logger.getLogger(MineJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MineJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MineJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MineJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MineJFrame().setVisible(true);
            }
        });
    }

    private MineButton[][] jButtons;
    private Board    board;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton B11;
    private java.awt.Menu menu1;
    private java.awt.Menu menu2;
    private java.awt.MenuBar menuBar1;
    // End of variables declaration//GEN-END:variables
}

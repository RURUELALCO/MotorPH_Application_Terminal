
package motorph.payroll_system;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;


public class Border extends javax.swing.JPanel {

   
    public Border() {
        initComponents();
        setOpaque(false);
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 240, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 456, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

     @Override
    protected void paintChildren(Graphics grphcs){
        Graphics2D g2=(Graphics2D)grphcs;
       g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
       GradientPaint g=new GradientPaint(0, 0, Color.decode("#ED213A"), 0, getHeight(), Color.decode("#93291E"));
       g2.setPaint(g);
       g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
       g2.fillRect(getWidth() - 20, 0, getWidth(), getHeight());
        super.paintChildren(grphcs);
    
    }
    private int x;
    private int y;
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
